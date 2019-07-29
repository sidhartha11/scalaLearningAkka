package org.geo.pisreview.chapter24

object ForTraversable {
  
    def sumNatNumb(x: Int) = 
      ( x * ( x + 1) )/ 2
    def triple : Traversable[(Int,Int,Double)] = {
            
    val xs2 = for ( x <- 0 until 100 ) yield {
      (x , x * x , Math.sqrt(x))
    }
    xs2.toTraversable
    }
    val xs = Traversable(
      1,2,3,4)
    val ysitr= Iterator(1,2,3,4)
    val ys   = Traversable (5,6,7,8)
    val xsPair = Traversable((1,"a") , (2,"b") , (3, "c") , (4,"d"))
    val xsOption = 
      Traversable(None, Some(1) , Some(2) , None , Some(3), None , Some(4))
      
    val xsTriple = triple
}