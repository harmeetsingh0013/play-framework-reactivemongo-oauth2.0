/**
 *
 */
package repository

import models.User
import scala.collection.immutable.List
import play.api.libs.json.JsObject
import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import reactivemongo.core.commands.LastError
import reactivemongo.core.commands.GetLastError

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
trait UserRepositoryComponents {

  def userLocater: UserLocator;
  
  trait UserLocator{
  }
}