package org.geo.pisreview.chapter19

/**
 * A non variant rigid cell class
 * Because of the set funcion, you cannot 
 * make this type covariant , +T , in 
 * class Cell[+T] 
 */
class Cell[T](init: T) {
  private[this] var current = init
  def get = current
  
  def set(x: T) = {
    current = x 
  }
}
object CellExample extends App{
  
  /**
   * arrays in scala are not COVARIANT
   * The following gets a compile time error
   */
//  val a1 = Array("abc")
//  val a2: Array[Any] = a1
  /**
   * however, you can make arrays in scala behave covariatly by casting 
   * the scala array to a java Object Array .. but this can cause 
   * a runtime error if you try put something incompatible in the array's
   * location. 
   */
  val a1 = Array("abc")
  val a2: Array[Object] = a1.asInstanceOf[Array[Object]]
}