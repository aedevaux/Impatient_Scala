trait Logged {
  def log(msg: String) { }
}

trait ConsoleLogger extends Logged {
  override def log(msg: String) { println(msg) }
}

class Account {
  protected var balance = 0.0
}

class SavingsAccount extends Account with Logged {
  def withdraw(amount: Double) {
    if (amount > balance) log("Insufficient funds")
    else balance -= amount
  }

  // More methods ...
}

object Main extends App {
  val acct1 = new SavingsAccount
  acct1.withdraw(100) // No log message is printed

  println("Overdrawing acct2");
  val acct2 = new SavingsAccount with ConsoleLogger
  acct2.withdraw(100)
}

