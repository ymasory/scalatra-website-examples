package org.scalatra.example

import org.scalatra._
import org.slf4j.LoggerFactory

class ProtectedController extends FormsAuthDemoStack with AuthenticationSupport {

  val logger = LoggerFactory.getLogger(getClass)


  get("/") {
    logger.info("Hitting controller")
    requireLogin
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }
  
}
