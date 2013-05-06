package org.scalatra.example

import com.basho.riak.client.RiakFactory
import org.slf4j.LoggerFactory
import com.basho.riak.client.bucket.Bucket

trait RiakInit {
  val logger = LoggerFactory.getLogger(getClass)

  // get access to a bucket using a binary connection and the riak-java-client
  val riakClient = RiakFactory.pbcClient

  def configureRiak() {
    logger.info("Creating a Riak bucket")

    // make sure we've got a bucket to use
    riakClient.createBucket("myBucket").execute
  }


  def closeRiakConnection() {
    logger.info("Closing Riak client")

    riakClient.shutdown()
  }

}

trait RiakSupport {

  def myBucket: Bucket = {
    RiakFactory.pbcClient.fetchBucket("myBucket").execute
  }

}