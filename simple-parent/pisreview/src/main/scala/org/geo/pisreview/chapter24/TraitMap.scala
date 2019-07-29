package org.geo.pisreview.chapter24

import scala.collection.mutable.Map
import org.geo.pisreview.Utilities._
import ForMaps._

object TraitMap extends App {
   val cache = Map[String,String]()
  /**
   * misc 
   */
  def f(x: String) = {
    println("taking my time.")
    Thread.sleep(2000)
    x.reverse
  }
  
  def cachedF(s: String) = cache.getOrElseUpdate(s,f(s))
  /** second direct calling version **/
  /**
   * since the second argument to getOrElseUpdate is call by 
   * name, it is not evaluated until it is used. In F2 below, 
   * that is not the case. 
   */
  def cachedF2(arg: String) = cache get arg match {
  case Some(result) => result 
  case None => 
    val result = f(arg)
    cache(arg) = result 
    result
}
  
  def fibrec(i: Int): Int = 
    i match {
    case a if a == 1 || a == 2
    => 1
    case _ => fibrec(i-1) + fibrec(i-2) 
  }
  
  def fibFrom(a: Int, b: Int): Stream[Int] = {
    a #:: fibFrom(b , a + b )
  }
  
  def example_1 {
  /**
   * Lookups
   */
  /**
   * ms get k
   * The value associated with key k in map ms as an option, or 
   * None if not found 
   */
  p("ms" , ms)
  p("ms get 1" , ms get 1)
  /**
   * ms(k)
   * ( or , written out, ms apply k)
   * The value associated with key k in map ms,
   * or a thrown exception if not found
   */
  var v = try {
    p("ms(1)" , ms(1))
  } catch {
    case a: Throwable => p("caught exception:" + a)
    case _ => ms(1)
  }
  p("v" , v)
  
  v = try {
    p("ms(-4)" , ms(-4))
  } catch {
    case a: Throwable => p("caught exception:" + a)
    case _ => ms(1)
  }
  p("v" , v)
  
  /**
   * ms getOrElse(k,d)
   * The value associated with key k in map ms, or the default value d
   * if not found
   */
  var v1 = ms getOrElse(100, -100)
  p("ms getOrElse ( 100 , -100 )" , v1)
  /**
   * ms contains k
   * Test whether ms contains a mapping fo rkey k 
   */
  p("ms contains 3" , ms contains 3 )
  /**
   * ms isDefinedAt k 
   * same as contains
   */
  p("ms isDefinedAt 6" , ms isDefinedAt 6)
  /**
   * Additions and updates
   */
  /**
   * ms + ( k -> v )
   * The map containing all mappings of ms as well as the mapping 
   * k->v from key k to value v 
   */
  var m1 = ms + ( 2 -> "OOPS" )
  p(" ms + ( 2 -> OOPS )" , m1)
  /**
   * ms + ( k -> v , 1 -> w )
   * The map containing all mappings of ms as well as the given
   * key/value pairs 
   */
  p("ms + ( 33 -> \"33\" , 34 -> \"34\" )" , ms + ( 33 -> "33" , 34 -> "34" ))
  p("ms" , ms)
  p("kvs" , kvs)
  p("ms ++ kvs" , ms ++ kvs)
  /**
   * ms updated ( k, v)
   * same as ms + ( k -> v ) 
   */
  p("ms updated ( 1000, \"1000\")" , ms updated(1000,"1000"))
  
  /**
   * Removals
   */
  /**
   * ms - k 
   * The ap containing all mappings of ms except for any mapping of key k
   */
  p("ms - 1" , ms -1)
  /**
   * ms - ( k , l , m)
   * a map containing all mappings of ms except for k ,1, m
   */
  p("ms - ( 1 , 2 , 3)" , ms -(1,2,3))
  /**
   * ms -- ks
   */
  val ks = Set(1,2,3,4)
  p("ks" , ks) 
  p("ms" , ms)
  p("ms -- ks" , ms -- ks)
  
  /**
   * Subcollections
   */
  /**
   * ms.keys
   * An iterable containing each key in ms
   */
  val l = for ( x <- ms.keys ) yield {
    x
  }
  p(l)
  /**
   * ms.keySet
   * A set containing each key in ms 
   */
  p("ms.keySet" , ms.keySet)
  /**
   * ms.values
   * An iterable containing each value associated with a key in ms
   */
  val values = ms.values
  for ( x <- values ) println(x)
  /**
   * ms.valuesIterator 
   * an iterator yielding each value associated with a key in ms
   */
  val itr = ms.valuesIterator 
  while ( itr.hasNext ) {
    p(itr.next())
  }
  /**
   * Transformation:
   */
  /**
   * ms filterKeys p
   * a map view containing only those mappings in ms where the 
   * key satisfies the predicate p
   */
  val newM = ms filterKeys ( _ % 2 == 0)
  p("m filterKeys ( _ % 2 == 0 )" , newM)
  /**
   * ms mapValues f 
   * A map view resulting from applying function f to each value 
   * ssociated with a key in ms 
   */
  val newV = ms mapValues ( v => "+" + v) 
  p("ms mapValues ( v -> + + v)", newV)
  /**
   * Operations in trait mutable.Map
   */
  /**
   * Additions and updates
   */
  /**
   * ms(k) = v 
   * ( or written out as ms.update(k,v) adds the mapping from key k
   * to value v to mams as a side effect , overwritting any previous mapping
   * if k
   * if there is no key mapping, then it will be created. 
   * 
   */
  p("ms" , ms)
  p("ms(1) = z" , ms(1) = "z")
  p("ms(100) = 100" , ms(100) = "100")
  p("ms" , ms)
  /**
   * ms += ( k -> v )
   * adds mapping from key k to value v to map ms as a side effect and 
   * returns ms itself 
   * and if it does not exist, create it. 
   */
  p("ms += ( 1 -> zz)" , ms += ( 1 -> "zz"))
  /**
   * ms += (k ->v , l -> w )
   * same as previous just adds multiple mappings, comma separate
   */
  p("ma += ( 11 -> 11 , 12 -> 12)" , ms += (11 -> "11" , 12 -> "12"))
  /**
   * ms ++= kvs 
   * Adds all the mappings in kvs to ms as a side effect and returns ms
   * itself.
   */
  p("ms" , ms)
  p("kvs" , kvs)
  p("ms ++= kvs" , ms ++= kvs)
  /**
   * ms put ( k,v)
   * Adds mapping from key k to value v and returns any value 
   * previously associated with k as an option
   * NOTE: it is legal to put null value in a map for the value
   * However, null not allowed as a key
   */
  p("ms put ( -9 , nil)" , ms put ( -9 , null))
  p("ms", ms)
  /**
   * ms getOrElseUpdate(k, d)
   * if key k is defined in map ms, returns its associated value.
   * Otherwise, updates ms with the mapping k-> d and returns d
   * 
   */
  p("ms getOrElseUpdate(1,r)" , ms getOrElseUpdate(1,"r"))
  p("ms getOrElseUPdate(-9, -9)" , ms getOrElseUpdate(-9,"-9"))
  p("ms" , ms)
  /**
   * REMOVALS
   */
  /**
   * ms -= k 
   * Removes mapping with key k from ms as a side effect and 
   * returns ms itself
   */
  p("ms -= 1" , ms -= 1)
  /**
   * ms -= ( k , l , m) 
   * removes mappings with the given keys
   */
  p("ms -= ( 8 , 11 , 2 )" , ms -= (8,11,2))
 /**
  * ms remove k
  */
  p("ms remove 1111" , ms remove 1111)
  p("ms remove 5" , ms remove 5 ) 
  p("ms" , ms)
  /**
   * ms retain p
   * keeps only those mappings in ms that have a key satisfying 
   * predicate p
   */
    p("ms retain ((_,_) => _ % 2 == 0)" , ms retain ((k,v) => k % 2 == 0))
    p("ms" , ms)
    /**
     * ms.clear()
     * Removes all mappings from ms 
     */
    p("ms.clear()" , ms.clear())
    /**
     * Transformation and cloning
     */
      ms = Map(
      1 -> "a" ,2 -> "b" , 3 -> "c" , 4 -> "d" ,
      5 -> "e" 
      )
    /**
     * ms transform f
     * transform all associated values in map ms with function f
     */
      p("ms" , ms)
      p("ms transform ( : + _ + : )" , ms transform ((k,v) =>  ":" + v + ":" ))
      p("ms" , ms)
  
  /**
   * ms.clone
   * Returns a new mutable map with the same mappings as ms
   */
      val newMs = ms.clone()
      p("ms.clone = %s".format(newMs))
      
     val cache = Map[String,String]()
     var cvalue = cachedF("abc")
     p("cvalue" , cvalue)
     cvalue = cachedF("abc")
     p("cvalue" , cvalue)
     val fibs = fibFrom(1,1).take(7)
     p("fibs" , fibs)
     p("fibs.toList" , fibs.toList)
  
  
  }
  
  
 (1 to 10).foreach(i => p(fibrec(i)))
  p("fibrec(4)" , fibrec(1))
  
 
}