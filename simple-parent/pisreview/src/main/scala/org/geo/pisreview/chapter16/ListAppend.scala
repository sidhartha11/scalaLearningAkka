package org.geo.pisreview.chapter16
import org.geo.pisreview.Utilities._

object ListAppend extends App {

  /**
   * This algorithm uses the divide and conquor mechanism to append
   * two lists as follows:
   * The first list is broken apart by tesing it for Nil or
   * taking the head and tail portions of the list appending the
   * head to a recursive call of the function passing the tail of the first
   * and the original of the second. Once the first is exhausted and returns Nil
   * in the match expression ... then we return the second list
   * Hence ..
   * x :: x1 :: x2 :: YS
   *
   * @param xs List[T]
   * @param ys List[T]
   * @return List[T]
   */
  def append[T](xs: List[T], ys: List[T]): List[T] =
    xs match {
      case Nil      => ys
      case x :: xs1 => x :: append(xs1, ys)
    }

  /**
   * using divide and conquor method to reverse a list.
   * We match on Nil or ( x :: xs ) 
   * Recursivily calling the function with the tail and doing a list append 
   * the the head as in List(x) 
 * @param xs
 * @return
 */
def reverse[T](xs: List[T]): List[T] = 
    xs match { 
    case Nil => Nil
    case x :: xs => reverse(xs) ::: List(x)
  }
def test_1 {
    val list = append(List(1, 2, 3), List(4, 5, 6))
  p(list)
  /** computing length of list **/
  p("list.length" , list.length)
  
  /** accessing the end of a list: init and last **/
  /**
   * last and init are analogous to head and tail with 
   * last --- taking the last element of a list 
   * init --- returning all elements except the last element 
   * Note:
   * last and init both run in O(n) time whereas head and tail each run 
   * in constant time O(1) 
   */
  val abcde = List('a' , 'b' , 'c' , 'd' , 'e')
  p("abcde" , abcde)
  p("abcde.last"  , abcde.last)
  p("abcde.init" , abcde.init) 
  p("reverse(abcde)" , reverse(abcde))
}

def test_2 {
  /**
 * Prefixes and suffixes: drop , tak and splitAt 
 */
val abcde = List('a','b','c','d','e')
p("abcde", abcde)
p("abcde take 2" , abcde take 2)
p("abcde splitAt 2" , abcde splitAt 2)
p("(abcde take 2 , abcde drop 2)",(abcde take 2, abcde drop 2))
p("abcde drop 2" , abcde drop 2)
p("abcde apply 2" , abcde apply 2)
p("abcde(2)" , abcde(2))
p("(abcde drop 2 head)", abcde drop 2 head )
p("abcde indices" , abcde indices)
p( for ( i <- abcde.indices ) yield i)
}

def test_3 {
  val ll = List(List(1,2) , List(3) , List() , List(4,5))
p("ll" , ll)
p("ll.flatten" , ll.flatten)
val fruit = List("Banana"  , "Apple" , "Pear" )
p("fruit" , fruit)
p("fruit.map(_.toCharArray)" , fruit map (_.toCharArray))
p("fruit map (_.toCharArray) flatten" , fruit map (_.toCharArray) flatten)
p("fruit indices" , fruit indices)
p("(fruit indices) zip fruit" , (fruit indices) zip fruit)
p("fruit zipWithIndex" , fruit zipWithIndex)
p("fruit zipWithIndex unzip" , (fruit zipWithIndex) unzip)
}

val arr2 = new Array[Int](10)
p("arr2.size" , arr2.size)
p("list(1,2,3) copyToArray ( arr2, 3)",List(1,2,3) copyToArray(arr2,3))
p("arr2" , arr2.mkString(":"))
  
}