package org.geo.pisreview.chapter5

object Chapter5 extends App{
  
  def objectEquality {
    val a = 2 << 2 + 2 
    println("2 << 2 + 2 = %d , (2 << 2) + 2 = %d".format(2 << 2 + 2 , (2 << 2) + 2))
    val y = 3
    var b = 1
    b *= y + 1
    println("b *= y + 1 = %s, y = %s".format(b, y))
  }
  def bitwiseops {
    /** logical And **/
    var x = 1 & 2
    println(s"1 & 2 = ${x}")
    
    /** logical or **/
    x = 1 | 2 
    println(s"1 | 2 = ${x}")
    
    /** exclusive or  **/
    x = 1 ^ 3
    println(s"1 ^ 3 = ${x}")
    /**
     * 01
     * 11
     */
    /** compliment operator **/
    x = ~1
    println(s"~1 = ${x}")
    /** shift operators **/
    /** shift right 31 places **/
    var y = -1 >> 31 
    println(s"-1 >> 31 = ${y}")
    y = -1 >>> 31 
    /** unsigned shift right **/
    println(s"-1 >>> 31 = ${y}")
    /** shift left **/
    y = 1 << 2 
    println(s"1 << 2 = ${y}")
  }
  def operators {
    val sumMore = 1.+(3)
    println(sumMore)
    /** prefix notation **/
    var s = - 7
    println(s"prefix $s")
    /** postfix **/
    var ls:Long  = 7.toLong
    println(s"postfix " + ls)
    /**
     * Unary operator example
     */
    println(s"-7 = 7.unary_- ${7.unary_-}")
  }
   def operators2() {
    val sumMore = 1.+(3)
    println(sumMore)
  }
  def stringStuff {
    
    // no literals
    val noEscape = raw"No\\\\escape!"
    println(noEscape)
    
    // printf-style
    var printfstyle = f"${math.Pi}%.5f"
    println(printfstyle)
    
    // default-style with f
    printfstyle = f"${math.Pi}"
    println(printfstyle)
  }
  
//  operators
//  // not allowed sinced defined witthout parens
//  // operators()
//  // operators2 can use both approaches 
//  operators2
//  operators2()
//  bitwiseops
 objectEquality
}