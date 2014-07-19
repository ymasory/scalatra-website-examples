package org.scalatra.example.swagger

import org.json4s.DefaultFormats
import org.scalatra._
import org.scalatra.json._
import org.scalatra.swagger._

class FlowersController()(implicit val swagger: Swagger) extends ScalatraServlet with JacksonJsonSupport with SwaggerSupport  {

  override protected implicit val jsonFormats = DefaultFormats

  // A description of our application. This will show up in the Swagger docs.
  protected val applicationDescription = "The flowershop API. It exposes operations for browsing and searching lists of flowers"

  // Before every action runs, set the content type to be in JSON format.
  before() {
    contentType = formats("json")
  }

  val getFlowers =
    (apiOperation[List[Flower]]("getFlowers")
      summary "Retrieve a list of flowers"
      notes "Shows all the flowers in the flower shop. You can search it too."
      parameter queryParam[Option[String]]("name").description("A name to search for"))

  get("/", operation(getFlowers)){
    params.get("name") match {
      case Some(name) => FlowerData.all filter (_.name.toLowerCase contains name.toLowerCase())
      case None => FlowerData.all
    }
  }


  val findBySlug =
    (apiOperation[Flower]("findBySlug")
      summary "Find a flower using its slug."
      parameters (
      pathParam[String]("slug").description("Slug of flower that needs to be fetched")))

  get("/:slug", operation(findBySlug)) {
    FlowerData.all find (_.slug == params("slug")) match {
      case Some(b) => b
      case None => halt(404)
    }
  }
}


// A Flower object to use as a faked-out data model
case class Flower(slug: String, name: String)

// An amazing datastore!
object FlowerData {

  /**
   * Some fake flowers data so we can simulate retrievals.
   */
  var all = List(
    Flower("yellow-tulip", "Yellow Tulip"),
    Flower("red-rose", "Red Rose"),
    Flower("black-rose", "Black Rose"))
}