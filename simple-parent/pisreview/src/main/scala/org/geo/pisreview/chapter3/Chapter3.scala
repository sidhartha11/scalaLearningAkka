package org.geo.pisreview.chapter3

import java.util.Arrays
import scala.collection.mutable

object Chapter3 extends App {
  def p ( msg: Any ) {
    println(msg);
  }
  
  def functional_1 {
    def printArgImperative ( args: Array[String]): Unit = {
      var i = 0 
      while ( i < args.length ) {
        println(args(i))
        i += 1
      }
    }
    
    def printArgFunctional ( args: Array[String]): Unit = {
      for ( a <- args ) {
        println(a)
      }
    }
    
    def printArgFunctional2 ( args: Array[String]): Unit = {
      args.toList match {
      case List() => println("====finshed====")
      case h :: t => 
        println(h)
        printArgFunctional2(t.toArray)
      }
    }
    
    def printArgFunctional3(args: Array[String]):String = args.mkString("\n")
    val args:Array[String] = Array.apply("apples","bananas","oranges")
    printArgImperative(args)
    println("======")
    printArgFunctional(args)
    println("======")
    printArgFunctional2(args)
    print(printArgFunctional3(args))
  }
  def map_1 {
    import scala.collection.mutable
    /**
     * Using a mutable.Map
     * -> can be invoked on any Scala object producing a tuple of 
     * two values.
     * e.g. 
     * 1.->("something) will produce a tuple ( 1 , something )
     * m.+= ( tuple)  will update the mutable map
     * To get the n-1
     * To get a particular key of map you just place the key in parens
     * m(n)  which is equivalent to m.apply(n) 
     */
    val treasureMap = mutable.Map[Int,String]()
    treasureMap += ( 1 -> "Go to island.")
    treasureMap += (2 -> "Find big X on ground.")
    treasureMap += (3 -> "Dig.")
    println(treasureMap(2))
  }
  
  def map_2 {
    val romanNumeral = Map ( 
        1 -> "I" , 2 -> "II" , 3 -> "III" , 4 -> "IV" , 5 -> "V"
        )
    val mutableMap = mutable.Map(1 -> 5,2 -> 10)
    println(romanNumeral(4) + ":" + romanNumeral.apply(2))
  }
  def set_2 {
    /**
     * Note:
     * With a mutable set you need not define the variable as a "var"
     * to add to it as you did with immutable sets.
     */
    val movieSet = mutable.Set("hitch" , "Poltergeist")
    movieSet += "Shrek"
    println(movieSet)
    
    import scala.collection.immutable.HashSet
    val hashSet = HashSet("Tomatoes" , "Chilies") 
    println(hashSet + "Coriander")
  }
  def set_1 {
    var jetSet = Set("Boeing" , "Airbus")
    /**
     * use += to append to a Set 
     * for a small set you get a highly customized/optimized
     * version:
     * scala.collection.immutable.Set$Set3
     * 
     * for larger sets you get:
     * scala.collection.immutable.HashSet$HashTrieSet
     * Note That a Set is an unordered list of unique elements.
     * 
     */
    jetSet += "Lear"
    p(jetSet, jetSet.getClass)
    jetSet += "Rocket"
    jetSet += "Missle"
    jetSet += "Lear"
    jetSet = jetSet + "tommaHawk"
    
    p(jetSet, jetSet.getClass)
    p("jetSet.contains(Lear)", jetSet.contains("Lear"))

  }
  
  def tuple_1 {
    val pair = (99, "Luftballons")
    p(pair)
    p(pair._1)
    p(pair._2)
  }
  def step_8 = {
   /** create a list **/
    val oneTwoThree = List(1,2,3)
    p("oneTwoThree:" + oneTwoThree)
    
    val oneTwo = List(1,2)
    val threeFour = List(3,4)
    /**
     * ::: appends one list to the head of the other list 
     */
    val oneTwoThreeFour = oneTwo ::: threeFour 
    println("oneTwo:%s, threeFour:%s, oneTwoThreeFour:%s".
        format(oneTwo, threeFour , oneTwoThreeFour))
    
    val twoThree = List(2,3)
    println(1 :: twoThree)
    println(twoThree.::(1))
    println(1 :: 2 :: 3 :: Nil)
  }
  
  def step_7 = {
    val big = new java.math.BigInteger("12345")
    p(big)
    
    /** initialize an array of length 3 **/
    val greetStrings: Array[String] = new Array[String](3) 
    greetStrings(0) = "Hello"
    greetStrings(1) = ", "
    greetStrings(2) = "world!\n"
    for ( i <- 0 until greetStrings.length ) {
      p(i)
      p(greetStrings(i))
    }
    p("======")
    for ( i <- 0.to(2) ) Console println i
    
    /**
     * greetStrings(1) is really a call to the apply method of the
     * Array class
     */
    p( greetStrings.apply(1))
    greetStrings.update(1,"dork")
    p(greetStrings.apply(1))
    
        /** create 3 element array **/
    val three = new Array[String](3)
    /** initialize the array **/
    three.update(0,"one")
    three.update(1, "two")
    three.update(2, "three")
    /** loop thru **/
    for ( i <- 0 until three.length ) p(three.apply(i))
    /**
     * def apply[T](xs: T*)(implicit evidence$2: ClassTag[T]): Array[T]
     */
    val array2 = Array("zero" , "one" , "two" )
    p(array2.toList)
  }
  
  
  
  functional_1
  
}