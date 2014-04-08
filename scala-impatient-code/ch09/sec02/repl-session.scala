// These are meant to be typed into the REPL. You can also run
// scala -Xnojline < repl-session.scala to run them all at once.

import scala.io.Source

def readWord(iter: BufferedIterator[Char]) = {
  val builder = new StringBuilder
  while (iter.hasNext && !Character.isWhitespace(iter.head))
    builder += iter.next()
  builder
} 

val source = Source.fromFile("mary.txt", "UTF-8")
val iter = source.buffered

while (iter.hasNext) {
  print(readWord(iter))
  if (iter.next == '\n') println() else print(",")
}

source.close()
