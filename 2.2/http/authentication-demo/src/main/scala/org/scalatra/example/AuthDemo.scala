package org.scalatra.example

import org.scalatra._
import scalate.ScalateSupport

class AuthDemo extends ScalatraServlet with AuthenticationSupport {

  get("/?") {
    basicAuth
    val nodes = Seq(
      <h1>Hello from Scalatra</h1>,
      <p><a href="/auth/linked" >click</a></p>
    )


  }


}
