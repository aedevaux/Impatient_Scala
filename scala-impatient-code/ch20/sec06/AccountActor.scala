import scala.actors.Actor

case class Deposit(amount: Double)
case class Withdraw(amount: Double)
case class Balance(amount: Double)


class AccountActor extends Actor {
  val delay = 5000

  private var balance = 0.0
  def act() {
    while (true) {
      receive {
        case Deposit(amount) => { 
          Thread.sleep(delay)
          balance += amount; 
          sender ! Balance(balance) 
        }
        case Withdraw(amount) => { balance -= amount; reply(Balance(balance)) }
      }
    }
  }
}

object Main extends App {
  println("Hit Ctrl+C to exit")

  val account = new AccountActor
  account.start

  val reply = account !? Deposit(1000)
  println("Sent with !?")
  reply match {
    case Balance(bal) => println("Current Balance: " + bal)
  }

  val replyFuture = account !! Deposit(1000)
  println("Sent with !!")
  println(replyFuture())
}
