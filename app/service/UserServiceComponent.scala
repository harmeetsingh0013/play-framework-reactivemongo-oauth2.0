/**
 *
 */
package service

import scala.concurrent.impl.Future
import scala.collection.immutable.List
import models.User
import scala.concurrent.Future
/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
trait UserServiceComponent {

  def userService: UserService;
  def customUserService: CustomUserService;
  
  trait UserService{}
  trait CustomUserService{
    
  }
}