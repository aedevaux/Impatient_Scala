trait Logged {
  def log(msg: String) { }
}

trait ConsoleLogger extends Logged {
  override def log(msg: String) { println(msg) }
}

trait TimestampLogger extends Logged {
  override def log(msg: String) {
    super.log(new java.util.Date() + " " + msg)
  }
}

trait ShortLogger extends Logged {
  val maxLength = 15 // See Section 10.8 on fields in traits
  override def log(msg: String) {
    super.log(
      if (msg.length <= maxLength) msg else msg.substring(0, maxLength - 3) + "...")
  }
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
  val acct1 = new SavingsAccount with ConsoleLogger with
    TimestampLogger with ShortLogger
  val acct2 = new SavingsAccount with ConsoleLogger with
    ShortLogger with TimestampLogger
  acct1.withdraw(100) 
  acct2.withdraw(100)
}

