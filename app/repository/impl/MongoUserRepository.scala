/**
 *
 */
package repository.impl

import repository.UserRepositoryComponents
import reactivemongo.extensions.json.dao.JsonDao
import reactivemongo.bson.BSONObjectID
import scala.concurrent.ExecutionContext.Implicits.global
import models.User
import models.UserFormatter._
import play.api.libs.json.JsObject
import scala.collection.immutable.List
import scala.concurrent.Future
import play.api.libs.json.Json

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
trait MongoUserRepository extends UserRepositoryComponents{

  def userLocater = new UserLocatorMongo;
  
  class UserLocatorMongo extends UserLocator{
  }
}