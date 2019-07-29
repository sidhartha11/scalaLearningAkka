package org.geo.pisreview.chapter30

import org.geo.pisreview.Utilities._

/**
 * Trait representing an abstract Tree
 */
trait Tree[+T] {
  def elem: T
  def left: Tree[T]
  def right: Tree[T] 
}

/**
 * implementations of trait Tree[T]
 */
/**
 * To define an empty tree we simply extend the trait Tree
 * and disallow any operation on it. 
 * No need to override equals and hashcode here because there 
 * is only one EmptyTree and it is definitely equal to itself.
 * Thus, it is ok for such a tree to inherit equals and hash from 
 * AnyRef. 
 */
object EmptyTree extends Tree[Nothing]  {
  def elem = 
    throw new NoSuchElementException("EmptyTree.elem")
  def left = 
    throw new NoSuchElementException("EmptyTree.left")
  def right = 
    throw new NoSuchElementException("EmptyTree.right")
  override def toString = "Empty"
}
/**
 * A branch represents a tree with an element and a right and left child
 * class Branch thusly mixes in trait Tree and declares all the appropriate
 * instance variables, elem of type T.
 * 
 */
class Branch[+T] (
    val elem: T, 
    val left: Tree[T] , 
    val right: Tree[T] 
    ) extends Tree[T] {
  
  override def equals(other: Any) = other match {
    case that: Branch[_] => (that canEqual this) && 
    this.elem == that.elem &&
    this.left == that.left && 
    this.right == that.right 
    
    case _ => false 
  }
  
  def canEqual(other: Any) = other.isInstanceOf[Branch[_]]
  
  override def hashCode: Int = (elem, left, right).##
  
  override def toString = "Branch: [elem:" + 
  elem + " ,left:" + left + " ,right:" + right + "]"
}

object TreeParameterized extends App {
     val b1 = new Branch[List[String]](Nil, EmptyTree, EmptyTree)
     p("b1" , b1)
     val b2 = new Branch[List[Int]](Nil,EmptyTree,EmptyTree)
     p("b2" , b2)
     p("b1 == b2" , b1 == b2)
     
     val b3: Branch[List[Int]] = new Branch(List[Int](1,2) ,EmptyTree , EmptyTree )
}