package org.scalatra.example

import org.scalatra.test.specs2._
import akka.actor.{Props, ActorSystem}

class MyActorAppSpec extends ScalatraSpec { def is = s2"""
  GET /ask on MyActorApp
    should return status 200 and body contains correct answer $ask
"""

  val system = ActorSystem()
  val myActor = system.actorOf(Props[MyActor])

  addServlet(new MyActorApp(system, myActor), "/*")

  def ask = get("/ask") {
    { status must_== 200 } and {
      body must contain("The answer is 42")
    }
  }
}
