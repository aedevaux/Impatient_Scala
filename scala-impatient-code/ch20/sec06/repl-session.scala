// These are meant to be typed into the REPL. You can also run
// scala -Xnojline < repl-session.scala to run them all at once.

import scala.actors._
import scala.actors.Actor._

case class Result(data: Int)

val seconds = 5
actor {
  receiveWithin(seconds * 1000) {
    case Result(data) => println("Received " + data)
    case TIMEOUT => println("Timed out")
  }
}
