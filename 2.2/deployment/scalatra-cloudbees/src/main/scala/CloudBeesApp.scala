package org.scalatra.example

import org.scalatra._
import scalate.ScalateSupport

class CloudBeesApp extends ScalatraServlet with ScalateSupport {

  get("/") {
    <html>
      <body>
        <h1>This is scalatra-cloudbees!</h1>
      </body>
    </html>
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
