// Compile as scalac -P:continuations:enable Continuations.scala

import scala.util.continuations._

object Main extends App {
  
  var cont : (Int => Double) = null  
  reset {
    0.5 * shift { 
        k: (Int => Double) => { 
          cont = k 
        } 
    } + 1
  }
  println(cont(10))
  println(cont(20))
}
