package org.geo.pisreview.chapter9
import org.geo.pisreview.Utilities._
import java.io.PrintWriter
import java.io.File
import java.util.Date

object FileMatcher {
  def dir =
    "C:/workspaces/scala/generalstudies/scalaLearningAkka/simple-parent/pisreview/src/main/scala/org/geo/pisreview/chapter3"

  private def filesHere = (new java.io.File(dir)).listFiles()

  //  def containsNeg(nums: List[Int]): Boolean = {
  //    var exists  = false
  //    for ( num <- nums )
  //      if ( num < 0 )
  //        exists = true
  //    exists
  //  }

  def containsOddImperative(nums: List[Int]): Boolean = {
    /**
     * imperative version of checking for an odd numbe
     */
    var exists = false
    /** for comprehension **/
    for (num <- nums)
      /** if num % 2 returns a 1 remainder **/
      if (num % 2 == 1)
        exists = true
    exists
  }

  def containsOddFunctional(nums: List[Int]): Boolean = nums.exists(_ % 2 == 1)

  def containsOddRecursive(nums: List[Int]): Boolean =
    nums match {
      case List() => false
      case (hd :: tail) => if (hd % 2 == 1)
        true
      else
        containsOddRecursive(tail)
    }

  def containsNeg(nums: List[Int]) = nums.exists(_ < 0)

  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere; if matcher(file.getName))
      yield file

  def filesEnding(query: String) =
    filesMatching(_.endsWith(query))

  def filesContaining(query: String) =
    filesMatching(_.contains(query))

  def filesRegex(query: String) =
    filesMatching(_.matches(query))
  def testExamples_1 {
    //  p("filesEnding('.scala')" , filesEnding(".scala").mkString("\n"))
    //  p("filesContaining('Review')", filesContaining("Review").mkString("\n\t"))
    //  p("filesRegex([a-z]*)", filesRegex("scala*").mkString("\n"))
    //  val list = List(1,4,3,6,-1,5)
    //  p("val list = List(1,4,3,6,-1,5)" , containsNeg(list))

    val nums = List(2, 4, 6, 8, 10, 3, 14)
    p("containsOddFunctional(nums)", containsOddFunctional(nums))
    p("containsOddImperative(nums)", containsOddImperative(nums))
    p("containsOddRecursive(nums)", containsOddRecursive(nums))
  }
  
  def testExamples2 {
      p("curriedSum(1)(2)", curriedSum(1)(2))
  p("twice( _ + 1 , 5)" , twice(_ + 1 , 5))
//  withPrintWriter(
//      new File("C:/temp/Chapter7.txt")
//      , (p: PrintWriter) => p.println("hello"))
  
  var file = new File("C:/temp/date.txt")
  withPrintWriter(file)
  {
   writer => writer.println(new Date())
  }
  file = new File("C:/temp/date2.txt")
  
  val useFile = withPrintWriter(file)_
  
  useFile {
    writer => writer.println(new Date())
  }
  }
  def plainOldSum(x: Int, y: Int) = x + y 
  def curriedSum(x:Int)(y: Int) = x + y 
  /**
   * Here a function that takes a function as parameter and 
   * applies it twice to the second parameter by applying 
   * the the param, then applying to the result
   */
  def twice(op: Double => Double , x: Double) = op(op(x))
  
  /**
   * load pattern.
   * PrintWriter is loaned to the "op" function. And is 
   * closed in a finally block once the op function finishes.
   */
  def withPrintWriter1(file: File, op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }
  
  def withPrintWriter(file: File)(op:PrintWriter => Unit) ={
    val writer = new PrintWriter(file)
    try { 
      op(writer)
    } finally {
      writer.close()
    }
  }
}

object Chapter9 extends App {
  import FileMatcher._
  
  var assertionsEnabled = false 
  
  def myAssertX(predicate: () => Boolean) = 
    if (assertionsEnabled && !predicate())
      throw new AssertionError 
      
  def boolAssert(predicate: Boolean) = 
    if (assertionsEnabled && !predicate)
      throw new AssertionError 
      
  def myAssert(predicate: => Boolean) = 
    if ( assertionsEnabled && !predicate)
      throw new AssertionError
      
 val x = 5 
  myAssert( x / 0 == 0 ) 
  boolAssert( x / 0 == 0 ) 

}