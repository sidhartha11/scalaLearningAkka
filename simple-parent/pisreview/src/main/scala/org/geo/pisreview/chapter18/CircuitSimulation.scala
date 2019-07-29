package org.geo.pisreview.chapter18

abstract class CircuitSimulation extends BasicCircuitSimulation{
  
  def halfAdder(a: Wire, b: Wire , s: Wire , c: Wire) = {
    val d , e = new Wire 
    orGate(a,b,d)
    andGate(a,b,c)
    inverter(c,e)
    andGate(d,e,s)
  }
  
  def fullAdder(a: Wire, b: Wire, cin: Wire, sum: Wire, cout: Wire) = {
    val s, c1, c2 = new Wire
    halfAdder(a, cin, s, c1)
    halfAdder(b, s, sum, c2)
    orGate(c1, c2, cout)
  }
}

object MySimulation extends CircuitSimulation {
  
  def InverterDelay = 1 
  def AndGateDelay = 3 
  def OrGateDelay = 5 
  
  def main(args: Array[String]) : Unit = {
   
    val input1, input2, sum, carry = new Wire 
    probe("sum" , sum)
    probe("carry", carry) 
    
    halfAdder(input1,input2, sum, carry)
    
    input1 setSignal true 
    run()
    
    input2 setSignal true 
    run()
    
  }
}