package org.scalatra.example

import org.scalatra._
import org.json4s.mongo.{JObjectParser, ObjectIdSerializer}
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

  get("/asJsonString") {
    mongoColl.find
  }

  get("/asJsonString/:key/:value") {
    val q = MongoDBObject(params("key") -> params("value"))
    mongoColl.findOne(q) match {
      case Some(x) => x
      case None => halt(404)
    }
  }

  // this is an approach to returning MongoDB results as JSON using json4s
  implicit val jsonFormats = DefaultFormats + new ObjectIdSerializer

  get("/asJson4s") {
    mongoColl.find
  }

  get("/asJson4s/:key/:value") {
    val q = MongoDBObject(params("key") -> params("value"))
    mongoColl.findOne(q) match {
      case Some(x) => x
      case None => halt(404)
    }
  }

  // hook mongo support into render pipeline
  override protected def renderPipeline = renderMongo orElse super.renderPipeline

  // renders DBObject and MongoCursor as String making use of standard toString() methods
  def renderMongo = {
    case dbo: DBObject =>
      contentType = formats("json")
      dbo.toString

    case xs: TraversableOnce[_] => // handles a MongoCursor
      contentType = formats("json")
      val l = xs map (x => x.toString) mkString(",")
      "[" + l + "]"

  }: RenderPipeline

  def renderMongoToJson4s = {
    // handle DBObject
    case dbo: DBObject => JObjectParser.serialize(dbo)

    // handle MongoCursor
    case xs: TraversableOnce[_] => JArray(xs.toList.map { x => JObjectParser.serialize(x) })

    // handle Option[DBObject]
    case Some(dbo: DBObject) => JObjectParser.serialize(dbo)
    case None => JNothing
  }: RenderPipeline



}