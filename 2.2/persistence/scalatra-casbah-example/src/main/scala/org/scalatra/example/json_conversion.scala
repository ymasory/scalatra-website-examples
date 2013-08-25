package org.scalatra.example

import org.scalatra._
import com.mongodb.casbah.Imports._
import org.scalatra.json.JacksonJsonSupport
import org.json4s._
import org.json4s.mongo.{JObjectParser, ObjectIdSerializer}

/**
 * this is a simple approach for converting MongoDB results to JSON strings
 */
trait SimpleMongoDbJsonConversion extends ScalatraBase with ApiFormats {

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

  // hook into render pipeline
  override protected def renderPipeline = renderMongo orElse super.renderPipeline

}

/**
 * this is alternative approach using json4s
 */
trait Json4sMongoDbJsonConversion extends JacksonJsonSupport {

  // required by scalatra-json and json4s
  implicit val jsonFormats = DefaultFormats + new ObjectIdSerializer

  // converts DBObject and MongoCursor to json4s JValue
  // JValue is handled by scalatra-json
  def transformMongoObjectsToJson4s = {
    // handle DBObject
    case dbo: DBObject => JObjectParser.serialize(dbo)

    // handle MongoCursor
    case xs: TraversableOnce[_] => JArray(xs.toList.map { x => JObjectParser.serialize(x) })

    // handle Option[DBObject]
    case Some(dbo: DBObject) => JObjectParser.serialize(dbo)
    case None => JNothing
  }: RenderPipeline

  // hook into render pipeline
  override protected def renderPipeline = transformMongoObjectsToJson4s orElse super.renderPipeline

}