package org.scalatra.example

import org.scalatra.test.specs2._

class HttpExampleSpec extends ScalatraSpec { def is = s2"""
On HttpExample
  GET / should return status 200 $root
  GET /date should return body contains specified date $date
  POST /form should return posted text $formSession
  POST /login should return login account $loginSession
  POST /flash-map/form should redirect and return specified message text $flashMapSession
"""

  addServlet(classOf[HttpExample], "/*")

  def root = get("/") {
    status must_== 200
  }

  def date = get("date/2017/12/31") {
    body must beMatching("""(?ms).+Year: 2017.+Month: 12.+Day: 31.+""")
  }

  def formSession = session {
    val postForm = get("/form") {
      status must_== 200
    }

    val posted = post(uri = "/post", params = Seq(("submission", "scalatra!"))) {
      body must contain("You posted: scalatra!")
    }

    postForm and posted
  }

  def loginSession = session {
    val loginForm = get("/login") {
      status must_== 200
    }

    val postForm = post(uri = "/login", params = Seq(("first", "Scala"), ("last", "tra"))) {
      body must contain("You have just logged in as: Scala tra")
    }

    val logout = get("/logout") {
      body must contain("You have logged out")
    }

    loginForm and postForm and logout
  }

  def flashMapSession = session {
    val form = get("/flash-map/form") {
      status must_== 200
    }

    val posted = post(uri = "/flash-map/form", params = Seq(("message", "scalatra!"))) {
      header("Location") must contain("/flash-map/result")
    }

    val redirected = get("/flash-map/result") {
      body must contain("Message = scalatra!")
    }

    form and posted and redirected
  }

}
