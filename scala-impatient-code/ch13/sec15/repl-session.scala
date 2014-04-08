// These are meant to be typed into the REPL. You can also run
// scala -Xnojline < repl-session.scala to run them all at once.

import scala.collection.JavaConversions.propertiesAsScalaMap

val props: scala.collection.mutable.Map[String, String] = System.getProperties()

props("com.horstmann.scala") = "impatient"

System.getProperty("com.horstmann.scala")


