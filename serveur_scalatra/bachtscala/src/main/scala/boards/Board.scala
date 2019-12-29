package boards
import boards._
import scala.collection.mutable.Map

class Board(var id:String, var owner:String, var listRoles:List[String], var elements:Map[String,Element]){
  def add(element:Element){
    elements(element.id) = element
  }
  def get(id:String): Element ={
    val returned = elements(id)
    elements = elements.-(id)
    returned
  }
}