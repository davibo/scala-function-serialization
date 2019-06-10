package io.davibo

class FunctionSerialization(var a: Int) {
  import FunctionSerialization._

  def getA: Int = {
    println(s"getA $a")
    a
  }

  def f1(x: Int)(y: Int): Int = x + y

  def g1(x: Int, y: Int): Int = x + y

  def h1(x: Int): Int => Int = y => x + y

  def getSomeFun: Int => Int = {
//    val someFun = f1(getA) //comile error
//    val someFun = (y: Int) => { g2(this.getA, y) }

//    val someFun = f1(getA) _ //serialization error
//    val someFun = g1(getA, _) //serialization error
//    val someFun = h1(a)

//    val someFun = f2(getA) _
//    val someFun = g2(getA, _)
    val someFun = h2(getA)
    println("Created someFun")
    someFun
  }
}

object FunctionSerialization {
  def f2(x: Int)(y: Int): Int = x + y

  def g2(x: Int, y: Int): Int = x + y

  def h2(x: Int): Int => Int = y => x + y
}
