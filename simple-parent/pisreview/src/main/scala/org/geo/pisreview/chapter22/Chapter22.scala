package org.geo.pisreview.chapter22

import scala.collection.mutable.ListBuffer 
import org.geo.pisreview.Utilities._

object Chapter22 extends App {
  
  def myMapOuter[T,U](xs:List[T] , f: T => U): List[U] = {
    def map[U](f: T => U): List[U] = {
      val b = new ListBuffer[U]
      var these:List[T] = xs
      while ( !these.isEmpty ) {
        b += f(these.head)
        these = these.tail
      }
      b.toList
    }
    map(f)
  }
  
  def incAll(xs: List[Int]): List[Int] = 
    xs match {
    case List() => Nil
    case h :: t => (h + 1) :: incAll(t)
  }
  
  def incAll2(xs: List[Int]) : List[Int] = 
  {
    def incAllInside(xs: List[Int] , ys: List[Int] = List[Int]()): List[Int] = {
      xs match {
        case List() => ys
        case h :: t => incAllInside(t,(h+1) :: ys)
      }
    }
    val l=incAllInside(xs)
    l
  }
  
  def incAll3(xs: List[Int]) : List[Int] = 
  {
    def incAllInside(xs: List[Int] , ys: List[Int] = List[Int]()): List[Int] = {
      xs match {
        case List() => ys
        case h :: t => incAllInside(t,ys ::: List((h + 1)))
      }
    }
    val l=incAllInside(xs)
    l
  }
  
  def test_1 {
  val l = incAll(List(1,2,3,4))
  p(l)
  val l2 = incAll2(List(1,2,3,4))
  p(l2)
  
  val l3 = incAll3(List(1,2,3,4))
  p(l3)
  }
  
  def usingListBuffer {
    /**
     * create an empty ListBuffer 
     * new ListBuffer[Int]
     * ListBuffer is mutable
     * += appends an element to the buffer 
     */
    val xs = List(1,2,3,4)
    val buf = new ListBuffer[Int]
    for ( x <- xs ) buf += x + 1
    p("ListBuffer" , buf)
    p(buf.getClass.getName)
    /**
     * convert to List
     */
    val l = buf.toList
    p("List" , l)
    p(l.getClass.getName)
    
  }
  def mapExample {
    val l:List[Int] = myMapOuter(List(1,2,3,4), (x: Int) => x + 2)
    println("l = " + l ) 
  }
  
}