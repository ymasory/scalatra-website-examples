import org.scalatra.example.swagger._
import org.scalatra.LifeCycle
import javax.servlet.ServletContext

import org.slf4j.{Logger, LoggerFactory}

class ScalatraBootstrap extends LifeCycle {

  implicit val swagger = new FlowersSwagger

  val logger =  LoggerFactory.getLogger(getClass)

  override def init(context: ServletContext) {
    try {
      context.mount(new FlowersController, "/flowers/*")
      context.mount (new ResourcesApp, "/api-docs/*")
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
}
