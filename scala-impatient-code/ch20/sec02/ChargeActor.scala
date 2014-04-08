import scala.actors.Actor

case class Charge(creditCardNumber: Long, merchant: String, amount: Double)

class ChargeActor extends Actor {
  def act() {
    while (true) {
      receive {
        case Charge(ccnum, merchant, amt) => println("Investigate " + ccnum)
      }
    }
  }
}

object Main extends App {
  println("Hit Ctrl+C to exit")

  val fraudControl = new ChargeActor
  fraudControl.start()

  fraudControl ! Charge(4111111111111111L, "Fred's Bait and Tackle", 19.95)
}
