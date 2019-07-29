package org.geo.pisreview.chapter17

import scala.collection.immutable.TreeSet
//import scala.collection.immutable.TreeMap
import org.geo.pisreview.Utilities._

import scala.collection.mutable.TreeMap

object SortedSetsAndMaps extends App {
  
  val ts:TreeSet[Int] = TreeSet[Int](9,3,1,8,0,2,7,4,6,5)
  
  
  val cs:TreeSet[Char] = TreeSet[Char]('f' , 'u' , 'n') 
  
  def test_1 { 
  for ( node <- ts ) {
    println(node)
  }
  
  for ( node <- cs ) println(node)
  
  /** TreeMap **/
  var tm:TreeMap[Int,Char] = TreeMap(3 -> 'x' , 1 -> 'x' , 4 -> 'x' )
  for ( (k,v) <- tm ) {
    println("k = " + k + " , v=" + v)
  }
  for ( m <- tm ) {
    println(m._1 + ":" + m._2)
  }
  println("====================")
  for ( node <- tm.tail ) println(node)
  val tmh = tm
  tm += (2 -> 'x')
  for ( node <- tm ) println(node)
  p("tm eq tmh" , tm eq tmh)
  }
  def test_2 {
  /** immutable set does not support += **/
  /** if you make it a var, scala add syntatic sugar to make it work **/
  var people = Set("Nancy" , "Jane" ) 
  p("people" , people)
  val peopleh = people 
  people += "Bob" 
  p("people" , people)
  p("people eq peopleh" , people eq peopleh) 
  }
  
  /** using immutable collections, note the "var" allows for the use of += **/
  var capital = Map("Us" -> "Washington" , "France" -> "Paris" )
  capital += ("Japan" -> "Tokyo" ) 
  p("capital(\"France\")", capital("France"))
  /** now bring in mutable Map **/
  import scala.collection.mutable.Map 
  var capital2 = Map("Us" -> "Washington" , "France" -> "Paris" )
  capital2 += ("Japan" -> "Tokyo")
  p("capital2(\"France\")" , capital2("France"))
  
  /** syntactic treatment works on any kind of value **/
  var roughlyPi = 3.0 
  roughlyPi += 0.1 
  /** note if it is a val that wiil not work! **/
//  val aPi = 3.0 
//  aPi += .1
  
}