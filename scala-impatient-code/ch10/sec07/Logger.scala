trait Logger {
  def log(msg: String)
  def info(msg: String) { log("INFO: " + msg) }
  def warn(msg: String) { log("WARN: " + msg) }
  def severe(msg: String) { log("SEVERE: " + msg) }
}

class Account {
  protected var balance = 0.0
}

class SavingsAccount extends Account with Logger {
  def withdraw(amount: Double) {
    if (amount > balance) severe("Insufficient funds")
    else balance -= amount
  }

  // More methods ...

  override def log(msg: String) { println(msg) }
}

object Main extends App {
  val acct1 = new SavingsAccount
  acct1.withdraw(100) 
}

