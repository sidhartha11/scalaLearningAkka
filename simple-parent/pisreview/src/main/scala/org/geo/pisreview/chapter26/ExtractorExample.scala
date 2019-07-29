package org.geo.pisreview.chapter26
import org.geo.pisreview.Utilities._

/**
 * NOTE: Review Chapter 15 on case classes and matching
 */

/**
 * Extractor returning some fixed elements along with a variable part 
 * occuring at the end. 
 */
object ExpandedEMail {
  def unapplySeq(email: String) : Option[(String, Seq[String])]= {
    
    val parts = email split "@" 
    if ( parts.length == 2 ) 
      Some(parts(0) , parts(1).split("\\.").reverse)
    else 
        None
  }
}
/**
 * Extractor used to match on a variable number of items
 */

object Domain {
  // the injection method (optional )
  def apply(parts: String*): String = 
    parts.reverse.mkString(".")
    
  // THe extraction method ( mandator )
  def unapplySeq(whole: String): Option[Seq[String]] =  {
    p("top of unapplySeq")
    val t = whole.split("\\.").reverse
    p("t" , t.mkString(","  ))
    Some(t)
  }
}
/**
 * Extractor for the case of 1 variable
 */
object Twice {
  def apply(s: String): String = s + s

  def unapply(s: String): Option[String] = {
    val length = s.length / 2
    val half = s.substring(0, length)
    if (half == s.substring(length)) {
      Some(half)
    } else {
      None
    }
  }
}
/**
 * Extractor that returns only returns a boolean value
 * and does not bind any variables
 */
object UpperCase {
  def unapply(s: String): Boolean = s.toUpperCase == s
}
/**
 * Extractor object for email addresses
 * by extending and inheriting from Scala's function type
 */

object EMail extends ((String, String) => String) {
  // The injection method (optional)

  def apply(user: String, domain: String) = user + "@" + domain

  // The extraction method (mandatory)
  def unapply(str: String): Option[(String, String)] = {
    val parts = str split "@"
    if (parts.length == 2) {
      Some(parts(0), parts(1))
    } else {
      None
    }
  }
}
object mainUtils {
  /**
   * Applying all the Extractors , EMail , Twice and UpperCase
   */
  def userTwiceUpper(s: String) = s match {
    /**
     * This says:
     * We have an email of the form
     * abc@domain
     * and the user part is identical halves and is upper case.
     *
     */
    case EMail(Twice(x @ UpperCase()), domain) =>
      "match: " + x + " in domain " + domain
    case _ =>
      "no match"
  }
  
  def isTomInDotCom(s: String): Boolean = s match {
    case EMail("tom" , Domain("com",_*)) => true 
    case _ => false 
  }
}
object ExtractorExample {

  import mainUtils._

  def main(args: Array[String]) {

    def example_1 {
    p("running")
    p("unapply(\"John@effl.ch\")", EMail.unapply("John@effl.ch"))
    p("unapply(\"Johneffl.ch\")", EMail.unapply("Johneffl.ch"))
    val emailaddress = "georgegeorgecurington.com"
    emailaddress match {
      case EMail(user, domain) => p("user=%s, domain=%s".format(user, domain))
      case e                   => p("ouch:" + e)
    }

    EMail.unapply(EMail.apply("jackson", "com")) match {
      case Some((user, domain)) => p("user=%s , domain=%s".format(user, domain))
      case e                    => p("e=%s".format(e))
    }

    EMail.unapply("jackson@com") match {
      case Some((u, d)) => p("apply(u,d)", EMail.apply(u, d))
      case e            => p("fart")
    }

    "oneone" match {
      case Twice(half) => p("got " + half)
    }

    /** testing a boolean extractor **/
    "HELLO" match {
      case UpperCase() => p("TRUE")
      case _           => p("FALSE")
    }
    
    p(userTwiceUpper("DID0@hotmail.com"))
    }
    
    println(isTomInDotCom("tom@sun.com"))
    println(isTomInDotCom("peter@sun.com"))
    println(isTomInDotCom("tom@acm.org"))
    
    val s = "tom@support.epfl.ch"
    val ExpandedEMail ( name , topdom , subdoms @ _*) = s
    p("name" , name)
    p("topdom" , topdom)
    p("subDoms" , subdoms.mkString(","))
    var List(x,y,_*) = List(1,2,3,4,5)
    p("x",x)
    p("y" , y)
    
    

  }
}