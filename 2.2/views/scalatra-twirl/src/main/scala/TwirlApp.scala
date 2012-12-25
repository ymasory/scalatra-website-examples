package org.scalatra.example

import org.scalatra._

class TwirlApp extends ScalatraServlet {

  get("/") {
    html.helloTwirl.render(new java.util.Date)
  }
}
