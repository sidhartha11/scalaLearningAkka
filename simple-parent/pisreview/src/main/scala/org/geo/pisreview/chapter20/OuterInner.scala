package org.geo.pisreview.chapter20


class Outer {
  class Inner 
}

object OuterInner extends App {
  
  val o1 = new Outer 
  val o2 = new Outer 
  val o1_inner = new o1.Inner
  
}