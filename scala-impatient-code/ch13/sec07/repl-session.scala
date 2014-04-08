// These are meant to be typed into the REPL. You can also run
// scala -Xnojline < repl-session.scala to run them all at once.

Vector(1, 2, 3) :+ 5
1 +: Vector(1, 2, 3)

import scala.collection.mutable.ArrayBuffer

val numbers = ArrayBuffer(1, 2, 3)
numbers += 5

var numbers = Set(1, 2, 3)
numbers += 5 // Sets numbers to the immutable set numbers + 5

Set(1, 2, 3) - 2

numbers ++ Vector(1, 2, 7, 9)

numbers -- Vector(1, 2, 7, 9)
