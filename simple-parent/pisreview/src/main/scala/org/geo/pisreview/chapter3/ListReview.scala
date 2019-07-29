package org.geo.pisreview.chapter3

import org.geo.pisreview.Utilities

object ListReview {
  import Utilities._
  def main(args:Array[String]) {
    
    /** List() empty list **/
    var l:List[String] = List()
    p("l" , l)
    /** List Creation **/
    l = List("Cool" , "tools" , "rule")
    p("l" , l)
    log(l.toString)
    /** List Creation **/
    val thrill = "Will" :: "fill" :: "until" :: Nil
    p(thrill)
    /** List Concatenation **/
    l = List("a" , "b") ::: List("c" , "d")
    p(l)
    /** Indexing a list **/
    p(thrill(2))
    p(thrill.apply(2))
    /** using count with a predicate on a list **/
    val pred1: String => Boolean = s => s.length == 4
    val cnt  = thrill.count(pred1)
    p(cnt)
    /** drop 2 elements from a list **/
    p(thrill drop 2)
    /** drop n elements from a list starting at the right **/
    p( thrill dropRight 2)
    /** determine if an element exists in a list **/
    p( thrill exists ( s => s == "until") )
    /** filter a list **/
    p ( thrill filter ( s => s.length == 4 ))
    /** predicate applied against all elements in a list **/
    val b = thrill forall( s => s.endsWith("l") )
    println("b = %b".format(b))
    /** foreach statement in scala **/
    thrill foreach print
    thrill.foreach( p => {
      println(p)
    }
    )
    /** return the  head of a list **/
    println(thrill head)
    /** return all but the last element of a list **/
    println( thrill init )
    /** determine if a list is empty **/
    println(thrill isEmpty)
    /** returns the last element in a list **/
    println(thrill last)
    /** return the number of elements in a list **/
    println(thrill length)
    /** returns a list reslting from adding a "y" to each element **/
    l = thrill map(_ + "y")
    println("l = " + l)
    println(thrill mkString)
    println(thrill mkString(","))
    l = thrill filterNot ( _.length == 4 )
    /** reverse list **/
    println(thrill reverse)
    // it is possible that List.sort no longer exists 
    //thrill.sort ( ( s , t ) => s.charAt(0).toLower < t.charAt(0).toLower )
    
    println("thrill.tail = " + thrill.tail)
    
  }
}