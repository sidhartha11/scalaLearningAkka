package org.geo.pisreview.chapter17
import scala.collection.mutable
import org.geo.pisreview.Utilities._

object CommonSetOps extends App {
  /**
   * val nums = Set(1, 2, 3)	
   * Creates an immutable set 
   * (nums.toString returns Set(1, 2, 3))
   * 
   */
  var numsM = mutable.Set(1,2,3)
  var nums = Set(1,2,3)
  for ( s <- nums ) p(s)
  for ( s <- numsM ) p(s)
  /**
   * nums + 5	
   * Adds an element (returns Set(1, 2, 3, 5))
   */
  var nums_h = nums
  var numsM_h = numsM
  nums +=  5
  numsM += 5 
  p("nums + 5" , nums)
  p("numsM + 5" , numsM)
  p("nums eq nums_h" , nums eq nums_h)
  p("numsM eq numsM_h" , numsM eq numsM_h)
  /**
   * nums ++ List(5, 6)	
   * Adds multiple elements (returns Set(1, 2, 3, 5, 6))
   */
  nums ++= List(5,6)
  numsM ++= List(5,6)
  p("nums" , nums)
  p("numsM" , numsM)
  p("nums eq nums_h" , nums eq nums_h)
  p("numsM eq numsM_h" , numsM eq numsM_h)
  /**
   * nums -- List(1, 2)	
   * Removes multiple elements (returns Set(3))
   */
  nums --= List(1,2)
  numsM --= List(1,2)
  p("nums" , nums)
  p("numsM" , numsM)
  p("nums eq nums_h" , nums eq nums_h)
  p("numsM eq numsM_h" , numsM eq numsM_h)
  /**
   * nums & Set(1, 3, 5, 7)	
   * Takes the intersection of two sets (returns Set(1, 3))
   */
  p("set intersection of nums and Set(1,3,5,7)")
  val numsI = nums & Set(1,3,5,7)
  val numsMI = numsM & Set(1,3,5,7)
  p("numsI" , numsI)
  p("numsMI" , numsMI)
  p("nums.size" , nums.size)
  p("numsM.size" , numsM.size)
  /**
   * nums.contains(3)	
   * Checks for inclusion (returns true)
   */
  p("nums.contains(3)" , nums.contains(3))
  /**
   * val words =                      
   * mutable.Set.empty[String]	
   * Creates an empty, mutable set (words.toString returns Set())
   * 
   */
  val words = mutable.Set.empty[String]
  p("mutable.Set.empty[String]" , words.toString)
  /**
   * words += "the"	
   * Adds an element (words.toString returns Set(the))
   */
  p("words += \"the\"" , words += "the")
  /**
   * words -= "the"	
   * Removes an element, if it exists (words.toString returns Set())
   */
  p("words -= \"the\"", words -= "the")
  /**
   * words ++= List("do", "re", "mi")	
   * Adds multiple elements (words.toString returns Set(do, re, mi))
   */
  p("words ++= List(do,re,mi)" , words ++= List("do" , "re" , "mi"))
  /**
   * words --= List("do", "re")	
   * Removes multiple elements (words.toString returns Set(mi))
   */
  p("words --= List(do,re)" , words --= List("do" , "re"))
  /**
   * words.clear	
   * Removes all elements (words.toString returns Set())
   */
  p("words.clear" , words.clear)
  p("words" , words)
  

  
}