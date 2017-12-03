package com.constructiveproof.example

import org.scalatra.test.specs2._

import java.net.URI

class ScentryAuthSuccessSpec extends ScalatraSpec { def is = s2"""
Authenticate by valid account should show login message $showLoginMessage
"""

  addServlet(new ProtectedController, "/*")
  addServlet(new SessionsController, "/sessions/*")

  def showLoginMessage = session {

    val root = get("/") {
      val location = header("Location").split(';').head // Split to strip jsessionid

      { status must_== 302 } and { location  must endWith("/sessions/new") }
    }
       
    val login = get("/sessions/new") {
      status must_== 200
    }

    var location: URI = null
    val success = post(
      uri = "/sessions",
      params = Seq(("login", "foo"), ("password", "bar"), ("rememberMe", "true"))
    ) {

      location = new URI(header("Location"))

      {
        status must_== 302
      } and {
        header("Set-Cookie") must contain("rememberMe=foobar")
      } and {
        val l = new URI(header("Location"))
        l.getPath must_== ""
      }
    }

    val redirection = get(location.getPath) {
      { status must_== 200 } and { body must contain("This is a protected controller action") }
    }

    val logout = get("sessions/logout") {
      {
        status must_== 302
      } and {
         val l = new URI(header("Location"))
         l.getPath must_== ""
      }
    }

    root and login and success and redirection and logout
  }
}

class ScentryAuthFailureSpec extends ScalatraSpec { def is = s2"""
Authenticate by invalid account should redirect login screen $redirectLoginScreen
"""

  addServlet(new ProtectedController, "/*")
  addServlet(new SessionsController, "/sessions/*")

  def redirectLoginScreen = session {

    val login = get("/sessions/new") {
      status must_== 200
    }

    var location: URI = null
    val redirection = post(
      uri = "/sessions",
      params = Seq(("login", "bar"), ("password", "baz"), ("rememberMe", "true"))
    ) {
      { status must_== 302 } and { header("Location") must endWith("/sessions/new") }
    }

    login and redirection
  }
}
