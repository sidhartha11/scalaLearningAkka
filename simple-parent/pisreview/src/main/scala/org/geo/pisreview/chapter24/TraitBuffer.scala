package org.geo.pisreview.chapter24

import scala.collection.mutable
import org.geo.pisreview.Utilities._
import ForBuffers._

object TraitBuffer extends App {
  /**
   * Additions
   * NOTE:
   * ListBuffer and ArrayBuffer only come in the "mutable" form
   * in scala.collection.mutable
   */
  
  /**
   * buf += x 
   * Applends element x to buffer buf, and returns buf itself as result
   */
  var xsTran = Traversable("my", "traversable", "list")
  /** start out with an empty Buffer[String]   **/
  var buf = mutable.Buffer[String]()
  p("buf += hello" , buf += "hello")
  val buf2 = mutable.Buffer("to", "be", "prepended")
  
  /**
   * buf += ( x, y , z )  
   * appends all elements to the buffer 
   */
 p("buf += (silly, gray, fox)" , buf += ( "silly" , "gray" , "fox" ))
 /**
  * buf ++= xs 
  * Appends all elements in xs to buffer 
  * In this case, xs must be of the same type
  */
 p("xsTran" , xsTran)
 p("buf" , buf)
 p("buf ++= Siq(plums,apples)" , buf ++= Seq("plums" , "apples"))
 p("buf ++= xsTran" , buf ++= xsTran)
 p("buf" , buf)
 /**
  * x +=: buf 
  * prepends element x to buffer 
  */
 p("earth +=: buf" , "earth" +=: buf)
 p("buf" , buf)
 /**
  * xs ++=: buf 
  * prepends all elements in xs to buf
  */
 p("buf2" , buf2)
 p("buf2 ++=: buf" , buf2 ++=: buf)
 /**
  * buf insert ( i , x ) 
  * inserts element x at index i in buffer 
  */
 p("buf2")
 buf2 insert(0,"fisrt")
 p(buf2)
 /**
  * buf insertAll ( i , xs )
  * inserts all elements in xs at index i in buffer
  */
  p("buf2 insertAll ( 0 , mutable.Buffer(\"a\", \"e\" , \"i\" , \"o\" , \"u\")",
      buf2 insertAll ( 0 , mutable.Buffer("a", "e" , "i" , "o" , "u")))
      p("buf2" , buf2)
      
  /**
   * REMOVALS
   */
   /**
    * buf -= x 
    * Removes element x from buffer   
    */
    p(buf)
    p("buf -= to" , buf -= "to")
    /**
     * buf remoe i 
     * removes element at index i from buffer 
     * ArrayBuffer(be, prepended, earth, hello, silly, gray, fox, plums, apples, my, traversable, list)
     * 
     */
    p(buf)
    p("buf remove 0" , (buf,buf remove 0))
    /**
     * buf remove(i, n) 
     */
    p("buf remove(0,4)" , (buf , buf remove(0,4)))
    p(buf)
    /**
     * buf trimStart n 
     * removes first n elements from buffer 
     */
    p("buf trimStart 2" , (buf , buf trimStart 2))
    /**
     * plums, apples, my, traversable
     * 
     */
    /**
     * buf trimEnd n
     * Removes last n elements from buffer 
     */
    p("buf trimEnd 3" , (buf ,buf trimEnd 3))
    /**
     * buf.clear()
     * Removes all elements from buffer 
     */
    p("buf.clear()" , (buf , buf.clear))
    /**
     * CLONING
     */
    /**
     * buf.clone
     * A new buffer with the same elements as buf
     */
    p("buf += (silly, gray, fox)" , buf += ( "silly" , "gray" , "fox" ))
    p("buf", buf)
    var buf3 = buf.clone()
    p("buf.clone()" , buf3)
    
      
      
      
      
      
      
      
      
      
      
      
      
      
  
}