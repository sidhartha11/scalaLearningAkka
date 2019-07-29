package org.geo.pisreview.chapter25

//import org.geo.pisreview.Utilities._
//
//import collection.IndexedSeqLike 
//import collection.mutable.{Builder, ArrayBuffer}
//import collection.generic.CanBuildFrom
//
//abstract class Base 
//case object A extends Base 
//case object T extends Base 
//case object G extends Base 
//case object U extends Base 
//
//object Base {
//  val fromInt: Int => Base = 
//    Array(A,T,G,U)
//    
//  val toInt: Base => Int = 
//    Map(A -> 0 , T -> 1 , G -> 2 , U -> 3 )
//}
//
//final class RNA1 private (val groups: Array[Int] , val length: Int) 
//extends IndexedSeq[Base] {
//  import RNA1._
//  def apply(idx: Int): Base = {
//    if ( idx < 0 || length <= idx) {
//      throw new IndexOutOfBoundsException 
//    }
//    Base.fromInt(groups(idx/N) >> ( idx % N * S) & M)
//  }
//  // one way to return an RNA1 type instead of a Vector which is IndexedSeq default
//  // override def take(count: Int): RNA1 = RNA1.fromSeq(super.take(count))
//}
//
//object RNA1 {
//  // Number of bits necessary to represent group
//  private val S = 2
//  
//  // Number of groups that fit in an Int
//  private val N = 32 / S
//  
//  // Bitmask to isolate a group
//  private val M = (1 << S) - 1 
//  
//  def fromSeq(buf: Seq[Base]): RNA1 = {
//    /**
//     * Determine the size of the groups array:
//     * note:
//     * # bits to represent a group: S = 2 
//     * # groups that fit in an Int: N = 32 / S = 16 
//     * Size of the groups array : Sequence length + 16 - 1
//     * for the example  List(A,G,T,A) we get:
//     * 4 + 16 - 1 = 19 / 16 = 1
//     */
//    val groups = new Array[Int]((buf.length + N - 1) / N)
//    p("groups" , groups)
//    p("groups.size" , groups.size)
//    /**
//     * Put the RNA strands into an RNA1 object
//     */
//    for ( i <- 0 until buf.length) {
//      // p("i" ,i)
//      val n = i/N
//      p("n" , n)
//      val o = Base.toInt(buf(i)) << ( i % N * S)
//      p("o" , o)
//      groups(n) |= o
//    }
//    p("groups" ,groups.mkString(","))
//    new RNA1(groups, buf.length)
//  }
//  
//  def apply(bases: Base*) = fromSeq(bases)
//}


object RNAstrands extends App {
//  import Base._ 
//  /**
//   * Create a Seq of RNA types 
//   */
//  val xs = List(A,G,T,A)
//  cof("xs" , xs)
//  /**
//   * Convert the sequence to an RNA1 strand 
//   */
//  val xs2 = RNA1.fromSeq(xs)
//  cof("xs2" , xs2)
//  val rna1 = RNA1(A,U,G,G,T)
//  cof("rna1" , rna1)
//  p("rna1.length" , rna1.length)
//  p("rna1.take(3)" , rna1.take(3))
}