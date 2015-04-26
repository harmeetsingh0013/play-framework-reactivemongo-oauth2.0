/**
 *
 */
package service

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
trait AccessTokenServiceComponent {

  def accessTokenService: AccessTokenService;
  
  trait AccessTokenService{}
}