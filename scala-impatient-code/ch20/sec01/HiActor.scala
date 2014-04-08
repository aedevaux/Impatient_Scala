import scala.actors.Actor
import scala.actors.Actor._

class HiActor extends Actor {
  def act() {
    while (true) {
      receive {
        case "Hi" => println("Hello")
      }
    }
  }
}

object Main extends App {
  println("Hit Ctrl+C to exit")

  val actor1 = new HiActor
  actor1.start()

  actor1 ! "Hi"

  val actor2 = actor {
    while (true) {
      receive {
        case "Hi" => println("Hello")
      }
    }
  }

  actor2 ! "Hi"
}
