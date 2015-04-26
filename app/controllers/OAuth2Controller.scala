/**
 *
 */
package controllers

import play.mvc.Controller
import scalaoauth2.provider.OAuth2Provider
import security.SecurityDataHandler
import play.api.mvc.Action
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
object OAuth2Controller extends Controller with OAuth2Provider{

  def accessToken = Action.async { implicit request =>
    issueAccessToken(new SecurityDataHandler())
  }
}