package org.geo.pisreview.chapter7
import org.geo.pisreview.Utilities._
import scala.io.StdIn
import java.io.FileReader
import java.io.IOException
import java.io.FileNotFoundException
import java.net.MalformedURLException
import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader

object Chapter7 extends App {
    def SUFFIX = ".scala"
    def PATTERN = ".*gcd.*"
  def dir = 
"C:/workspaces/scala/generalstudies/scalaLearningAkka/simple-parent/pisreview/src/main/scala/org/geo/pisreview/chapter7"
  
  def gcdLoopR(x: Long, y: Long): Long =
    if ( y == 0 ) x else gcdLoopR(y,x % y)
  def dirFiles = 
   ( new java.io.File(dir)).listFiles
    def filesHere = 
   ( new java.io.File(dir)).listFiles
   
   def fileLines(file: java.io.File) = 
     scala.io.Source.fromFile(file).getLines().toList
     
  def gcdLoop(x: Long , y: Long): Long = {
    var a = x
    var b = y
    while (a != 0) {
      val temp = a
      a = b % a
      b = temp
    }
    b
  }
  def test_7_4 {
    def greet() = {
      println("hi")
    }
    p("() == greet()" , () == greet())
    
    /**
     * testing while returning a value does not work in scala
     * reassignment to vars yields Unint , the the value of the expression
     * as you used to do in the old Java idiom.
     */
//    println("reading from Stdin")
//    var line = ""
//    while ( (line = StdIn.readLine()) != "" ) // This doesn't work
//      println("Read:" + line)
    
    p("gcdLoopR(142, 1068)" , gcdLoopR(142,1068))
      
  }
  def test_7_3 {
    p("testing do while")
     
    var line = ""
    do {
      line = StdIn.readLine()
      Console.printf("read line %s",line)
    } while ( line != "" )
      Console.printf("exiting")
      
  }
  def test_7_2 {
    p("gcdLoop(24, 78)" , gcdLoop(24, 78))
  }
  def test_7_1 {
    /**
     * imperative usage of control structure
     */
    var filename = "default.txt"
    if (!args.isEmpty) {
      filename = args(0)
    }
    p("filename", filename)

    /**
     * Functional style
     */
    val anotherfile =
      if (!args.isEmpty)
        args(0)
      else
        "default.txt"
    p("anotherfile", anotherfile)
    
    /**
     * functional style to print the expression
     */
    p("funcstyle", if (!args.isEmpty) args(0) else "bad.txt")
  }
  def test_7_5 {
    p("dir",dir)
    val filesHere = ( new java.io.File(dir)).listFiles
    for ( x <- filesHere ) println(x)
    p("range example")
    for ( i <- 0 to filesHere.length-1 ) println(filesHere(i))
    for ( x <- filesHere if x.isFile) println(x)
  }
  
  def test_7_8 =  {
  
    for ( 
        file <- dirFiles 
        if file.getName.endsWith(SUFFIX);
        line <- fileLines(file) 
        if line.trim.matches(PATTERN)
        ) println(file + ":" + line.trim)
    
  }
  def test_7_9 = {
    
    for {
      file <- dirFiles 
      if file.getName.endsWith(SUFFIX)
      line <- fileLines(file)
      if line.trim.matches(PATTERN)
    } println(file + ":" + line.trim)
    }
  
  def test_7_10 {
    for {
      file <- dirFiles
      if file.getName.endsWith(".scala")
      line <- fileLines(file) 
      /**
       * midstream binding , note the "val" is left off 
       * When using conditionals within the for comprehension structure
       * you do not need to use parens. 
       */
      trimmed = line.trim
      if (trimmed.matches(PATTERN))
    } println(file + ": " + trimmed)
  }
    
  def test_7_11 = {
    /**
     * Here we see the yield will create a datastructure 
     * that is the same as the source of the loop: filesHere
     * Which in this case is an Array[File]
     */
    for {
      file <- filesHere 
      if file.getName.endsWith(".scala")
      
    } yield file
  }
  
  /**
   * Conceptually, this is a transformation process.
   * The initial Array[String] of filesHere collection 
   * is transformed to an Array[Int] by the second 
   * for statement: line <- fileLines(file)
   */
  def test_7_12 = {
    for {
      file <- filesHere 
      if file.getName.endsWith(".scala") 
      line <- fileLines(file)
      trimmed = line.trim
      if trimmed.matches(".*for.*")
    } yield trimmed.length
  }
  def test_74_1(n: Int) = {
    val half = 
      if ( n % 2 == 0 )
        n/2
      else {
         new RuntimeException("n must be even")
      }
    half
  }
    def test_74_1_1(n: Int) = {
    val half = 
      if ( n % 2 == 0 )
        n/2
      else {
        throw new RuntimeException("n must be even")
      }
    half
  }
    def test_throw {
      /**
   * MAIN LINE OF CODE
   * Note that because the function, test_74_1 can return either an Int 
   * or a String, the scala compiler inferrs a return type of ANY
   */
  val n = test_74_1 { 12 }
  p("n" , n)
  val n2 = test_74_1_1 ( 1 )
  p("n2" , n2)
    }
    def test_catch_1 {
      try {
        val f = new FileReader(dir + "/Chapter7.scala")
        val i = f.read()
        p("read", i)
        f.close()
    } catch {
      case ex: FileNotFoundException => p("ex" , ex) 
      case ex: IOException => p("ex", ex)
    }
    }
    
    def test_finally_1 {
      val file = new FileReader(dir + "/Chapter7.scala")
      var done = false 
      while ( !done ) {
        try { 
          val i = (file.read)
  //        Thread.sleep(2000)
          println( i)
          if ( i == -1 ) 
            done = true
          else print(i.toChar)
        } catch {
          case ex: Throwable =>  
            p("exception caught,closing file")
            file.close()
            done = true
         }
      }
    }
    def test_yielding_value(path: String) = {
      def urlFor = 
        try {
          new URL(path)
        } catch {
          case e: MalformedURLException => 
            new URL("http://www.sala-lang.org")
        }
        urlFor
    }
    
    /**
     * As in Java a return clause in the finally will override the 
     * return value in the try block 
     */
    def f(): Int = try return 1 finally return 2 
    
    /**
     * However, without the explicit return , the try block value is 
     * returned
     */
    def g(): Int = try 1 finally 2
    
import scala.util.control.Breaks._
import java.io._
  def likeJava {
    var i = 0 
    var foundIt = false 
    while ( i < args.length && !foundIt ) {
      if ( !args(i).startsWith("-")) {
        if ( args(i).endsWith(".scala")) 
          foundIt = true 
      }
      i = i + 1 
    }
    p(foundIt)
    }
    
    def searchFrom  (i : Int ) : Int = 
      if ( i > args.length ) -1
      else if (args(i).startsWith("-")) searchFrom(i+1)
      else if (args(i).endsWith(".scala")) i 
      else 
        searchFrom(i+1)
        
     /**
      * Imperative approach to multiplication tables
      */
        def printMultiTable() = { 
        
        var i = 1 
        // only i in scope here 
        
        while ( i <= 10 ) {
          
          var j = 1 
          // both i and j in scope here 
          
          while ( j <= 10 )  {
            
            val prod = ( i * j).toString 
            // i, j and prod in scope here 
            
            var k = prod.length 
            // i , j , prod and k in scope here 
            
            while (k < 4) { 
              print(" ")
              k += 1
            }
            
            print(prod)
            j += 1
          }
          
          // i and j still in scope; prod and k out of scope 
          
          println()
          i += 1 
        }
        
        // i still in scope; j, prod, and k out of scope 
      }
    
    /**
     * Functional Style Multi 
     */
    def makeRowSeq(row: Int) = {
      /**
       * generate an indexedSeq[String] of time tables values for row 
       */
      val s = for ( col <- 1 to 10 ) yield {
        /** calculate the timetable for col with input row **/
        val prod = ( row * col ).toString
        /** generate ( 4 - prod.length) spaces **/
        val padding = " " * (4 - prod.length)
        /** prepend the spaces onto the value, prod **/
        padding + prod 
      }
      s
    }
    
    // Returns a row as a string 
    // converts the indexedSeq to a String 
    def makeRow(row: Int) = {
      p("converting to string", makeRowSeq(row))
      makeRowSeq(row).mkString
    }
    
    def multiTable() = { 
      val tableSeq = // a sequence of row strings 
        for ( row <- 1 to 10 ) 
          yield makeRow(row) 
      p("converting to String", tableSeq)
      p("========")
      tableSeq.mkString("\n") 
    }
        
        
        
        
     //p(searchFrom(0))
     
        def usingBreak { 
        val in = new BufferedReader ( new InputStreamReader(System.in))
    breakable { 
        while (true) {
          println("? ")
          val s = in.readLine()
          if ( s == "" ) break 
          println("read:" + s)
          
        }
      }
      }
    // printMultiTable
    p(multiTable)
}