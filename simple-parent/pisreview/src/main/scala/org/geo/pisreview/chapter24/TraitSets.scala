package org.geo.pisreview.chapter24

import scala.collection.mutable.Set
import org.geo.pisreview.Utilities._
import ForSets._
object TraitSets extends App {
  /**
   * TESTS
   */
  /**
   * xs contains x 
   * Tests whether x is an element of s
   */
  p("xs", xs)
  p("xs contains 1", xs contains 1)
  /**
   * xs(x)
   * Same as xs contains x
   */
  p("xs(1)" , xs(1))
  /**
   * xs subsetOf ys 
   * Tests whether xs is a subset of ys
   */
  p("ys" , ys)
  p("xs subsetOf ys" , xs subsetOf ys)
  p("zs" , zs)
  p("xs subsetOf zs" , xs subsetOf zs)
  /**
   * Additions
   */
  /**
   * xs + x
   * The set containing all elements of xs as ell as x 
   */
  p("xs + x", xs + "x" )
  /**
   * xs + ( x,y,z) 
   * The set containing all elements of xs as well as the given
   * additional elements
   */
  var  x = 1 ; var  y = 2 ; var   z = 3
  p("xs + ( x , y , z )" , xs + ( x , y , z ))
  /**
   * xs -- ys 
   * The set containing all elements of xs except the elements of ys
   */
  p("xs" , xs)
  p("ys" , ys)
  p("xs -- ys" , xs -- ys)
  /**
   * xs.empty 
   * An empty set of the same class as xs 
   */
  p("xs.getClass.getName", xs.getClass.getName)
  p("xs.empty.getClass.getName" , xs.empty.getClass.getName)
  /**
   * Binary Operations
   */
  /**
   * xs & ys
   * The intersection of xs and ys
   */
  pp("xs", xs)
  pp("ys", ys)
  p("xs & ys" , xs & ys ) 
  /**
   * xs intersect ys 
   * The smae as xs & ys
   */
  p("xs intersect ys" , xs intersect ys)
  /**
   * xs | ys 
   * The union of xs and ys 
   */
  p("xs | ys" , xs | ys)
  /**
   * xs union ys 
   * the same as xs | ys 
   */
  p("xs union ys" , xs union ys)
  /**
   * xs &~ ys 
   * The set difference of xs and ys 
   * ( items in xs that are not in ys ) 
   */
  p("xs &~ ys" , xs &~ ys)
  /**
   * xs diff ys 
   * same as xs &~ ys 
   */
  p("xs diff ys" , xs diff ys)
  
  /**
   *   MUTABLE SET OPERATIONS 
   */
  /**
   * Addiitions
   */
  /**
   * xs += x
   * Adds element x to set xs as a side effect and returns xs itself
   */
  p("xs += x" , xs += 30)
  /**
   * xs += ( x , y , z ) 
   * Adds the given elements to set xs as a side effect and returns xs itself
   * 
   */
  x = 31 ; y = 32 ; z = 33
  p("xs += ( x , y , z )" , xs += ( x , y , z ))
  /**
   * xs ++= ys 
   * add all the elements in ys to xs 
   */
  p("xs ++= ys" , xs ++= ys)
  /**
   * xs add x 
   */
  p("xs add 1000" , (xs , xs add 1000))
  /**
   * REMOVALS 
   */
  /**
   * xs -= x
   * removes element x from set xs as a side effect and returns xs itslef
   */
  p("xs -= 1000" , xs -= 1000)
  /**
   * xs -= ( x , y , z ) 
   * Removes the given elements from set xs as a side effect and returns
   * xs itself
   */
  p("xs -= ( x , y , z )" , xs -= ( x , y , z ))
  /**
   * xs --= ys 
   * removes all elements in ys from set xs and returns xs as 
   * a side effect 
   */
  p("xs --= ys" , xs --= ys)
  p("xs" , xs)
  p("ys" , ys)
  p("x" , x)
  p("xs remove x" , xs remove x)
  /**
   * xs retain p
   * keeps only those elements in xs that satify predicate p
   */
  p("xs retain ( _ = _ )" , xs, ( xs retain ( x => x == x)))
  p("xs" , xs)
  p("xs.clear()" , xs.clear)
  p("xs" , xs)
  /**
   * Update
   */
  /**
   * xs(x) = b 
   * ( or written out, xs.update(x,b) 
   * if b is true, adds x to xs , otherwise removes x from xs 
   */
  p("xs ( 1 ) = true" , xs(1) = true)
  p("xs" , xs)
  /**
   * Cloning 
   */
  /**
   * a new mutable set with the same elements as xs 
   */
  val xs2 = xs.clone
  p("xs.clone" , xs2)

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}