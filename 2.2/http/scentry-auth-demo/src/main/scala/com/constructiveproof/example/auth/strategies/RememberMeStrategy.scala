package com.constructiveproof.example.auth.strategies

import org.scalatra.{Cookie, CookieOptions, ScalatraBase}
import org.scalatra.auth.ScentryStrategy
import com.constructiveproof.example.models.User
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.slf4j.LoggerFactory

class RememberMeStrategy(protected val app: ScalatraBase)(implicit request: HttpServletRequest, response: HttpServletResponse)
  extends ScentryStrategy[User] {

  val logger = LoggerFactory.getLogger(getClass)

  override def name: String = "RememberMe"


  val COOKIE_KEY = "rememberMe"
  private val oneWeek = 7 * 24 * 3600

  private def tokenVal = {
    app.cookies.get(COOKIE_KEY) match {
      case Some(token) => token
      case None => ""
    }
  }

  override def isValid(implicit request: HttpServletRequest):Boolean = {
    logger.info("RememberMeStrategy: determining isValid: " + (tokenVal != "").toString())
    tokenVal != ""
  }

  /***
    * In a real application, we'd check the cookie's token value against a known hash, probably saved in a
    * datastore, to see if we should accept the cookie's token. Here, we'll just see if it's the one we set
    * earlier ("foobar") and accept it if so.
    */
  def authenticate()(implicit request: HttpServletRequest, response: HttpServletResponse) = {
    logger.info("RememberMeStrategy: attempting authentication")
    if(tokenVal == "foobar") Some(User("foo"))
    else None
  }


  override def unauthenticated()(implicit request: HttpServletRequest, response: HttpServletResponse) {
    app.redirect("/sessions/new")
  }

  /***
    * After successfully authenticating with either the RememberMeStrategy, or the UserPasswordStrategy with the
    * "remember me" tickbox checked, we set a rememberMe cookie for later use.
    */
  override def afterAuthenticate(winningStrategy: String, user: User)(implicit request: HttpServletRequest, response: HttpServletResponse) = {
    logger.info("rememberMe: afterAuth fired")
    logger.info("winningStrategy is: " + winningStrategy)
    logger.info("rememberMe param is: " + app.params.get("rememberMe"))
    if (winningStrategy == "RememberMe" ||
      (winningStrategy == "UserPassword" && checkbox2boolean(app.params.get("rememberMe").getOrElse("").toString))) {

      val token = "foobar"
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

