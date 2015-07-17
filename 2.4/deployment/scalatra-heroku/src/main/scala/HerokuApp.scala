package org.scalatra.example

import org.scalatra._

class HerokuApp extends ScalatraServlet {

  get("/") {
    <html>
      <body>
        <h1>This is
          <a href="http://scalatra.org/2.4/guides/deployment/heroku.html">
            scalatra-heroku
          </a>!
        </h1>
      </body>
    </html>
  }
}
