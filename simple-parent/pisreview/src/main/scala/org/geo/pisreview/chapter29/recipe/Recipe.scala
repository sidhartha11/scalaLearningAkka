package org.geo.pisreview.chapter29.recipe

class Recipe(
    val name: String , 
    val ingredients: List[Food],
    val instructions: String
    )
{
  override def toString = name
}