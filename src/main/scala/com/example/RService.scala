package com.example

import akka.actor.Actor
//import ProductionDB
//import DBConfig
import spray.http.MediaTypes.{ `text/html` }
import spray.httpx.SprayJsonSupport.sprayJsonMarshaller
import spray.httpx.SprayJsonSupport.sprayJsonUnmarshaller
import spray.json.DefaultJsonProtocol
import spray.routing.Directive.pimpApply
import spray.routing.HttpService
//import Recipe
import scala.None
//import spray.routing.ExceptionHandler


class RServiceActor extends Actor with RService with ProductionDB {
def actorRefFactory =context
def receive = runRoute(rRoute)

}
object JsonImplicits extends DefaultJsonProtocol {
  implicit val impPerson = jsonFormat5(Recipe)
}
trait RService extends HttpService {
  this:DBConfig =>

val rRoute = {
 // import spray.httpx.SprayJsonSupport.sprayJsonMarshaller
 //   import spray.httpx.SprayJsonSupport.sprayJsonUnmarshaller
    import JsonImplicits._
    
  get {
    path("") {
      respondWithMediaType(`text/html`) {
        complete {
          <html>
              <body>
                <h1>The <b>r</b> - <i>Slick Spray Scala webservice Stack</i> is running :-)</h1>
              </body>
            </html>
        }
      }
    }  ~ path("recipes") {
      ctx =>
        ctx.complete {
               val result:List[String]=m.getNames()
          result
        }
    }  ~ path("test") {
      ctx =>
        ctx.complete {
               val result:List[String]=m.getLikeNames("D")
          result
        }
    }  ~ pathPrefix("recipeof" / Rest) {
          mstr =>
        complete {
          val result:List[Recipe]=m.getSpecRecipe(mstr)
          result
        }
    } ~ pathPrefix("recipelike" / Rest) {
          mstr =>
        complete {
          val result:List[String]=m.getLikeNames(mstr)
          result
        }
    }
  }
}    
}