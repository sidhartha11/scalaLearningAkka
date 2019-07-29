package org.geo.pisreview.chapter18

import org.geo.pisreview.Utilities._

abstract class Simulation {
  /** commmon type used in
   *  all concrete implementations
   */
  /**
   * This defines a type that is a function
   * that takes 0 parameters and returns Unit
   */
  type Action = () => Unit 
  
  /**
   * case class WorkItem takes a delay parameter.
   * And an Action () => Unit 
   */
  case class WorkItem(time: Int, action: Action)
  
  private var curtime = 0 
  def currentTime: Int = curtime 
  
  /**
   * the list of work to do .. 
   */
  private var agenda: List[WorkItem] = List()
  
  private def insert(ag: List[WorkItem], item: WorkItem): List[WorkItem] = {
    
    /**
     * this will recursively find the correct position based on time
     * to insert a WorkItem into the list 
     */
    if ( ag.isEmpty || item.time < ag.head.time  ) item :: ag 
    else ag.head :: insert(ag.tail, item)
  }
  
  def afterDelay(delay: Int) (block: => Unit) = {
    val item = WorkItem(currentTime + delay, () => block)
    agenda = insert(agenda,item)
  }
  
  private def next() = {
    (agenda: @unchecked) match {
      case item :: rest  => 
        agenda = rest 
        curtime = item.time
        item.action()
    }
  }
  
  def run() = {
    afterDelay(0) {
      println("**** simulation started, time = " + 
          currentTime + " ***")
    }
    while (!agenda.isEmpty) next()
  }
  
}