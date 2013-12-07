package org.scalatra.example

import org.scalatra.auth.ScentryStrategy

import org.scalatra.{ScalatraBase}
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.slf4j.LoggerFactory


/**
 * Authentication strategy to authenticate a user from a username (or email) and password combination.
 */
class UserPasswordStrategy(protected val app: ScalatraBase)(implicit request: HttpServletRequest, response: HttpServletResponse)
  extends ScentryStrategy[User] {

  override def name: String = "UserPassword"
  val logger = LoggerFactory.getLogger(getClass)


  private def login = app.params.getOrElse("login", "")
  private def password = app.params.getOrElse("password", "")

  /***
    * Determine whether the strategy should be run for the current request.
    */
  override def isValid(implicit request: HttpServletRequest) = {
    logger.info("valid???: " + (login != "" && password != "").toString())

    login != "" && password != ""
  }

  /**
   * Authenticates a user by validating the username (or email) and password request params.
   */
  def authenticate()(implicit request: HttpServletRequest, response: HttpServletResponse): Option[User] = {
    logger.info("Attempting authentication with UserPasswordStrategy")
    User.login(login, password) match {
      case None => {
        logger.info("None on login")
        None
      }
      case Some(usr) => {
        logger.info("Found user on login")

        Some(usr)
      }
    }
  }

  /**
   * What should happen if the user is currently not authenticated?
   */
  override def unauthenticated()(implicit request: HttpServletRequest, response: HttpServletResponse) {
    app.redirect("/sessions/new")
  }

  protected def getUserId(user: User): String = user.userIdAsString
}