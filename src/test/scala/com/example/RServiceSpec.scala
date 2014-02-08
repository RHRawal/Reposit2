package com.example

//import RService
import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
//import domain.DBConfig
//import TestDB
//import org.junit.runner.RunWith
//import org.specs2.runner.JUnitRunner




/*import org.junit.runner.RunWith
//import org.specs2.Specification
//import s4.domain.Person
//import s4.domain.TestDB
import spray.http.BasicHttpCredentials
import spray.http.ContentType
import spray.http.HttpCharsets.{ `UTF-8` }
import spray.http.HttpEntity
import spray.http.HttpHeaders.Authorization
import spray.http.MediaTypes.{ `application/json` }
import spray.httpx.SprayJsonSupport.sprayJsonUnmarshaller
import spray.routing.AuthenticationFailedRejection
//import spray.routing.AuthenticationRequiredRejection
import spray.testkit.Specs2RouteTest
import org.specs2.runner.JUnitRunner
*/


//@RunWith(classOf[JUnitRunner])
class RServiceSpec extends Specification with Specs2RouteTest with RService with ProductionDB {
def actorRefFactory = system
val smallRoute =
    get {
      pathSingleSlash {
        complete {
          <html>
            <body>
              <h1>Say hello to <i>spray</i>!</h1>
            </body>
          </html>
        }
      } ~
      path("ping") {
        complete("PONG!")
      }
    }
val actroute=rRoute

"return a greeting for GET requests to the root path" in {
      Get() ~> smallRoute ~> check {
        responseAs[String] must contain("Say hello")
      }
    }

    "return a 'PONG!' response for GET requests to /ping" in {
      Get("/ping") ~> smallRoute ~> check {
        responseAs[String] === "PONG!"
      }
    }
    "other paths gets must be unhandled" in {
      Get("/asas") ~> smallRoute ~> check {
        handled must beFalse
      }
    }
"return string with 'Dal' for get requests to /recipes " in {
  Get("/recipes") ~> rRoute ~> check {
    responseAs[String] must contain("Dal")
  }
}  

}