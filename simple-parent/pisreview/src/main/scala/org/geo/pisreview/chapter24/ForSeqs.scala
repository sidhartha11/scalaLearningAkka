package org.geo.pisreview.chapter24

object ForSeqs {
     def triple : Seq[(Int,Int,Double)] = {
            
    val xs2 = for ( x <- 0 until 100 ) yield {
      (x , x * x , Math.sqrt(x))
    }
    xs2.toSeq
    }
    val zs = Seq(
        6,4,5,3)
    val xs = Seq(
      1,2,3,4)
    val xsMixed = Seq (
        1,2,3,2,6,7,5,5,3,4,5)
        
    val ysitr= Iterator(1,2,3,4)
    val ys   = Seq (5,6,7,8)
    val xsPair = Seq((1,"a") , (2,"b") , (3, "c") , (4,"d"))
    val xsOption = 
      Seq(None, Some(1) , Some(2) , None , Some(3), None , Some(4))
      
    val xsTriple = triple
}