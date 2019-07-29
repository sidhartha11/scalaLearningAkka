package org.geo.pisreview.chapter20

abstract class AbstractCurrency1 {
  /**
   * Sets the abstract type as being a subclas of AbstractCurrency1
   */
  type Currency2 <: AbstractCurrency1 
  val amount: Long 
  def designation: String
  override def toString = amount + " " + designation
  def + (that: Currency2) : Currency2 = ???
  def * (x: Double): Currency2 = ???
}

abstract class Dollar2 extends AbstractCurrency1 {
  type Currency2 <: Dollar2
  def designation = "USD"
}

abstract class Currency1 {
  val amount: Long
  def designation: String
  override def toString = amount + " " + designation
  def + (that: Currency1): Currency1 = ???
  def * (x: Double): Currency1 = ???
}

abstract class Dollar1 extends Currency1 {
  def designation = "USD"
}

abstract class Euro1 extends Currency1 {
  def designation = "Euro"
}

object RunCurrency1 extends App {
  /**
   * Method of creating a concrete representationof Currency1"
   * new <object> {
   * implementation of anstract elements 
   * }
   */
  new Currency1 {
    val amount = 79L
    def designation = "USD"
  }
  
}