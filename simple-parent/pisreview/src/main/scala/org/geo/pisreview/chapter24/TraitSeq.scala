package org.geo.pisreview.chapter24
import org.geo.pisreview.Utilities._
import ForSeqs._

import scala.collection.mutable

object TraitSeq extends App {
  def palindrome ( str: String ): Boolean = {
  def palin(str: String, n: Int): Boolean = {
    n match {
      case 0 => true
      case 1 => if (str.head == str.last)
        true
      else
        false
      case _ => if (str.head == str.last)
        palin(str.tail.init, n - 1)
      else
        false
    }
  }
  if ( str.isEmpty() ) true
  else
    palin(str,str.length()/2)
  }
  
  def palindrome2 ( str: String ): Boolean = {
  def palin(str: String, n: Int): Boolean = {
    n match {
      case 0 => true
      case _ => if (str.head == str.last)
        palin(str.tail.init, n - 1)
      else
        false
    }
  }
  if ( str.isEmpty() ) true
  else
    palin(str,str.length()/2)
  }
  /**
   * Recursive palindrome function.
   * I was curious about how to formulate  a simple 
   * palindrome function using recursion.  As you know,
   * a palindrome is a string that is identical to the 
   * reverse of itself:
   * aaa is a palindrom
   * abba is a palindrom.
   * xy1yx is a palindrom. 
   * And so on and so on. 
   * 
   * So .. to expand upon this idea. How can you determine if
   * the elements of a list represent a palindrom ? 
   * For example:
   * var l = List("a" , "big" , "brown" , "fox" , "brown" , "big" , "a")
   * Would be considered a palindrom because the reverse of the list would 
   * equal the list itself. 
   * So here a function that will determine if a list ( or String ) is 
   * a palindrom. 
   */
  def palindrome3[T] ( str: Traversable[T] ): Boolean = {
  def palin[T](str: Traversable[T], n: Int): Boolean = {
    n match {
      case 0 => true
      case _ => if (str.head == str.last)
        palin(str.tail.init, n - 1)
      else
        false
    }
  }
  if ( str.isEmpty ) true
  else
    palin(str,str.size/2)
  }

  def example_1 { 

  /**
   * Indexing and length
   */
  /**
   * xs(i)
   * or written xs apply i , the element of xs at index i
   */
  p(xs)
  p("xs(0)", xs(0))
  /**
   * xs isDefinedAt i
   * Tests wheter i is contained in xs.indices
   */
  p("xs isDefinedAt 9", xs isDefinedAt 9)
  /*
   * xs.length
   * The length of the sequence ( same as size )
   */
  p("xs.length", xs.length)
  /**
   * xs.lengthCompare ys
   * Retrns -1 if xs is shorter than ys , +1 if it is longer and 0 is
   * they are the same length
   */
  val b = xs.lengthCompare(4)
  p(b)
  /**
   * xs.indices
   * The index range of xs, extending from 0 to len-1
   */
  p("xs.indieces", xs.indices)
  /**
   * Index Search
   */
  /**
   * xs indexOf x
   */
  p("xs indexOf 4", xs indexOf 4)
  /**
   * xs lastIndexOf x
   */
  p(xsMixed)
  p("xsMixed lastIndexOf 5", xsMixed lastIndexOf 5)
  val start = Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3)
  val slc = Seq(4, 5, 6)
  val slc2 = Seq(1, 2, 3)
  /**
   * xs indexOfSlice ys
   * The first index of xs such that successive elements starting from that
   * index for the sequence ys
   */
  p("start", start)
  p("slc", slc)
  p("start indexOfSlice slc", start indexOfSlice slc)
  /**
   * xs lastIndexOfSlice ys
   */
  p("start", start)
  p("slc2", slc2)
  p("start lastIndexOfSlice slc2", start lastIndexOfSlice slc2)
  /**
   * xs indexWhere p
   * The index of the first element in xs that satisfies p
   */
  p("start indexWhere ( _ == 3 )", start indexWhere (_ == 3))
  /**
   * xs segmentLength(p,i)
   * The length of the lonest uninterrupted segment of elements
   * in xs, starting with xs(i), that all satisfy the predicate p
   */
  p("start segmentLength(e => e <= 4,3)", start segmentLength (e => e <= 4, 3))
  /**
   * xs prefixLength p
   * The lenth of hte longest prefix of elements in xs that all
   * satisfy predicate p
   */
  p("start prefixLength ( _ <= 4 )", start prefixLength (_ <= 4))
  /**
   * Additions
   */
//  p("palin(\"strxrts\" ,\"strxrts\"/2)", palin("strxrts", "strxrts".length() / 2))
//  p("palin(\"sbrxrts\" ,\"sbrxrts\"/2)", palin("sbrxrts", "sbrxrts".length() / 2))
//  p("palin(\"sbrrts\" ,\"sbrrts\"/2)", palin("sbrrts", "sbrrts".length() / 2))
//  p("palin(ab)", palin("ab", "ab".length / 2))
//  p("palin(aa)", palin("aa", "aa".length / 2))
//  p("palin(aaa)", palin("aaa", "aaa".length / 2))
//  p("palin(aba)", palin("aba", "aba".length / 2))
//  p("palin(a)", palin("a", "a".length / 2))
  
  p("palindrome2(\"strxrts\" ", palindrome2("strxrts"))
  p("palindrome2(\"sbrxrts\" )", palindrome2("sbrxrts"))
  p("palindrome2(\"sbrrts\" ", palindrome2("sbrrts" ))
  p("palindrome2(ab)", palindrome2("ab" ))
  p("palindrome2(aa)", palindrome2("aa" ))
  p("palindrome2(aaa)", palindrome2("aaa" ))
  p("palindrome2(aba)", palindrome2("aba" ))
  p("palindrome2(a)", palindrome2("a" ))
  p("=================")
  p(palindrome3(Traversable("a" , "a" , "a")))
  p(palindrome3("aba".toTraversable))
  var l = List("a" , "big" , "brown" , "fox" , "brown" , "big" , "a")
  p(palindrome3(l))
  l = List("a" , "big" , "browv" ,  "brown" , "big" , "a")
  p(palindrome3(l))
  }
  
  /**
   * Additions
   */
  /**
   * x += xs 
   * A new sequence consisting of x prepended to xs
   */
  p(" a +: xs ", "a" +: xs )
  var a = "a" +: xs
  cof("a" +: xs)
  /**
   * xs :+ x 
   * A new sequence that consists of x appended to xs 
   */
  p("xs :+ a" , xs :+ "a" )
  /**
   * xs padTo ( len , x ) 
   * The sequence resulting from appending the value x to xs until 
   * length len is reahed 
   */
  a = xs padTo ( 10 , 'x')
  p("xs padTo ( 10 , 'x')" , xs padTo ( 10 , 'x'))
  /**
   * UPDATES
   */
  /**
   * xs patch ( i , ys , r)
   * The sequence resulting from replacing r elements of xs starting with 
   * i by the patch ys.
   */
  p("xsMixed" , xsMixed)
  p("ys" , ys)
  p("xsMixed patch ( 3 , ys , 3)" , xsMixed patch ( 3 , ys , 3))
  /**
   * xs updated(i,x) 
   * A copy of xs with the element at index i replace by x
   */
  p("xs updated(0,191)" , xs updated(0,191))
  
  /**
   * xs(i) = x  
   * or written out , xs.update(i,x) , only available for mutable 
   * Sequence 
   */
  val mseq = mutable.Seq(1,2,3,4)
  p("mseq(0) = 20" , (mseq, mseq(0) = 20) )
  /**
   * Sorting
   */
  /*
   * xs.sortd 
   * A new sequence obtained by sorting the elements of xs using the 
   * standard ordering of the element type of xs 
   */
  p("xsMixed.sorted" , xsMixed.sorted)
  /**
   * xs sortWith lessThan 
   */
  p("xsMixed sortWith lessThan" , xsMixed sortWith( (x,y) => x < y))
  /**
   * xs sortBy f
   */
  p("xsMixed sortBy ( _ ))" ,
      
  xsMixed sortBy ( p => p ))
  /**
   * REVERSALS 
   */
  /**
   * xs.reverse
   * a sequence with the elements of xs in reverse order 
   */
  p("xs.reverse" , xs.reverse)
  /**
   * xs.reverseIterator 
   * An iterator yielding all the elements of xs in reverse order.
   */
  val ritr = xs.reverseIterator 
  p("ritr" , ritr)
  while ( ritr.hasNext ) {
    p("ritr.next" , ritr.next)
  }
  /**
   * xs reverseMap f 
   * A sequence obtained by mapping f over the elemrnts of xs i 
   * reverse order 
   */
  val xsRev = xs reverseMap ( x => x * x)
  p("xs reverseMap ( x => x * x )" , xsRev)
  /**
   * Comparisons
   */
  /**
   * xs startsWith ys 
   * Tests whether xs starts with sequence ys 
   */
  p("xs startsWith Seq(1,2,3))" , xs startsWith Seq(1,2,3))
  /**
   * xs endsWith ys 
   * Tests whether xs ends with sequence ys 
   */
  p("xs endsWith Seq(567)" , xs endsWith Seq(5,6,7))
  p("xs endsWith Seq(1,2,3)" , xs endsWith Seq(1,2,3))
  p("xs endsWith Seq(2,3)" , xs endsWith Seq(2,3))
  p("xs endsWith Seq(2,3,4)" , xs endsWith Seq(2,3,4))  
  /**
   * xs contains x
   * Test whether xs has an element equal to x
   */
  p("xs contains 1" , xs contains 1)
  /**
   * xs containsSlice ys
   * Tests where xs ha a contiguous subsequence equal to ys
   */
  p("xsMixed containsSlice Seq(5,6,7)" , xsMixed containsSlice Seq(5,6,7))
  p("xsMixed containsSlice Seq(3,4,5)" , xsMixed containsSlice Seq(3,4,5))
  /**
   * (xs corresponds ys)(p) 
   * Tests whether corresponding elements of xs and ys satisfy 
   * the binary predicate p 
   */
  val list_1 = Seq(1,2,3,4) 
  val list_2 = for ( x <- list_1 ) yield x * x
  p("list_1" , list_1)
  p("list_2" , list_2)
  p("list_1 corresponds list_2 ((x,y) => y = x * x)" , 
      (list_1 corresponds list_2) ( ( x, y ) => y == x * x ))
  /**
   * Multiset 
   * Operations
   */
      /**
       * xs intersect ys 
       * The multi-set intersection of sequences xs and ys that 
       * preserves the order of elements in xs 
       */
      p("xs" , xs)
      p("ys" , ys)
      p("zs" , zs)
      p("xs intersect ys" , xs intersect ys)
      p("xs interset zs" , xs intersect zs)
      p("zs intersect xs" , zs intersect xs)
      /**
       * xs diff ys
       * The multi-set difference of sequences xs and ys that preserves
       * the older of elements in xs 
       * ( this means the elements in xs that are not in zs ) 
       */
      p("xs diff zs" , xs diff zs)
      p("zs diff xs" , zs diff xs)
      /**
       * xs union ys 
       * The multiset union; same as xs ++ ys 
       */
      p("xs union zs" , xs union zs)
      p("zs union xs" , zs union xs)
      /**
       * Note when comparing union for equality, order of elements must
       * also be the same 
       */
      p("(xs union zs) == (xs ++ zs)" , (xs union zs) == (xs ++ zs))
      p("(xs union zs) == (zs ++ xs)" , (xs union zs) == (zs ++ xs))
      /**
       * xs.distinct
       * A subsequence of xs that contains no duplicated element.
       */
      p("xsMixed" , xsMixed)
      p("xsMixed distinct" , xsMixed distinct)
  
  
  
  
  
  
  
  
}