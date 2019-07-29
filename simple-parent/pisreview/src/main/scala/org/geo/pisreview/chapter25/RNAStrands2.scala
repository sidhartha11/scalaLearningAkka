package org.geo.pisreview.chapter25

import org.geo.pisreview.Utilities._

import collection.IndexedSeqLike 
import collection.mutable.{Builder, ArrayBuffer}
import collection.generic.CanBuildFrom

abstract class Base 
case object A extends Base 
case object T extends Base 
case object G extends Base 
case object U extends Base 

object Base {
  val fromInt: Int => Base = 
    Array(A,T,G,U)
    
  val toInt: Base => Int = 
    Map(A -> 0 , T -> 1 , G -> 2 , U -> 3 )
}

final class RNA2 private (val groups: Array[Int] , val length: Int) 
extends IndexedSeq[Base]  with IndexedSeqLike[Base,RNA2]{
  import RNA2._
  
  override protected[this] def newBuilder: Builder[Base, RNA2] = {
   RNA2.newBuilder
  }
  
  def apply(idx: Int): Base = {
    if ( idx < 0 || length <= idx) {
      throw new IndexOutOfBoundsException 
    }
    Base.fromInt(groups(idx/N) >> ( idx % N * S) & M)
  }
  
  override def foreach[U](f: Base => U): Unit = {
    var i = 0 
    var b = 0 
    while ( i < length ) {
      b = if ( i % N == 0) groups(i / N) else b >>> S
      f(Base.fromInt(b & M))
    }
    i += 1 
  }
  // one way to return an RNA21 type instead of a Vector which is IndexedSeq default
  // override def take(count: Int): RNA21 = RNA21.fromSeq(super.take(count))
}

object RNA2 {
  // Number of bits necessary to represent group
  private val S = 2
  
  // Number of groups that fit in an Int
  private val N = 32 / S
  
  // Bitmask to isolate a group
  private val M = (1 << S) - 1 
  
  def fromSeq(buf: Seq[Base]): RNA2 = {
    /**
     * Determine the size of the groups array:
     * note:
     * # bits to represent a group: S = 2 
     * # groups that fit in an Int: N = 32 / S = 16 
     * Size of the groups array : Sequence length + 16 - 1
     * for the example  List(A,G,T,A) we get:
     * 4 + 16 - 1 = 19 / 16 = 1
     */
    val groups = new Array[Int]((buf.length + N - 1) / N)
    p("groups" , groups)
    p("groups.size" , groups.size)
    /**
     * Put the RNA2 strands into an RNA21 object
     */
    for ( i <- 0 until buf.length) {
      // p("i" ,i)
      val n = i/N
      p("n" , n)
      val o = Base.toInt(buf(i)) << ( i % N * S)
      p("o" , o)
      groups(n) |= o
    }
    p("groups" ,groups.mkString(","))
    new RNA2(groups, buf.length)
  }
  
  def apply(bases: Base*) = fromSeq(bases)
  
  def newBuilder: Builder[Base,RNA2] = 
    new ArrayBuffer mapResult fromSeq 
    
  implicit def canBuildFrom: CanBuildFrom[RNA2, Base, RNA2] = {
    new CanBuildFrom[RNA2, Base, RNA2] {
      def apply(): Builder [Base, RNA2] = newBuilder 
      def apply(from: RNA2): Builder[Base,RNA2] = newBuilder
    }
  }
}


object RNA2strands extends App {
  import Base._ 
  def example_1 {
  /**
   * Create a Seq of RNA2 types 
   */
  val xs = List(A,G,T,A)
  cof("xs" , xs)
  /**
   * Convert the sequence to an RNA21 strand 
   */
  val xs2 = RNA2.fromSeq(xs)
  cof("xs2" , xs2)
  val rna2 = RNA2(A,U,G,G,T)
  cof("rna2" , rna2)
  p("rna2.length" , rna2.length)
  p("rna2.take(3)" , rna2.take(3))
  p("rna2 filter ( U != )" , rna2 filter ( U !=))
  val list = List("a" , "x" , "b", "c")
  p("list" , list )
  p("list filter ( \"a\" != )" , list filter ( "a" !=  ))
  }
  
  val rna = RNA2(A, U,G,G,T)
  cof("rna" , rna)
  cof("rna map { case A => T case b => b }" , rna map { case A => T case b => b})
  cof("rna ++ rna" , rna ++ rna )
  
  cof ( "rna map Base.toInt" , rna map Base.toInt)
  cof ("rna ++ List(\"missing\" , \"data\")" , rna ++ List("missing" , "data"))
  
}