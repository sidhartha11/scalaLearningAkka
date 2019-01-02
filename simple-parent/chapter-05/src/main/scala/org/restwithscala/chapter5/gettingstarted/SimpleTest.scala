package org.restwithscala.chapter5.gettingstarted

object SimpleTest extends App {
  def crypt(key: String, message: String): String = {
    val keyi = Integer.parseInt(key, 2)
    for ( c <- message ) yield ( c ^ keyi).toChar
  }
  
  val r = crypt("1101101" , "roger")
  println("r = %s, class = %s".format(r,r.getClass))
}