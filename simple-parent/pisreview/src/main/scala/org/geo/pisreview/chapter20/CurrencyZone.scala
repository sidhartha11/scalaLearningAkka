package org.geo.pisreview.chapter20


///**
// * converter between different Currency Types
// */
//object Converter {
//  var exchangeRate = Map 
//  (
//      "USD"  -> Map("USD" ->    1.0     , "EUR" -> 0.7596,
//                    "JPY" ->    1.211   , "CHF" -> 1.223),
//                    
//      "EUR"  -> Map("USD" ->    1.316   , "EUR" -> 1.0   ,
//                    "JPY" ->    1.594   , "CHF" -> 1.623),
//                    
//      "JPY"  -> Map("USD" ->    0.8257  , "EUR" -> 0.6272,
//                    "JPY" ->    1.0     , "CHF" -> 1.018),
//                    
//      "CHF"  -> Map("USD" ->    0.8108  , "EUR" -> 0.6160,
//                    "JPY" ->    0.982   , "CHF" -> 1.0   )
//  )
//}
////abstract class CurrencyZone {
//////  import Converter._
////  
////  type Currency <: AbstractCurrency 
////  val CurrencyUnit: Currency
////  
////  def make(x: Long): Currency 
////  
////  abstract class AbstractCurrency {
////    val amount: Long
////    def designation: String
////    
////    override def toString =
////    ((amount.toDouble / CurrencyUnit.amount.toDouble)
////        formatted("%." + decimals(CurrencyUnit.amount) + "f")
////        + " " + designation)
////
////    private def decimals(n: Long): Int = 
////      if ( n == 1 ) 0 else 1 + decimals(n/10)
////      
////    def + (that: Currency): Currency = 
////      make(this.amount + that.amount)
////      
////    def * (x: Double): Currency = 
////      make( (this.amount * x).toLong)
////      
////      def from(other: CurrencyZone#AbstractCurrency): Currency = 
////      make(math.round(
////        other.amount.toDouble * Converter.exchangeRate
////          (other.designation)(this.designation)))
////
////  }
////}
//abstract class CurrencyZone {
//  type Currency <: AbstractCurrency
//  def make(x: Long): Currency
//  abstract class AbstractCurrency {
//    val amount: Long
//    def designation: String 
//    def + (that: Currency): Currency = 
//      make(this.amount + that.amount)
//    def * (x: Double): Currency = 
//      make((this.amount * x).toLong)
//    def -(that: Currency): Currency = 
//      make(this.amount - that.amount)
//    def /(that: Double) = 
//      make((this.amount / that).toLong)
//    def /(that: Currency) = 
//      this.amount.toDouble / that.amount
//    def from(other: CurrencyZone#AbstractCurrency): Currency = 
//      make(math.round(
//        other.amount.toDouble * Converter.exchangeRate
//          (other.designation)(this.designation)))
//    private def decimals(n: Long): Int = 
//      if (n == 1) 0 else 1 + decimals(n / 10)
//    override def toString = 
//      ((amount.toDouble / CurrencyUnit.amount.toDouble)
//       formatted ("%." + decimals(CurrencyUnit.amount) + "f")
//       + " " + designation)
//  }
//  val CurrencyUnit: Currency
//}
//
//
/////** US currency **/
////object US extends CurrencyZone {
////  /**
////   * Here within a subclass of CurrencyZone, we create 
////   * an abstract class of type Dollar to extend AbstractCurrency.
////   * We let the abstract type Currency defined in CurrencyZone be 
////   * implemented by this abstract class Dollar , ie. we tie the knot.
////   * We create an anonomous class from AstractCurrency, implementing the 
////   * abstract variable "amount" 
////   * The type of money defined here is US.Dollar 
////   */
////  abstract class Dollar extends AbstractCurrency {
////    def designation = "USD"
////  }
////  
////  type Currency = Dollar 
////  
////  def make(cents: Long) = new Dollar {
////    val amount = cents
////  }
////  
////  val Cent = make(1)
////  val Dollar = make(100)
////  val CurrencyUnit = Dollar
////}
////
////object Europe extends CurrencyZone {
////  abstract class Euro extends AbstractCurrency {
////    def designation = "EUR" 
////  }
////  
////  type Currency = Euro 
////  def make(cents: Long) = new Euro {
////    val amount = cents 
////  }
////  
////  val Cent = make(1)
////  val Euro = make(100)
////  val CurrencyUnit = Euro 
////}
////
////object Japan extends CurrencyZone {
////  abstract class Yen extends AbstractCurrency {
////    def designation = "JPY"
////  }
////  
////  type Currency = Yen
////  def make(yen: Long) = new Yen {
////    val amount = yen 
////  }
////  
////  val Yen = make(1)
////  val CurrencyUnit = Yen
////}
//
//object US extends CurrencyZone {
//  abstract class Dollar extends AbstractCurrency {
//    def designation = "USD"
//  }
//  type Currency = Dollar
//  def make(cents: Long) = new Dollar {
//    val amount = cents
//  }
//  val Cent = make(1)
//  val Dollar = make(100)
//  val CurrencyUnit = Dollar
//}
//
//object Europe extends CurrencyZone {
//  abstract class Euro extends AbstractCurrency {
//    def designation = "EUR"
//  }
//  type Currency = Euro
//  def make(cents: Long) = new Euro {
//    val amount = cents
//  }
//  val Cent = make(1)
//  val Euro = make(100)
//  val CurrencyUnit = Euro
//}
//
//object Japan extends CurrencyZone {
//  abstract class Yen extends AbstractCurrency {
//    def designation = "JPY"
//  }
//  type Currency = Yen
//  def make(yen: Long) = new Yen {
//    val amount = yen
//  }
//  val Yen = make(1)
//  val CurrencyUnit = Yen
//}
abstract class CurrencyZone {
  type Currency <: AbstractCurrency
  def make(x: Long): Currency
  abstract class AbstractCurrency {
    val amount: Long
    def designation: String 
    def + (that: Currency): Currency = 
      make(this.amount + that.amount)
    def * (x: Double): Currency = 
      make((this.amount * x).toLong)
    def -(that: Currency): Currency = 
      make(this.amount - that.amount)
    def /(that: Double) = 
      make((this.amount / that).toLong)
    def /(that: Currency) = 
      this.amount.toDouble / that.amount
    def from(other: CurrencyZone#AbstractCurrency): Currency = 
      make(math.round(
        other.amount.toDouble * Converter.exchangeRate
          (other.designation)(this.designation)))
    private def decimals(n: Long): Int = 
      if (n == 1) 0 else 1 + decimals(n / 10)
    override def toString = 
      ((amount.toDouble / CurrencyUnit.amount.toDouble)
       formatted ("%." + decimals(CurrencyUnit.amount) + "f")
       + " " + designation)
  }
  val CurrencyUnit: Currency
}

object US extends CurrencyZone {
  abstract class Dollar extends AbstractCurrency {
    def designation = "USD"
  }
  type Currency = Dollar
  def make(cents: Long) = new Dollar {
    val amount = cents
  }
  val Cent = make(1)
  val Dollar = make(100)
  val CurrencyUnit = Dollar
}

object Europe extends CurrencyZone {
  abstract class Euro extends AbstractCurrency {
    def designation = "EUR"
  }
  type Currency = Euro
  def make(cents: Long) = new Euro {
    val amount = cents
  }
  val Cent = make(1)
  val Euro = make(100)
  val CurrencyUnit = Euro
}

object Japan extends CurrencyZone {
  abstract class Yen extends AbstractCurrency {
    def designation = "JPY"
  }
  type Currency = Yen
  def make(yen: Long) = new Yen {
    val amount = yen
  }
  val Yen = make(1)
  val CurrencyUnit = Yen
}

object Converter {
  var exchangeRate = Map(
    "USD" -> Map("USD" -> 1.0   , "EUR" -> 0.7596, 
                 "JPY" -> 1.211 , "CHF" -> 1.223),
    "EUR" -> Map("USD" -> 1.316 , "EUR" -> 1.0   , 
                 "JPY" -> 1.594 , "CHF" -> 1.623),
    "JPY" -> Map("USD" -> 0.8257, "EUR" -> 0.6272, 
                 "JPY" -> 1.0   , "CHF" -> 1.018),
    "CHF" -> Map("USD" -> 0.8108, "EUR" -> 0.6160, 
                 "JPY" -> 0.982 , "CHF" -> 1.0  )
  )
}

object testCurrencyZone extends App {
  val jap_cur = Japan.Yen from US.Dollar * 100
  println(jap_cur);
  val eu_cur = Europe.Euro from jap_cur
  println(eu_cur)
  val us_cur = US.Dollar from eu_cur
  println(us_cur)
  val us_cur2 = US.Dollar * 100 + us_cur
  println(us_cur2)
  // val bad = US.Dollar + Europe.Euro 
  
}