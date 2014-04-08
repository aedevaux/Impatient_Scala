/*

This code won't compile. Can you identify the problem from the error message?

$ scalac -P:continuations:enable Continuations.scala
Continuations.scala:9: error: type mismatch;
 found   : Any @scala.util.continuations.cpsParam[Unit,java.lang.String]
 required: Any @scala.util.continuations.cpsParam[Any,java.lang.String]
    if (scala.util.Random.nextBoolean()) {
    ^
one error found

*/

import scala.util.continuations._

object Main extends App {
  val result = reset { 
    if (scala.util.Random.nextBoolean()) {
      shift { 
        k: (Unit => Unit) => {
          "Exit"
        }
      }
    } 
    else "End"
  }

  println(result)
}

