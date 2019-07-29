package org.geo.pisreview.chapter12

import scala.collection.mutable.ArrayBuffer

import org.geo.pisreview.Utilities._

abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}

trait Incrementing extends IntQueue {
  p("ctr of Incrementing called")
  abstract override def put(x: Int) = {
    p("Incrementing called",x)
    super.put(x + 1)
  }
}

trait Filtering extends IntQueue {
  p("ctr of Filtering")
  abstract override def put(x: Int) = {
    p("Filtering called",x)
    if ( x >= 0) super.put(x)
  }
}
/**
 * because the trait extends a superclass
 * you can only mix it in with a class that
 * also extends IntQueue
 *
 */
trait Doubling extends IntQueue {
  p("ctr of Doubling")
  abstract override def put(x: Int) = {
    p("calling super.put in Doubling", x)
    super.put(2 * x)
  }
}
/**
 * scala.collection.mutable.ArrayBuffer
 */
class BasicIntQueue extends IntQueue {
  p("ctr of BasicIntQueue called")
  private val buf = new ArrayBuffer[Int]

  def get() = {
    p("BasicIntQueue get called:" + getClass.getName)
    buf.remove(0)
  }
  def put(x: Int) = {
    p("BasicIntQueue put called", x)
    /**
     * buf += x appends to beginning of array
     */
    buf += x
    p("BasicIntQueue buf after" + buf)
  }
}

class MyQueue extends BasicIntQueue with Doubling {
  p("MyQueue Constructor")
}

object Main2 extends App {
  def test_1 {
    val queue = new BasicIntQueue
    queue.put(10)
    queue.put(3)
    p("queue.get()", queue.get())
    p("queue.get()", queue.get())
  }
  
  def test_2 {
     /**
   * THis appears to work as follows:
   * 1. The trait's put is called first; hence calleing 
   * abstract override def put(x: Int)
   * 2. next the super.put() is called inside the Doubling trait.
   * 3. the super.put appears to just be the adjacent put happening here.
   */
  val queue = new MyQueue 
  queue.put(10)
  val x = queue.get()
  val queue2 = new BasicIntQueue with Doubling
  queue2.put(6)
  val x2 = queue2.get()
  }
  
  /**
   * Filter out negative numbers and add 1 to all
   * that you keep
   * Note:
   * when the declaration happens, constructors are called 
   * in a left to right order:
   * BasicIntQueue
   * Incrementing
   * Filtering
   * 
   * when the call to the overriden put method, the order of 
   * calls is reveresed, going from right to left:
   * Filtering
   * Incrementing
   * BasicIntQueue.
   */
  val queue = ( new BasicIntQueue with Incrementing with Filtering )
  
  queue.put(-1)
  queue.put(0)
  queue.put(1)
  queue.get()
  queue.get()
 
}