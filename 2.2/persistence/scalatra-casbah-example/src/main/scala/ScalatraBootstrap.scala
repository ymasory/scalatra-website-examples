import com.mongodb.casbah.Imports._
import org.scalatra.example._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    // Connect to default - localhost, 27017
    val mongoClient =  MongoClient()
    val mongoColl = mongoClient("casbah_test")("test_data")

    context.mount(new MongoController(mongoColl), "/*")
  }
}
