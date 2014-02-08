package com.example

import scala.slick.driver.JdbcProfile
import org.slf4j.Logger
import org.slf4j.LoggerFactory


trait Profile { val profile:JdbcProfile}
class DAL (override val profile:JdbcProfile) extends RecipeComponent with Profile {
import profile.simple._
val logger:Logger = LoggerFactory.getLogger("domain")
logger.info("RHR","Class DAL entered")
def create (implicit session:Session):Unit = {
  try {recipes.ddl.create} catch {
    case e:Exception => logger.info("RHR", "Could not create database")
   }
}
def drop(implicit session: Session): Unit = {
    try { recipes.ddl.drop } catch {
      case e: Exception => logger.info("RHR","Could not drop database")
    }
  }
def purge(implicit session: Session) = { drop; create }
}