/**
 *
 */
package binders

import reactivemongo.bson.BSONObjectID

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
object StringToBSONObjectIdBinder {

  /* This is for Path Parameter*/
  
  implicit object pathBindableBSONObjectID extends play.api.mvc.PathBindable.Parsing[BSONObjectID](
    BSONObjectID(_), _.stringify, 
    (key: String, e: Exception) =>
      "Cannot parse parameter %s as BSONObjectID: %s".format(key, e.getMessage)
  )
  
  /* This is for query String*/
  
  implicit object queryStringBindableBSONObjectID extends play.api.mvc.QueryStringBindable.Parsing[BSONObjectID ](
     BSONObjectID(_), _.stringify, 
    (key: String, e: Exception) =>
      "Cannot parse parameter %s as BSONObjectID: %s".format(key, e.getMessage)
  )
}