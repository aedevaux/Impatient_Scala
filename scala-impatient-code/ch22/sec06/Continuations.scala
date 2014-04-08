// Compile as scalac -P:continuations:enable Continuations.scala

import scala.util.continuations._

object Main extends App {
  var cont : (Unit => Unit) = null  
  var filename = "myfile.txt"
  var contents = ""

  def tryRead(): Unit @cps[Unit] = {
    while (contents == "") {
      try {
        contents = scala.io.Source.fromFile(filename, "UTF-8").mkString
      } catch { case _ => }
      shift { k : (Unit => Unit) => 
        cont = k 
      }
    }
  }

  reset {
    tryRead()
  }

  if (contents == "") {
    println("Try another filename: ");
    filename = readLine
    cont()
  }
  println(contents)
}
