package org.geo.pisreview.chapter23
import org.geo.pisreview.Utilities._

case class Book(title: String, authors: String*) 
object things {
  val books: List[Book] = 
    List(
        Book(
            "Structure andf Interpretation of Computer Programs" ,
            "Abelson, Harold" , "Sussman, Gerald J."
            ),
        Book (
            "principles of Compiler Design" , 
            "Aho, Alfred" , "Ullman, Jeffrey" 
            ),
        Book (
            "Programming in Modula-2" , 
            "Wirth, Niklaus"
            ),
        Book ( 
            "Elements of ML Programming" , 
            "Ullman, Jeffrey"
            ),
        Book (
            "The Java Language Specifcation", "Gosling, James" , 
            "Joy, Bill" , "Steele, Guy" , "Bracha, Gilad"
            )
        )
}
object QueryingExample extends App {
  import things._
  var a = for ( b <- books ; a <- b.authors if a.startsWith("Gosling") ) yield {
    b.title
  }
  p("a = " + a ) 
  a = for ( b <- books ; if b.title.contains("Program")) yield b.title
  p("a = " + a ) 
  
  val a2 = 
    for ( b <- books ; b2 <- books if b != b2 ; 
    a1 <- b.authors ; a2 <- b2.authors if a1 == a2 ) yield a1
  p("2books=" + a2)
  
  /**
   * Translating the above expression using flatMaps 
   */
  val a3 = 
    books flatMap 
    (b1 => books withFilter  
        ( b2 => b1 != b2 ) flatMap ( b2 => 
          b1.authors flatMap ( a1 => 
            b2.authors withFilter ( a2 => a1 == a2) map ( a2 =>
              a1))))
  p(a3) 
        

}