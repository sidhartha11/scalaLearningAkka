package org.geo.pisreview.chapter3

object SortExamples {
  
  /** Example 1 **/
  def example_1 {
    /**
     * the sorted method relies on an implicit scala.math.Ordering
     */
    val a = List(10,5,8,1,7).sorted
    println(a)
    val b = List("banana" , "pear" , "apple", "orange").sorted
    println(b)
  }
  
  def example_2 {
    /**
     * sortWith allows you to supply your own sorting function
     */
    var a = List(10,5,8,1,7) sortWith(_ < _)
    println(a)
    a = List(10,5,8,1,7) sortWith ( _ > _ )
    println(a)
    var b = List("banna" , "pear" , "apple" , "orange") sortWith (_ < _ )
    println(b)
    b = List("banna" , "pear" , "apple" , "orange") sortWith ( _ > _ )
    println(b)
    
    b = List("banna" , "pear" , "apple" , "orange" ) sortWith( _.length < _.length )
    println(b)
  }
  def main(args: Array[String]){
    example_2
  }
}