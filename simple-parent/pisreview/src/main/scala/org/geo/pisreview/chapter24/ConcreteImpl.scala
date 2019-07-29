package org.geo.pisreview.chapter24

import org.geo.pisreview.Utilities._
import java.nio.file.Path
import java.io.File
import java.nio.file.Files
import java.util.concurrent.ThreadLocalRandom

object ConcreteImpl extends App {
  
  def walkTree {
    val dir = 		"C:\\languages\\scala\\" + 
		"src\\scala-2.12.6\\src\\library\\scala\\collection"
    
    val start: Path = new File(dir).toPath()
    val walking = Files.walk(start)
//    walking.map(p => p.toFile())
//    .filter((f: File) => f.isFile())
//    .forEach(println)
    walking.forEach(println)
//    walking.map(p => p.toFile())
//    .filter(_.isFile) 
//    .map(f => f.getAbsolutePath() + " " + f.length())
//    .foreach(println)
  }
  
  def testVector {
    /**
     * create an empty vector 
     * Vectors are good when you need to access in "good" time 
     * any location within the collection. 
     */
    val vec = scala.collection.immutable.Vector.empty
    val vec2 = vec :+ 1 :+ 2
    p("vec :+ 1 :+ 2" ,
        vec2)
    val vec3 = 100 +: vec2
    p("100 +: vec2" , 100 +: vec2)
    p("vec3(0)" , vec3(0))
  }
  
  def testStack {
    val stack = scala.collection.immutable.Stack.empty
    p("scala.collection.immutable.Stack.empty" , 
        scala.collection.immutable.Stack.empty )
    val hasOne = stack.push(1)
    p("stack.push(1)" , hasOne)
    p("hasOne.top" , hasOne.top)
    p("hasOne" , hasOne)
    p("hasOne.pop" , hasOne.pop)
    p("hasOne" , hasOne)
    val pop = hasOne.pop
    p("pop" , pop)
    val peek = hasOne.top
    p("peek"  , peek ) 
  }
  
  def testQueue {
    /**
     * create an empty immutable queue
     */
    val empty = scala.collection.immutable.Queue[Int]()
    p("empty" , empty)
    /**
     * append to a queue with enqueue
     */
    val has1 = empty.enqueue(1)
    p("has1" , has1)
    val has123 = has1.enqueue(List(1,2))
    p("has123" , has123)
    val (element,has23) = has123.dequeue 
    p("element:" + element + ", has23:" + has23)
    
  }
  def testTree {
    /**
     * Create an empty TreeSet 
     */
    val set = collection.immutable.TreeSet.empty[Int]
    var setNew = set + 1 + 2 + 3 
    p("set" , set)
    p("set + 1 + 2 + 3" , setNew )
  }
  
  def testBitSet {
    val bits = scala.collection.immutable.BitSet.empty 
    val moreBits = bits + 3 + 4 + 4 
    val t1 = bits + 3 + 2 + 0 
    p("moreBits" , moreBits ) 
    p("t1" , t1)
  }
  
  def testListMap {
    val map = collection.immutable.ListMap (
        1 -> "one" , 2 -> "two" )
        p("map" , map)
        p("map(2)" , map(2))
  }
  

  def testArrayBuffer {
    /**
     * Similar to java's ArrayList 
     */
    val buf = collection.mutable.ArrayBuffer.empty[Int]
    p("buf" , buf)
    p("buf += 1" , buf += 1)
    p("buf" , buf)
    p("buf += 10" , buf += 10)
    p("buf.toArray" , buf.toArray.mkString("[" , "," , "]"))
  }
  /**
   * ListBuffer Similar to ArrayBuffer except that 
   * one use an Array as a backing store while the other 
   * uses linked allocation
   */
  def testListBuffer {
    val buf = collection.mutable.ListBuffer.empty[Int]
       p("buf" , buf)
    p("buf += 1" , buf += 1)
    p("buf" , buf)
    p("buf += 10" , buf += 10)
    p("buf.toArray" , buf.toList)
  }
  
  def testStringBuilder {
    val buf = new StringBuilder 
    p("buf" , buf)
    p("buf += 'a' " , buf += 'a' )
    p("buf ++= \"bcdef\"" , buf ++= "bcdef")
    p("buf" , buf)
    p("buf.toString" , buf.toString)
  }
  
  def testQueues {
    val queue = new scala.collection.mutable.Queue[String]()
    p("queue" , queue)
    
    p("queue += \"a\"", queue += "a")
    p("queue ++= List(b,c)" , queue ++= List("b" , "c"))
    p("queue dequeue" , (queue , queue dequeue))
  }
  
  def testMutableStack {
    val stack = new scala.collection.mutable.ArrayStack[Int]
    p("stack" , stack)
    p("stack.push(1)" , stack.push(1))
    p("stack" , stack)
    p("stack.push(2)" , stack.push(2))
    p("stack" , stack)
    p("stack.top" , stack.top)
    p("stack", stack)
    p("stack.pop" , stack.pop)
    p("stack" , stack)
  }
  
  def testHashMap {
    val map = collection.mutable.HashMap.empty[Int, String]
    val map2 = new collection.mutable.HashMap[Int,String]
    p("map" , map)
    p("map2" , map2)
    p("map += (1 -> \"make a web site\")", 
        map += (1 -> "make a web site"))
    p("map += ( 3 -> profit )" , map += ( 3 -> "profit" ))
    p("map(1)" , map(1))
    p("map contans 2" , map contains 2)
  }
  
  
  def testArray {
    val a1 = Array(1,2,3)
    p("a1" , a1.mkString )
    val a2 = a1 map ( _ * 3 )
    p("a2" , a2.mkString)
    val a3 = a2 filter ( _ % 2 != 0 )
    p("a3" , a3.mkString)
    a3.reverse
    p("a3" , a3.mkString)
    val a4 = a3.reverse
    p("a4" , a4.mkString)
  }
  def testArray2 {
    
    /**
     * In order for seq of Seq[Int] to be assigned an array, it 
     * is wrapped in a WrappedArray via impliciit conversion mechanism
     */
    val a1 = Array(1,2,3)
    val seq: Seq[Int] = a1 
    cof(seq)
    val a4: Array[Int] = seq.toArray 
    cof(a4)
    p(a4.mkString("[" , "," , "]"))
    pa("a4" , a4)
    p("seq" , seq)
    p("a1 == a4" , a1 == a4)
    /**
     * ArrayOps explanation
     */
    p("seq.reverse" , seq.reverse)
    cof(seq.reverse)
    val ops: collection.mutable.ArrayOps[Int] = a1 
    pa("ops" ,ops)
    pa("ops.reverse" , ops.reverse)
    /** normaly, you never first convert to an ArrayOps object 
     *  This is done implicitly for you, and you only need call
     *  reverse on the array object 
     */
    val zx = intArrayOps(a1).reverse
    p("intArrayOps(a1).reverse" , intArrayOps(a1).reverse)
    /**
     * The implicit conversion here works silently under neath the 
     * covers:
     * array -> intArrayOps -> reverse -> aray 
     */
    pa("intArrayOps(a1).reverse" , intArrayOps(a1).reverse)
  }
  
  def testArray3 {
    /**
     * Generic array creation in Scala 
     * The code below does not work.
     * Compiler complains: Cannot find type T
     */
//    def evenElems[T]( xs: Vector[T]): Array[T] = {
//      val arr = new Array[T]((xs.length + 1)/2)
//      for ( i <= 0 until xs.length by 2)
//        arr(i/2) = xs(i)
//        
//        arr
//    }
    import scala.reflect.ClassTag
    def evenElems[T: ClassTag](xs: Vector[T]): Array[T] = {
      val arr = new Array[T]((xs.length + 1)/2)
      for ( i <- 0 until xs.length by 2)
        arr(i / 2) = xs(i)
        arr
    }
    
    def wrap[U: ClassTag](xs: Vector[U]) =  evenElems(xs)
    
    var a = evenElems[Int](Vector(2,3,4))
    cof("a" , a)
    var a2 = evenElems[String](Vector("2","3","4"))
    cof("a2" , a2)
    
    var a3 = wrap(Vector("2","3","4"))
    cof("a3" , a3)
    
  }
  def testString {
    
    val str = "hello"
    p("str" , str)
    p("str.reverse" , str.reverse)
    p("str.map(_.toUpper)" , str.map(_.toUpper))
    p("str drop 3" , str drop 3)
    p("str slice (1,4)"  , str slice (1,4))
    val s: Seq[Char] = str
    cof("s: Seq[Char] = str" , str)
  }
  
  def testEquality {
    import collection.mutable.{HashMap , ArrayBuffer }
    val buf = ArrayBuffer(1,2,3)
    cof("buf" , buf)
    
    val map = HashMap(buf -> 3)
    cof("map" , map)
    p("map(buf)" , map(buf))
    p("buf(0) += 1" ,(buf, buf(0) += 1))
    try { 
    p("map(buf)" , map(buf))
    }
    catch {
      case t: Throwable =>  p("ooops")
    }
  }
  
  def testView {
    def lazyMap[T,U](coll: Iterable[T], f: T => U) = 
      new Iterable[U] {
      def iterator = coll.iterator map f 
    }
    
    def isPalindrome(x: String) = x == x.reverse 
    def findPalindrome(s: Seq[String]) = s find isPalindrome
    
    val m = Map("a" -> "a".hashCode , "b" ->"b".hashCode , "c" -> "c".hashCode)
    val lazyItr = lazyMap(Iterable("a" , "b" ,"c") , k => m(k))
    p("lazyItr" , lazyItr)
    
    val v = Vector(1 to 10: _*)
    p("Vector( 1 to 10:_*)" , v)
    val v2 = v map ( _ + 1 ) map ( _ * 2 )
    p("v map ( _ + 1 ) map ( _ * 2 )" , v2 )
    
    /**
     * transforming the mapping operations to a view initially to 
     * avoid an intermediate object creation
     */
    val toview =  ( v.view map ( _ + 1 ) map ( _ * 2 )).force 
    p("toview" , toview)
    
    val buffer = scala.collection.mutable.ArrayBuffer(
        "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p",
        "q","r","s","t","u","v","w","x","y","z")
    println("generating words=====")
    val buf =  new StringBuilder
    val words =  new scala.collection.mutable.ListBuffer[String]()
    for ( z <- 0 until 500 ) {
    val x = for ( i <- 0 until 10 ) {
    val x = ThreadLocalRandom.current().nextInt(0, 14)
    val c = buffer(x).toCharArray
    buf += c(0)
    }
    
    buf += ' '
    words += buf.toString
    }

    println("words=" + words)

    
    
    val b = findPalindrome(words take 1000000)
    println("b=" + b)
  }
  
  def testMisc {
    val t = Traversable() // empty traversable 
    p("t" , t)
    val l = List() // empty list 
    p("l" , l)
    val l1 = List(1.0, 2.0) 
    /**
     * l1 expands to 
     * List.apply(1.0, 2.0 )
     */
    p("l1" , l1)
    val l2 = List.apply(1.0,2.0)
    p("l2" , l2)
    p("l2 == l1" , l2  == l1)
    p("l2 eq l1" , l2 eq l1)
    val v  = Vector(1.0 , 2.0)
    p("v" , v)
    val i = Iterator(1,2,3)
    p("i" , i)
    val s = Set ("dog" , "cat" , "bird")
    p("s" , s)
    val h = scala.collection.mutable.HashSet("dog" , "cat" , "bird")
    p("h" , h)
    val m = Map('a' -> 7 , 'b' -> 0)
    p("m" , m)
    /**
     * Apply methods in companion object reverts the the default implementation
     * when used on a trait.
     */
  }
  
  
  
  
  testMisc
}