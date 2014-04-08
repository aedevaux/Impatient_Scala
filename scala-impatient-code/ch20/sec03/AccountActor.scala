import scala.actors.Actor

case class Deposit(amount: Double)
case class Withdraw(amount: Double)

class AccountActor extends Actor {
  private var balance = 0.0
  def act() {
    while (true) {
      receive {
        case Deposit(amount) => { balance += amount; println(balance) }
        case Withdraw(amount) => { balance -= amount; println(balance) }
        case _ => println("Huh?")
      }
    }
  }
}

object Main extends App {
  println("Hit Ctrl+C to exit")

  val account = new AccountActor
  account.start

  new Thread {
    override def run {
      for (i <- 1 to 100) account ! Deposit(i)
    }
  }.start

  new Thread {
    override def run {
      for (i <- (1 to 100).reverse) account ! Withdraw(i)
    }
  }.start
}
