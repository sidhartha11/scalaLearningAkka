package org.geo.pisreview.chapter24
import org.geo.pisreview.Utilities._

object ViewExamples extends App {
  
  /** 
   *  a lazy map implementation
   */
  def lazyMap[T,U](coll: Iterable[T] , f: T => U) = 
    new Iterable[U] {
    def iterator = coll.iterator map f 
  }
  
  val itr = Iterable(1,2,3,4)
  val lazyItr = lazyMap[Int,String](itr, i => "str:" + i.toString())
  p ( lazyItr )
  
  val v = Vector ( 1 to 10: _*)
  p("v" , v)
  val v2 = v map ( _ + 1 ) map ( _ * 2 )
  p("v2" , v2)
  
  val v3 = ( v.view map ( _ + 1 ) map ( _ * 2 )).force
  p("v3" , v3) 
  
  val vv = v.view 
  p("vv" , vv.getClass) 
}