package org.geo.pisreview.chapter29

import org.geo.pisreview.chapter29.mockentities.SimpleDatabase
import org.geo.pisreview.chapter29.mockentities.StudentDatabase

import org.geo.pisreview.Utilities._
import org.geo.pisreview.chapter29.mockentities.SimpleBrowser
import org.geo.pisreview.chapter29.mockentities.StudentBrowser


object Main extends App {
  
  
  val apple = SimpleDatabase.foodNamed("Apple").get
  p("apple" , apple)
  
  val recipe = SimpleBrowser.recipesUsing(apple)
  p("recipe" , recipe)
  
  val food = StudentDatabase.foodNamed("FrozenFood").get
  p("food" , food)
  val item = StudentBrowser.recipesUsing(food)
  p("item" , item)
  
  val category = StudentDatabase.allCategories.head
  /**
   * category in StudentDatase is a different type than 
   * category in the SimpleBrowser
   */
  // SimpleBrowser.displayCategory(category)
  StudentBrowser.displayCategory(category)
  
}