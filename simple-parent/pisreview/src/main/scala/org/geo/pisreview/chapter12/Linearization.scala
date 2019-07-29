package org.geo.pisreview.chapter12


class Animal2

trait Furry2 extends Animal2 

trait HasLegs2 extends Animal2 

trait FourLegged2 extends HasLegs2
class Cat2 extends Animal2 with Furry2 with FourLegged2

