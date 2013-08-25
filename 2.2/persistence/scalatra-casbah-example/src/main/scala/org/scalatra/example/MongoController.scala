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
  with JacksonJsonSupport /* this is optional and used by the scalatra-json/json4s sample at the end */ {

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

  // in the following there are two approaches to returning MongoDB results as JSON string

  // hook simple mongo support into render pipeline
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

  // this is required by scalatra-json and json4s
  implicit val jsonFormats = DefaultFormats + new ObjectIdSerializer

  // converts DBObject and MongoCursor to json4s JValue
  // JValue is handled by scalatra-json
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