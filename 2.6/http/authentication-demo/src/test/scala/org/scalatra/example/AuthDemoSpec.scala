package org.scalatra.example

import org.scalatra.test.specs2._

import java.util.Base64;

class AuthDemoSpec extends ScalatraSpec { def is = s2"""
  GET / on AuthDemo with no user & password should return status 401      $noUser
  GET / on AuthDemo with valid user & password should return status 200   $validUser
  GET / on AuthDemo with invalid user & password should return status 401 $invalidUser
"""

  addServlet(classOf[AuthDemo], "/*")

  def noUser = get("/") {
    status must_== 401
  }


  def validUser = {
    val validAuth = "Basic " + Base64.getEncoder.encodeToString("foo:bar")

    get(
      uri = "/",
      params = Seq.empty,
      headers = Seq[(String, String)](("Authorization", validAuth))
    ) {
      status must_== 200
      body must contain("You are authenticated.")
    }
  }


  def invalidUser = {
    val invalidAuth = "Basic " + Base64.getEncoder.encodeToString("baz:qux")

    get(
      uri = "/",
      params = Seq.empty,
      headers = Seq[(String, String)](("Authorization", invalidAuth))
    ) {
      status must_== 401
    }
  }
}
