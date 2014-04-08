// These are meant to be typed into the REPL. You can also run
// scala -Xnojline < repl-session.scala to run them all at once.

import scala.collection.JavaConversions.mapAsScalaMap
val scores: collection.mutable.Map[String, Int] =
  new java.util.TreeMap[String, Int]

scores += ("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)

import scala.collection.JavaConversions.propertiesAsScalaMap
val props: scala.collection.Map[String, String] = System.getProperties()

import scala.collection.JavaConversions.mapAsJavaMap
import java.awt.font.TextAttribute._ // Import keys for map below
val attrs = Map(FAMILY -> "Serif", SIZE -> 12) // A Scala map
val font = new java.awt.Font(attrs) // Expects a Java map
