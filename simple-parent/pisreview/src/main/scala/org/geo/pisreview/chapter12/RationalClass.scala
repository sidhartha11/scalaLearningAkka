package org.geo.pisreview.chapter12

/**
 * @author Owner
 * <p>
 * class parameters:
 * n: Int numerator
 * d: Int denominator
 * The compiler will create a primary constructor for
 * the two class parameters.
 *
 */
// import org.geo.pisreview.Utilities._
class Rational(n: Int, d: Int)  extends Ordered[Rational]{
  require(d != 0, "Illegal Operand:" + d)

  private val g = gcd(n abs, d abs)
  val numer: Int = n / g
  val denom: Int = d / g
  /** override toString method **/
  override def toString = numer + "/" + denom

  /**
   * auxiliary constructors:
   * Scala uses the "this" keyword to define auxiliary constructors
   * As in java , if you call another constructor , it must be the first
   * call in the class. Also, you are required to call the primary or
   * another constuctor that will eventually call the primary constructor.
   * Note: You are not allowed to put the Class Type following the colon
   * when declaring a constructor.
   * Note: Java Constructors do not have the requirement that you must call
   * another constructor and eventually call the primary constructor.
   */
  def this(n: Int) = {
    this(n, 1)
    println("in auxialiar constructor")

  }
  /**
   * Adding Rationals
   */
  def add(that: Rational): Rational =
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)

  def +(that: Rational): Rational = {
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)
  }
  
  def + (i: Int): Rational = {
    new Rational(numer + i * denom, denom)
  }
  
  def -(that: Rational): Rational = {
    new Rational(
        numer * that.denom - that.numer * denom,
        denom * that.denom
        )
  }
  
  def - ( i: Int): Rational = {
    new Rational(numer - i * denom, denom)
  }
  
  def * (that: Rational): Rational =  {
    new Rational ( 
        numer * that.numer , denom * that.denom
        )
  }
  
  def * (i: Int): Rational = {
    new Rational(numer * i, denom)
  }
  
  def / (that: Rational): Rational =  {
    new Rational(numer * that.denom, denom * that.numer)
  }
  
  def / (i: Int): Rational = {
    new Rational(numer, denom * i)
  }

  /**
   * lessThan
   * @param that input Rational number to compare less than against
   * e.g.
   * 1/2 < 2/3  = 1 * 3  < 2 * 2
   * which is another way of saying this.numer * that.denom  < that.numer * this.denom
   * which is equivalent to saying
   * numer * that.denom < that.numer * denom
   */

  def lessThan(that: Rational) =
    this.numer * that.denom < that.numer * this.denom

  /**
   * The first this below is reduntant , the second is needed to return an
   * instance of this object if the condition fails
   */
  def max(that: Rational) =
    if (this.lessThan(that)) that else this

  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }
  
  /**
   * 1/2 < 2/3 =  1 * 3 < 2 * 2 
   *
   */
  /**   WITHOUT USING THE ORDERED TRAIT **/
//  def < (that: Rational) = 
//    this.numer * that.denom < that.numer * this.denom
//  def > (that: Rational) = that < this 
//  def <= (that: Rational) = (this < that) || ( this == that )
//  def >= (that: Rational) = ( this > that) || (this == that ) 
  
  def compare(that: Rational) = 
    (this.numer * that.denom) - (that.numer * this.denom)

}

/** test runner **/
object RationalRunner_1 {
  implicit def intToRational(x: Int) = new Rational(x)
  def p(msg: Any) {
    println(msg)
  }
  def p(msg: Any, msg2: Any) {
    println(msg + ":" + msg2)
  }

  def p(symbol: Symbol, msg2: Any) {
    // println(symbol.hashCode())
    println(symbol.name + " " + msg2)
  }
  def test_1 {
    var oneHalf = new Rational(1, 2)
    var twoThirds = new Rational(2, 3)
    val sum = oneHalf add twoThirds
    p("sum=", sum)
    /**
     * Scala Rule:
     * You cannot directly access the class parameters in the declaration.
     * sum.d ---> illegal to do
     */
    p("sum.d", sum.denom)
    p("sum.n", sum.numer)

    val wholenumber = new Rational(4)
    p("wholenumber", wholenumber)

    val reduced = new Rational(66, 42)
    p("reduced", reduced)
    
    var mult = oneHalf * twoThirds
    
    p("mult" , mult)
    var added = oneHalf.+(twoThirds)
    p("added" , added)
    
    var x = new Rational(1,2)
    var y = new Rational(2,3)
    p("x", x)
    p("y" , y)
    p(" x + x * y", x + x * y)
    p("(x + x) * y" , (x + x) * y)
    
    x = new Rational(2,3)
    p("x" , x)
    p("x * x" , x * x)
    p("x * 2", x * 2)
    
    val r = new Rational(2,3)
    p("r", r)
    p(" 2 * r", 2 * r)
  }
  def test_2 {
    
    val half = new Rational(1,2)
    val third = new Rational(1,3) 
    var b = half < third 
    p("half" , half)
    p("third" , third)
    p("half < third" , b)
  }
  def main(args: Array[String]) {
    test_2
  }
}