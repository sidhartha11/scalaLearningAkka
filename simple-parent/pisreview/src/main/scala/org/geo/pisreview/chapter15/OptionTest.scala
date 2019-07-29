package org.geo.pisreview.chapter15

import org.geo.pisreview.Utilities._

object OptionTest extends App {
  
  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }
  
  val capitals = Map("France" -> "Paris" , 
      "Japan" -> "Tokyo")
  /**
   * Note:
   * Scala Hashap cannot contain null values, hence null can never
   * be returned ( unlike java ) 
   */
  p("capitals" , capitals)
  /** Map get function returns an option type: Some(value) or None **/
  /** Scala's map function symbol same as Java's lambda symbol **/
  p("capitals get \"France\"", capitals get "France")
  
  p(show(capitals get "Japan") )
  p(show(capitals get "France"))
  p(show(capitals get "Hungary"))
  
  for ( (country, city) <- capitals ) 
    println("The capital of " + country + " is " + city)
    
  val results = List(Some("apple") , None , Some("orange") ) 
  for ( Some(fruit) <- results ) println(fruit)
  
  val l = List(List(1,2) , List(1) , List() , List(1,2,3,4) , List(3,4))
  for ( List(a,b) <- l ) println("a=" + a + ",b=" + b)
}