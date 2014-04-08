// These are meant to be typed into the REPL. You can also run
// scala -Xnojline < repl-session.scala to run them all at once.

import scala.collection.JavaConversions.bufferAsJavaList
import scala.collection.mutable.ArrayBuffer

val command = Buffer("ls", "-al", "/home/cay")
val pb = new ProcessBuilder(command) // Scala to Java

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.mutable.Buffer

val cmd : Buffer[String] = pb.command() // Java to Scala

cmd == command
