package org.scalatra.example

import com.basho.riak.client.RiakFactory
import org.slf4j.LoggerFactory

trait RiakJavaClientInit {
  val logger = LoggerFactory.getLogger(getClass)

  // get access to a bucket using a binary connection and the riak-java-client
  val riakClient = RiakFactory.pbcClient

  def configureRiakJavaClient() {
    logger.info("Creating a Riak bucket")

    // make sure we've got a bucket to use
    riakClient.createBucket("myBucket").execute
  }


  def closeRiakJavaClient() {
    logger.info("Closing Riak client")

    riakClient.shutdown()
  }

}

trait RiakSupport {

  def myBucket = {
    RiakFactory.pbcClient.fetchBucket("myBucket").execute
  }

}