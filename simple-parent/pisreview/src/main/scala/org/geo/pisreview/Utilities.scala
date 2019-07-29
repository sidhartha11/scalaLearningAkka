package org.geo.pisreview
import org.slf4j.LoggerFactory
import org.apache.commons.text.RandomStringGenerator




object Utilities {
    def dir = 
"C:/workspaces/scala/generalstudies/scalaLearningAkka/simple-parent/pisreview/src/main/scala/org/geo/pisreview/chapter7"
 
  val logger = LoggerFactory.getLogger("Utils")
  
   def log(msg:String) {
    logger.info(msg)
  }
  
  def pent(msg:Any) {
    p("enter>>" + msg)
  }
  def pout(msg:Any) {
    p("exit>>" + msg)
  }
  def p(msg:Any) {
    println(msg)
  }
  def p(msg:Any, msg2:Any) {
    println(msg + ":" + msg2)
  }
  
    def prn(msg:Any) {
    println(msg)
  }
  def prn(msg:Any, msg2:Any) {
    println(msg + ":" + msg2)
  }
   def prn(msg:Any, msg2: List[Any]) {
    println(msg + ":" + msg2.mkString("[" , "," , "]"))
  }
  
 
  def pa(msg:Any, msg2:Array[_]) {
    p(msg, msg2.mkString("[",", ","]"))
  }
    def pa(msg:Any, msg2:collection.mutable.ArrayOps[_]) {
    p(msg, msg2.mkString("[",", ","]"))
  }
  def p(symbol: Symbol,msg2:Any) {
    // println(symbol.hashCode())
    println(symbol.name + " " + msg2)
  }
   def prn(symbol: Symbol,msg2:Any) {
    // println(symbol.hashCode())
    println(symbol.name + " " + msg2)
  }
  def cof ( c : Traversable[_] ) {
    p(c);
    p("class of:" + c.getClass.getName )
  }
  
    def cof ( c : AnyRef ) {
    p(c);
    p("class of:" + c.getClass.getName )
  }
    def cof ( msg:Any, c : AnyRef ) {
    p(msg,c);
    
    p("class of:" + c.getClass.getName )
  }
    def cof (msg: Any ,  c : Traversable[_] ) {
    p(msg)
    p(c);
    p("class of:" + c.getClass.getName )
  }
    
    def st() {
      val stackTraceAsArray = Thread.currentThread.getStackTrace
                    stackTraceAsArray.foreach(println)
    }
}