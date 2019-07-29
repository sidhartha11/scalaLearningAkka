package org.geo.pisreview.chapter8

import org.geo.pisreview.Utilities._

import scala.io.Source
object LongLines {
  def dir =
    "C:/workspaces/scala/generalstudies/scalaLearningAkka/simple-parent/pisreview/src/main/scala/org/geo/pisreview/chapter7"

  def processFile1(filename: String, width: Int) = {
    val source = Source.fromFile(filename)

    for (line <- source.getLines) {
      processLine1(filename, width, line)
    }
  }

  private def processLine1(filename: String, width: Int, line: String) = {
    if (line.length() > width) {
      p(filename + ":" + line.trim)
    }
  }

  def processFile(filename: String, width: Int) = {

    def processLine(filename: String, width: Int, line: String) = {

      if (line.length > width)
        println(filename + ":" + line.trim)
    }
    /** main line of processFile **/
    val source = Source.fromFile(filename)
    for (line <- source.getLines()) {
      processLine(filename, width, line)
    }
  }
}
object Chapter8 extends App {

  def doFunctionLiterals {
    /**
     * creating a literal function definition and storing their
     * value in a variable
     *
     */
    var increase = (x: Int) => x + 1
    p(increase(10))
    increase = (x: Int) => x + 9999
    p(increase(10))
    
    increase = (x: Int) => {
      p("we")
      p("are")
      p("here")
      x + 1
    }
    p(increase(10))
    val someNumbers = List(-11, -10, -5, 0, 5, 10)
    val t = someNumbers.filter(x => x > 0 )
    p(t)
    
  }
  var more = 1 
  
  /*
   * here makeIncreaser is a function that returns a function that adds 
   * its parameter to the free variable "more"
   */
  def makeIncreaser(more: Int) = (x: Int) => x + more 
  
  def doFunctionClosures {
    
    val addMore = (x: Int) => {
      more = x + more 
      x + more 
    }
    p("addMore(3)=" , addMore(3))
    p("more=" , more)
    val someNumbers = List(-11, -10, -5, 0, 5, 10)
    var sum = 0 
    someNumbers foreach ( sum += _)
    p("sum" , sum)
    more = 1 
    val inc1 = makeIncreaser(1)
    val inc9999 = makeIncreaser(999)
    p("inc1(1)" , inc1(1))
    p("inc999(1)" , inc9999(1))
    
  }
  def doSpecialParameters(args: String*) =  {
    for ( arg <- args ) println(arg)
  }
  def doNamedArguments(distance: Float, time: Float): Float = 
    distance/time
    
  def printTime(out: java.io.PrintStream = Console.out) = 
    out.println("time = " + System.currentTimeMillis())
    
  def callPrintTime {
    val arr = Array("What's" , "up" , "doc?")
    doSpecialParameters(arr:_*)
    doSpecialParameters("fi" , "fi" , "fi")
    p("doNamedArguments(100,10)", doNamedArguments(100,10))
    p("doNamedArguments(distance=100,time=10)",doNamedArguments(distance=100,time=10))
    p("doNamedArguments(time=10,distance=100)",doNamedArguments(time=10,distance=100))
    p("doNamedArguments(time=10,distance=100)",doNamedArguments(time=10,distance=100))

    printTime ()
    printTime(Console.err)
  }
  
  def approximate(guess: Double): Double = 
    if ( isGoodEnough(guess)) guess 
    else approximate(improve(guess)) 
    
  def isGoodEnough(guess: Double): Boolean = guess == 10
  def improve (guess: Double): Double = guess + 1 
  
  def approximateLoop(initialGuess: Double): Double = {
      var guess = initialGuess
      while (!isGoodEnough(guess))
        guess = improve(guess)
        
        guess
    }
  def boom(x: Int): Int = 
    if ( x == 0 ) throw new Exception("boom!")
    else boom(x - 1) + 1 
    
   def boomTail(x: Int): Int = 
    if ( x == 0 ) throw new Exception("boomTail!")
    else boomTail(x - 1)
    
   def isEven(x: Int): Boolean = 
     if ( x == 0 ) true else isOdd(x-1)
     
   def isOdd(x: Int): Boolean = 
     if ( x == 0 ) false else isEven(x - 1)
     
   val funValue = nestedFun _ 
   
   def nestedFun(x: Int): Unit = {
       if ( x != 0 ) { println(x); funValue(x - 1) }
     }
    
  import LongLines._
  
  p("approximate(0)" , approximate(0))
  p("approximateLoop(0)" , approximateLoop(0))
  
  p("isEven(15)" , isEven(15))

  
}