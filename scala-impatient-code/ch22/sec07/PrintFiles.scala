// Compile as scalac -P:continuations:enable PrintFiles.scala

import scala.util.continuations._
import java.io._

object PrintFiles extends App {
  var cont : (Unit => Unit) = null
  def processDirectory(dir : File) : Unit @cps[Unit] = {
    val files = dir.listFiles
    var i = 0
    while (i < files.length) {
      val f = files(i)
      i += 1
      if (f.isDirectory)
        processDirectory(f)
      else {
        shift {
          k: (Unit => Unit) => {
            cont = k // ➋
          }
        } // ➎
        println(f)
      }
    }
  }
  reset {
    processDirectory(new File("/")) // ➊
  } // ➌
  for (i <- 1 to 100) cont() // ➍
}
