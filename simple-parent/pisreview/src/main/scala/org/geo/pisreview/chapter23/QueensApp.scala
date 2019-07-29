package org.geo.pisreview.chapter23

import org.geo.pisreview.Utilities._

object Queens {
  def queens(n: Int): List[List[(Int,Int)]] = {
    
    def placeQueens(k: Int): List[List[(Int, Int)]] = {
      /**
       * if k has become 0 we are finished generating 
       * solutions
       */
      if ( k == 0 )  {
         List(List())
      } else {
        for {
          /**
           * Recursively call placeQueens, each time decrementing k until
           * reaching zero .. at which point a List(List()) is returned.
           * Then start processing elements placed on the recursion stack.
           */
          queens <- placeQueens ( k - 1 ) 
          column <- 1 to n 
          queen = (k, column)
          if isSafe(queen, queens)
        } yield 
        {
        p("yielding queen = " + queen ) 
        p("yielding queens = " + queens )
        queen :: queens
        }
      }
      
    }  /** end of placeQueens **/
    placeQueens(n)
  } /** end of queens **/
  
  def isSafe ( queen: (Int , Int) , queens: List[(Int, Int)]) = {
    queens.forall( q => {
      p("isSafe:checking q=" + q + " , against:" + queen)
      !inCheck(queen,q)
    })
    }
  
  def inCheck(q1: (Int, Int) , q2: (Int, Int)) = {
    p("inCheck: q1 = " + q1 + " , q2 = " + q2 )
    
    /** in same row ? **/
    val bol = q1._1 == q2._1  ||
    /** in same column **/
    q1._2 == q2._2  ||
    /** on diagonal ? **/
    (q1._1 - q2._1).abs == (q1._2 - q2._2).abs 
    
    p("inCheck returning " + bol)
    bol
  }
    
}


object QueensApp extends App {
  import Queens._
  
  val solution = queens(4)
  p("got solution back of size:" + solution.size)
  solution.foreach(p => {
    println(p)
  })
  
  
}