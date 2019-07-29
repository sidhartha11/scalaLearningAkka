package org.geo.pisreview.chapter20

import org.geo.pisreview.Utilities._

/**
 * Scala does not have a language construct for 
 * Enumerations.Instead there is a class in scala called
 * Enumeration.
 */
object Direction extends Enumeration {
  val North = Value("North")
  val East  = Value("East")
  val South = Value("South")
  val West  = Value("West")
}

object Color extends Enumeration {
  val Red = Value 
  val Green = Value 
  val Blue = Value 
}

object EnumerationsExample extends App {
  import Direction._

  for ( d <- Direction.values ){
    p(d + " ,id=" + d.id)
  }
  for ( d <- ( 0 until Direction.maxId ) ) {
    p ( "value = " + Direction(d))
  }
  
}