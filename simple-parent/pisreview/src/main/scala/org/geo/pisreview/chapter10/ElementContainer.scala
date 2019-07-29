package org.geo.pisreview.chapter10

import org.geo.pisreview.Utilities._

import Element._

/**
 * In scala an abstract class is defined by preceding
 * the class with "abstract"
 * Any method without an implementation is considered
 * an abstract method
 * Abstract classes can also have concrete methods as
 * height and width below.
 * Note:
 * height and width are parameterless methods
 */
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length()
  
//  override def toString = "%s [height = %d, width = %d, contents = %s]"
//  .format(getClass.getName , height, width, contents.mkString(","))
  override def toString = contents mkString "\n"
  
  /**
   * Utility Methods 
   */
  
  def above ( that: Element):  Element = {
    val this1 = this widen that.width
    val that1 = that widen this.width
    elem(this1.contents ++ that1.contents)
  }
  
  def besideImperative(that: Element): Element = {
    val contents = new Array[String](this.contents.length)
    p("new Array[String](this.contents.Length)",contents)
    for ( i <- 0 until this.contents.length) {
      contents(i) = this.contents(i) + that.contents(i)
    }
      // new ArrayElement(contents)
      elem(contents)
  }
  
  def beside(that: Element): Element = {
     val this1 = this heighten that.height
     val that1 = that heighten this.height 
     elem ( 
         for ( (line1 , line2) <- this1.contents zip that1.contents)
           yield line1 + line2)
  }
  
  def widen(w: Int): Element = 
    if ( w <= width ) this 
    else {
      val left = elem(' ' , (w - width) / 2 , height )
      val right = elem( ' ' , w - width - left.width, height)
      left beside this beside right 
    }
  
  def heighten(h: Int): Element = 
    if ( h <= height ) this 
    else {
      val top = elem( ' ' , width, (h - height) / 2 )
      val bot = elem( ' ' , width, h - height - top.height)
      top above this above bot 
    }
  
}

/**
 * Factory OBject Element
 */
object Element {
  
  def elem(contents: Array[String]): Element = {
    new ArrayElement(contents)
  }
  
  def elem(chr: Char, width: Int, height: Int): Element =  {
    new UniformElement(chr,width,height)
  }
  
  def elem(line: String): Element = {
    new LineElement(line)
  }
  
//}

/**
 * Avoiding Code-Smell by simply making the Constructor argument
 * a usable field: putting "val" in front of it.
 */
private class ArrayElement(val contents: Array[String]) extends Element {

}

/**
 * Line Element represented by a single string
 * Note: This is also how an subclass invokes its super class
 * Constructor
 */
private class LineElement(s: String) extends Element {
  /**
   * overriding contents in Element
   */
  val contents = Array(s)
  override def width = s.length
  override def height = 1
}

/**
 * Creating an element with a given width and height and filled everywhere
 * with a given character
 */
private class UniformElement(
  ch:                  Char,
  override val width:  Int,
  override val height: Int) extends Element {
  /**
   * given a String you can create multiple occurrences of that String
   * with the Star operator
   */
  private val line = ch.toString * width
  /**
   * override optional here since contents is implemented from
   * the abstract contents in the supercalss
   */
  override def contents = Array.fill(height)(line)
}

private class ArrayElementCodeSmell(conts: Array[String]) extends Element {
  /**
   * contents is abstract in Element.
   * contents is implemented in the subclass ArrayElement
   */
  def contents: Array[String] = conts
}
/**
 * The uniform access principle is just one aspect where Scala treats
 * fields and methods more uniformly than Java
 * Note:
 * In scala you can override a parameterless method in an superclass with a
 * val in the subclass. Of course the val must be of the same type as the
 * method in the superclass. In Scala fields and methods belong to the same
 * name space. Whereas in Java they belong to different namespaces; hence
 * in java you can have a field and a method of the same name but not in Scala.
 */
private class ArrayElementUniform(conts: Array[String]) extends Element {
  val contents: Array[String] = conts
}

}

object ElementRunner extends App {
  def test_1 {
    val ae = elem(Array("*****"))
    println("ae = %s , ae.width = %d , ae.height = %d".format(ae, ae.width, ae.height))
    val e: Element = elem(Array("hello"))
    println("e.getClass.getName=%s".format(e.getClass.getName))
  }

  val e1: Element = elem(Array("hello", "world"))
  val ae: Element = elem("hello")
  val e2: Element = ae
  val e3: Element = elem('x' , 2, 3) 
  p("e1" , e1)
  p("ae" , ae)
  p("e2" , e2)
  p("e3" , e3)
  
  var p2 = elem(Array("ape","waterloo")) above 
  elem(Array("world!"))
  p("p2" , p2)
  println(p2)
  p2= elem(Array("one" , "two")) beside 
  elem(Array("one"))
  p("p2" , p2)
  println(p2)
}