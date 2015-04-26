/**
 *
 */
package models

import reactivemongo.bson.BSONObjectID
import reactivemongo.bson.BSONDateTime
import reactivemongo.bson.Macros
import java.util.Date
import play.api.libs.json.Json
import play.modules.reactivemongo.json.BSONFormats._

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
case class AccessToken (
    
    val _id: Option[BSONObjectID],
    val accessToken: String, 
    val refreshToken: Option[String], 
    val userId: BSONObjectID, 
    val scope: Option[String], 
    val expiresIn: Int, 
    val createdAt: BSONDateTime, 
    val clientId: String
    
)

object AccessTokenFormatter{
  implicit val accessTokenFormatter = Json.format[AccessToken];
}