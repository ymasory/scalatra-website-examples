package com.example.app

import _root_.akka.actor.ActorSystem
import _root_.akka.dispatch.{Future, ExecutionContext}
import _root_.akka.dispatch.{Promise => AkkaPromise}

import org.scalatra._
import akka.AkkaSupport

import dispatch._

object DispatchAkka {

  def retrievePage()(implicit ctx: ExecutionContext): Future[String] = {
    val prom = AkkaPromise[String]()
    dispatch.Http(url("http://http://asofterworld.com/") OK as.String) onComplete {
      case r => prom.complete(r)
    }
    prom.future
  }
}

class MyScalatraServlet extends ScalatraServlet with AkkaSupport {

  implicit val system = ActorSystem()
  protected implicit def executor: ExecutionContext = system.dispatcher

  get("/") {
    DispatchAkka.retrievePage()
  }

}
