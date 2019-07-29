package org.geo.pisreview.chapter30

import org.geo.pisreview.Utilities._
import scala.collection.mutable

object Color extends Enumeration {
  val Red, Orange, Yellow, Green, Blue, Indigo, Violet = Value 
}

//class ColoredPoint (x: Int, y: Int, val color: Color.Value) 
//extends Point(x,y) { // Problem: equals not symmetric 
//  override def equals(other: Any) = other match {
//    case that: ColoredPoint => 
//      this.color == that.color && super.equals(that)
//    case _ => false 
//  }
//  override def toString = "ColoredPoint: [" + super.toString + " ,color=" + color + "]"
//}
/**
 * Making it more general as a possible solution
 * However, this breaks transitivity
 */
//class ColoredPoint (x: Int, y: Int, val color: Color.Value) 
//extends Point(x,y) { // Problem: equals not symmetric 
//  override def equals(other: Any) = other match {
//    case that: ColoredPoint => 
//      this.color == that.color && super.equals(that)
//    case that: Point => 
//      that equals this
//    case _ => false 
//  }
//  override def toString = "ColoredPoint: [" + super.toString + " ,color=" + color + "]"
//}

/**
 * reverting back to the ColoredPoint equal that had previously
 * broken transitivity, however now it is not allowed to compare 
 * points of Colored Point and Point. So transitivity is achieved but 
 * with added strickness.
 * However this does not allow subclasses to be compared using equals.
 * 
 */
//class ColoredPoint (x: Int, y: Int, val color: Color.Value) 
//extends Point(x,y) { // Problem: equals not symmetric 
//  override def equals(other: Any) = other match {
//    case that: ColoredPoint => 
//      this.color == that.color && super.equals(that)
//
//    case _ => false 
//  }
//  override def toString = "ColoredPoint: [" + super.toString + " ,color=" + color + "]"
//}

/**
 * Now allowing subclasses to be compared 
 */
class ColoredPoint (x: Int, y: Int, val color: Color.Value) 
extends Point(x,y) { // Problem: equals not symmetric 
  override def equals(other: Any) = other match {
    case that: ColoredPoint => 
      (that canEqual this) && 
      this.color == that.color && super.equals(that)

    case _ => false 
  }
  override def toString = "ColoredPoint: [" + super.toString + " ,color=" + color + "]"

  override  def canEqual(other: Any) = 
    other.isInstanceOf[ColoredPoint]
    

}

//class Point(val x: Int, val y: Int) {
//  override def toString = 
//    "Point: x=" + x + " ,y=" + y
//    override def equals(other: Any) = other match {
//    case that: Point => this.x == that.x && this.y == that.y 
//    case _ => false 
//  }
//  
//  override def hashCode = (x, y).##
//}

/**
 * Making class Point only compare elements of type Point
 * However this does not allow subclasses to be equal
 */
//class Point(val x: Int, val y: Int) {
//  override def toString = 
//    "Point: x=" + x + " ,y=" + y
//   override def hashCode = (x, y).##   
//   
//   override def equals(other: Any) = other match {
//    case that: Point => 
//      this.x == that.x && this.y == that.y &&
//      this.getClass == that.getClass 
//    case _ => false 
//  }
//  
//
//}

/**
 * Now altered to allow subclasses to be compared
 */
class Point(val x: Int, val y: Int) {
  override def toString = 
    "Point: x=" + x + " ,y=" + y
   override def hashCode = (x, y).##   
   
   override def equals(other: Any) = other match {
    case that: Point => 
      (that canEqual this) && 
      (this.x == that.x)  && (this.y == that.y) 
    case _ => false 
  }
  
  def canEqual(other: Any) = other.isInstanceOf[Point] 

}


object Point  {
  def apply(x: Int, y: Int): Point =
    new Point(x,y)
}

object Chapter30 extends App {
  def example_1 {
  val point1 = Point(4,5)
  prn("point1" , point1)
  val point2 = Point(4,5)
  prn("point2" , point2)
  prn("point1 == point2", point1 == point2)
  prn("point1.equals(point2)" , point1.equals(point2))
  
  val p1, p2 = new Point(1,2)
  val q = new Point(2,3)
  prn("p1 = %s , p2 = %s , q = %s".format(p1,p2,q))
  prn(" p1 equals p2 " , p1 equals p2 )
  prn(" p1 equals q " , p1 equals q )
  /**
   * now lets put the points into a HashSet
   */
  val col1 = mutable.HashSet(p1)
  prn("col1" , col1)
  prn("col1 contains p2" , col1 contains p2)
  
  val p2a: Any = p2
  prn("p2a" , p2a)
  prn("p1 equals p2a" , p1 equals p2a )
  }
  
  def example_2 {
  val p1 = new Point(1,2)
  val cp = new ColoredPoint(1,2,Color.Red)
  prn("p1" , p1) 
  prn("cp" , cp)
  prn("p1 equals cp" , p1 equals cp)
  prn("cp equals p1" , cp equals p1)
  
  val col1 = mutable.HashSet(p1)
  val col2 = mutable.HashSet(cp);
  prn("col1" , col1)
  prn("col2" , col2)
  prn("col1 contains cp" , col1 contains cp)
  // prn("col2 contains p1" , col2 contains p1)
  }
  def example_3 {
  val p = new Point(1,2)
  val redp = new ColoredPoint(1,2,Color.Red)
  val bluep = new ColoredPoint(1,2,Color.Blue)
  val cp = new ColoredPoint(1,2,Color.Red)
  prn(List(cp,p,redp,bluep)) 
  /**
   * using Print equal everyhthing works fine 
   */
  prn("p equals cp" , p equals cp)
  /**
   * using ColoredPrint equals not symmetric
   **/
  prn("cp equals p" , cp equals p)
  /**
   * Here the collected object's equal method's signature is of 
   * type Any ... which will recognize that it is a subset of Point.
   */
  prn(
      "mutable.HashSet[Point](p) contains cp" , 
      mutable.HashSet[Point](p) contains cp)
  /**
   * here we are using the collected objects equal method which does 
   * not recognize objects of type ColoredPoint 
   */
  prn("mutable.HashSet[Point](cp) contains p" , 
      mutable.HashSet[Point](cp) contains p )
  prn ("redp == p" , redp == p)
  prn(" p == bluep" , p == bluep )
  prn ("redp == bluep" , redp == bluep)
  val pAnon = new Point(1,1) {
    override val y = 2
  }
  prn("pAnon" , pAnon)
  prn("p" , p)
  prn("pAnon == p" , pAnon == p)
  prn("p == pAnon" , p == pAnon)
  }
  val p = new Point(1,2)
  prn("p" , p)
  val cp = new ColoredPoint(1,2,Color.Indigo)
  val pAnon = new Point(1,1) {
    override val y = 2 
  }
  prn("pAnon" , pAnon)
  val col1 = List(p)
  prn("col1" , col1)
  prn("col1 contains p" , col1 contains p)
  prn("col1 contains pAnon" , col1 contains pAnon)
 
}