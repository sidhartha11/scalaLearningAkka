package org.geo.pisreview.chapter29

import org.geo.pisreview.chapter29.mockentities.Database
import org.geo.pisreview.chapter29.mockentities.StudentDatabase
import org.geo.pisreview.chapter29.mockentities.SimpleDatabase
import org.geo.pisreview.chapter29.mockentities.Browser

import org.geo.pisreview.Utilities._


object GotApples {
  def main(args1: Array[String]) = {
    val args = Array("student")
    val db: Database = 
      if ( args(0) == "student") {
        StudentDatabase 
      } else {
        SimpleDatabase
      }
    
    /**
     * Note: Author is putting all this in the main
     * function.
     * Note the way we define the type of the input 
     * database -- this functionality is not available in java 
     */
    object browser extends Browser {
      val database: db.type = db 
    }
    
    val apple = SimpleDatabase.foodNamed("Apple").get
    
    for ( recipe <- browser.recipesUsing(apple))
      p("recipe" , recipe)
    
      /**
       * Compiler cannot figure out that the category below belongs to
       * who ??? 
       */
    for (category <- db.allCategories) {
      browser.displayCategory(category)
    }
      
  }
}