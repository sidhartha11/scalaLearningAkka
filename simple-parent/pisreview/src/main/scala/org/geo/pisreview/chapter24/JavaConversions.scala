package org.geo.pisreview.chapter24

/**
 * to use java collections in scala code
 * import JavaConversions object
 * since all java objects are mutable by default
 * you need to use scala mutable collections
 */

import collection.JavaConversions._
import collection.JavaConverters._
import  collection.mutable._
import org.geo.pisreview.Utilities._


object JavaConversions extends App {
  
  val jul: java.util.List[Int] = ArrayBuffer(1,2,3).asJava
  cof("jul" , jul)
  
  val buf: Seq[Int] = jul.asScala
  cof("buf" , buf)
  
  var m: java.util.Map[String,Int] = HashMap("abc" -> 1 , "hello" -> 2).asJava

  cof("m" , m)
}