package org.scalatra.example

import com.basho.riak.client.RiakFactory
import org.slf4j.LoggerFactory

/**
 * A trait we mix into Scalatra's initalization lifecycle to ensure we've got
 * a Riak client and a bucket set up after the application starts.
 */
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

/**
 * A trait we can use to get a handle on the Riak bucket we created at
 * application start.
 *
 * The big questions here: does this need to be clustered? What are the implications
 * for thread-safety - do we need to be prefixing operations with a clientId?
 */
trait RiakSupport {

  def myBucket = {
    RiakFactory.pbcClient.fetchBucket("myBucket").execute
  }

}