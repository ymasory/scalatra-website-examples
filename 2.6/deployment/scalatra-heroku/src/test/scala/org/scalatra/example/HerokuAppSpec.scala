package org.scalatra.example

import org.scalatra.test.specs2._

class HerokuAppSpec extends ScalatraSpec { def is = s2"""
  GET / on HerokuApp
    should return status 200 $root
"""

  addServlet(classOf[HerokuApp], "/*")

  def root = get("/") {
    status must_== 200
  }
}
