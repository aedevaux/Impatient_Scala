import scala.actors._
import scala.actors.Actor._

case class Work(n: Int)

class Worker extends Actor {
  override def act() {
    trapExit = true

    while (true) {
      receive {
        case Work(n) => 
          if (n % 3 == 0)
            throw new NullPointerException
          else if (n % 3 == 1) 
            exit('tired)
        
        // Exit if the supervisor exits
        case Exit(linked, reason) => {
          println("Worker: " + linked + " exited with reason " + reason)
          exit()
        }
      }    
    }
  }
}

class Supervisor(workers: Worker*) extends Actor {
  override def act() {
    trapExit = true

    for (w <- workers) {
      link(w)    
      w.start()
    }

    while (true) {
      receive {
        case Exit(linked, UncaughtException(_, _, _, _, cause)) => { 
          println("Supervisor: " + linked + " terminated with exception " + cause)
          exit('oh_noes)
        }

        case Exit(linked, reason) =>
          println("Supervisor: " + linked + " exited with reason " + reason)
      }
    }
  }
}

object Main extends App {
  val w1 = new Worker
  val w2 = new Worker
  val w3 = new Worker
  val supervisor = new Supervisor(w1, w2, w3)
  supervisor.start()
  w1 ! Work(1)
  w2 ! Work(2)
  w3 ! Work(3)
}
