package org.scalatra.example

import org.scalatra._

class ScalatraTwirlServlet extends ScalatraServlet {

  get("/") {
    org.scalatra.example.html.hello.render(new java.util.Date)
  }

}
