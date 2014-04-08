// These are meant to be typed into the REPL. You can also run
// scala -Xnojline < repl-session.scala to run them all at once.

import scala.actors._
import scala.actors.Actor._

val actor1 = actor {
  while (true) {
    receive {
      case "Hi" => println("Hello")
      case "Bye" => exit()
    }
  }
}

actor1 ! "Hi"

actor1 ! "Bye"

val actor2 = new Actor {
  override def exceptionHandler = {
    case e: IllegalArgumentException => println(e)
  }
  
  override def act() {
    while (true) {
      receive {
        case "Hi" => throw new IllegalArgumentException("Hi")
        case "Bye" => throw new IllegalStateException("Bye")
      }
    }
  }
}

actor2.start()

actor2 ! "Hi"

actor2 ! "Bye" // Terminates the actor by causing an exception



