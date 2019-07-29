package org.geo.pisreview.chapter16

import org.geo.pisreview.Utilities._

/**
 * List Data Type in Scala 
 * scala/collection/immutable/List
 * Java Difference 
 * In Java Generic List are not covariant 
 * ie
 * In Java 
 * List<Integer> is not a subtype of List<Number> ... they are invariat 
 * In Scala 
 * List<Int> would be a subtype of List<Number> 
 */

object Chapter16 extends App {
    val fruit = "apples" :: ( "oranges" :: ( "pears" :: Nil ))
    
    val nums = 1 :: ( 2 :: ( 3 :: ( 4 :: Nil )))
    
    val nums2 = 1 :: 2 :: 3 :: 4 :: Nil 
    p(nums == nums2)
    
    val diag3 = (1 :: ( 0 :: ( 0 :: Nil))) :: 
                (0 :: ( 1 :: ( 0 :: Nil))) ::
                (0 :: ( 0 :: ( 1 :: Nil))) :: Nil 
    
    val empty = Nil 
  /**
   * Recursive Insertion sort
   * 
   */
  def isort ( xs: List[Int]): List[Int] =  {
    p("isort: xs=" + xs)
    if (xs.isEmpty) Nil 
    else insert(xs.head,isort(xs.tail))
  } 
  def insert(x: Int, xs: List[Int]): List[Int] =  {
    if (xs.isEmpty || x <= xs.head) {
      p("insert: if-1 x = " + x + ", xs = " + xs)
      x :: xs 
    }
    else {
      p("insert: if-2 x = " + x + ", xs = " + xs)
      xs.head :: insert(x , xs.tail)
    }
  }
  
  /**
   * Insertion using match expressions
   */
  
  def isort_1(xs: List[Int]): List[Int] = xs match {
    case List() => List() 
    case x :: xs1 => insert_1(x, isort_1(xs1))
  }
  
  def insert_1(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if ( x <= y) x :: xs 
    else  y :: insert(x,ys)
  }
    
  /** the A* is a variable list of objects **/
  def applyOne[A](xs: A*): List[A] = xs.toList
  
  def section16_1{
    /** list of Strings **/
    val fruit = List("apples", "oranges","pears")
    /** list of numbers **/
    val nums = List(1,2,3,4)
    /** List of lists of String **/
    val diag3 = 
      List (
          List(1,0,0),
          List(0,1,0),
          List(0,0,1)
          )
     /** empty list **/
     val empty = List()
  }
  /**
   * with explicit types added 
   */
  def section16_2 {
    val fruit: List[String] = List("apples" , "oranges" , "pears")
    val nums:  List[Int] = List(1,2,3,4)
    val diag3: List[List[Int]] = 
      List (
          List(1,0,0), 
          List(0,1,0),
          List(0,0,1)
          )
   p(diag3.getClass.getName)
  }
  
  def section16_3 {

    
    p("fruit", fruit)
    
    /**
     * isEmpty
     */
    p("empty.isEmpty" , empty.isEmpty)
    /**
     * fruit.isEmpty
     */
    p("fruit.isEmpty" , fruit.isEmpty)
    /**
     * fruit.head
     */
    p("fruit.head" , fruit.head)
    /**
     * fruit.tail.head
     */
    p("fruit.tail.head", fruit.tail.head)
    /**
     * diag3.head
     */
    p("diag3", diag3)
    p("diag3.head" , diag3.head)
    
  }
  
  def callInsertionSort {
    val l = List(10,5,3,7,5)
    p(l)
    val l_s = isort(l)
    p(l_s)
    
  }
  def section16_5 {
    val fruit2 = "jack" :: "be" :: "nimble" :: "jack" :: "be" :: "quick" :: Nil
    val List(a,b,c) = fruit
    val d :: e :: f = fruit 
    var g :: h :: i = fruit2
    p("a = " + a + ",b = " + b + ", c = " + c)
    p("d = " + d + ",e = " +  e + ", f = " + f)
    p("g = " + g + ",h = " +  h + ", i = " + i)
  }
  
  def section16_6 {
    p("List(1,2) ::: List(3,4,5)" , List(1,2) ::: List(3,4,5))
    p("List() ::: List(1,2,3)" , List() ::: List(1,2,3))
    p("List(1,2,3) ::: List(4)" , List(1,2,3) ::: List(4))
    p("List(1,2,3) ::: List(1,2,3)" , List(1,2,3) ::: List(1,2,3))
    p("List(1,2,3) :: List(1,2,3)" , List(1,2,3) :: List(1,2,3))
  }
  
  section16_6
}