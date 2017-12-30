package org.scalatra.example

import java.io.File

import org.scalatra._
import org.scalatra.test.specs2._

class FileUploadSupportSpec extends ScalatraSpec { def is = s2"""
  GET / on FileUploadExample
    should return status 200 $root
  POST / on FileUploadExample
    without file should return status 400 $noupload
    with file should return status 200 and body contains uploaded file $upload
"""

  addServlet(new FileUploadExample, "/*")

  def root = get("/") {
    status must_== 200
  }

  def noupload = post("/") {
    status must_== 400
  }

  def upload = post(
    "/",
    Map(),
    Map("file" -> new File("src/test/resources/org/scalatra/example/scalatra.txt"))
  ) {
    { status must_== 200 } and {
      body must contain("Tiny Scala high-performance")
    }
  }
}
