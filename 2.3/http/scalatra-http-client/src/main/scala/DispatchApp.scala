package org.scalatra.example

import org.scalatra._

class DispatchApp extends ScalatraServlet {

  get("/") {
    <html>
      <body>
        <h1>This is
          <a href="http://scalatra.org/2.3/guides/http/http-client.html">http/http-client</a>!
        </h1>
      </body>
    </html>
  }
}
