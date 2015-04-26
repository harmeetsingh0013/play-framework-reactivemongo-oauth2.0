/**
 *
 */
package service.impl

import models.AccessToken
import models.AccessTokenFormatter._
import reactivemongo.bson.BSONObjectID
import service.AccessTokenServiceComponent
import scala.concurrent.ExecutionContext.Implicits.global
import reactivemongo.extensions.json.dao.JsonDao
import play.modules.reactivemongo.json.BSONFormats._

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
trait AccessTokenServiceComponentImpl extends AccessTokenServiceComponent{

  def accessTokenService = new DefaultAccessTokenServiceComponent;
  
  class DefaultAccessTokenServiceComponent extends JsonDao[AccessToken, BSONObjectID](MongoContext.db, "accessToken") with AccessTokenService
  
  //class CustomAccessTokenServiceComponent 
}
  