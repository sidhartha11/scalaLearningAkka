package org.geo.pisreview.chapter24
import scala.collection.mutable.Set

import org.geo.pisreview.Utilities._

object ForSets {
 
  val xs =
    Set(1,2,3,4,5)
  val ys = 
    Set(3,4,5,6,7)
  val zs = 
    xs ++ ys
    
  def pp(msg: String,  f: => Any ){
    p("id:" + msg)
    p("class:" + f.getClass.getName)
    p("value:" + f)
  }
}