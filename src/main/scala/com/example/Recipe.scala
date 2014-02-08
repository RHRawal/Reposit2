package com.example
//import scala.slick.jdbc.{GetResult, StaticQuery => Q}
//import Q.interpolation

case class Recipe (id: Option[Long] = None,recname:String,keypoints:String,ingred:String,method:String)

trait RecipeComponent {
  this: Profile =>
    
    import profile.simple._
   
class Recipes(tag:Tag) extends Table[(Option[Long],String, String, String,String)](tag,"RECIPE") {

     def id = column[Option[Long]]("ID", O.PrimaryKey, O.AutoInc)
    def recname = column[String]("RECNAME", O.NotNull)
    def keypoints = column[String]("KEYPOINTS")
    def ingred = column[String]("INGRED")
    def method = column[String]("METHOD")
    def * = (id, recname, keypoints, ingred, method)
    }
    val recipes=TableQuery[Recipes]
 
    def qAll = for (x <- recipes) yield x
    def qAllNames = for (x <- recipes) yield (x.recname)
    def qSpecRecipe(mrec:String) = for (x<- recipes if (x.recname === mrec)) yield x
     def findSpecRecipe(implicit session: Session,mrec:String): List[Recipe] = { qSpecRecipe(mrec).list map { x => Recipe(recname = x._2, keypoints = x._3,ingred = x._4,method=x._5, id = x._1) } }
     def findAllRecipes(implicit session: Session): List[Recipe] = { qAll.list map { x => Recipe(recname = x._2, keypoints = x._3,ingred = x._4,method=x._5, id = x._1) } }
    def findAllNames(implicit session:Session): List[String] = {qAllNames.list()}
    def insert(recipe: Recipe)(implicit session: Session): Option[Long] = {
         (recipes returning recipes.map(_.id)) += (None,recipe.recname,recipe.keypoints,recipe.ingred,recipe.method)
    }
    def qLikeNames(mstr:String) = for (x<-recipes if (x.recname.startsWith(mstr))) yield x.recname
def findLikeNames(implicit session:Session,mstr:String): List[String] = {qLikeNames(mstr).list()}  
 
    
    
    
}