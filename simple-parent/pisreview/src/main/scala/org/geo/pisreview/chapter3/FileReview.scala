package org.geo.pisreview.chapter3
import scala.io.Source

import org.geo.pisreview.Utilities._

object FileReview extends App {
  
  def getMaxWidth1(lines: List[String]): Int = {
    var maxWidth = 0 
    for ( line <- lines ) {
      maxWidth = maxWidth.max(widthOfLength(line))
    }
    maxWidth
  }
  
  def getMaxWidth2(lines: List[String]): String = {
    lines.reduceLeft( (a,b) => if ( a.length > b.length ) a else b )
  }
  
  def widthOfLength(s: String) = s.length.toString.length
      
  def getLines(file: String): List[String] = {
    /**
     * Note:
     * Source.fromFile(file).getLines returns an iterator and thus,
     * can only be used once.  By converting it to a list you can reuse it 
     * over and over again.
     */
    Source.fromFile(file).getLines().toList
  }
  
  def readFile() : Unit =  {
  if ( args.length > 0 ) {
    for ( line <- Source.fromFile(args(0)).getLines() ) {
      p(line.length + " " + line)
    }
  }
  else 
    Console.err.println("Please enter filename")
  
}
  
  val lines = getLines(args(0))
  p("lines" , lines)
  val  longestLine = getMaxWidth2(lines)
  p("longestLine" , longestLine)
  val maxWidth = widthOfLength(getMaxWidth2(lines))
  p("longestline" , maxWidth)
}