/**
 *
 */
package models

import play.api.libs.json.Json
import reactivemongo.bson.BSONObjectID
import play.modules.reactivemongo.json.BSONFormats._

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
case class User (
    val _id: Option[BSONObjectID],
    val name: String,
    val email: String,
    val password: String,
    val grantType: String
)

object UserFormatter{
  implicit val userFormatter = Json.format[User]
}