package org.geo.pisreview.chapter21

import org.geo.pisreview.Utilities._

import javax.swing.JButton
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import org.geo.pisreview.chapter12.Rational




object Chapter21 extends App {

  case class Rectangle(width: Int , height: Int) 
  
  implicit class RectangleMaker(width: Int) {
    def x(height: Int) = Rectangle(width,height)
  }
  /**
   * First put the ArrowAssoc in this object
   */
  class ArrowAssoc2[A](x: A) {
    p("instantiating ArrowAssoc2 with:" + x)
    def ->[B](y: B): Tuple2[A, B] = Tuple2(x, y)
  }
  class ArrowAssoc3(x: Int) {
    p("instantiating ArrowAssoc2 with:" + x)
    def ->(y: String): Tuple2[Int, String] = Tuple2(x, y)
  }
  implicit def any2ArrowAssoc3(x: Int): ArrowAssoc3 = new ArrowAssoc3(x)

  //  implicit def any2ArrowAssoc2[A](x: A): ArrowAssoc2[A] = {
  //    p("calling any2ArrowAssoc2 with:" + x)
  //    val y = new ArrowAssoc2(x)
  //    p("got a y = " + y )
  //    y
  //  }
  implicit def any2ArrowAssoc2[A](x: A): ArrowAssoc2[A] = new ArrowAssoc2(x)
  //    p("calling any2ArrowAssoc2 with:" + x)
  //    val y =
  //    p("got a y = " + y )
  //    y
  //  }
  /**
   * The arrow assoc example given in PRogramming In Scala is not identical
   * to ArrowAssoc located in the Predef.scala file for scala 2.12.6
   */

  /**
   * method of converting a reciever , int, to add Rationals
   * this is basically acheived by converting the receiver to the
   * correct type so that in then has the correct api to carry out the
   * operation.
   */
  implicit def intToRational(x: Int) = new Rational(x, 1)

  implicit def doubleToInt(x: Double): Int = x.toInt
  implicit def function2ActionListener(f: ActionEvent => Unit) =
    new ActionListener {
      def actionPerformed(event: ActionEvent) = f(event)
    }

  def test_1 {
    val button = new JButton
    button.addActionListener(
      new ActionListener {
        def actionPerformed(event: ActionEvent) = {
          println("pressed!")
        }
      })

    println("button=" + button)

    /**
     * simplier approach
     */
    button.addActionListener( // type mismatch!
      (_: ActionEvent) => println("pressed!"))

    /**
     * Using an implicit example
     * We are passing a function that takes an ActionEvent and
     * executes a println statement ( Unit return )
     */
    button.addActionListener(
      function2ActionListener(
        (_: ActionEvent) => println("pressed!")))
    /**
     * Here we are directly using the implicit feature.
     * we have an implicit function defined in our environment that
     * takes this shape:   ActionEvent => Unit and returns what this
     * addAtionListener actually wants:
     * new ActionListener with a defind actionPerformed function taking
     * and action event.
     */
    button.addActionListener(
      (_: ActionEvent) => println("pressed!"))
  }
  def test_2 {
    val i: Int = 3.5
    p(i)
    val d: Double = 5
    p("double:", d)
  }
  def test_3 {
    val oneHalf = new Rational(1, 2)
    val one = oneHalf + oneHalf
    p("oneHalf", oneHalf)
    p("one", one)
    val threeHalves = oneHalf + 1
    p("threeHalves", threeHalves)
    /**
     * The recievier below , 1 , does not have a + that adds a Rational.
     */
    val notWork = 1 + oneHalf
    p("notWork", notWork)
    val sameAs = intToRational(1) + oneHalf
    p("sameAs", sameAs)
    //  val m = Map(1 -> "one" , any2ArrowAssoc2(2) -> "two" , any2ArrowAssoc2(3) -> "three")
    val m = 1 -> "one"

    p("m = " + m)
 
  }
     val rect = 5 x 4
    p("rect" , rect)
  
}