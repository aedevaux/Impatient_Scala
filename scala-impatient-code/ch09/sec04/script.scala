// Type these commands into the REPL, or run scala script.scala 

import scala.io.Source

val source1 = Source.fromURL("http://horstmann.com", "UTF-8")
println(source1.mkString.length + " bytes")
val source2 = Source.fromString("Hello, World!")
println(source2.mkString.length + " bytes")
  // Reads from the given string—useful for debugging
println("What is your name?")
val source3 = Source.stdin
  // Reads from standard input
println("Hello, " + source3.getLines.next)
