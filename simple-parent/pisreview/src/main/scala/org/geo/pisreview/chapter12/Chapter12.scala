package org.geo.pisreview.chapter12

import org.geo.pisreview.Utilities._

 class Animal 
 trait HasLegs
  /**
   * Traits all inherit from the super type AnyRef
   */
  trait Philosophical {
    def philosophize() = {
      p("I consume memory, therefore I am!")
    }
  }

 class Frog extends Animal with Philosophical
 with HasLegs{
   
  override def philosophize() = {
    p("It ain't easy being " + toString + "!")
  }
  override def toString = "green" 
}
 
 


object Chapter12 {

  
   def main(args: Array[String]){
     val f = new Frog
     p("f",f)
     p("f.pilosophize",f.philosophize())
     
     val phil: Philosophical = f
     p("phil" , phil)
   }
}