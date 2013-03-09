package org.scalatra.example

import org.scalatra.example.data.DatabaseInit
import org.scalatra.test.scalatest._
import org.scalatest.{ FunSuite, BeforeAndAfter }

class ArticlesControllerTest extends ScalatraSuite with DatabaseInit with FunSuite with BeforeAndAfter {
  addServlet(classOf[ArticlesController], "/*")
  
  before {
    configureDb()
  }
  
  after {
    closeDbConnection()
  }
  
  test("simple get") {
    get("/") {
      status should equal (200)
    }
  }
}

