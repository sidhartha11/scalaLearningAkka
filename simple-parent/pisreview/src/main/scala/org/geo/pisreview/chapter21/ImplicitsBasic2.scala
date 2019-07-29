package org.geo.pisreview.chapter21

import org.geo.pisreview.Utilities._


/**
 * For Sort to work, we have to define an Ordering on Boxes.
 */
trait Box2[T] {
  def value: T
}

/**
 * IntBox2 mixes in the Box2 Trait , an Int specialization of the 
 * Box2 trait. 
 */
case class IntBox2(value: Int) extends Box2[Int] {
  def +(other: IntBox2) = IntBox2(value + other.value)
}
/**
 * BoxOrdering2[T] is a generic class that specializes the Ordering
 * trait to order objects of type Box2[T] 
 * Not that this knows about the elements of the Object it wants to sort. 
 */
class BoxOrdering2[T](ordering: Ordering[T]) extends Ordering[Box2[T]] {
  def compare(x: Box2[T], y: Box2[T]): Int = ordering.compare(x.value, y.value)
}
/**
 * object boxSort2 is simply used to sort a generic list using an 
 * implicit ordering: Ordering[T] 
 */
object boxSort2 {
  def apply[T](boxes: Seq[Box2[T]])(implicit ordering: Ordering[T]) = {
    val boxOrdering = new BoxOrdering2(ordering)
    boxes.sorted(boxOrdering)
  }
}
object ImplicitsBasic2 extends App {
  /**
   * Now .. if we crete a list of IntBox2s 
   * We should be able to sort it using the setup above
   */
  
  val list = List(IntBox2(1)
      , IntBox2(2)
      , IntBox2(3)
      , IntBox2(2)
      , IntBox2(1))
      val list2 = boxSort2(list)
      p ( list ) 
      p ( list2 ) 
}