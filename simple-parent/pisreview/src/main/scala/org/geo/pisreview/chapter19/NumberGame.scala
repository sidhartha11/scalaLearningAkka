package org.geo.pisreview.chapter19

import scala.collection.mutable
object NumberGame extends App {
  /**
   * @author George Curington
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
  /**
   *
   * Example of calling the function with a list of integers
   *
   */
  val list = List(5, 1, 2, 6, 4, 4, 8, 0, 3, -4, 12)
  val n = 8
  val m3 = findPairsSummingN(list, n).collect {
    case Some(x) => x
  }
  println(m3)
}