package org.geo.pisreview.chapter29.mockentities

import org.geo.pisreview.chapter29.recipe.Sugar
import org.geo.pisreview.chapter29.recipe.Apple
import org.geo.pisreview.chapter29.recipe.Cream
import org.geo.pisreview.chapter29.recipe.Orange
import org.geo.pisreview.chapter29.recipe.Food
import org.geo.pisreview.chapter29.recipe.Recipe
import org.geo.pisreview.chapter29.recipe.FruitSalad

import org.geo.pisreview.Utilities._
/**
 * The different mock databases used in testing 
 */
object StudentDatabase extends Database {
  object FrozenFood extends Food("FrozenFood")
  
  object HeatItUp extends Recipe (
      "heat it up" , 
      // List(FrozenFood) , 
      allFoods,
      "Microwave the 'food' for 10 minutes.")
  
  def allFoods = List(FrozenFood,Apple)
  def allRecipes = List(HeatItUp)
  def allCategories = List (
      FoodCategory("edible" , List(FrozenFood)))
      
}

/**
 * SimpleDatabase extending and mixing in objects
 */
object SimpleDatabase  extends Database 
with SimpleRecipes with SimpleFoods {
//  def allFoods = List(Apple, Orange, Cream,Sugar)
//    /**
//     * returns a list of all recipes 
//     */
//  def allRecipes: List[Recipe] = List(FruitSalad)
//  
//  private var categories = List (
//      FoodCategory("fruits" , List(Apple,Orange)),
//      FoodCategory("misc"   , List(Cream,Sugar)))
//      
//  def allCategories = categories
}

/**
 * THe different Browsers that use a particular 
 * mock database 
 */
object SimpleBrowser extends Browser {
  val database = SimpleDatabase 
}

object StudentBrowser extends Browser {
  val database = StudentDatabase
}

/**
 * The abstract browser that all browsers extend
 */
abstract class Browser {
  /**
   * abstract database must be implemented by concrete 
   * implementations
   */
  val database: Database
  
  def recipesUsing(food: Food) = {
//    p("food" , food)
//    p("database.allRecipes", database.allRecipes)
    database.allRecipes.filter(recipe => 
      recipe.ingredients.contains(food))
  }
      
  def displayCategory(category: database.FoodCategory) = {
    p("category", category)
  }
}

/**
 * The abstract database that all mock databases extend and add 
 * specalized functionality 
 */
//abstract class Database {
//  def allFoods: List[Food] 
//  def allRecipes: List[Recipe]
//  def allCategories: List[FoodCategory] 
//  
//  
//  def foodNamed(name: String) = 
//    allFoods.find(f => f.name == name )
//    
//  case class FoodCategory(name: String , foods: List[Food]) 
//   
//}
/**
 * Here we remove Category information from Database and use the 
 * trait instead
 */
abstract class Database extends FoodCategories {
  def allFoods: List[Food] 
  def allRecipes: List[Recipe]
  /** this is now in the trait, but can be overriden if need be **/
  // def allCategories: List[FoodCategory] 
  
  
  def foodNamed(name: String) = 
    allFoods.find(f => f.name == name )
    
  /** note that you cannot override a class **/
  //override case class FoodCategory(name: String , foods: List[Food]) 
   
}

/**
 * To make them a little more modular, we can turn FoodCategory into
 * a trait. 
 */
trait FoodCategories {
  case class FoodCategory(name: String , food: List[Food])
  def allCategories: List[FoodCategory] 
}

/** 
 *  Now attempt to convert foods and recipes to traits for use 
 *  with the SimpleDatabase 
 */

trait SimpleFoods {
  object Pear extends Food("Pear")
  def allFoods = List(Apple, Pear)
  def allCategories = Nil 
}

trait SimpleRecipes { // does Not Compile
  /**
   * Compiles and compiler will locate Pear if 
   * a self type is indicated
   */
  this: SimpleFoods => 
  object FruitSalad extends Recipe (
      "fruid salad" ,
      List(Apple,Pear), // Uh oh 
      "Mix it all together."
      )
  def allRecipes = List(FruitSalad)
}








