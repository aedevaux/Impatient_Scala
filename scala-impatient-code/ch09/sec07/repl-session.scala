// These are meant to be typed into the REPL. You can also run
// scala -Xnojline < repl-session.scala to run them all at once.

import java.io.File

def subdirs(dir: File): Iterator[File] = {
  val children = dir.listFiles.filter(_.isDirectory)
  children.toIterator ++ children.toIterator.flatMap(subdirs _)
}

val home = new File(System.getProperty("user.home"))
val dirs = subdirs(home)
for (d <- dirs.take(20))
  println(d)

import java.nio.file._
implicit def makeFileVisitor(f: (Path) => Unit) = new SimpleFileVisitor[Path] {
  override def visitFile(p: Path, attrs: attribute.BasicFileAttributes) = {
    f(p)
    FileVisitResult.CONTINUE
  }
}

Files.walkFileTree(new File("..").toPath, (f: Path) => println(f))


