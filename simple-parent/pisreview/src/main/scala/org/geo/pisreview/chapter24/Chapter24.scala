package org.geo.pisreview.chapter24

import org.geo.pisreview.Utilities._
import org.geo.pisreview.chapter20.Color
import scala.collection.SortedSet
import scala.collection.mutable.Buffer
import scala.collection.LinearSeq


object Chapter24 extends App {
  
  def section24_2 {
 
    cof ("1. traversable(1,2,3)", Traversable(1,2,3) )
    cof ( "2. Iterable(x,y,z)" , Iterable("x" , "y" , "z"))
    cof("3. Map(x -> 24 , y => 25 , z -> 26)" , 
        Map("x" -> 24 , "y" -> 25 , "z" -> 26 ))
    cof("4. Set(Color.Red, Color.Green , Color.Blue)" ,
        Set(Color.Red, Color.Green, Color.Blue))
    cof("5. SortedSet(Hello, world)" , SortedSet("Hello" , "world"))
    val x,y,z = 1
    /** note Buffer is only mutable impl is ArrayBuffer  **/
    cof("6. Buffer(x,y,z)" , Buffer(x,y,z))
    /** returns a Vector **/
    /**
     * Traversable/Iterable/Seq/IndexedSeq/Vector 
     */
    cof("7. IndexedSeq(1.0,2.0)" , IndexedSeq(1.0,2.0))
    cof("8. List(1,2,3)" , List(1,2,3))
    val a , b , c = "value"
    cof("9. LinearSeq(a,b,c)" , LinearSeq(a,b,c))
    
  }
  
  /** section 24.2 **/
  section24_2
}