package com.example
import scala.slick.jdbc.JdbcBackend._
class Model (dal:DAL, db:Database) {
import dal._
def createDB = db.withSession(session=>dal.create(session))
def dropDB = db.withSession(session=>dal.drop(session))
def purgeDB = db.withSession(session=>dal.purge(session))
def getRecipes():List[Recipe]={
  db.withSession{session=>
    findAllRecipes(session)}
}
def getSpecRecipe(mstr:String):List[Recipe]={
  db.withSession{session=>
    findSpecRecipe(session,mstr)}
}
def getNames():List[String]={
  db.withSession{session=>
    findAllNames(session)}
}
def getLikeNames(mstr:String):List[String]={
  db.withSession{session=>
    findLikeNames(session,mstr)}
}
def addRecipe(rec:Recipe):Option[Long]={
  db.withSession{
    session=>
      insert(rec)(session)
  }
}

}