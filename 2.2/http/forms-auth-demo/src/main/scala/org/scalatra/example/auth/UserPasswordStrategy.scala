package org.scalatra.example

import org.scalatra.auth.ScentryStrategy

import org.scalatra.{ScalatraBase}

/**
 * Authentication strategy to authenticate a user from a username (or email) and password combination.
 */
class UserPasswordStrategy(protected val app: ScalatraBase)
  extends ScentryStrategy[User] {
  
  private def login = app.params.get("userName")
  private def password = app.params.get("password")

  private def remoteAddress = {
    val proxied = app.request.getHeader("X-FORWARDED-FOR")
    val res = if (proxied != "" ) proxied else app.request.getRemoteAddr

    res
  }

  override def isValid = {
    login.isDefined && password.isDefined
  }

  /**
   * Authenticates a user by validating the username (or email) and password request params.
   */
  def authenticate: Option[User] = {
    User.login(login.get, password.get) match {
      case None => {
        None
      }
      case Some(usr) => {
        Some(usr)
      }
    }
  }

  protected def getUserId(user: User): String = user.userIdAsString
}