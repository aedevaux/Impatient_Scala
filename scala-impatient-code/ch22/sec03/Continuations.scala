// Compile as scalac -P:continuations:enable Continuations.scala

import scala.util.continuations._

object Main extends App {
  
  var cont : (Unit => Unit) = null  
  reset { 
    println("Before shift")
    shift { 
        k : (Unit => Unit) => { 
          cont = k 
          println("Inside shift")
        } 
    }
    println("After shift")
  }
  println("After reset")
  cont()
}
