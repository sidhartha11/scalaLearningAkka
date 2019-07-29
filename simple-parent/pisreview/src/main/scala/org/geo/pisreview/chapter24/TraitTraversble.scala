package org.geo.pisreview.chapter24


import org.geo.pisreview.Utilities._
import ForTraversable._
import java.util.ArrayDeque
import java.awt.Point
import java.util.Arrays

object TraitTraversble extends App {
  
  /**
   * xs foreach f 
   * Executes function f for every element of xs
   */
  p(xs)
  xs foreach ( x =>  { 
    p(x * x )
  })

  /**
   * xs ++ ys
   * A collection consisteing of the elements fo both  xs and ys.
   * ys is a TraversableOnce collection, i.e. , either a Traversable or 
   * an Iterator 
   */
  val addedUp = xs ++ ys
  p("xs:" + xs)
  p("ys:" + ys)
  p("xs ++ ys", addedUp)
  
  /**
   * xs map f 
   * The collection obtained from applying the function f to 
   * every element in xs
   */
  p(xs map ( Math.pow(_,3)))
  
  /**
   * xs flatMap f
   * The collection obtained from applying the collection-valued
   * function f to every element in xs and concatenating the results
   */
  p( xs flatMap ( x => List(Math.pow(x,3))))
  p( xs map ( x => List(Math.pow(x,3))))
  
  /**
   * xs collect f 
   * The collection obtained from applying the partial function f to 
   * every element in xs for which it is defined and 
   * collecting the results
   */
  val r = xs collect {
    case 1 => 1
  }
  p("r = " + r ) 
  /**
   * CONVERSIONS
   */
  /**
   * xs.toArray    converts the collection to an array
   * xs.toList     converts the Traversable to a list 
   * xs.toIterable converts the Traversable to an iterable
   * xs.toSeq      converts the Traversable to an sequence
   * xs.toIndexedSeq converts the Traversable to an indexed sequence
   * xs.toStream converts the collectionto a stream ( a lazily computed sequence )
   * xs.toSet convertst the collection to a set
   * xs.toMap Converts a collection fo key/value pairs to a map 
   * 
   */
  p("xs.toArray", Arrays.toString(xs.toArray))
  cof(xs.toArray)
  p("xs.toList" , xs.toList)
  cof(xs.toList)
  p("xs.toIterable" , xs.toIterable)
  cof(xs.toIterable)
  p("xs.toSeq" , xs.toSeq)
  cof(xs.toSeq)
  /** toIndexedSeq = Vector **/
  p("xs.toIndexedSeq" , xs.toIndexedSeq)
  cof(xs.toIndexedSeq)
  val aStream = xs.toStream
  p("xs.toStream" , aStream)
  p("aStream.take 10" , aStream take 10 )
  aStream take 10 foreach println
  aStream drop 2 foreach println
  p("xs.toSet" , xs.toSet)
  cof(xs.toSet)
  p("xsPair.toMap" , xsPair.toMap)
  cof(xsPair.toMap)
  
  /** COPYING **/
  /**
   * xs copyToBuffer buf 
   * Copies all elements of the collection to buffer buf.
   * mutable.Buffer will realize an ArrayBuffer 
   */
  import scala.collection.mutable
  val buf = mutable.Buffer[(Int,String)]()
  p("xsPair copyToBuffer buf" , xsPair copyToBuffer buf)
  cof(buf)
  /**
   * xs copyToArray(arr, s , len ) 
   * copies at most len elements of arr , starting at index s.
   */
  val arr:Array[Int] = Array(1,2,3)
  xs copyToArray(arr,0,4)
  println("----")
  p(arr.mkString(","))
  arr.foreach(println)
  /** size info **/
  /**
   * xs.isEmpty
   * Tests whether the collection is empty
   * xs.nonEmpty
   * Tests whether the collection contains elements
   * xs.size
   * The number of elements in the collection
   * xs.hasDefiniteSize
   * True if xs is know to have finite size
   */
  val aStrem = xsPair.toStream
  p("aStrem.hasDefiniteSize" , aStrem.hasDefiniteSize)
  p("xsPair.hasDefiniteSize" , xsPair.hasDefiniteSize) 
  p("xsPair.size" , xsPair.size)
  /**
   * ELEMENT RETRIEVAL
   */
  /**
   * xs.head
   * the first element of the collection( or , some element , if 
   * no order is defined )
   */
  p("xs.head" ,xs.head)
  /**
   * xs.headOption
   * The first element of xs in an option value, or None if xs is 
   * empty 
   */
   p("xsOption.headOption", xsOption.headOption)
   var x= xsOption.collect{
    case Some(e) => e
  }
  println(x)
  val xx = xsOption.map{
    case Some(e) => e
    case _ => 
  }
  println(xx)
  /**
   * xs.last
   * the last element of the collection ( or , some element, if no order
   * is defined.
   */
  p(xsOption)
  p("xsOption.last" , xsOption.last)
  /**
   * xs find p
   * An Option contaiing the first eement in xs that satisfies
   * p, or None if no element qualifies
   */
  val what = 
    xs find ( p => p == 234 ) 
  p(what)
  /** 
   *  SUBCOLLECTIONS
   */
  /**
   * xs tail 
   * the rest of the colletion except xs.head
   */
  p(xs)
  p("xs.tail" , xs tail)
  /**
   * xs init 
   * The rest of the collection except xs.last 
   */
  p(xs)
  p("xs init" , xs init)
  /**
   * xs slice ( from  , to ) 
   */
  val xs2 = xs slice ( 0 , xs.size)
  p(xs)
  p(xs2)
  /**
   * xs take n 
   * A collection consisting of the first n elements of xs ( or 
   * some arbitray n elements, if no order is defined
   */
  p("xs" , xs)
  p("xs take 2" , xs take 2)
  /**
   * xs drop n 
   * The rest of the collection except xs take n )
   */
  p("xs drop 2" , xs drop 2 ) 
  /**
   * xs takeWhile p 
   * The longest prefix of elements in the collection that all 
   * satisfy p
   * 
   */
  p(xs)
  p( xs takeWhile (_ < 100 ))
  p( xs dropWhile ( _ < 100 )) 
  /**
   * xs filter p 
   * THe collection consisting of those elements of xs that satisfy 
   * the predicate p 
   */
  p(xs filter ( _ > 100 ))
  /**
   * xs withFilter p 
   * A non-strict filter of this collection. All operations on the resulting
   * filter will only apply to those eolement of xs for the condition p
   * is true
   */
  val px  = xs.withFilter( _ < 3 ).map(x => x)
  p(px)
  /**
   * xs filterNot p 
   */
  p("xs filterNot ( _ > 100 )" , xs filterNot ( _ > 100 ))
  
  /**
   * SUBDIVISIONS
   */
  /**
   * xs splitAt n
   * Splits xs at a position, giving the pair of collections
   * ( xs take n  xs drop n) 
   */
  p("xs splitAt xs.size/2 " , xs splitAt xs.size/2 )
  /**
   * xs span p 
   * Splits xs according to a predicate, giving the pair of 
   * collections ( xs takeWhile p , xs dropWhile p )
   */
  p("xs span ( _ < 2 )" , xs span ( _ < 2 ) )
  
  /**
   * xs partition p 
   * Splits xs into a pair of collections; one with elements 
   * that satisfy the predicate p, the other with elements that do 
   * not , giving the pair of colletions
   */
  p("xs partition ( _ % 2 == 0 )" , xs partition ( _ % 2 == 0 ))
  
  /**
   * xs groupBy f 
   * Partitions xs into a map of collections according to a 
   * discriminator function f 
   */
  p("xs groupBy ( _ % 2 == 0 )" , xs groupBy ( _ % 2 == 0 ))
  /**
   * ELEMENT CONDITIONS
   */
  /**
   * xs forall p 
   * A boolean indicating whether the predicate p holds for all
   * elements of xs
   */
  p("xs2 forall ( x => x._2 % 2 == 0 )" , xsTriple forall ( x => x._2 % 2 == 0 ))
  
  /**
   * xs exists p
   * A boolean indicating whether the predicate p holds for some element in xs
   */
    p("xsTriple exists ( x => x._2 % 2 == 0 )" , xsTriple exists ( x => x._2 % 2 == 0 ))

    /**
     * xs count p 
     * The number of elements in xs that satisfy the predicate p
     */
    p("xsTriple count ( x => x._2 % 2 == 0)" , 
        xsTriple count ( x => x._2 % 2 == 0 )) 
 //   p( xsTriple filter ( _._2 % 2 == 0 ))
        
        /**
         * FOLDS
         */
        /**
         * ( z /: xs)(op) 
         * fold left operation 
         * ( initialval /: collection ) (operation)
         * Applies binary operation op between successive elements of xs,
         * going left to right, starting with z 
         * 
         */
        p(" ( 0 /: xs )(_ + _ )" , (0 /: xs) ( _ + _ ))
        p("sumNatNumb(50)" , sumNatNumb(50))
//        var xxx = 0 
//        for ( x <- 0 to 50 ) {
//          println(x)
//          xxx += x
//        }
//        println(xxx) 
        /**
         * fold right
         * ( list :\ initialvalue ) (op)
         * Applies binary operation op betwen successive elements of list
         * moving right to left starting with z
         */
        p("( list :bs 0 )(_ * _ )" , (xs :\ 1)(_ * _ )) 
        /**
         * xs.foldLeft(z)(op)
         * Same as : ( z /: xs)(op)
         * xs.foldRight(z)(op) 
         * (xs :\ z)(op)
         */
        p("xs.foldLeft(0)(_ + _)" , xs.foldLeft(0)(_+_))
        p("xs.foldRight(1)(_ * _)" ,xs.foldRight(1)(_ * _))
        /**
         * xs reduceLeft op
         * 
         */
        p("xs reduceLeft (_ + _)" , xs reduceLeft (_ + _))
        /**
         * xs reduceRight op
         */
        p("xs reduceRight ( _ * _)" , xs reduceRight ( _ * _ ) )
        /**
         * SPECIFIC FOLDS
         */
        /**
         * xs.sum
         * The sum of the numeric element values of collection xs
         * xs.product
         * The product of the numeric element values of collection xs
         * xs.min 
         * The minimum fo the ordered element values of collection xs
         * 
         */
        p("xs" , xs)
        p("xs sum", xs sum)
        p("xs product" , xs product)
        p("xs min" , xs min)
        p("xs max" , xs max)
        /**
         * STRINGS
         */
        /**
         * xs addString (b, start,sep,end)
         * 
         */
        val sb = new StringBuilder
        xs addString(sb)
        p("xs addString(sb)" , sb)
        xs addString(sb,"[" , "," , "]")
        p("xs addString(sb,[,,,])", sb)
        /**
         * xs mkString ( start, set, end)
         * 
         */
        p("xs mkString ( start, sep, end )" , xs mkString ("[" , "," , "]"))
        /**
         * xs.stringPrefix
         * The collection name at the beginning of the string returned from 
         * xs toString 
         */
        p("xs.stringPrefix" , xs.stringPrefix)
        /**
         * Views
         */
        /**
         * xs.view
         * Produces a view over xs 
         */
        val view_1 = xs.view
        p("view_1" , view_1)
        p("view_1 foreach println" , view_1 foreach println)
        for ( x <- view_1 ) println ( x ) 
        val view_2 = xs.view(0,1)
        view_2 foreach ( println(_)) 
        
        
  
}