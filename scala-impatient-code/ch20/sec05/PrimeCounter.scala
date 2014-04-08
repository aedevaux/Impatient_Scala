/*
 * This program demonstrates communication between actors.
 * The program uses actors to count how many primes there are in
 * a given range of integers.
 * 
 * The PrimeCounter sends messages to a continuation actor that
 * is passed in the Compute method.
 *
 * The Summarizer actor is constructed with an actor argument. It
 * sends messages to that actor.
 */

import scala.actors._
import scala.actors.Actor._

case class Compute(data: Seq[Int], result: Channel[Int])

class PrimeCounter extends Actor {
  def isPrime(n: Int) = new java.math.BigInteger("" + n).isProbablePrime(20)

  def act() {
    while (true) {
      receive {
        case Compute(data, continuation) => {
          val answer = data.count(isPrime)
          continuation ! answer // Send the answer to the channel
        }
      }
    }
  }
}

object Main extends App {
  val max = 100000 
  // try with 1000000 
  val nactors = 10
  val groupSize = max / nactors / 10

  val primeCounters = for (i <- 0 until nactors) yield new PrimeCounter
  for (a <- primeCounters) a.start()

  actor {
    val c = new Channel[Int] // A channel to this actor
    
    val groups = (2 to max).grouped(groupSize).zipWithIndex.toSeq
    var count = groups.length
    var total = 0

    for ((g, i) <- groups)  {
      val a = primeCounters(i % primeCounters.length)
      a ! Compute(g, c) // a is an actor, not a channel
    }

    while (count > 0) {
      c.receive { 
        case t => { // The channel receives an Int
          total += t
          count -= 1
        }
      }
    }
    println(total + " primes")
    System.exit(0)
  }
}

