package org.geo.pisreview.chapter16

import org.geo.pisreview.Utilities._

object HigherOrderFunctions extends App {
  val diag3 = (1 :: (0 :: (0 :: Nil))) ::
    (0 :: (1 :: (0 :: Nil))) ::
    (0 :: (0 :: (1 :: Nil))) :: Nil
  val words = List("the", "quick", "brown", "fox")
  val xss = List(
    List("hello", "Mr", "rogers"),
    List(1, 2, 3),
    List('z', 'x', 'y', 'w'))
  def sum(xs: List[Int]): Int = {
    (0 /: xs)(_ + _)
  }

  def product(xs: List[Int]): Int = {
    (1 /: xs)(_ * _)
  }
  def hasZeroRow(m: List[List[Int]]) =
    {
      m exists (row => row forall (_ == 0))
    }

  def concat(xs: List[String]): String = {
    (" " /: xs)(_ + " " + _)
  }
  def concat2(xs: List[String]): String = {
    (xs.head /: xs.tail)(_ + " " + _)
  }
  def test_1 {
    /**
     * map applies a function to each element of a list
     * T => U
     * The operation xs map f takes as operands a list xs of
     * type List[T] and a function f of type T => U.
     * It returns the list that results from applying the
     * function f to each list element in xs.
     *
     */
    p("List(1,2,3) map ( _ + 1 )", List(1, 2, 3) map (_ + 1))
    val words = List("the", "quick", "brown", "fox")
    /** map each string to its length **/
    p("words map ( _.length)", words map (_.length))
    /** reverse a string **/
    /**
     * steps"
     * map each element of words as follows :
     * 1. to a list
     * 2. reverse the list
     * 3. convert back to string
     */
    val revString = words map (_.toList.reverse.mkString)
    p("revString", revString)
  }
  def test_2 {
    val words = List("the", "quick", "brown", "fox")
    /**
     * converts the original list to a list containing lists
     * The --> List(the)
     * quick --> List(quick)
     * brown --> List(brown)
     * resulting in List ( List(the) , List(quick) , List(brown))
     */
    p("words map (_.toList)", words map (_.toList))
    /**
     * first each element is mapped to a list .
     * This List is then flattened into single elements.
     * _.tolist --> List(the) --> t,h,e
     */
    p("words flatMap(_.toList)", words flatMap (_.toList))
  }
  def test_3 {
    List.range(1, 5)
  }
  def test_4 {
    val l =
      List.range(1, 5) flatMap (
        i => List.range(1, i) map (j => (i, j)))

    val l2 =
      for (i <- List.range(1, 5); b <- List.range(1, i)) yield ((i, b))
    p(l)
    p(l2)

    for (i <- (1 until 5)) println(i)
    var sum = 0
    List(1, 2, 3, 4, 5) foreach (sum += _)
    p(sum)
  }
  def test_5 {
    var lf = List(1, 2, 3, 4, 5) filter (_ % 2 == 0)
    p("List(1,2,3,4,5) filter ( _ % 2 == 0 )", lf)

    val w = words filter (_.length == 3)
    p("words filter(_.length ==  3)", w)

    val (l, r) = List(1, 2, 3, 4, 5) partition (_ % 2 == 0)
    p(l, r)

    var op = List(1, 2, 3, 4, 5) find (_ % 2 == 0)
    p(op)
    op = List(1, 2, 3, 4, 5) find (_ <= 0)
    p(op)
  }

  def test_6 {
    /**
     * The takeWhile and dropWhile operators also take a predicate
     * as their right operand. The operation xs takeWhile p
     * takes the longest prefix of list xs such that every element in the prefix
     * satisfies p. Analogously, the operation xs dropWhile p removes the
     * longest prefix from list xs such that every element in the prefix satisfies p.
     * Here are some examples:
     * Here is a better example:
     * We want this: Given a list of elements:
     * List(1,2,3,4,5,6)
     * Return a list in which each element is a list containing the prefixes of
     * the original list.
     *
     */
    /** simple look at takeWhile **/
    val l = List(1, 2, 3, -4, 5)

    /** lets try this with pairs **/
    val list = List(1, 2, 3, -4, 5).zipWithIndex

    val prefixes = for (i <- list.indices) yield {
      val ix = list.takeWhile(_._2 <= i).map(_._1)
      ix
    }
    p(l)
    p("prefixes", prefixes)
    val sufixes = for (i <- list.indices) yield {
      val ix = list.dropWhile(_._2 < i).map(_._1)
      ix
    }
    p("sufixes", sufixes)
    /**
     * Other examples from book
     */
    p("words dropWhile ( _ startsWith t)", words dropWhile (_ startsWith "t"))
  }
  def test_6_1 {
    /**
     * The span method combines takeWhile and dropWhile in one operation,
     * just like splitAt combines take and drop.
     * It returns a pair of two lists, defined by the equality:
     * xs span p    equals    (xs takeWhile p, xs dropWhile p)
     */
    val l = List(1, 2, 3, -4, 5) span (_ > 0)
    p("List(1,2,3,-4,5) span ( _ > 0 )", List(1, 2, 3, -4, 5) span (_ > 0))

    var i = 1; var s = 1
    val n = 100
    while (s <= 120) {
      //    println("i = " + i + " , s = " + s)
      println(s)
      i = i + 1
      s = s + i

    }
  }
  def test_7 {
    p("hasZeroRow(diag3)", hasZeroRow(diag3))
    p("concat(words)", concat(words))
    p("concat2(words)", concat(words))
    /**
     * fold left: (initializer (right leaning: ) list) (op)
     */
    var cw = ("" /: words)(_ + " " + _)
    p("|" + cw + "|")
    /**
     * fold right: ( list on left  (left leaning slash with colon first ) ) (op)
     *
     */
    cw = (words :\ "")(_ + " " + _)
    p("|" + cw + "|")
    /**
     * remove the space for fold right
     * fold right:
     * list comes first  :/ initializer
     *
     * fold left:
     * initiaizer /: list op
     *
     */
    cw = (words.head /: words.tail)(_ + " " + _)
    p("|" + cw + "|")
    cw = (words.init :\ words.last)(_ + " " + _)
    p("|" + cw + "|")
    cw = (words.init :\ words.last)((x, y) => y + " " + x)
    p("|" + cw + "|")
    /**
     * Flatten example
     */
    /** fold right **/
    var cf = (diag3 :\ List[Int]())(_ ::: _)
    p("cf", cf)
    /** fold left **/
    cf = (List[Int]() /: diag3)(_ ::: _)
    p("cf", cf)
  }

  def flattenLeft[T](xss: List[List[T]]): List[T] = {
    (List[T]() /: xss)(_ ::: _)
  }

  def flattenRight[T](xss: List[List[T]]): List[T] = {
    (xss :\ List[T]())(_ ::: _)
  }

  def reversalleft[T](xs: List[T]): List[T] = {
    (List[T]() /: xs)((x, y) => {
      p("y=" + y + ", x=" + x)
      y :: x
    })
  }

  //  def reversalright[T](xs: List[T]): List[T] = {
  //    (xs :\ List[T]())( ( x , y ) => y :: x )
  //  }
  def test_8 {
  p("flattenLeft(xss)", flattenLeft(xss))
  p("reversalleft(diag3)", reversalleft(diag3))
   /** sorting **/
  val l1 = List(1, -3, 4 , 2 , 6 ) sortWith ( _ < _ ) 
  val l2 = words sortWith ( _.length > _.length ) 
  //p(l1); p(l2)
  }
  def test_9 {
  val r1 = List.range(1,5)
  val r2 = List.range(1,9,2)
  val r3 = List.range(9,1,-3)
  
  p("List.range(1,5)", List.range(1,5))
  p("List.range(1,9,2)",List.range(1,9,2))
  p("List.range(9,1,-3)", List.range(9,1,-3))
  }
  def test_10 {
  /**
   * List.tabulate(range)(function)
   */
  val squares = List.tabulate(5)(n => n * n)
  p("squares" , squares)
  val squares2 = List.tabulate(5,3,2)((a,b,c) => {
    p("a = " + a + " , b = " + b + " , c = " + c)
    a * b * c
  }
    ) 
  p("squares2" , squares2)
  }
  
  def test_11 {
  val l1 = List.concat(List('a' , 'b') , List('c') , List('d') )
  p(l1)
  val l2 = List.concat(List() , List('b') , List('c'))
  p(l2)
  val l3 = List.concat() 
  p(l3) 
  val lz = (List(10,20) , List(3,4,5)).zipped
  val lm = lz.map(_ * _)
  p("lz"  , lz )
  p("lm" , lm)
  val l = (List(10,20) , List(3,4,5)).zipped.map(_ * _ )
  p ( "l" , l)
  }
  
  val lz = (List(10,20) , List(3,4,5)).zipped
  val lm = lz.map(_ * _)
  p("lz"  , lz )
  p("lm" , lm)
  
  val x = (List("abc" , "de"), List(3,2)).zipped.forall(_.length == _ ) 
  p("x"  , x ) 
  val x2 = (List("abc" , "de") , List(3,2,3)).zipped.exists(_.length != _)
  p("x2" , x2)
  
}