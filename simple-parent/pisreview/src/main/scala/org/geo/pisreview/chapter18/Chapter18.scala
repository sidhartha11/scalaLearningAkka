package org.geo.pisreview.chapter18

import org.geo.pisreview.Utilities._
import java.util.concurrent.ThreadLocalRandom

class BankAccount {
  private var bal: Int = 0

  def balance: Int = bal

  def deposit(amount: Int) = {
    require(amount > 0)
    bal += amount
  }

  def withdraw(amount: Int): Boolean = {
    if (amount > bal) false
    else {
      bal -= amount
      true
    }
  }
}

class Keyed {
  def computeKey: Int = {
    p("generating key")
    Thread.sleep(2000)
    ThreadLocalRandom.current().nextInt()
  }
  //...
}

class MemoKeyed extends Keyed {
  private var keyCache: Option[Int] = None
  override def computeKey: Int = {
    if (!keyCache.isDefined) keyCache = Some(super.computeKey)
    keyCache.get
  }

}
class Timep {
  var hour = 12
  var minute = 0
}
class Times {
  private[this] var h = 12
  private[this] var m = 0

  def hour: Int = h
  def hour_=(x: Int) = {
    h = x
  }

  def minute: Int = m
  def minute_=(x: Int) = {
    m = x
  }
}

class Time {
  private[this] var h = 12
  private[this] var m = 0

  def hour: Int = h
  def hour_=(x: Int) = {
    require(0 <= x && x < 24)
    h = x
  }

  def minute = m
  def minute_=(x: Int) = {
    require(0 <= x && x < 60)
    m = x
  }
}

class Thermometer { 
  /** how to initialize a var with a default value for that type **/
  var celsius: Float = _
  def fahrenheit = celsius * 9 / 5 + 32 
  def fahrenheit_= (f: Float) = {
    celsius = ( f - 32 ) * 5 / 9 
  }
  override def toString = fahrenheit + "F/" + celsius + "C"
}

object Chapter18 extends App {

  def test_1 {
    val account = new BankAccount
    p(account deposit 100)
    p(account withdraw 80)
    p(account withdraw 80)

    val memoKeyed = new MemoKeyed
    var key = memoKeyed.computeKey
    p("key", key)
    key = memoKeyed.computeKey
    p("key", key)
  }
  def test_2 {
    val time = new Times
    p("time.hour", time.hour)
    p("time.hour_= 10", time.hour_=(10))
    val time2 = new Timep
    p(time2.minute)
    p(time2.minute_=(12))
    p(time2.minute)

    val time3 = new Time
    p(time3.minute_=(120))
  }
  
  val t = new Thermometer
  p(t)
  t.celsius = 100
  p(t)
  t.fahrenheit = -40 // this is internally converted to t.fahrenheit_=
  p(t.fahrenheit)
  p(t)
  t.fahrenheit_=(-40)
  p(t)
  

}