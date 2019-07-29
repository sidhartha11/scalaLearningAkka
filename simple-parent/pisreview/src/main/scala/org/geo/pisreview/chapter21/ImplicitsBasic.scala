package org.geo.pisreview.chapter21

import org.geo.pisreview.Utilities._

/**
 * Trivial example with implicits
 */
object Trivial {
  def add(x: Int)(implicit y: Int) = x + y
  //implicit def aToB(a: A): B = ???
  implicit def intToIntBox(int: Int): IntBox = IntBox(int)
}

case class IntBox(value: Int) extends Box[Int] {
  def +(other: IntBox) = IntBox(value + other.value)
}
/**
 * Simple value case class that holds
 * integers
 * Notes:
 * The one abstract method that the class that mixes in Ordered needs
 * to override is "compare"
 * Ordered[T] trait is equivalent to Java's Comparable[T]
 * Note
 */

case class Value(i: Int) extends Ordered[Value] {
  /** simple compararison of integers **/
  def compare(that: Value) = this.i - that.i
}
/**
 * generic container used to illustrate ordering
 */
trait Box[T] {
  def value: T
}

/**
 * We can sort on the generic container using this sort class
 */
class Sort[T](ordering: Ordering[Box[T]]) {
  def apply(boxes: Seq[Box[T]]) = {
    boxes.sorted(ordering)
  }
}
/**
 * For Sort to work, we have to define an Ordering on Boxes.
 */

class BoxOrdering[T](ordering: Ordering[T]) extends Ordering[Box[T]] {
  def compare(x: Box[T], y: Box[T]): Int = ordering.compare(x.value, y.value)
}

object boxSort {
  def apply[T](boxes: Seq[Box[T]])(implicit ordering: Ordering[T]) = {
    val boxOrdering = new BoxOrdering(ordering)
    boxes.sorted(boxOrdering)
  }
}

object ImplicitsBasic extends App {
  def example_1 {
    val list = List(IntBox(1), IntBox(2), IntBox(3), IntBox(2), IntBox(1))

    val sort = new Sort(new BoxOrdering(scala.math.Ordering.Int))
    val l = sort(list)
    p(l)
  }

  def example_2 {
    import Trivial._
    var l = add(3)(4)
    p(l)
    implicit val x: Int = 4
    l = add(3)
    p(l)

    val a = IntBox(3)
    val b = IntBox(4)
    val c = a + b.value
    val d = a + b
    p("a", a)
    p("b", b)
    p("a + b.value", a + b.value)
    p("a + b", a + b)
  }
  
  val list = List(IntBox(1) , IntBox(2) , IntBox(3), IntBox(2), IntBox(1))
  val list2 = boxSort(list)
  p("list2" , list2)
}