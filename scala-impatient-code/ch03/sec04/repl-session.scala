// These are meant to be typed into the REPL. You can also run
// scala -Xnojline < repl-session.scala to run them all at once.

val a = Array(2, 3, 5, 7, 11)
val result = for (elem <- a) yield 2 * elem

for (elem <- a if elem % 2 == 0) yield 2 * elem

import scala.collection.mutable.ArrayBuffer

def removeAllNegativesExceptTheFirst(a: ArrayBuffer[Int]) = {
  var first = true
  var n = a.length
  var i = 0
  while (i < n) {
    if (a(i) >= 0) i += 1
    else {
      if (first) { first = false; i += 1 }
      else { a.remove(i); n -= 1 }
    }
  }
}

val b = ArrayBuffer(1, -2, -3, 4, -5)
removeAllNegativesExceptTheFirst(b)
b

def removeAllNegativesExceptTheFirst(a: ArrayBuffer[Int]) = {
  val indexes = for (i <- 0 until a.length if a(i) < 0) yield i
  for (j <- (1 until indexes.length).reverse) a.remove(indexes(j))
}

val b = ArrayBuffer(1, -2, -3, 4, -5)
removeAllNegativesExceptTheFirst(b)
b


