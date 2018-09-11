package zzz.akka.avionics

import akka.actor.{Props,Actor,ActorLogging}

object Plane {
  // Returns the control surface to the Actor that 
  // asks for them
  case object GiveMeControl
}

// We want the plane to won the Altimeter and we're going to 
// do that by passing in a specific factory we can use to 
// build the Altimeter

class Plane extends Actor with ActorLogging  {
  import Altimeter._
  import Plane._ 

  
  /**
   * This version of actorOf ties the altimeter actor 
   * to the plane Actor as a child.
   */
  val altimeter = context.actorOf (
      Props[Altimeter], "Altimeter")
      
  val controls = context.actorOf(
      Props(new ControlSurfaces(altimeter)),"ControlSurfaces")
      
  def receive = {
    case AltitudeUpdate(altitude) => 
      log info(s"Altitude is now: $altitude")
      
    case GiveMeControl => 
      log info ("Plane giving control.")
      sender ! controls 
  }
  
  import EventSource._
  override def preStart() {
    altimeter ! RegisterListener(self)
  }
}