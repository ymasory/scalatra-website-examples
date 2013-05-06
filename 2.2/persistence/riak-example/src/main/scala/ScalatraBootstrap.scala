import org.scalatra.example._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle with RiakInit {

  override def init(context: ServletContext) {
    configureRiak()
    context.mount(new SimpleRiakController, "/*")
  }

  override def destroy(context: ServletContext) {
    closeRiakConnection()
  }
}
