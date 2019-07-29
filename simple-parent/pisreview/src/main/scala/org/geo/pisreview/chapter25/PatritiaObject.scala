package org.geo.pisreview.chapter25

import collection._
import org.geo.pisreview.Utilities._


/**
 * the main class
 */
class PrefixMap[T]
extends mutable.Map[String, T]
with mutable.MapLike[String, T, PrefixMap[T]] {
  
  var suffixes: immutable.Map[Char, PrefixMap[T]] = Map.empty 
  var value:Option[T] = None
  
  def get(s: String): Option[T] = 
    if (s.isEmpty) value
    else suffixes get (s(0)) flatMap (_.get(s substring 1))
    
  def withPrefix(s: String): PrefixMap[T] =  {
      p("calling withPrefix with %s, suffixes=%s".format(s,suffixes))
    if (s.isEmpty) {
      this
    }
    else {
      val leading = s(0) 
      p("leading set to %s, suffixes set to %s".format(leading,suffixes))
      suffixes get leading match  {
        case None => 
          p("case None")
          suffixes = suffixes + ( leading -> empty)
        case e => 
          p("case %s".format(e))
      }
      p("recursive call, suffixes = %s , leading = %s , s = %s , s substring 1 = %s".
          format(suffixes, leading, s , s substring 1))
      suffixes(leading) withPrefix ( s substring 1)
    }
    }
  
  override def update(s: String , elem: T) =  {
    p("calling update with s:%s and elem:%s".format(s,elem))
    withPrefix(s).value = Some(elem) 
    p("leaving update")
  }
    
  override def remove(s: String): Option[T] = 
    if ( s.isEmpty) {
      val prev = value
      value = None
      prev
    } else {
      suffixes get (s(0)) flatMap (_.remove(s substring 1))
    }
  
  def iterator: Iterator[(String, T)] = 
    (for (v <- value.iterator) yield ("" , v)) ++ 
    (for ((chr, m) <- suffixes.iterator ;
    (s,v) <- m.iterator) yield (chr +: s,v))
    
  def += (kv: (String, T)): this.type = {
    p("calling += with %s".format(kv))
    update (kv._1 , kv._2)
    p("returning %s".format(this))
    this
  }
  
  def -= (s: String): this.type = {
    remove(s)
    this
  }
  
  override def empty = new PrefixMap[T]
}

/**
 * Companion Object
 */
import scala.collection.mutable.{Builder, MapBuilder}
import scala.collection .generic.CanBuildFrom 

object PrefixMap { 
  def empty[T] = new PrefixMap[T]
  
  /**
   * The apply method of the companion object allows for the initialization
   * of an array of key-value pairs. Note function parameter:
   * (String, T)*  
   */
  def apply[T](kvs: (String, T)*): PrefixMap[T] = {
    /**
     * First create an empty PrefixMap[T] 
     */
    val m: PrefixMap[T] = empty 
    /**
     * next use a for comprehension to iterate thru all the 
     * key-value pairs and add them using += to the mutable Map
     */
    for ( kv <- kvs ) {
      p("invoking += with %s".format(kv))
      m += kv 
    }
    /**
     * return the map
     */
    m
  }
  
  def newBuilder[T]: Builder[(String, T) , PrefixMap[T]] = 
    new MapBuilder[String, T, PrefixMap[T]](empty)
    
  implicit def canBuildFrom[T] : CanBuildFrom[PrefixMap[_], (String, T), PrefixMap[T]] = 
    new CanBuildFrom[PrefixMap[_] , (String, T) , PrefixMap[T]] {
    def apply(from: PrefixMap[_]) = newBuilder[T]
    def apply() = newBuilder[T] 
  }
}




object PatritiaObject extends App {

  def example_1 {
  val m = PrefixMap("abc" ->  0
      , "abd" -> 1 
      , "a1" -> 2 
      , "all" -> 3 
      , "xy" -> 4 
      , "azr" ->  10
      , "xx" -> 1 
      , "x29" -> 3
      )
  cof("m" , m)
  
  val m1 = m withPrefix "a"
  cof("m1" , m1)
  
  val x = m withPrefix "x" 
  cof("x" , x )
  }
  
  val m = PrefixMap("abcdebf" -> 0  , "abd" -> 1) 
  cof("m" , m ) 
  
}