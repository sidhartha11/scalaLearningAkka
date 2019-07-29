package org.geo.pisreview.chapter19
import org.geo.pisreview.Utilities._
import scala.collection.mutable

/**
 * Example of mergesort using elements that mix in
 * the ordered trait
 */
object Utils {
  def orderedMergeSort[T <: Ordered[T]](xs: List[T]): List[T] = {

    /**
     * Merge called with two sublist
     */
    def merge(xs: List[T], ys: List[T]): List[T] = {

      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (x < y) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
    }

    /**
     * find the midpoint of the input list
     */
    val n = xs.length / 2
    /**
     * if it == 0 , just return the list
     * otherwise split the list into two halves
     */
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      /**
       * call merge on each half
       */
      merge(orderedMergeSort(ys), orderedMergeSort(zs))
    }
  }
}
/**
 * mixing in the Ordered trait
 */
class Person(val firstName: String, val lastName: String)
  extends Ordered[Person] {

  def compare(that: Person) = {
    val lastNameComparison =
      lastName.compareToIgnoreCase(that.lastName)
    if (lastNameComparison != 0)
      lastNameComparison
    else
      firstName.compareToIgnoreCase(that.firstName)
  }

  override def toString = firstName + " " + lastName

}
object Main {

  def orderTest1 {
    val robert = new Person("Robert", "Jones")
    val sally = new Person("SallyA", "Jones")
    val betty = new Person("Betty", "Jones")
    val l = List(robert, sally, betty)
    p("sally", sally)
    p("robert", robert)
    p("robert < sally", robert < sally)
    p("robert > sally", robert > sally)
    p("orderedMergeSort(" + l + ")", Utils.orderedMergeSort(l))

    val people = List(
      new Person("Larry", "Wall"),
      new Person("Anders", "Hejlsberg"),
      new Person("Guido", "van Rossum"),
      new Person("Alan", "Kay"),
      new Person("Yukihiro", "Matsumoto"))
    p("orderedMergeSort(" + people + ")", Utils.orderedMergeSort(people))

  }

  def convertMap(l: List[Int], n: Int): Map[(Int, Int), Int] = {
    val map = for (l2 <- convertToList(l)) yield {
      // println ( "l2._1 = " + l2._1 + ", l2._2 = " + l2._2 + ", sumval = " + ( n - l2._1) )
      (l2 -> (n - l2._1))
    }
    map.toMap
  }
  def convertToMap(list: List[Int]): Map[String, Int] = {
    ((for (x <- list zipWithIndex) yield x._1 + ":" + x._2) zipWithIndex) toMap
  }
  def convertToList1(list: List[Int]): List[String] = {
    (for (x <- list zipWithIndex) yield x._1 + ":" + x._2)
  }
  def convertToList(list: List[Int]): List[(Int, Int)] = {
    (for (x <- list zipWithIndex) yield x)
  }

  def convertToMapOfList(list: List[Int], n: Int, map: mutable.Map[Int, List[(Int, Int)]]): mutable.Map[Int, List[(Int, Int)]] = {
    list match {
      case Nil => map
      case h :: t => {
        map.get(h) match {
          case None =>
            map += ((n - h) -> List((h, n - h)))
          case Some(x) =>
            map += (h -> ((h, n - h) :: x))
        }
        convertToMapOfList(t, n, map)
      }
    }
  }

  def convertToMapOfList2(list: List[Int], n: Int, map: mutable.Map[Int, List[(Int, Int)]]): mutable.Map[Int, List[(Int, Int)]] = {
    list match {
      case Nil => map
      case h :: t => {
        map.get(n - h) match {
          case None =>
            map += ((n - h) -> List((h, n - h)))
          case Some(x) =>
            map += ((n - h) -> ((h, n - h) :: x))
        }
        convertToMapOfList2(t, n, map)
      }
    }
  }

  def convertToMapOfList3(list: List[Int], n: Int, map: mutable.Map[Int, Int]): mutable.Map[Int, Int] = {
    list match {
      case Nil => map
      case h :: t => {
        map.get(h) match {
          case None =>
            map += ((h) -> (n - h))
          case Some(x) =>
            map += (h -> (n - h))
        }
        convertToMapOfList3(t, n, map)
      }
    }
  }

  def convertToMapOfList4(list: List[Int], n: Int, map: mutable.Map[Int, Int]): List[Option[(Int, Int)]] = {
    val ans: List[Option[(Int, Int)]] = for ((v, i) <- list.zipWithIndex) yield {
      if (map.contains(v)) {
        Some((v, map(v)))
      } else {
        map += ((n - v) -> v)
        None
      }
    }
    ans
  }
  /**
   * Problem: Given a list of integers, write a function that will return a list
   * of all pairs that sum up to some integer n. 
   * For example: 
   * List = List(5, 1, 2, 6, 4, 4, 8, 0, 3, -4, 12)
   * n    = 8
   * Output will be:
   * List((6,2), (4,4), (0,8), (3,5), (12,-4))
   * Calling it as follows:
   * 
   * val m3 = findPairsSummingN(list, n).collect {
   *    case Some(x) => x
   * }
   * println(m3) 
   * 
   */
  def findPairsSummingN(list: List[Int], n: Int, map: mutable.Map[Int, Int] = mutable.Map.empty[Int, Int]): List[Option[(Int, Int)]] = {

    list match {
      case Nil => Nil
      case h :: t =>
        if (map.contains(h))
          Some((h, map(h))) :: findPairsSummingN(t, n, map)
        else {
          map += ((n - h) -> h)
          findPairsSummingN(t, n, map)
        }
    }
  }

  def test_1 {
    p("convertToList(List(3,4,4,6,7,2,1))", convertToList(List(3, 4, 4, 6, 7, 2, 1)))

    p("convertToMap(List(3,4,4,6,7,2,1))", convertToMap(List(3, 4, 4, 6, 7, 2, 1)))

    val l = convertToList(List(3, 4, 4, 6, 7, 2, 1))
    p("l=" + l)
    val n = 8
    for (l2 <- l) {
      println("l2._1 = " + l2._1 + ", l2._2 = " + l2._2 + ", sumval = " + (n - l2._1))
    }

    val map = for (l2 <- l) yield {
      println("l2._1 = " + l2._1 + ", l2._2 = " + l2._2 + ", sumval = " + (n - l2._1))

      (l2 -> (n - l2._1))
    }

    p("convertMap(List(3,4,4,6,7,2,1),8)", convertMap(List(3, 4, 4, 6, 7, 2, 1), 8))

    val convertedMap = convertMap(List(3, 4, 4, 6, 7, 2, 1), 8)
    println("convertedMap = " + convertedMap)
    println("traversing map")
    for (z <- convertedMap) println(z)
    for (list <- List(3, 4, 4, 6, 7, 2, 1)) {
      println(list)
    }
  }

  def test_2 {
    val list = List(1, 2, 6, 4, 4, 8, 0, 3)

    val n = 8
    val m = convertToMapOfList3(list, n, mutable.Map.empty[Int, Int])
    println(m)
    println("list = " + list)
    val answer = for (item <- list) yield {
      if (m.get(n - item) != None) {
        m.get(n - item)
      }
    }
    println("answer=" + answer)
  }

  def test_3 {
    val list = List(5, 1, 2, 6, 4, 8, 0, 3, -4, 12)
    val n = 8
    val m = convertToMapOfList3(list, n, mutable.Map.empty[Int, Int])
    println(m)
    println("list = " + list)
    val answer = for (item <- list) yield {
      /**
       * m(item) contains the number we want to pair up:
       * m(item) == n-item == sum value
       * if the sum value is also a key in the map the we want it
       * if m.contains(sum)  then we get it
       */
      val subtraction = m(item)
      if (m.get(subtraction) != None && m.contains(subtraction)) {

        //       if ( m.contains(m(item))) {
        if (item < subtraction)

          (item, subtraction)
        else
          (subtraction, item)
        //       }

        //(item,m.get(m(item)).get)
        //m.get(n-item)
      } else {
        (-item, -99999)
      }
    }
    println("answer=" + answer.toSet.filter(_._2 != -99999))
  }

  def main(args: Array[String]) {
    val list = List(5, 1, 2, 6, 4, 4, 8, 0, 3, -4, 12)
    val n = 8
    val m = convertToMapOfList3(list, n, mutable.Map.empty[Int, Int])
    println(m)
    println("list = " + list)
    val answer = for (item <- list) yield {
      val subtraction = m(item)
      if (m.get(subtraction) != None && m.contains(subtraction)) {
        if (item < subtraction)

          (item, subtraction)
        else
          (subtraction, item)
      } else {
        (-item, -99999)
      }
    }
    println("answer=" + answer.toSet.filter(_._2 != -99999))

    val m2 = convertToMapOfList4(list, n, mutable.Map.empty[Int, Int]).collect {
      case Some(x) => x
    }
    println(m2)
    val m3 = findPairsSummingN(list, n).collect {
      case Some(x) => x
    }
    println(m3)
  }
}