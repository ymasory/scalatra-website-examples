package com.example

import org.scalatra._

class ScalatraTwirlServlet extends ScalatraServlet {

  get("/") {
    com.example.html.hello.render(new java.util.Date)
  }

}
