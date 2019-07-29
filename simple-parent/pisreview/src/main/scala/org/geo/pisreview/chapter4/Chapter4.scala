package org.geo.pisreview.chapter4

import org.geo.pisreview.Utilities._

/** class example **/
/**
 * In scala you cannot reasign function parameters because they are val
 */
/** companion class   **/
class ChecksumAccumulator {
  private var sum = 0
  
  def add(b: Byte)= sum += b
  
  def checksum() = ~(sum & 0xFF) + 1
  
  override def toString = "CheckSumAccumulator [sum = %s]".format(sum)
}

/** companion object : note must reside in same file **/
/** companion objects and classes have access to each other's private members **/

import scala.collection.mutable 
object ChecksumAccumulator {
  
  private val cache = mutable.Map.empty[String, Int]
  
  def calculate(s: String): Int = 
    if ( cache.contains(s))
      cache(s)
      else {
        val acc = new ChecksumAccumulator 
        for ( c <- s )
          acc.add(c.toByte)
         val cs = acc.checksum()
         cache += ( s -> cs)
         cs
      }
}

import ChecksumAccumulator.calculate
object Chapter4 {
  def main(args: Array[String]) {

    for ( arg <- args ) 
      println(arg + ":" + calculate(arg))

  }
}