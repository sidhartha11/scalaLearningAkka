package org.geo.pisreview.chapter12

import org.geo.pisreview.Utilities._

class Point ( val x: Int , val y: Int)

class Rectangle(val topLeft: Point , val bottomRight: Point)
extends Rectangular {
 
  // and many more geometric methods .. .
}

abstract class Component extends Rectangular {
 
  // and many more geometric methods .. 
}

trait Rectangular {
  def topLeft: Point 
  def bottomRight: Point
  
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left 
  // and many other geometric methods ... 
}

object Main extends App {
  val rect = new Rectangle(new Point(1,1), new Point(10,10))
  
  p ("rect" , rect ) 
  p ("rect.left" , rect.left ) 
  p ("rect.right" , rect.right ) 
  p ("rect.width" , rect.width ) 
}