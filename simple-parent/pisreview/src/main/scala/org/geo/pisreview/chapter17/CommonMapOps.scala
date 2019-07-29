package org.geo.pisreview.chapter17

import scala.collection.mutable
import org.geo.pisreview.Utilities._

object CommonMapOps extends App {
  def test_1 {
  /**
   * defined in object Map extends MutableMapFactory 
   * def empty[K, V]: Map[K, V] = new HashMap[K, V]
   * 
   */
  val map = mutable.Map.empty[String,Int]
  p("map" , map)
  map("hello") = 1
  map("there") = 2 
  p("map" , map)
  p("map(\"hello\")" , map("hello"))
  }
  
  def test_2{
     
  val map = countWords("See Spot run! Run, Spot. Run!")
  p(map)
  
  }
  
  def countWords ( text: String ) = {
    val counts = mutable.Map.empty[String, Int] 
    
    for ( rawWord <- text.split("[ !,.]+")) {
      val word = rawWord.toLowerCase()
      val oldCount = 
        if ( counts.contains(word)) counts(word)
        else 0
        /** if word is already in there , then only count is updated **/
        counts += (word -> (oldCount +1))
    }
    counts
  }
 
  def test_3 {
  /**
   * val nums = Map("i" -> 1, "ii" -> 2)	
   * Creates an immutable map 
   * (nums.toString returns Map(i -> 1, ii -> 2))
   */
  var nums = Map("i" -> 1 , "ii" -> 2 )
  val nums_h = nums
  p("nums" , nums)
  /**
   * nums + ("vi" -> 6)	
   * Adds an entry (returns Map(i -> 1, ii -> 2, vi -> 6))
   */
   nums += ("vi" -> 6)
   p("nums" , nums)
   p("nums eq nums_h" , nums eq nums_h)
   /**
    * nums - "ii"	
    * Removes an entry (returns Map(i -> 1))
    */
   nums -= "ii"
   p("nums" , nums)
   p("nums eq nums_h" , nums eq nums_h)
   /**
    * nums ++ List("iii" -> 3, "v" -> 5, "ii" -> 2)	
    * Adds multiple entries (returns Map(i -> 1, ii -> 2, iii -> 3, v -> 5))
    */
   nums ++= List("iii" -> 3 , "v" -> 5, "ii" -> 2 )
   p("nums" , nums)
   p("nums eq nums_h" , nums eq nums_h)
   /**
    * nums -- List("i", "ii")	
    * Removes multiple entries (returns Map())
    */
   nums --= List("i" , "ii")
   p("1 nums" , nums)
   p("1 nums eq nums_h" , nums eq nums_h)  
   /**
    * nums.size	
    * Returns the size of the map (returns 2)
    */
   p("nums.size" , nums.size)
  }
  
   /**
   * val nums = Map("i" -> 1, "ii" -> 2)	
   * Creates an immutable map 
   * (nums.toString returns Map(i -> 1, ii -> 2))
   */
  val nums = Map("i" -> 1 , "ii" -> 2 )
  
  p("nums" , nums)
  /**
   * nums + ("vi" -> 6)	
   * Adds an entry (returns Map(i -> 1, ii -> 2, vi -> 6))
   */
   p("nums + (\"vi\" -> 6)" , nums + ( "vi" -> 6 ))
   p("nums" , nums)
   /**
    * nums - "ii"	
    * Removes an entry (returns Map(i -> 1))
    */
    p("nums - \"ii\"" , nums - "ii")
    p("nums" , nums)
    /**
     * nums ++ List("iii" -> 3, "v" -> 5)	
     * Adds multiple entries 
     * (returns Map(i -> 1, ii -> 2, iii -> 3, v -> 5))
     */
    p("nums ++ List(\"iii\" -> 3 , \"v\" -> 5)", nums ++ List("iii" -> 3 , "v" -> 5))
    p("nums" , nums)
    /**
     * nums -- List("i" , "ii") 
     * Removes multiple entries (returns Map())
     */
    p("nums -- List(\"i\" , \"ii\")" , nums -- List("i" , "ii"))
    p("nums" , nums)
    
    /**
    * nums.size
    */
    p("nums.size", nums.size)
    /**
     * nums.contains("ii")
     * Checks for inclusion (returns true)
     * 
     */
    p("nums.contains(\"ii\")" , nums.contains("ii"))
    /**
     * nums("ii")
     * returns the value specified by the key 
     */
    p("nums(\"ii\")" , nums("ii"))
    /**
     * nums.keys
     * Returns the keys ( returns an Iterable over the strings i and ii
     */
    val po = for ( i <- nums.keys ) yield i
    p("po" , po)
    p("po.head" , po.head)
    po.foreach(println)
    /**
     * nums.keySet
     * Returns the keys as a set ( returns Set(i,ii)
     */
    nums.keySet foreach println
    /**
     * nums.values
     */
    for ( i <- nums.values ) {
      println(i)
    }
    
    /**
     * nums.isEmpty 
     * Indicates whether the map is empty ( returns false )
     */
  p("nums.isEmpty" , nums.isEmpty ) 
  
  /**
   * Create an empty mutable map 
   */
  val words = mutable.Map.empty[String, Int] 
  /**
   * words += ("one" -> 1)
   * Adds an map entry from "one" to 1 
   */
  val keepW = words 
  p("words += (\"one\" -> 1)" , words += ("one" -> 1))
  /**
   * 
   */
  //p(words += ("two" -> 12))
  /**
   * words -= "one"	
   * Removes a map entry, if it exists (words.toString returns Map())
   */
  p("words -= \"one\"", words -= "one")
  /**
   * words ++= List("one" -> 1, "two" -> 2, "three" -> 3)	
   * Adds multiple map entries (words.toString returns 
   * Map(one -> 1, two -> 2, three -> 3))
   * 
   */
  words ++= List("one" -> 1, "two" -> 2 , "three" -> 3 )
  p("words" , words)
  /**
   * words --= List("one", "two")	
   * Removes multiple objects (words.toString returns Map(three -> 3))
   */
  words --= List("one" , "two")
  p("words" , words)
  
    
    
        
    
  
}