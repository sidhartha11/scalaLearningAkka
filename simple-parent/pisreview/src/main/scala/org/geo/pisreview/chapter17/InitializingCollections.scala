package org.geo.pisreview.chapter17

import org.geo.pisreview.Utilities._
import java.util.Arrays


object InitializingCollections extends App {
  
  def test_1 { 
  val l = List(1,2,3)
  val s = Set('a' , 'b' , 'c' ) 
  import scala.collection.mutable
  val m = mutable.Map("hi" -> 2 , "there" -> 5)
  m += ("see" -> 3)
  
  val a = Array(1.0 , 2.0 , 3.0)
  p(a.getClass().getName)
  
  val stuff = mutable.Set[Any](42)
  stuff += "abracadabra" 
  p(stuff)
  import scala.collection.immutable.TreeSet
  
  val colors = List("blue" , "yellow" , "red" , "green" ) 
  
  val treeSet = TreeSet[String]() ++ colors 
  p("TreeSet[String]() ++ colors" , treeSet)
  val list = treeSet.toList
  p("treeSet.toList" , list)
  p("set.ToList" , stuff.toList)
  val ar:Array[String] = treeSet.toArray
  p("treeSet.toArray" , ar.mkString(","))
  
  /** 
   *  converting between mutable and immutable sets
   *  
   */
  p("treeSet"  ,  treeSet ) 
  val mutaSet = mutable.Set.empty ++= treeSet 
  p("mutable.Set.empty ++= treeSet" , mutaSet )
  val immutaSet = Set.empty ++ mutaSet 
  p("Set.empty ++ mutaSet" , immutaSet)
  
  /**
   * converting between mutable and immutable maps 
   */
  val muta = mutable.Map("i" -> 1 , "ii" -> 2 )
  val immu = Map.empty ++ muta 
  p("muta" , muta)
  p("Map.empty ++ muta" , immu)
  }
  def getWordAndLoc(words: Array[String]): (String,Int) = {
    words.zipWithIndex.max ( ( (x : (String,Int) , y: (String,Int) ) =>
      {
      if ( x._1.length() > y._1.length() ) 1
      else if ( x._1.length() == y._1.length() ) 0
      else -1
      }
      ))
  }
  /**
   * TUPLES 
   */
  val con = Console
  con.println("hello from Console")
  val t1 = (1,"hello" , Console)
  p("(1,\"hello\" , Console)" , t1)
  p("t._1, t._2,t._3" , t1._1 + t1._2 + t1._3 )
  p(getWordAndLoc(Array("hello" , "bob" , "john" , "x")))
  val (word,indx) = getWordAndLoc(Array("hello" , "bob" , "john" , "x"))
  p("word = " + word + ", indx=" + indx)
}