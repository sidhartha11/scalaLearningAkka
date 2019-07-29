package org.geo.pisreview.chapter24

object ForIterables {
    def triple : Iterable[(Int,Int,Double)] = {
            
    val xs2 = for ( x <- 0 until 100 ) yield {
      (x , x * x , Math.sqrt(x))
    }
    xs2.toIterable
    }
    val xs = Iterable(
      1,2,3,4)
    val ysitr= Iterator(1,2,3,4)
    val ys   = Iterable (5,6,7,8)
    val xsPair = Iterable((1,"a") , (2,"b") , (3, "c") , (4,"d"))
    val xsOption = 
      Iterable(None, Some(1) , Some(2) , None , Some(3), None , Some(4))
      
    val xsTriple = triple
}