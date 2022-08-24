package org.scalatra.example

import org.scalatra._

class CoffeeScriptApp extends ScalatraServlet {

  get("/") {
    <html>
      <body>
        <h1>This is
          <a href="http://scalatra.org/2.3/guides/resources/coffeescript.html">resources/coffeescript</a>!
        </h1>

        <script type="text/javascript" src="compiled/scripts.js"></script>
      </body>
    </html>
  }
}
