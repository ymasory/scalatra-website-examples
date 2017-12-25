package org.scalatra.example

import org.scalatra.test.specs2._
import akka.actor.{ActorSystem}

class FutureControllerSpec extends ScalatraSpec { def is = s2"""
  GET / on FutureController
    should return status 200 $root
"""

  val system = ActorSystem()

  addServlet(new FutureController(system), "/*")

  def root = get("/") {
    status must_== 200
  }

}
