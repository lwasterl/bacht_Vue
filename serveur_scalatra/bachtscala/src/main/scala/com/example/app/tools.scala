/*import scala.collection.mutable.Map
import java.util.Date
import java.sql.Timestamp
import scala.util.Random
class Tools {
  def createid():String = {
  val random = new Random()
  val date = new java.util.Date
  //println(random.nextInt().toString + date.toString)
  random.nextInt().toString + date.toString
}

case class User(var id:String, var role:String)

object UserList(var ul:List[User])

object Element(var Body:String,var id:String, var creatorId:String, var creationDate:String)

object Board(var id:String, var owner:String, var listRoles:List[String], var elements:Map[String,Element]){
  def add(element:Element){
    elements(element.id) = element
  }
  def get(id:String): Element ={
    val returned = elements(id)
    elements = elements.-(id)
    returned
  }
}

//Singleton
object BoardList(var corresp:Map[String,Board]){
  def add(board:Board){
    corresp(board.id) = board
  }
  def get(id:String): Board ={
    val returned = corresp(id)
    corresp = corresp.-(id)
    returned
  }
}
}

*/