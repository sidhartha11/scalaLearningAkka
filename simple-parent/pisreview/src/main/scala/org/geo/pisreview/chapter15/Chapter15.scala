package org.geo.pisreview.chapter15
import math.{ E, Pi }
import org.geo.pisreview.Utilities._
import scala.collection.immutable.HashMap

/**
 * The sealed keyword says:
 * no more subclasses can be added to this abstract class other than the 
 * ones in this file. The compiler will flag cases where you are not covering all
 * possible case matches 
 * 
 */
sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, ag: Expr) extends Expr
case class BinOp(
  operator: String,
  left:     Expr, right: Expr) extends Expr

object Chapter15 extends App {
  /**
   * Pattern match where order matters 
   */
  def simplifyAll ( expr: Expr ): Expr = 
    expr match {
    
    case UnOp("-", UnOp("-", e)) => 
      simplifyAll(e)   // - is its own inverse 
     
    case BinOp("+" , e , Number(0)) =>
      simplifyAll(e)   // 0 is a neatral element for '+' 
      
    case BinOp("*" , e , Number(1)) =>
      simplifyAll(e)    // 1 is a neutral element for '*'
      
    case UnOp(op, e) => 
      UnOp(op, simplifyAll(e))
    
    case BinOp(op, l, r) => 
      BinOp(op, simplifyAll(l), simplifyAll(r))
    
    case _ => expr 
    
  }
  
  
  

  def generalSize(x: Any) = x match {
    case s: String => s.length
    case m: Map[_, _] =>
      println("m = " + m)
      m.size
    case _ => -1
  }

  def generalSize2(x: Any) = {
    if (x.isInstanceOf[String]) {
      val s = x.asInstanceOf[String]
      s.length
    } else if (x.isInstanceOf[Map[_, _]]) {
      val s = x.asInstanceOf[Map[_, _]]
      s.size
    } else
      -1
  }
  def isStringArray(x: Any) = x match {
    case a: Array[String]  => "yes is String"
    case b: Array[Boolean] => "yes is Boolean"
    case c                 => "unknown type of Array:" + c.getClass().getName
  }

  def tupleDemo(expr: Any) = {
    expr match {
      case (a, b, c) => println("matched %d".format((a, b, c).productArity))
      case _         =>
    }
  }
  def describe(x: Any) = {
    /**
     * enclosing a variable in back ticks allow
     * it to be properly used in case expressions
     */
    val y = "dog"
    x match {

      case 5       => "five"
      case true    => "truth"
      case "hello" => "hi!"
      case Nil     => "the empty list"
      case `y`     => "the val y=" + y
      case a       => "the val a=" + a
    }
  }

  def simplifyTop(expr: Expr): Expr =
    expr match {
      case UnOp("-", UnOp("-", e)) => {
        p("UnOp(-,Unop(-,e))", e)
        e
      }

      case BinOp("+", e, Number(0)) => {
        p("BinOp(+,e,Number(0))", e)
        e
      }

      case BinOp("*", e, Number(1)) => {
        p("BinOp(*,e,Number(1))", e)
        e
      }

      case _ => {
        p("default case", expr)
        expr
      }
    }
  /**
   * case classes add several syntactic pieces of sugar.
   * 1. A factory method is added, i.e. apply
   */
  def example_1 {
    val v = Var("x")
    p("v", v)
  }
  /**
   * case classes add an override def toString method for you
   * all parameters in the contructor list implicitly get a val prefix
   * which means they are maintained as fields
   * In addition:
   * The compiler adds natural implementations of methods toString, hashCode and equals
   * A copy function comes with case classes allowing you to create a copy of
   * the original with some of the parameters changed by using the named parameters.
   */
  def example_2 {
    val v = Var("x")
    val op = BinOp("+", Number(1), v)
    p("op", op)
    p("op.operator", op.operator)
    p("op.left", op.left)
    p("op.right", op.right)
    p("v.name", v.name)
    p("op.right == Var(\"x\")", op.right == Var("x"))
    /**
     * make a copy of op
     */
    val op2 = op.copy(operator = "-")
    p("op2", op2)
  }
  def example_3 {
    val x = simplifyTop(BinOp("*", Var("x"), Number(1)))
    println(x)
  }
  def example_4 {
    val expr: Any = Number(1)
    expr match {
      case BinOp("+", left, right) =>
        println(expr + " is a binary operation")
      case Number(e) =>
        println(expr + " is a number")
      case _ => println(" got " + expr)
    }
  }

  def example_5(expr: Any) = {
    expr match {
      case BinOp(op, left, right) =>
        println(expr + " is a binary operation")
      case _ => println("ooops:" + expr)
    }

  }

  def example_6(expr: Any) = {
    expr match {
      case BinOp(_, _, _) => println(expr + " is a binary operation")
      case _              => println(expr + " is not a binary operation")
    }
  }
  def example_7 {
    val expr: Any = "a word"
    val p1 = expr match {
      case 0             => "zero"
      case somethingElse => "not zero: " + somethingElse
    }
    /** note: functions and vals in the same name space **/
    p(E match {
      case Pi => "strange math? Pi = " + Pi
      case _  => "OK"
    })

    p(describe("dog"))
    val pi = math.Pi
    p(E match {
      case `pi` => "strange math? Pi = " + pi
      case _    => "OK"
    })
    Thread.`yield`()
  }

  def example_8 {
    BinOp("+", Var("a"), Number(0)) match {
      case BinOp("+", e, Number(0)) => println("a deep match")
      case _                        =>
    }

    val expr = List(0, 1, 2)
    expr match {
      case List(0, _, _) => println("found it")
      case _             =>
    }
    /**
     * will match List(0,4,5, .... ) or List(0)
     */
    List(0) match {
      case List(0, _*) => println("found it")
      case _           =>
    }
  }
  def example_9 {
    tupleDemo((List(1), List(2), List(3)))
    (1, 2, 3).productIterator.foreach(println)

    val m: Map[String, String] = HashMap("a" -> "b", "c" -> "d")
    val s: String = "A String"
    var siz = generalSize(m)
    p("siz =", siz)
    siz = generalSize(189)
    p("siz", siz)
    p(isStringArray(Array(100)))
  }
  
  def example_10 {
  
  val expr = UnOp("abs" , UnOp("abs" , Number(-2)))
  
  val e = 
    expr match {
    case UnOp(f @ "abs" , e @ UnOp("abs",_)) => (f , e)
    case _ =>
  }
  println("e = " + e)
  }
  
  def simplifyAdd(e: Expr) = e match {
    /**
     * pattern guard usage ( with or with out parens and before => )
     */
    case BinOp("+" , x , y) if (x == y) => BinOp("*" , x , Number(2))
    case _ => e
  }
  def describeSealedExample(e: Expr): String = ( e: @unchecked )  match {
    case Number(_) => "a number"
    case Var(_)    => "a variable"
//    case _ => throw new RuntimeException  // should not happen 
  }
  def test_1 {
      val myTuple = (123, "abc") 
  val (number , string) = myTuple 
  val (a , b ) = (123, "abc") 
  p("myTuple" , myTuple)
  p("number = " + number + ", string = " + string)
  p("a = " + a + ", b = " + b ) 
  }
  
 
  val exp = new BinOp("*" , Number(5) , Number(1))
  val BinOp(op,left,right) = exp 
  // val BinOp("+" , leftt , rightt ) = exp
  
  val second: List[Int] => Int = {
    case x :: y ::_ => y
  }
  
  p(second(List(1,2,3)))
  val secondPartial: PartialFunction[List[Int], Int] = {
    case x :: y :: _ => y
  }
  p("secondPartial isDefinedAt List()", secondPartial isDefinedAt List() )
  
  val secondP = new PartialFunction[List[Int] , Int] {
    def apply(xs: List[Int]) = xs match { 
      case x :: y :: _ => y 
    }
    def isDefinedAt(xs: List[Int]) = xs match {
      case x :: y :: _ => true 
      case _ => false
    }
  }
  p("secondP isDefinedAt List()" , secondP isDefinedAt List() )
  
}