package io.davibo

import java.io.{ByteArrayOutputStream, ObjectOutputStream}

object Main {
  def main(args: Array[String]): Unit = {
    val fs = new FunctionSerialization(2)
    val someFun = fs.getSomeFun

    doAddition(someFun)
    fs.a += 1
    doAddition(someFun)

    serializeFun(someFun)
    printFun(someFun)
  }

  def doAddition(someFun: Int => Int): Unit = {
    println(someFun(3))
  }

  def printFun(someFun: Int => Int): Unit = {
    println("Values captured in the function")
    someFun.getClass.getDeclaredFields.map(f => {
      f.setAccessible(true)
      s"${f.getName}: ${f.getType} = ${f.get(someFun)}"
    }).foreach(println)
  }

  def serializeFun(someFun: Int => Int): Unit = {
    try {
      new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(someFun)
      println("Successful serialization")
    } catch {
      case ex: Exception => println(s"${ex.getClass.getName}: ${ex.getMessage}")
    }
  }
}
