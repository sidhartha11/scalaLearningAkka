package org.geo.pisreview.chapter11

import org.geo.pisreview.Utilities._


object Chapter11 {
  
  class Dollars ( val amount: Int) extends AnyVal {
    override def toString() = "$" + amount
  }
  def error(message: String): Nothing = 
    throw new RuntimeException(message)
  
  def divide(x: Int, y: Int): Int = 
    if ( y != 0 ) x / y
    else error("can't divide by zero")
  
  def main(args: Array[String]): Unit = {
    val p2 = "hello"
    p("p2=" , p2)
    p("p2.##",p2.##)
    
    val dollars = new Dollars(10)
    p(dollars)
  }
}