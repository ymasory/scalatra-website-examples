package org.scalatra.example

import org.scalatra._
import org.scalatra.json.JacksonJsonSupport
import org.json4s._
import org.json4s.mongo.{JObjectParser, ObjectIdSerializer}

// MongoDb-specific imports
import com.mongodb.casbah.Imports._

class MongoController(mongoColl: MongoCollection)
  extends ScalatraCasbahExampleStack
  with JacksonJsonSupport /* this is optional and used by the json4s sample at the end */ {

  /**
   * Insert a new object into the database. You can use the following from your console to try it out:
   *   curl -i -H "Accept: application/json" -X POST -d "key=super&value=duper" http://localhost:8080/
   */
  post("/") {
    val key = params("key")
    val value = params("value")
    val newObj = MongoDBObject(key -> value)
    mongoColl += newObj
  }

  /**
   * Retrieve everything in the MongoDb collection we're currently using.
   */
  get("/") {
    mongoColl.find()
    for { x <- mongoColl} yield x
  }

  /**
   * Query for the first object which matches the values given. If you copy/pasted the insert example above,
   * try http://localhost:8080/query/super/duper in your browser.
   */
  get("/query/:key/:value") {
    val q = MongoDBObject(params("key") -> params("value"))
    for ( x <- mongoColl.findOne(q) ) yield x
  }

  // this is an approach to returning MongoDB results as JSON using json4s
  implicit val jsonFormats = DefaultFormats + new ObjectIdSerializer

  get("/asJson") {
    mongoColl.find
  }

  get("/asJson/:key/:value") {
    val q = MongoDBObject(params("key") -> params("value"))
    mongoColl.findOne(q) match {
      case Some(x) => x
      case None => halt(404)
    }
  }

  // convert MongoDB results to JValue
  override protected def renderPipeline = ({
    // handle DBObject
    case dbo: DBObject => JObjectParser.serialize(dbo)

    // handle MongoCursor
    case xs: TraversableOnce[_] => JArray(xs.toList.map { x => JObjectParser.serialize(x) })

    // handle Option[DBObject]
    case Some(dbo: DBObject) => JObjectParser.serialize(dbo)
    case None => JNothing
  }: RenderPipeline) orElse super.renderPipeline

  notFound {
    // remove content type in case it was set through an action
    contentType = null
  }

}