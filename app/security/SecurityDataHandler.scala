/**
 *
 */
package security

import java.util.Date

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import models.AccessToken
import models.User
import play.modules.reactivemongo.json.BSONFormats.BSONObjectIDFormat
import reactivemongo.bson.BSONDateTime
import reactivemongo.bson.BSONObjectID
import reactivemongo.extensions.json.dsl.JsonDsl.$doc
import reactivemongo.extensions.json.dsl.JsonDsl.ElementBuilderLike
import reactivemongo.extensions.json.dsl.JsonDsl.toElement
import reactivemongo.extensions.json.dsl.JsonDsl.toJsObject
import repository.impl.MongoUserRepository
import scalaoauth2.provider.AuthInfo
import scalaoauth2.provider.ClientCredential
import scalaoauth2.provider.DataHandler
import service.impl.AccessTokenServiceComponentImpl
import service.impl.DefaultUserServiceComponent
import util.Crypto

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
class SecurityDataHandler extends DataHandler[User]{

  val userServiceComponent = new DefaultUserServiceComponent with MongoUserRepository{}
  val userService = userServiceComponent.userService;
  
  val accessTokenServiceComponenet = new AccessTokenServiceComponentImpl {}
  val accessTokenService = accessTokenServiceComponenet.accessTokenService
  
  def validateClient(clientCredential: ClientCredential, grantType: String) = {
    val future = userService.findRandom($doc("email" $eq clientCredential.clientId, "password" $eq clientCredential.clientSecret, "grantType" $eq grantType));
    future.map { option => {
     if (!option.isEmpty ) true else false;
    }}
  }

  def findUser(username: String, password: String) = {
    userService.findRandom($doc("email" $eq username, "password" $eq password));
  }

  def createAccessToken(authInfo: AuthInfo[User]): Future[scalaoauth2.provider.AccessToken] = {
    val now = new Date();
    val refreshToken = Some(Crypto.generateToken());
    val accessToken = Crypto.generateToken();

    var document = AccessToken(Some(BSONObjectID.generate), accessToken, refreshToken, authInfo.user._id.get, 
                      Some(""), Crypto.accessTokenExpiresIn, new BSONDateTime(now.getTime), authInfo.user.email);
    for{
      _ <- accessTokenService.remove($doc("clientId" $eq authInfo.user.email, "userId" $eq authInfo.user._id.get))
      _ <- accessTokenService.insert(document)
      accessToken <- Future.successful(scalaoauth2.provider.AccessToken(accessToken, refreshToken, authInfo.scope, Some(Crypto.accessTokenExpiresIn.toLong), now))
    }yield accessToken
  }

  def getStoredAccessToken(authInfo: AuthInfo[User]) = {
    var future = accessTokenService.findOne($doc("clientId" $eq authInfo.user.email, "userId" $eq authInfo.user._id.get)); //user findOne instead of findRandom in JsonDao
    future.map { option => {
      if (!option.isEmpty){
       var accessToken = option.get;
       var value = Crypto.validateToken(accessToken.createdAt.value)
       Some(scalaoauth2.provider.AccessToken(accessToken.accessToken, accessToken.refreshToken, authInfo.scope, 
           Some(value), new Date(accessToken.createdAt.value)))
      }else{
        Option.empty
      }
    }}
  }

  def refreshAccessToken(authInfo: AuthInfo[User], refreshToken: String) = {
    createAccessToken(authInfo);
  }

  def findAuthInfoByCode(code: String): Future[Option[AuthInfo[User]]] = {
    Future.successful(None)
  }

  def findAuthInfoByRefreshToken(refreshToken: String) = {
    accessTokenService.findOne("refreshToken" $eq refreshToken).map { option => {
     var accessToken = option.get;
     var user = userService.findById(accessToken._id.get).value;
     Some(AuthInfo(user.get.get.get, Some(accessToken.clientId), accessToken.scope, Some("")))
    }}
  }

  def findClientUser(clientCredential: ClientCredential, scope: Option[String]): Future[Option[User]] = {
    Future.successful(None);
  }

  def findAccessToken(token: String) = {
    accessTokenService.findOne($doc("accessToken" $eq token)).map { option => {
     if(!option.isEmpty){
       var accessToken = option.get;
       Some(scalaoauth2.provider.AccessToken(accessToken.accessToken, accessToken.refreshToken, accessToken.scope, Some(accessToken.expiresIn.toLong), new Date(accessToken.createdAt.value))) 
     }else{
       Option.empty       
     }
    }}
  }

  def findAuthInfoByAccessToken(accessToken: scalaoauth2.provider.AccessToken) = {
    accessTokenService.findOne($doc("accessToken" $eq accessToken.token)).flatMap { option => {
      if(!option.isEmpty && option.get.expiresIn.toLong >= Crypto.validateToken(option.get.createdAt.value)){
       var accessToken = option.get;
       userService.findById(accessToken.userId).map { option => {
        if(!option.isEmpty){
          var innerUser = option.get
          Some(AuthInfo(innerUser, Some(accessToken.clientId), accessToken.scope, Some("")))
        }else{
          Option.empty;
        } 
       }}
     }else{
       Future.successful(Option.empty);
     }
    }}
  }

}