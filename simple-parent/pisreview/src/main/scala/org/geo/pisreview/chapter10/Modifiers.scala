package org.geo.pisreview.chapter10

class Cat {
  val dangerous = false
}

class Tiger(
  override val dangerous: Boolean,
  private var age:        Int) extends Cat

/**
 * * Class Tiger is shorthand for the following
 * class
 * Note:
 * You can put modifiers like private, protected
 * and override in the parameter list
 */
class Tiger2(param1: Boolean, param2: Int) extends Cat {
  override val dangerous = param1
  private var age = param2
}