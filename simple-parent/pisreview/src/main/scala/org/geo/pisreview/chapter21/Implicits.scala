package org.geo.pisreview.chapter21
import org.geo.pisreview.Utilities._

object OrderingObject {
  /**
   * A function with an upper bound 
   */
  def maxListOrdering[T](elements: List[T] )
  (ordering: Ordering[T]): T = 
    elements match {
      case List() => 
        throw new IllegalArgumentException("empty list!")
      case List(x) =>
        p("last element:" + x)
        x
      case x :: rest => 
        p("recursing with " + x + " and " + rest )
        val maxRest = maxListOrdering(rest)(ordering)
        p("returning with " + x + " and " + maxRest )
        if ( ordering.gt(x,maxRest)) {
          p(" returned " + x )
          x
        }
        else {
          p(" returned " + maxRest )
          maxRest
        }
    }
    /**
     * Listing 21.3 - A function with an implicit parameter.
     */
    def maxListImpParm[T](elements: List[T]) 
    (implicit ordering: Ordering[T]): T = 
      
      elements match {
      case List() => 
        throw new IllegalArgumentException("empty list!")
      
      case List(x) => 
        p("single element returned " + x ) 
        x
        
      case x :: rest => 
        p("recursing with " + x + " and " + rest )
        val maxRest = maxListImpParm(rest)(ordering)
        p("returning with " + x + " and " + maxRest )
        if ( ordering.gt(x, maxRest)) {
          p("returned " + x ) 
          x
        } else {
          p("returned " + maxRest )
          maxRest
        }
    }
    
    def maxList[T](elements: List[T])
    (implicit ordering: Ordering[T]): T = 
      
      elements match {
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x 
      case x :: rest => 
        val maxRest = maxList(rest)  // ordring left out 
        if ( ordering.gt(x, maxRest)) x  // this ordering is 
        else maxRest                     // still explicit
        
    }
    
    def maxListOrdering2[T](elements: List[T])
    (implicit ordering: Ordering[T]): T =
      elements match {
      case List() => 
        throw new IllegalArgumentException("empty list!")
        
      case List(x) => x
      case x :: rest =>
        val maxRest = maxListOrdering2(rest)
        if ( implicitly[Ordering[T]].gt(x,maxRest)) x
        else maxRest
    }
    
       def maxListOrdering3[T : Ordering](elements: List[T]): T =
      elements match {
      case List() => 
        throw new IllegalArgumentException("empty list!")
        
      case List(x) => x
      case x :: rest =>
        val maxRest = maxListOrdering3(rest)
        if ( implicitly[Ordering[T]].gt(x,maxRest)) x
        else maxRest
    }
}

object JoesPrefs {
  implicit val prompt = new PreferredPrompt("Yes, master> ")
  implicit val drink  = new PreferredDrink("tea")
}
class PreferredPrompt ( val preference: String)
class PreferredDrink  ( val preference: String)

object Greeter {
  def greet(name: String)(implicit prompt: PreferredPrompt, drink: PreferredDrink) = {
    p("Welcome, " + name + ". The system is ready.")
    p("But while you work, ")
    p("why not enjoy a cup of " + drink.preference + "?")
    p(prompt.preference)
  }
}
object Implicits extends App {
  def test_1 {
  import JoesPrefs._
  val bobsPrompt = new PreferredPrompt("relax> ")
  Greeter.greet("joe")
  /**
   * Note the implicit parameter list must be in scope as a single identifier,
   * hence you must import it from the JoesPrefs object
   */
  }
  
  def test_2 { 
  import OrderingObject._
  var i = maxListImpParm(List(1,5,10,3))
  p("i = " + i ) 
  
  var s = maxListImpParm(List("one" , "tow" , "three"))
  p(s) 
  
  i = maxList(List(1,5,10,3))
  p("i = " + i ) 
  
  s = maxList(List("one" , "tow" , "three"))
  p(s) 
  }
  
  def printLength ( seq: Seq[Int]) = println(seq.length)
  implicit def intToRange(i: Int) = 1 to i
  
  implicit def intToDigits(i: Int) = 
    i.toString.toList.map(_.toInt)
    // printLength(12)
}