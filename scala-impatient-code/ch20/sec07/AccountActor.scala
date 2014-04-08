import scala.actors.Actor

case class Deposit(amount: Double)
case class Withdraw(amount: Double)
case class Confirm(amount: Double)

class AccountActor extends Actor {
  private var balance = 0.0
  def act() {
    loop { // while (true) doesn't work here
      react { // Partial function f1
        case Withdraw(amount) =>
          println("Waiting for confirmation")
          react { // Partial function f2
            case Confirm(amt2) => {
              if (amount == amt2) {
                println("Confirming withdrawal of " + amount)
                balance -= amount
                println("Balance is " + balance)
              }
            }
          }
        case Deposit(amount) => balance += amount 
      }
    }
  }
}

object Main extends App {
  println("Hit Ctrl+C to exit")

  val account = new AccountActor
  account.start
  
  account ! Deposit(1000)
  account ! Withdraw(500)
  account ! Confirm(500)
}
