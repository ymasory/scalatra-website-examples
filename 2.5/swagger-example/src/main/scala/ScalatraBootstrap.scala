import org.scalatra.example.swagger._
import org.scalatra.LifeCycle
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {

  implicit val swagger = new FlowersSwagger

  override def init(context: ServletContext) {
    context.initParameters("org.scalatra.cors.allowedOrigins") = "http://petstore.swagger.io"
    context.mount(new FlowersController, "/flowers", "flowers")
    context.mount (new ResourcesApp, "/api-docs")
  }
}
