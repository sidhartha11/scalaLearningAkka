package org.geo.pisreview.chapter17
import org.geo.pisreview.Utilities._
import java.util.Arrays
import scala.collection.mutable

object Chapter17 extends App {
  
  /**
   * Sequence types let you work with groups of data lined up in order. 
   * Because the elements are ordered, you can ask for the first element, 
   * second element, 103rd element, and so on. 
   * In this section, we'll give you a quick tour of the 
   * most important sequences
   * scala 2.1.2.6 source declaration 
   * sealed abstract class List[+A] extends AbstractSeq[A]
   *                               with LinearSeq[A]
   *                               with Product
   *                               with GenericTraversableTemplate[A, List]
   *                               with LinearSeqOptimized[A, List[A]]
   *                               with scala.Serializable {
   * 
   */
  
  /**
   * List is the most important sequence.
   */
  def section17_1 {
    val colors = List("red" , "blue" , "green" ) 
    p("colors" , colors)
    p("colors.head" , colors.head)
    p("colors.tail" , colors.tail)
  }
  /**
   * Arrays
   * Initialization
   * Array[Int](size) will initial an array of 5 elements with zeros 
   */
  def section17_2 {
    val fieInts = new Array[Int](5)
    p("fieInts" , Arrays.toString(fieInts))
    val fiveToOne = Array(5,4,3,2,1)
    p("fiveToOne" , Arrays.toString(fiveToOne))
    /**
     * access and update an element of an Array
     */
    fiveToOne(0) = fiveToOne(4)
    p("fiveToOne" , Arrays.toString(fiveToOne))
  }
  
  def section17_3 { 
    import scala.collection.mutable.ListBuffer 
    val buf = new ListBuffer[Int]
    /**
     * +=   list append operation 
     */
    buf += 1
    buf += 2 
    p("buf" , buf) 
    /**
     * +=: prepend operation
     * adds to the beginning o f the list 
     */
    3 +=: buf 
    p("3 +=: buf" , buf)
    /**
     * convert to a list 
     */
    p("buf.toList" ,buf.toList)
  }
  def section17_4 {
    import scala.collection.mutable.ArrayBuffer 
    /** 
     *  ArrayBuffer dynamically sized 
     */
    val buf = new ArrayBuffer[Int]()
    buf +=1 
    buf += 2
    p("buf" , buf)
    p("buf.length" , buf.length)
    p("buf(0)" , buf(0))
    buf(0) = buf(1)
    p("buf(0)=buf(1)" , buf)
    100 +=: buf
    p("100 +=: buf" , buf)
    val l = buf.toList
    p("l.getClass.getName" , l.getClass.getName)
  }
  /**
   * One other sequence to be aware of is StringOps, which implements many 
   * sequence methods. Because Predef has an implicit conversion from 
   * String to StringOps, you can treat any string like a sequence. 
   * Here's an example:
   * 
   */
  def hasUpperCase(s: String) = s.exists(_.isUpper)
  def section17_5 {
    p("hasUpperCase(abCde)" , hasUpperCase("abCde"))
    p("abcde.map(_.toUpper)" , "abcde".map(_.toUpper))
  }
  def section17_6 { 
    val mutaSet = mutable.Set(1,2,3)
    p("mutaSet" , mutaSet)
    /**
     * example:
     * remove duplicates from an Array of Strings
     */
    /**
     * example String.
     */
    val text = "See Spot run. Run, Spot. Run!"
    val wordsArray = text.split("[ !,.]+")
    /** 
     *  create an empty Set 
     *  Notes:
     *  empty is defined in mutable.HashSet as:
     *  override def empty[A]: HashSet[A] = new HashSet[A]
     *  
     */
    val words = mutable.Set.empty[String]
    /** 
     *  update the mutable set with the elements of the wordsArray,
     *  removing duplicates 
     *  += is the operator used to insert/update a mutable set 
     */
    wordsArray.foreach(println)
   //  p("wordsArray" , Arrays.toString(wordsArray))
    for ( word <- wordsArray ) words += word.toLowerCase()
    p("words" , words)
  }
  
  section17_6
}