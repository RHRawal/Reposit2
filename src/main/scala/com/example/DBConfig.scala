package com.example
/*
import scala.slick.driver.H2Driver
import scala.slick.driver.PostgresDriver
import scala.slick.session.{ Database, Session }*/
import scala.slick.driver.{H2Driver,SQLiteDriver}
import scala.slick.jdbc.JdbcBackend.Database

trait DBConfig {
  def m: Model
}

trait TestDB extends DBConfig {
  val m = new Model( new DAL(H2Driver),
    Database.forURL("jdbc:h2:mem:servicetestdb", driver = "org.h2.Driver"))
  m.createDB
}

trait ProductionDB extends DBConfig {
   val m = new Model( new DAL(SQLiteDriver),
    Database.forURL("jdbc:sqlite:d:/recipesdb/rhrdb.db", driver = "org.sqlite.JDBC"))
  m.createDB
  
}
