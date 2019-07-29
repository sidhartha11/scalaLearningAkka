package org.geo.pisreview.chapter16

import org.geo.pisreview.Utilities._

object SortingObject {
  /**
   * Recursive merge sort
   * Asymptopic Time Complexity
   * O(nlogn) 
   * 
   * Merge sort works as follows: 
   * First, if the list has zero or one elements, it is already sorted, 
   * so the list can be returned unchanged. 
   * Longer lists are split into two sub-lists, 
   * each containing about half the elements of the original list. 
   * Each sub-list is sorted by a recursive call to the sort function, 
   * and the resulting two sorted lists are then combined in a merge 
   * operation.
   * Design:
   * Outer function is a multiple parameter list function with allows more 
   * flexibility in how the function is called. Currying is available with multiple
   * list functions. Calling such a function with fewer parameters that the list creates
   * a partical function that can be invoked with the missing parameters. 
   * 
   * parameters to msort:
   * 1. Functional parameter used to compare list elements of the form:
   * (T,T) => Boolean  a function taking two T elements and returning a Boolean
   * after performing some operaton on the two. 
   * 
   * merge part:
   * Mere takes two sorted lists and merges them togehter recursively 
   */
  def msort[T](less: (T,T) => Boolean)(xs: List[T]) : List[T] = {
    /**
     * merge works as follows. 
     * Does a match on the two list to determine if one is empty. That means we can 
     * just return that list since there is nothing left to compare. 
     * If both lists contain elements then append the smallest element to 
     * the beginning of the list being sorted and continue to merge the list 
     * recursively less that one element that was smaller. 
     */
    def merge(xs: List[T] , ys: List[T]): List[T] = {
      (xs, ys) match {
        case (Nil, _) => ys 
        case (_, Nil) => xs 
        case (x :: xs1 , y :: ys1) =>
          if (less(x,y)) x :: merge (xs1,ys)
          else y :: merge(xs, ys1)
      }
    } /** end of merge **/
    /**
     * Determine if the list is greater than 1 item. If it is only one item,
     * it is already sorted so just return that item
     */
    val n = xs.length / 2 
    if ( n == 0 ) xs 
    /**
     * Using function splitAt n to return a tuple containing the 
     * two sub-lists. The split happens like this:
     * splitAt n is equivalent to List take n , list drop n 
     * 
     */
    else {
      val (ys, zs) = xs splitAt n
      /**
       * call merge , passing the two sublists sorted by te msort function.
       * Note the recursion that is going on here: In two places.
       * Inside Merge, the two sorted list are recursively merged together.
       * But the two list are sorted by repeatedly calling the msort function.
       * This has the effect of continually dividing the list until there is only 
       * a simple case left. 
       */
      merge(msort(less)(ys) , msort(less)(zs))
    }
  }
  
  /**
   * main function
   * Note: main does not override anything  
   */
  def main(args: Array[String]) : Unit = {
    
    /**
     * Example of creating a partial function
     */
    val intSort = msort((x: Int, y: Int) => x < y ) _
    val revSortInt = msort((x: Int, y: Int) => x > y ) _
    p(intSort(List(9,3,1,2,8,2,5)))
    p(msort((x: Int,y: Int) => x < y)(List(9,3,1,2,8,2,5)))
    p(revSortInt(List(9,3,1,2,8,2,5)))
    
  }
}