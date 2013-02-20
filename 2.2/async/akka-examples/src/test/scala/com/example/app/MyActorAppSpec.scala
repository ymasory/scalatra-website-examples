package com.example.app

import org.scalatra.test.specs2._
import akka.actor.{Props, ActorSystem}

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class MyActorAppSpec extends ScalatraSpec { def is =
  "GET /async on MyActorApp"                     ^
    "should return status 200"                  ! async200^
                                                end


  val system = ActorSystem()
  val myActor = system.actorOf(Props[MyActor])

  addServlet(new MyActorApp(system, myActor), "/*")

  def async200 = get("/async") {
    status must_== 200
  }
}
