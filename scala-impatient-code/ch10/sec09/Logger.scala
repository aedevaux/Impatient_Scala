trait Logged {
  def log(msg: String) { }
}

trait ConsoleLogger extends Logged {
  override def log(msg: String) { println(msg) }
}

trait ShortLogger extends Logged {
  val maxLength: Int // An abstract field
  override def log(msg: String) {
    super.log(
      if (msg.length <= maxLength) msg else msg.substring(0, maxLength - 3) + "...")
  }
}

class Account {
  protected var balance = 0.0
}

class SavingsAccount extends Account with Logged {
  var interest = 0.0
  def withdraw(amount: Double) {
    if (amount > balance) log("Insufficient funds")
    else balance -= amount
  }

  // More methods ...
}

object Main extends App {
  val acct = new SavingsAccount with ConsoleLogger with ShortLogger {
    val maxLength = 20
  }
  acct.withdraw(100) 
    // Log message is not truncated because maxLength is 20
}

