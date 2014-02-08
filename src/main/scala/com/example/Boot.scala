package com.example

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http


object Main extends App {
implicit val system=ActorSystem()
val handler = system.actorOf(Props[RServiceActor], name = "rservice")
  IO(Http) ! Http.Bind(handler, interface = "localhost", port = 8080)
}