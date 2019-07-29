package org.geo.pisreview.chapter20


/**
 * Abstract type example 
 */
class Food 
abstract class Animal {
  type SuitableFood <: Food
  def eat ( food: SuitableFood ) 
}

class Grass extends Food
class Cow extends Animal {
  type SuitableFood = Grass
  override def eat(food: Grass) = {}
}

class Fish extends Food 

class DogFood extends Food
  class Dog extends Animal {
    type SuitableFood = DogFood
    override def eat(food: DogFood) = {}
  }

//class Grass extends Food 
//class Cow extends Animal {
//  override def eat(food: Grass) = {}  // This won't compile 
//}

/**
 * example of the four abstract types
 * type
 * var
 * val
 * methods
 * 
 */
trait RationalTrait {
  val numerArg: Int
  val denomArg: Int
  require(denomArg != 0)
  private val g = gcd(numerArg, denomArg)
  val numer = numerArg / g
  val denom = denomArg / g 
  /**
   * gcd(6,12)
   * 1. 6 , 12 
   * 2. 12 , 6
   * 3. 6 , 0
   * 4. 6
   */
  private def gcd(a: Int, b: Int): Int = 
    if ( b == 0 ) a else gcd(b , a % b) 
  override def toString = numer + "/" + denom 
}


trait Abstract {
  /** abstract type **/
  type T
  /** abstract method **/
  def transform(x: T): T
  val initial: T
  var current: T
}

class Concrete extends Abstract {
  type T = String 
  def transform(x: String) = x + x 
  val initial = "hi" 
  var current = initial 
}

object twoThirds extends {
  val numerArg = 2 
  val denomArg = 3 
} with RationalTrait 

class RationalClass(n: Int, d: Int) extends {
  val numerArg = n 
  val denomArg = d 
} with RationalTrait {
  def + (that: RationalClass) = new RationalClass(
      numer * that.denom + that.numer * denom,
      denom * that.denom
      )
}

trait LazyRationalTrait {
  val numerArg: Int
  val denomArg: Int
  lazy val numer = numerArg / g 
  lazy val denom = denomArg / g 
  override def toString = numer + "/" + denom
  private lazy val g = {
    require(denomArg != 0)
    gcd(numerArg, denomArg)
  }
  private def gcd(a: Int, b: Int): Int = 
    if ( b == 0 ) a else gcd(b, a % b)
}



object Chapter20 extends App {
  def test_1 {
      val r = new RationalTrait {
    val numerArg = 1 
    val denomArg = 2 
  }
  println("r.numerArg=" +r.numerArg) 
  
  val x = 2 
  val r2 = new RationalTrait {
    val numerArg = 1 * x 
    val denomArg = 2 * x
  }
  }
  
  def test_2 {
  val x = 2 
  val r2 = new  {
    val numerArg = 1 * x 
    val denomArg = 2 * x
  } with RationalTrait 
  println(r2)
  
  println(twoThirds)
  
  /**
   * note that you cannot refer to numerArg with the this 
   * because the this refers to the trait being constructed at this 
   * time , not the object that is being created. 
   */
//  val r3 = new {
//    val numerArg = 1 
//    val denomArg = this.numerArg * 2 
//  } with RationalTrait 
  
  val r3 = new LazyRationalTrait {
    val numerArg = 1 * x 
    val denomArg = 2 * x
  }
  println(r3)
  }
  
  val bessy = new Cow 
  bessy eat (new bessy.SuitableFood) // does not compile for some reason
  
}