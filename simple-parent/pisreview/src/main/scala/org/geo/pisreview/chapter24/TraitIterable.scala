package org.geo.pisreview.chapter24
import org.geo.pisreview.Utilities._
import ForIterables._

object TraitIterable extends App {
    /**
   * example implementation of
   * foreach 
   */
  def myforeach[R,U](itr:Iterable[R], f: R => U): Unit = {
    val it = itr.iterator
    while ( it.hasNext ) {
      f(it.next)
    }
  }
  
  val itr = Iterable('a' , 'b' , 'c')
  myforeach[Char,Int](itr, i => 
  { 
    val u = i.asInstanceOf[Int]
    p(u)
    i
    
  })
  
  /**
   * xs.iterator 
   * An iterator that yields every element in xs, in the same order 
   * as foreach traverses elements
   */
  p("xs.iterator" , xs.iterator)
  xs.iterator foreach ( println )
  val itr1 = xs.iterator 
  while (itr1.hasNext ) {
    p("itr1.next" , itr1.next)
  }
  /**
   * Other Iterators 
   */
  /**
   * xs grouped size
   * An iterator that yields fixed-sized "chunks" of this collection
   */
  val itr2 = xs grouped 3 
  p("xs grouped 3" , itr2)
  itr2 foreach {
    println
  }
  /**
   * xs sliding size 
   * An iterator that yields a sliding fixed-sized window of elements
   * in this collection
   */
  var itr3 = xs sliding 3
  p("xs sliding 3" , itr3)
  itr3 foreach { 
    println
  }
  itr3 = xs sliding 3
  //p("itr3.length",itr3.length)
  val v = for ( i <- itr3.take(2) ) yield {
    p("i=" + i)
    i
  }
  p("v" , v)
  /**
   * Subcollections
   */
  /**
   * xs takeRight n
   * A collection consisting of the last n elements of xs ( or , 
   * some argitray n elements, if no order defined.
   */
  p("xs takeRight 8" , xs takeRight 8)
  /**
   * xs dropRight n 
   * The rest of xs except for takeright n
   */
  val n = 10
  p("xs dropRight n" , xs dropRight n)
  /**
   * Zippers 
   */
  /**
   * xs zip ys 
   * An iterable of pairs corresponding to elemments from xs and ys 
   * 
   */
  val c = Iterable('a' , 'b' , 'c' , 'd') zip Iterable(1,2,3,4)
  p(c)
  p("xs zipWithIndex", xs zipWithIndex )
  /**
   * Comparison
   */
  /**
   * xs sameElements ys 
   * test whether xs and ys contain the same elements 
   */
  var bol = Iterable('a' , 'b' , 'c' , 'd') sameElements Iterable(1,2,3,4)
  p("bol" , bol)
  
 
}