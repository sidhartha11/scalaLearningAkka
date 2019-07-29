package org.geo.pisreview.chapter25
import org.geo.pisreview.Utilities._
import collection.mutable.ArrayBuffer

object Chapter25 extends App {

  val buf = new ArrayBuffer[Int]
  val bldr = buf mapResult (_.toArray)
  cof("buf", buf)
  cof(bldr)
  
  /**
   * Reversing a maps key,value pairs 
   * Where maps definition is:
   * def map[B, That]
   * (f: ((String, Int)) => B)
   * (implicit bf: CanBuildFrom[Map[String, Int], B, That]): That
   */
  val m1 = Map("a" -> 1 , "b" -> 2 )
  val m1Rev = m1 map {
    case (x,y) => (y,x)
  }
  cof("m1"    , m1)
  cof("m1Rev" , m1Rev)
  /**
   * Now convert to a list 
   */
  val m1List = m1 map {
    case (x,y) => y
  }
  /**
   * Results in an immutable list 
   */
  cof("m1List" , m1List)
  /**
   * mapping dynamic types 
   */
  val xs: Iterable[Int] = List(1,2,3)
  cof("xs" , xs)
  val ys = xs map (x => x * x)
  cof("ys" , ys)
  
}