package zzz.akka.investigation

import akka.actor.{Actor, Props,ActorSystem }

/** the messages processed by this actor **/
case class Gamma(g: String)
case class Beta(b: String, g: Gamma)
case class Alpha(b1: Beta, b2: Beta)

class MyActor extends Actor  {
  
  def receive = {
    case "Hello" => println("Hi")
    
    case 42 => 
      println("I don't know the question." + 
          "Go ask the Earth Mark II.")
          
    case s: String => 
      println(s"You sent me a string: $s")
      
    case Alpha(Beta(b1, Gamma(g1)) , Beta(b2, Gamma(g2))) =>
      println(s"beta1: $b1, beta2: $b2, gamma1: $g1, gamma2: $g2")
      
    case _ =>
      println("huh?")

  }
}

object MyActorMain {
  /** first create an ActorSystem **/
  val system = ActorSystem("MyActor")
  /** next create a reference to an actor in the system **/
  val actor = system.actorOf(Props[MyActor])
  
  def send(x: Any) {
    println("sending %s".format(x))
    actor ! x
  }
  
  def main(args: Array[String]) {
    send(42)
    send("Sammy")
    send(32.4)
    send(Alpha(Beta("older" , Gamma("gamma")),Beta("older2",Gamma("gamma"))))
  }
}