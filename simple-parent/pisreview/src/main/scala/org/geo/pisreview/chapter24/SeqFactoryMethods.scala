package org.geo.pisreview.chapter24

import org.geo.pisreview.Utilities._

object SeqFactoryMethods extends App {

  def test_1 {
    val xs = Seq(1, 2, 3, 4)
    val zs = Seq("a", "b", "c", "d")
    val ys = Seq(1.0, 2.0, 3.0)
    /**
     * S.empty
     * The empty sequence
     */
    val s = Seq.empty[String]
    cof("s", s)
    /**
     * S(x,y,z)
     * A sequence consistingof elements x , y , z
     */
    val s1 = Seq('x', 'y', 'z')
    cof("s1", s1)
    /**
     * S.concat(xs,ys,zs)
     * The sequence obtained by concatenating the elements of
     * xs, ys, and zs
     */
    val all = Seq.concat(xs, ys, zs)
    cof("all", all)
    println(all.getClass)
    /**
     * S.fill(n)(e)
     * A sequence of length n where each element is compouted by
     * expression e
     * A sequence of sequences of dimension m x n  where
     * each element is computed by expression e ( exists also in
     * higher dimensions )
     */
    val l = List(1, 3, 5, 7, 9)
    // val l = List( 0 to 9: _*)
    p("l", l)
    val filler = Seq.fill(10)((i: Int) =>
      {
        println("*** i = " + i)
        i * i
      })
    p("filler(0)", filler(0))
    l.map(x => filler(x)(x)).map((x: Any) =>
      {
        println("***** " + x)
        x
      }).foreach(x => {
      println(x)
    })

    /**
     * S.fill(m,n)(e)
     * A sequence of sequences of dimension m x n
     * where each element is computed by the expression e
     */
    val filler2 = Seq.fill(2, 3)("a")
    p("filler2", filler2)
    /**
     * S.tabulate(n)(f)
     * A sequence of length n where the element at each index i is
     * computed by f(i)
     *
     */
    val tabber = Seq.tabulate(3)(Math.pow(_, 2))
    p("tabber", tabber)
    /**
     * S.range(start,end)
     * The sequence of integers start...end - 1
     */
    val l2 = List(Seq.range(0, 10): _*)
    p("l2", l2)
    for (c <- Seq.range(0, 10)) println(c)

  }

  val l = List(Seq.range(0, 10): _*)
  p("l", l)
  val lit: Int => String = i => if (i % 2 == 0)  "even" else "odd"
  val filler = Seq.fill(10)(lit)  
  l.map(x => filler(x)(x)).foreach(println)
  /**
   * tabulate(n,m)(f)
   * A sequence of sequences of dimension m x n where the element 
   * at each index ( i , j ) is computed by f(i,j) (
   * exists also at higher dimensions ) 
   */
  val squareUp: (Int,Int) => Int = (i,y) => 
    if ( i == 0 && y == 0 ) 1
    else if ( i == 0 ) 1 * y 
    else if ( y == 0 ) i * 1
    else i * y
    
  val table1 = 
    Seq.tabulate(5,5)( squareUp )
    println("table1" , table1)
    /**
     * range(start, end)
     * The sequence of integers start ... end - 1 
     */
    for ( r <- Seq.range(0,10) if r % 2 == 0 ) println(r)
    /**
     * range(start, end, step)
     * The sequence of integers starting with start and progressing 
     * by step increments up to and excluding the end.
     */
    for ( r <- Seq.range(0,100,10) ) println(r)
    /**
     * iterate(x,n)(f)
     * The sequence of length n with elements x , f(x) , f(f(x)), ....
     * 
     */
    val zs1 = Seq.iterate(2,4)( x => x * x )
    p("zs1" , zs1)

}