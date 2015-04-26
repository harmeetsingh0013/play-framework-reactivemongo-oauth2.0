package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import models.User
import models.UserFormatter._
import play.api._
import play.api.mvc._
import repository.UserRepositoryComponents
import repository.impl.MongoUserRepository
import service.impl.DefaultUserServiceComponent
import service.impl.DefaultUserServiceComponent
import reactivemongo.bson.BSONObjectID
import scalaoauth2.provider.OAuth2Provider
import security.SecurityDataHandler
import scalaoauth2.provider.AuthorizedActionFunction
import scalaoauth2.provider.OAuth2ProviderActionBuilders._
import play.api.libs.json.Json

object Application extends Controller{

  val userServiceComponent = new DefaultUserServiceComponent with MongoUserRepository{}
  val userService = userServiceComponent.userService;
  
  def index = AuthorizedAction(new SecurityDataHandler()).async { implicit request => 
      
      var future: Future[List[User]] = userService.findAll();
      future.map { list => Ok(Json.toJson(list)) }
  }
  
  def custom = AuthorizedAction(new SecurityDataHandler()).async { implicit request => 
    
    var user: User = User(Some(BSONObjectID.generate), "Billy Boy", "billy@user.com", "123456", "password");
    userService.save(user);
    
    var future: Future[List[User]] = userService.findAll();
    Future.successful(Ok("DOne"));
  }

}