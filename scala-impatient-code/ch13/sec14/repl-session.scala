// These are meant to be typed into the REPL. You can also run
// scala -Xnojline < repl-session.scala to run them all at once.

import scala.math._

val powers = (0 until 1000).view.map(pow(10, _))

powers(100)

val powers = (0 until 1000).view.map(n => { println(n) ; pow(10, n) })

powers(100) // Prints 100 in the method call
powers(100) // Prints 100 again; the method is called twice

// Contrast with streams

def powers(n: Int): Stream[Double] = { println(n) ; pow(10, n) } #:: powers(n + 1)

val powerStream = powers(0) // Calls method with 0
powerStream(100) // Calls method with 1 to 100
powerStream(100) // Doesn't call the method again

(0 to 1000).map(pow(10, _)).map(1 / _)

(0 to 1000).view.map(pow(10, _)).map(1 / _).force

(0 to 1000).map(x => pow(10, -x))
