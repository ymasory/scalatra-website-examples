package org.scalatra.example

import org.scalatra._

class JelasticApp extends ScalatraServlet {

  get("/") {
    <html>
      <body>
        <h1>This is
          <a href="http://scalatra.org/2.3/guides/deployment/jelastic.html">
            scalatra-jelastic
          </a>!
        </h1>
      </body>
    </html>
  }
}
