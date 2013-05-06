package org.scalatra.example

import org.scalatra.test.specs2._
import com.basho.riak.client.RiakFactory

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class RiakControllerSpec extends ScalatraSpec { def is =
  "GET / on SimpleRiakController"                     ^
    "should return status 200"                  ! root200^
                                                end

  val riakClient = RiakFactory.pbcClient
  val myBucket = riakClient.createBucket("myBucket").execute
  addServlet(new SimpleRiakController(myBucket), "/*")

  def root200 = get("/") {
    status must_== 200
  }
}
