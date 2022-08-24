package org.scalatra.example

import org.scalatra.test.specs2._
import org.specs2.execute._

import scala.util.matching._

class CookiesExampleSpec extends ScalatraSpec { def is = s2"""
  GET / on CookiesExample twice, the value of the second counter
    should be larger than the first one $counter
"""

  addServlet(classOf[CookiesExample], "/*")

  val re = """(?ms).*you have been on this page (\d+) times.*""".r
  def getCount(msg: String): Int = {
    msg match {
      case re(num) => num.toInt
      case _       => throw new Exception(s"Can't find counter message!: $msg")
    }
  }

  def counter = session {
    val first = get("/") {
      getCount(body)
    }

    val second = get("/") {
      getCount(body)
    }

    second must be_>(first)
  }
}
