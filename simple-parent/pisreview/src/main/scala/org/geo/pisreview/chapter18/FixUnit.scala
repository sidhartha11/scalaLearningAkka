package org.geo.pisreview.chapter18

object FixUnit extends App {
  
 val lst = List(1, 2, 3).map {
   case z if z > 2 => Some(z)
   case _ => None 
 }.flatMap(p => p).foreach(println)
// val l = lst.map {
// case z if z > 2 => Some(z)
// case _ => None
// }
// println(lst)
println(List(1, 2, 3).filter(_ > 2).map(_ * 2))
}