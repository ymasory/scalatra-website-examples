package org.scalatra.example.swagger

// The basic Scalatra framework code
import org.scalatra._

// JSON handling support from Scalatra
import org.scalatra.json._

// Scalatra's built-in Swagger integration
import org.scalatra.swagger._

// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}

// Our models
import org.scalatra.example.swagger.models._

// Data
import org.scalatra.example.swagger.data._

class FlowersController(implicit val swagger: Swagger) extends ScalatraServlet
  with JacksonJsonSupport with JValueResult with SwaggerSupport with CorsSupport {

  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit val jsonFormats: Formats = DefaultFormats

  // The name of our application. This will show up in the Swagger docs.
  override protected val applicationName = Some("flowers")

  // A description of our application. This will show up in the Swagger docs.
  protected val applicationDescription = "The flowershop API. It exposes operations for browing and searching lists of flowers"


  // Before every action runs, set the content type to be in JSON format.
  before() {
    contentType = formats("json")
  }

  models = Map(classOf[Flower])

  /*
   * Retrieve a list of flowers. It's possible to search by name by adding
   * a name=foo query string parameter.
   */
  get("/",operation(getFlowers)){
    params.get("name") match {
      case Some(name) => FlowerData.all filter (_.name.toLowerCase contains name.toLowerCase)
      case None => FlowerData.all
    }
  }

  /*
   * Retrieve a single book based on its slug.
   */
  get("/:slug", operation(findBySlug)) {
    FlowerData.all find (_.slug == params("slug")) match {
      case Some(b) => b
      case None => halt(404)
    }
  }

  val getFlowers =
    (apiOperation[List[Flower]]("getFlowers")
      summary "Show all flowers"
      notes "Shows all the flowers in the flower shop. You can search it too."
      parameter queryParam[Option[String]]("name").description("A name to search for"))

  val findBySlug =
    (apiOperation[Flower]("findBySlug")
      summary "Find by slug"
      parameters (
      pathParam[String]("slug").description("Slug of flower that needs to be fetched")))

}
