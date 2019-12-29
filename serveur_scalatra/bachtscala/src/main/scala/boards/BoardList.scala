package boards
import boards._
import scala.collection.mutable.Map

class BoardList(var corresp:Map[String,Board]){
  def add(board:Board){
    corresp(board.id) = board
  }
  def get(id:String): Board ={
    val returned = corresp(id)
    corresp = corresp.-(id)
    returned
  }
}