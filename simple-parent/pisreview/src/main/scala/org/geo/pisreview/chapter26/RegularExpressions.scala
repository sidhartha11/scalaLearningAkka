package org.geo.pisreview.chapter26

/**
 * Import scala's regular expresion library
 */
import scala.util.matching.Regex
import org.geo.pisreview.Utilities._


object RegularExpressions extends App {
  
  val decimal = new Regex("(-)?(\\d+)(\\.\\d*)?")
  val str = "hello my name is 123.56 and the 34.56 is there"
  val strp = "-99.01 hello my name is 123.56 and the 34.56 is there"

  var str2 = decimal findFirstIn str 
  p("str2" , str2)
  val str3 = decimal findAllIn str 
  p("str3" , str3.mkString(","))
  
  val str4 = decimal findPrefixOf strp
  p("str4" , str4)
  
  val input = "for -1.0 to 99 by 3"
  for ( s <- decimal findAllIn input ) println(s)
  
  val decimal(sign, integerpart, decimalpart) = "-1.23"
  p("sign = " + sign + " ,integerpart=" + integerpart + " ,decimalpart=" + decimalpart)
  
  for (decimal(s,i,d) <- decimal findAllIn input ){
    println("sin: %s , integer: %s , decimal %s".format(s,i,d))
  }
  
}