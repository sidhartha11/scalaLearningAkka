package org.geo.pisreview.alexander.recursive

import org.geo.pisreview.Utilities._
import scala.annotation.tailrec


object RecursiveApp extends App {
  
  def sum1(list: List[Int]): Int = {
    list match {
      case List() => 
        st
        0
      case h :: t => 
        h + sum1(t)
    }
    
  }
  
 
 def sum2(list: List[Int]): Int = {
   @tailrec
   def sum(list: List[Int], accum :Int = 0): Int = {
     list match {
       case List() => 
         st
         accum
       case h :: t => 
         sum(t, accum + h)
     }
   }
   sum(list)
 }
  
  p("sum1(List(1,2,3,4,5,6)" , sum1(List(1,2,3,4,5,6)))
  p("sum2(List(1,2,3,4,5,6)" , sum2(List(1,2,3,4,5,6)))
}