package org.restwithscala.example.model

import spray.json.DefaultJsonProtocol

/**
 * This trait is used in marshalling json object
 * to and from case classes
 */
trait ApplicationProtocol extends DefaultJsonProtocol {
  implicit val appFormat = jsonFormat3(Models.Application)
}

object Models {
  final case class Application(jarUri: String, className: String, args: List[String])

}