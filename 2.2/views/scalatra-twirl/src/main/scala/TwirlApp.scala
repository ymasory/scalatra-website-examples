package org.scalatra.example

import org.scalatra._
import scalate.ScalateSupport

class TwirlApp extends ScalatraServlet with ScalateSupport {

  get("/") {
    html.helloTwirl.render(new java.util.Date)
  }

  notFound {
    // remove content type in case it was set through an action
    contentType = null
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound()
  }
}
