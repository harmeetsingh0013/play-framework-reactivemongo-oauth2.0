/**
 *
 */
package service.impl

import service.UserServiceComponent
import service.UserServiceComponent
import repository.UserRepositoryComponents
import models.User
import models.UserFormatter._
import play.api.libs.json.Json
import scala.concurrent.ExecutionContext.Implicits.global
import reactivemongo.bson.BSONObjectID
import reactivemongo.extensions.json.dao.JsonDao
import play.modules.reactivemongo.json.BSONFormats._

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
trait DefaultUserServiceComponent extends UserServiceComponent{

  this: UserRepositoryComponents =>
    
  def userService = new DefaultUserService;
  def customUserService = new CustomUserServiceImpl; 
  
  class DefaultUserService extends JsonDao[User, BSONObjectID](MongoContext.db, "users") with UserService
  
  class CustomUserServiceImpl extends CustomUserService {
    
  }
}