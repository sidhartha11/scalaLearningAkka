package org.geo.pisreview.chapter23

import org.geo.pisreview.Utilities._

object Demo {
  def map[A,B](xs: List[A], f: A => B): List[B] = {
    for ( x <- xs ) yield f(x) 
  }
  
  def flatMap[A,B](xs: List[A], f: A => List[B]): List[B] = {
    for ( x <- xs ; y <- f(x)) yield{
      println("yielding y = " + y ) 
      y 
    }
  }
  
  def filter[A](xs: List[A] , p: A => Boolean): List[A] = {
    for ( x <- xs if p(x)) yield x 
  }
}
/**
 * Every for expression can be expressed in terms of the three higher-order
 * unctions map, flatMap, and withFilter
 */
object ForTranslations extends App {
  def do_23_4 {
    val list = List(1,2,3,4) 
    var l1 = for ( ele <- list ) yield List(ele)
    p(l1)
    l1 = list.map(p => List(p))
    p(l1)
    
    var l2 = for ( ele <- list ; if ele % 2 == 0 ) yield ele
    p(l2)
    l2 = list withFilter( _ % 2 == 0 ) map (p => p )
    p(l2)
    
    /**
     * Two generators translation
     */
    var l3 = for ( x <- list ; x2 <- list ) yield (x, x2)
    p(l3)
    
    l3 = list.flatMap( x => for ( y <- list ) yield (x,y))
    p(l3)
    
    var l4 = list.map( x => for ( y <- list ) yield (x,y))
    p(l4)
    
    /**
     * Simple side-effect translations 
     */
    for ( x <- list ) {
      println(x)
    }
 
    
  }
  
  def do_23_4_a { 
   val xss = List(
    List(1,2,3),
    List(2,3,4),
    List(5,6,7))
    val list = List(1,2,3,4) 

  
    for ( x <- list; if x % 2 == 0 ) println(x)
    list withFilter ( _ % 2 == 0 ) foreach( p =>  println(p))
    
    /**
     * larger example 
     */
    for ( x <- list ; if x %2 == 0 ; x2 <- list ) {
      println( (x,x2))
    }
    println("====")
    (list.withFilter (x => x % 2 == 0 )).foreach( x2 => 
        list foreach  ( x3 => println(x2,x3))
    )
    var sum = 0 
    for ( xs <- xss; x <- xs ) sum += x
    println("sum=" + sum)
    
    sum = 0 
    xss foreach ( xs => 
      xs foreach ( x => 
        sum += x))
    println("sum=" + sum)
  }
  
   val l = List ( 1 , 2,3)
   val l2 = Demo.flatMap[Int,Int](l , p => {
     val x = List(p*2)
     println("listing x = " + x ) 
     x
   }
     ) 
   println(l2)
}