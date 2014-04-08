// These are meant to be typed into the REPL. You can also run
// scala -Xnojline < repl-session.scala to run them all at once.

class Pair[T <% Comparable[T]](val first: T, val second: T) {
  def smaller = if (first.compareTo(second) < 0) first else second
  override def toString = "(" + first + "," + second + ")"
}

val p = new Pair(4, 2) // Works

// Ordered is nicer--< instead of compareTo

class Pair[T <% Ordered[T]](val first: T, val second: T) {
  def smaller = if (first < second) first else second
  override def toString = "(" + first + "," + second + ")"
}

val p = new Pair(4, 2)
p.smaller

val q = new Pair("Fred", "Brooks")
q.smaller


