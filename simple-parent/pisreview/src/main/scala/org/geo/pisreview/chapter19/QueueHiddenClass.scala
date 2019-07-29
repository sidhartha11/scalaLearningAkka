package org.geo.pisreview.chapter19

import org.geo.pisreview.Utilities._

/**
 * This queue implementation has a trait public interface
 */
trait QueueHide[T] {
  def head: T
  def tail: QueueHide[T]
  def enqueue(x: T): QueueHide[T]
}
/**
 * The actual implementation is hidden inside a companion object
 */

object QueueHide {
  
  def apply[T](xs: T*): QueueHide[T] = 
    new QueueImpl[T](xs.toList, Nil)
    
    /**
     * Hidden implementaion
     */
    private class QueueImpl[T] ( 
        private val leading: List[T] , 
        private val trailing: List[T]
        ) extends QueueHide[T] {
    def mirror = 
      if (leading.isEmpty)
        new QueueImpl(trailing.reverse, Nil)
      else 
        this
    
    def head: T = mirror.leading.head
    
    def tail: QueueImpl[T] = {
        val q = mirror 
        new QueueImpl(q.leading.tail, q.trailing)
      }
    def enqueue(x: T) = 
      new QueueImpl(leading, x :: trailing)
    
    override def toString = "[" + leading + " , " + trailing + "]"
  }
}

/**
 * Covariance test
 */
//class StrangeIntQueue extends QueueHide[Int]  {
//  override def enqueue(x: Int) = {
//    println(math.sqrt(x))
//     super.enqueue(x)
//  }
//}




object QueueHiddenClass extends App {
  import QueueHide._
  
  /**
   * In this implementation, QueueHide is a trait .. not a type. 
   * Queue is not a trype because it takes a type parameter.
   * 
   */
//  def doesNotCompile(q: QueueHide) = {}
  def doesCompile(q: QueueHide[AnyRef]) = {}
  
  
  val q = QueueHide("ab","b","c")
  p(q)
  val q2 = q enqueue "blog"
  p("q enqueue \"blog\"" , q2)
  p("q2 head" , q2 head)
  
  /** test covariance **/
//  val x:QueueHide[Any] = new StrangeIntQueue
  
}