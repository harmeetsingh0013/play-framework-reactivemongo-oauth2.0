/**
 *
 */
package util

import reactivemongo.bson.BSONObjectID

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
object CommonUtillity {

  def converterStringToBsonObjectID(id: String): BSONObjectID = {
    BSONObjectID.apply(id);
  }
}