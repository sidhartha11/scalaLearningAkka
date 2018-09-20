package org.geo.akkaconcurrency.chapter8

import akka.actor.{Actor,ActorSystem,Props}
import akka.util.Timeout
import scala.concurrent.duration._


object MyActor {
  case object Initialize
}
/**
 * Chapter 8 examples and illustrations
 */

class MyActor extends Actor {
  import MyActor._
  
  /**
   * Lifecycle function, preStart.
   * Perform any initialization setup here
   * Often this is a good spot to send yourself a message
   * such as self ! Initialize
   */
  override def preStart(): Unit = {
    println("in preStart()")
    self ! Initialize
  }
  
  /**
   * Lifecycle function, postStop.
   * The message pump is shut down
   * so any message you send to yourself will only go to the
   * dead letter office, but if you'd like to clean up any 
   * resources,such as Database sessions, now's the time to
   * do it.
   */
  
  override def postStop(): Unit = {
    println("in postStop")
  }
  
  /**
   * Partial function to receive case class/object messages 
   */
  def receive = {
    // do your usual processing here. For example:
    case Initialize => 
      println("initialization message received")
      // call your own post start initialization function here
  }
}

object Chapter8 extends App {
  
  /** start up MyActor for a test run **/
   implicit val timeout = Timeout(5 seconds)
  /**
   * Creates a system of actors used to create child actors
   */
  val system = ActorSystem("MyActorTesting")
  /** create a actorRef to the Plane object **/
  val myActor = system.actorOf(Props[MyActor], "MyActor")
  Thread.sleep(2000)
  system.terminate()
  
  
}