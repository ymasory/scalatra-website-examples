package com.constructiveproof.example.auth.strategies

import org.scalatra.{Cookie, CookieOptions, ScalatraBase}
import org.scalatra.auth.ScentryStrategy
import com.constructiveproof.example.models.User
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.slf4j.LoggerFactory

class RememberMeStrategy(protected val app: ScalatraBase)(implicit request: HttpServletRequest, response: HttpServletResponse)
  extends ScentryStrategy[User] {

  val logger = LoggerFactory.getLogger(getClass)


  val COOKIE_KEY = "rememberMe"
  private val oneWeek = 7 * 24 * 3600

  private def tokenVal = {
    app.cookies.get(COOKIE_KEY) match {
      case Some(token) => token
      case None => ""
    }
  }

  def isValid = {
    logger.info("RememberMeStrategy: determining isValid")
    tokenVal != ""
  }

  def authenticate()(implicit request: HttpServletRequest, response: HttpServletResponse) = {
    logger.info("RememberMeStrategy: attempting authentication")

//    User.validateRememberToken(token) match {
//      case None => {
//        None
//      }
//      case Some(usr) ⇒ {
//        logger.info("rememberMe: validated["+token+"]")
//        Some(usr)
//      }           //
//    }

    None
  }


  override def unauthenticated()(implicit request: HttpServletRequest, response: HttpServletResponse) {
    app.redirect("/sessions/new")
  }

  def afterAuthenticate(winningStrategy: Symbol, user: User) = {
    logger.info("rememberMe: afterAuth fired")
    if (winningStrategy == "RememberMe" ||
      (winningStrategy == "UserPassword" && checkbox2boolean(app.params.get("rememberMe").getOrElse("").toString))) {

      val token = "foopads" //user.rememberMe.value
      logger.info("rememberMe: set Cookie["+token+"]")
      app.response.addHeader("Set-Cookie",
        Cookie(COOKIE_KEY, token)(CookieOptions(secure = false, maxAge = oneWeek, httpOnly = true)).toCookieString)
    }
  }

  override def beforeLogout(user: User)(implicit request: HttpServletRequest, response: HttpServletResponse) = {
    logger.info("rememberMe: beforeLogout")
    if (user != null){
      user.forgetMe
    }
    app.cookies.get(COOKIE_KEY) foreach {
      _ => app.cookies.update(COOKIE_KEY, null)
    }
  }

  /**
   * Used to easily match a checkbox value
   */
  private def checkbox2boolean(s: String): Boolean = {
    s match {
      case "yes" => true
      case "y" => true
      case "1" => true
      case "true" => true
      case _ => false
    }
  }
}

