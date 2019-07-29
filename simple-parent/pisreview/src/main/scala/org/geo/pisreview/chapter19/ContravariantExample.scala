package org.geo.pisreview

import org.geo.pisreview.Utilities._

/**
 * visible field called title
 */
class Publication(val title: String)
class Book(title: String) extends Publication(title)

object Library {
  val books: Set[Book] = 
    Set (
    new Book("Programming in Scala") , 
    new Book("Walden")
    )
    
    def printBookList(info: Book => AnyRef ) = {
    for ( book <- books ) println(info(book))
  }
}



object Customer {
  def getTitle(p: Publication): String = p.title
  Library.printBookList(getTitle)
  
  def main (args: Array[String] ) {
  val pub = new Publication("bob")
  println(pub.title)
  
  val scalaBook = new Book("scalabooks")
  p(scalaBook,scalaBook.getClass.getName)
  }
}