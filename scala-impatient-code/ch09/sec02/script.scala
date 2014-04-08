// Type these commands into the REPL, or run scala script.scala 

import scala.io.Source

val source = Source.fromFile("values.txt", "UTF-8")
val tokens = source.mkString.split("\\s+")

val numbers = for (w <- tokens) yield w.toDouble
println("Sum: " + numbers.sum)

print("How old are you? ")
val age = readInt()
println("Next year, you will be " + (age + 1))
