// These are meant to be typed into the REPL. You can also run
// scala -Xnojline < repl-session.scala to run them all at once.

import java.io._

val file = new File("repl-session.zip")
val in = new FileInputStream(file)
val bytes = new Array[Byte](file.length.toInt)
in.read(bytes)
in.close()

printf("Zip files starts with %c%c, the initials of their inventor.\n",
        bytes(0), bytes(1))
