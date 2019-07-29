package org.geo.pisreview.chapter19
import org.geo.pisreview.Utilities._

/**
 * Example of a private constructor in Scala
 */
class Que_1[+T] private (
    private val leading: List[T] , 
    private val trailing: List[T]
    ) {
  def this() = this(Nil , Nil)
  def this(elems: T*) = this(elems.toList,Nil)
}

/**
 * Companion Object Example with an apply method for Que_1 consruction
 */
object Que_1 {
  // constructs a queue with initial elements `xs' 
  def apply[T](xs: T*) = new Que_1[T](xs.toList, Nil)
}

class Que[+T] (
    private val leading: List[T] , 
    private val trailing: List[T] 
    ) {
  private def mirror = 
    if ( leading.isEmpty )
      new Que(trailing.reverse,Nil)
    else
      this
      
  def head = mirror.leading.head
  
  def tail = {
      val q = mirror 
      new Que ( q.leading.tail, q.trailing )
    }
  /**
   * In order to use the covariant type annotation, Que[+T] , you 
   * need give the enqueue a type annotation also with a lower bound.
   * [U >: T] where T is the lower bound. The type parameter of the 
   * method becomes U. This simply amounts to allowing the method to 
   * take subsets of the original Type annotation. 
   */
  def enqueue[U >: T](x: U) = 
    new Que[U](leading, x :: trailing)
  
  override def toString = "[" + leading + "," + trailing + "]"
}


class SlowAppendQueue[T](elems: List[T]) { // not efficient 
  def head = elems.head
  def tail = new SlowAppendQueue(elems.tail)
  def enqueue(x: T) = new SlowAppendQueue(elems ::: List(x))
}

class SlowHeadQueue[T](smele: List[T]) { // Not efficient
  // smele is elems reversed 
  def head = smele.last
  /** init returns everything except the last element **/
  def tail = new SlowHeadQueue(smele.init)
  def enqueue(x: T) = new SlowHeadQueue(x :: smele )
}
object Chapter19 extends App {

  val q1 = new SlowHeadQueue(List('a' , 'p' ,'p' , 'l' , 'e').reverse)
  p(q1.head)
  
  val q2 = new Que[String](Nil, Nil)
  var q3 = q2.enqueue("bob")
  p(q3)
  q3 = q3.enqueue("harry")
  p(q3)
  p("q3 head", q3 head)
  /**
   * accessing a Queue with a private constructor 
   */
  //  new Que_1(List(1,2) , List(3) )  NOT VISIBLE 
  /** with factory apply method in apply method **/
  val q4 = Que_1[String]("a","b","c")
  
}