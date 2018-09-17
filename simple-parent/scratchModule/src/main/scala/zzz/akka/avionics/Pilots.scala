package zzz.akka.avionics

import akka.actor.{Actor, ActorRef}
import scala.concurrent.duration._
import akka.util.Timeout

object Pilots {
  
  case object ReadyToGo
  case object RelinquishControl
  
}

class Pilot extends Actor {
  import Pilots._
  import Plane._
  
  var controls: ActorRef = context.system.deadLetters
  var copilot:  ActorRef = context.system.deadLetters
  var autopilot: ActorRef = context.system.deadLetters
  
  val copilotName = context.system.settings.config.getString(
      "zzz.akka.avionics.flightcrew.copilotName")
   
      
   def receive = {
//    implicit val timeout = Timeout(1 second)
    case ReadyToGo => 
      context.parent ! GiveMeControl 
      copilot = context.actorSelection("../" + copilotName).resolveOne(1 second)
    
  }
}