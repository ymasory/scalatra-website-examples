package com.constructiveproof.example.auth.strategies

import org.scalatra.ScalatraBase
import org.scalatra.auth.ScentryStrategy
import com.constructiveproof.example.models.User
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.slf4j.LoggerFactory

class UserPasswordStrategy(protected val app: ScalatraBase)(implicit request: HttpServletRequest, response: HttpServletResponse)
  extends ScentryStrategy[User] {

  val logger = LoggerFactory.getLogger(getClass)


  private def login = app.params.getOrElse("login", "")
  private def password = app.params.getOrElse("password", "")

  override def name: String = "UserPassword"

  override def isValid(implicit request: HttpServletRequest) = {
    logger.info("UserPasswordStrategy: determining isValid: " + (login != "" && password != "").toString())
    login != "" && password != ""
  }

  def authenticate()(implicit request: HttpServletRequest, response: HttpServletResponse): Option[User] = {
    logger.info("UserPasswordStrategy: attempting authentication")

    // In real life, this is where we'd consult our data store, asking it whether the user credentials matched
    // any existing user. Here, we'll just check for a know login/password combination and return a user if
    // it's found.
    if(login == "foo" && password == "foo") {
      logger.info("UserPasswordStrategy: login succeeded")
      Some(User("foo"))
    } else {
      logger.info("UserPasswordStrategy: login failed")
      None
    }
  }

  override def unauthenticated()(implicit request: HttpServletRequest, response: HttpServletResponse) {
    app.redirect("/sessions/new")
  }

}

