/**
 *
 */
package util

import java.util.Date

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
object Crypto {

  val accessTokenExpiresIn = 60 * 60; // 1 hour
  
  def generateToken(): String = {
    val key = java.util.UUID.randomUUID.toString()
    new sun.misc.BASE64Encoder().encode(key.getBytes())
  }
  
  def validateToken(milliseconds: Long): Long ={
    var date = new Date().getTime;
    var millis = date - milliseconds;
    var converted = DateUtility.millisecondsToSeconds(millis);
    if(accessTokenExpiresIn >= converted) accessTokenExpiresIn - converted else 0;
  }
}