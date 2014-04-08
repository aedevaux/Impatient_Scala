// Compile as scalac -P:continuations:enable Continuations.scala

import scala.util.continuations._

object Main extends App {
  val result1 = reset { shift { k: (String => String) => "Exit" }; "End" }
  println(result1)

  val result2 = reset { if (false) shift { k: (String => String) => "Exit" }; else "End" }
  println(result2)

  val result = reset { 
    if (scala.util.Random.nextBoolean()) {
      shift { 
        k: (String => String) => {
          "Exit"
        }
      }
    } 
    else "End"
  }

  println(result)
}

