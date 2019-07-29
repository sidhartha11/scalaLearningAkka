package org.geo.pisreview.chapter18

import org.geo.pisreview.Utilities._


abstract class BasicCircuitSimulation extends Simulation {
  
  /**
   * This abstract class declares a few methods that need to be implemented
   * in subclasses 
   * Java versus Scala Note
   * In Scala , an abstract method does not require the keyword abstract
   * 
   * The authors methodology here is to Create an abstract subclass of 
   * the base abstract Simulation class. Then expose some delay functions that must
   * be implemented.  
   * 
   * Also, within this abstract subclass, a nested class representing the objects
   * to be simulated is placed. 
   * 
   */
  def InverterDelay: Int
  def AndGateDelay: Int
  def OrGateDelay: Int
  
  class Wire {
    private var sigVal = false 
    /**
     * Action () => Unit 
     */
    private var actions: List[Action] = List()
    
    def getSignal = 
    {
      pent("getSignal")
      sigVal 
    }
    
    def setSignal(s: Boolean) = {
      pent("setSignal")
      if( s != sigVal )  {
        sigVal = s 
        actions foreach ( _() )
      }
    }
    
    def addAction(a: Action) = {
      pent("addAction")
      actions = a :: actions 
      a()
    }
  }   /** end of class Wire **/
  
  def inverter(input: Wire, output: Wire) = {
    def invertAction() = {
      val inputSig = input.getSignal 
      afterDelay(InverterDelay) {
        output setSignal !inputSig
      }
    } /** invertAction **/
    input addAction invertAction
  } /** inverter **/
  
  def andGate(a1: Wire , a2: Wire , output: Wire) = {
    def andAction() = {
      val a1Sig = a1.getSignal
      val a2Sig = a2.getSignal 
      afterDelay(AndGateDelay) {
        output setSignal ( a1Sig & a2Sig )
      }
    }
    a1 addAction andAction 
    a2 addAction andAction 
  } /** andGate **/
  
  def orGate(o1: Wire, o2: Wire, output: Wire) = {
    def orAction() = {
      val o1Sig = o1.getSignal
      val o2Sig = o2.getSignal
      afterDelay(OrGateDelay) {
        output setSignal ( o1Sig | o2Sig )
      }
    } /** orAction **/
    o1 addAction orAction 
    o2 addAction orAction
  } /** orGate **/
  
  def probe(name: String, wire: Wire) = {
    def probeAction() = {
      p(name + " " + currentTime + " new-value = " + wire.getSignal)
    } /** probeAction **/
    wire addAction probeAction
  }
  
  
  
}