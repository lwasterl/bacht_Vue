import com.example.app._
import org.scalatra._
import boards._
import javax.servlet.ServletContext
import scala.collection.mutable.Map
import java.util.Date
import java.sql.Timestamp
import scala.util.Random

class ScalatraBootstrap extends LifeCycle {


  override def init(context: ServletContext) {
    context.mount(new MyScalatraServlet(new UserList(Map()),new BoardList(Map())), "/*")
  }
}