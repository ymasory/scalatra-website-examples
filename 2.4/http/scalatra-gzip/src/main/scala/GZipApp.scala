package org.scalatra.example

import org.scalatra._

class GZipApp extends ScalatraServlet with GZipSupport {

  get("/") {
    <html>
      <body>
        <h1>This is
          <a href="http://scalatra.org/2.4/guides/http/gzip.html">
            http/gzip
          </a>!
        </h1>
      </body>
    </html>
  }
}
