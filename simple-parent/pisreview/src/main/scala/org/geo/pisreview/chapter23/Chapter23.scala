package org.geo.pisreview.chapter23

import org.geo.pisreview.Utilities._

case class Person(name: String, 
   isMale: Boolean ,
    children: Person* 
     
    ) 
    
object Chapter23 extends App {
  /**
   * Note:
   * when 3rd parameter is Object* it obviously represents 
   * 0 or more so can be ommited if it is the last argument. 
   */
  val tooly = Person("Tooly" , false)
  val lara = Person("Lara" , false)
  val bob = Person("bob", true)
  val julie = Person("Julie" , false , lara , bob)
  val persons = List(lara, bob, julie, tooly)
  p(persons)
  
  val pairs = persons filter ( p => !p.isMale ) flatMap ( p => p.children 
      map ( c => (p.name , c.name)))
  
  p(pairs)
  /**
   * using withFilter avoids the creation of an intermediate data structure
   * for female persons. 
   */
  val pairs2 = persons withFilter ( p => !p.isMale && p.children.size > 0) map ( p =>{
    println("mapped --> " + p )
    p.children 
  }
      map ( c => {
        println("2 mapped --> " + c ) 
        ( p.name , c.name)}
      ))
      
   p(pairs2) 
   val pairs3 = for ( mom <- persons if !mom.isMale; sibling <- mom.children ) yield {
     (mom.name , sibling.name)
  
   }
  p(pairs3)
  
  var n = for ( p <- persons; n = p.name ; if ( n startsWith "Ju")) yield n
  p("n = " + n)
  
  n = for {
    p <- persons
    n = p.name 
    if ( n startsWith "To") 
  } yield {
    n
  }
  p("n = " + n)
  
  
  val x = for ( x <- List(1,2); y <- List("one" , "two")) yield ( x, y) 
  p(x)
  
  
  
  
  
  
}