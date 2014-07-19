import org.scalatra.example.swagger._
import org.scalatra.LifeCycle
import javax.servlet.ServletContext

import org.scalatra.swagger.{Swagger, ApiInfo}

class ScalatraBootstrap extends LifeCycle {

  implicit val apiInfo = new ApiInfo(
    "The Flowershop API",
    "Docs for the Flowers API",
    "http://scalatra.org",
    "apiteam@scalatra.org",
    "MIT",
    "http://opensource.org/licenses/MIT")

  implicit val swagger = new Swagger(Swagger.SpecVersion, "1.0.0", apiInfo)

  override def init(context: ServletContext) {
    context.mount (new FlowersSwagger, "/api-docs")
    context.mount(new FlowersController, "/flowers", "flowers")
  }
}
