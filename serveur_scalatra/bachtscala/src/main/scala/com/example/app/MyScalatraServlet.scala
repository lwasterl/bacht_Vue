package com.example.app

import org.scalatra._
//librairie générale json
import org.json4s.{DefaultFormats, Formats}
//librairie spécifique à scalatra
import org.scalatra.json._
import scala.collection.mutable.Map
import java.util.Date
import java.sql.Timestamp
import scala.util.Random
import boards._
case class FailureMessage(message:String)
case class BoardReq(titleBoard:String,userId:String,listRoles:List[String])
case class ElementReq(titleBoard:String,userId:String,elementId:String)
case class IdReturned(id:String)
case class BoardReturned(id:String,owner:String,elements:Map[String,Element],listRoles:List[String]) 
case class ElementReturned(body:String,id:String,creatorId:String,creationDate:String)
case class UserReq(name:String,role:String)
case class UserIdReq(name:String)

class MyScalatraServlet(ul:UserList,bl:BoardList) extends ScalatraServlet with JacksonJsonSupport{




def createid():String = {
  val random = new Random()
  val date = new java.util.Date
  //println(random.nextInt().toString + date.toString)
  random.nextInt().toString + date.toString
}
def ask_board(boardId:String):Boolean = {
	bl.corresp.contains(boardId)
}
def ask_elt(boardId:String,eltId:String): Boolean = {
	bl.corresp(boardId).elements.contains(eltId)
}


// Sets up automatic case class to JSON output serialization, required by
// the JValueResult trait.
protected implicit lazy val jsonFormats: Formats = DefaultFormats

//protected implicit lazy val jsonFormats: Formats = DefaultFormats.withBigDecimal

//set all content to json format
before() {
	contentType = formats("json")
}
  get("/") {
    views.html.hello()
  }
  post("/tell/board"){
  	val req = parsedBody.extract[BoardReq]
  	bl.add(new Board(req.titleBoard + req.userId,req.userId,req.listRoles,Map()))
  	IdReturned(req.titleBoard + req.userId)
  }
  post("/tell/element"){
  	val req = parsedBody.extract[ElementReq]
  	val date = new java.util.Date
  	if(ask_board(req.titleBoard+req.userId)){
  		if (ul.ul.contains(req.userId)){
	  		if (bl.corresp(req.titleBoard+req.userId).listRoles.contains(ul.ul(req.userId).role)){
	  			bl.corresp(req.titleBoard + req.userId).add(new Element("",req.elementId,req.userId,date.toString))
  				IdReturned(req.elementId)	
  			}
  			else{
  				FailureMessage("User has not the permission to do that")
  				}
  		}
  		else{
  			FailureMessage("No Such User")
  		}


  	}
  	else{
  		FailureMessage("No such board left")
  	}

  }
	post("/tell/user"){
  	val req = parsedBody.extract[UserReq]
  	ul.ul(req.name) = new User(req.name,req.role)
  	req

  }
  post("/get/board"){
  	val req = parsedBody.extract[BoardReq]
  	if(ask_board(req.titleBoard+req.userId)){
  		if(ul.ul.contains(req.userId)){
  			if(bl.corresp(req.titleBoard+req.userId).listRoles.contains(ul.ul(req.userId).role)){
			  	val brd = bl.get(req.titleBoard + req.userId)
	  			BoardReturned(brd.id,brd.owner,brd.elements,brd.listRoles)
  			}
  			else{
  				FailureMessage("User has not the permission to do that")
  			}

  		}

  		else{
  			FailureMessage("No Such User")
  		}
  	}
  	else{
  		FailureMessage("No such board left")
  	}

  }
  post("/get/element"){
  	val req = parsedBody.extract[ElementReq]
  	if(ask_board(req.titleBoard + req.userId)){
  		if(ask_elt(req.titleBoard + req.userId,req.elementId)){
  			if(ul.ul.contains(req.userId)){
  				if(bl.corresp(req.titleBoard+req.userId).listRoles.contains(ul.ul(req.userId).role)){
 		  			val elt = bl.corresp(req.titleBoard + req.userId).get(req.elementId)
  					ElementReturned(elt.body,elt.id,elt.creatorId,elt.creationDate)   					
  				}
  				else{
  					FailureMessage("User has not the permission do that")
  				}
				
  			}
  			else{
  				FailureMessage("No Such User")
  			}

  		}
  		else{
  			FailureMessage("No such element Left")
  		}

  	}
  	else{
  		FailureMessage("No such board Left")
  	}

  }
  post("/ask/board"){
  	val req = parsedBody.extract[BoardReq]
  	ask_board(req.titleBoard+req.userId)
  }
  post("/ask/element"){
  	val req = parsedBody.extract[ElementReq]
  	if(ask_board(req.titleBoard+req.userId)){
  		ask_elt(req.titleBoard+req.userId,req.elementId)
  	}
  	else{
  		FailureMessage("No Such Board")
  	}
  }
	post("/nask/board"){
  	val req = parsedBody.extract[BoardReq]
  	!ask_board(req.titleBoard+req.userId)
  }
  post("/nask/element"){
  	val req = parsedBody.extract[ElementReq]
  	if(ask_board(req.titleBoard+req.userId)){
  		!ask_elt(req.titleBoard+req.userId,req.elementId)
  	}
  	else{
  		FailureMessage("No Such Board")
  	}
  	
  }

  post("/read/user"){
  	val req = parsedBody.extract[UserIdReq]
  	if(ul.ul.contains(req.name)){
  		var usr = ul.ul(req.name)
  		UserReq(usr.name,usr.role)
  	}
  	else{
  		FailureMessage("No Such User")
  	}
  }
  get("/readall/board"){
  	for((y,x) <-bl.corresp)
  		yield BoardReturned(x.id,x.owner,x.elements,x.listRoles)
  }
}
