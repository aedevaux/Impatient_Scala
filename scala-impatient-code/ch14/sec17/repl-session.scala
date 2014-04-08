// These are meant to be typed into the REPL. You can also run
// scala -Xnojline < repl-session.scala to run them all at once.

val f: PartialFunction[Char, Int] = { case '+' => 1 ; case '-' => -1 }
f('-') 
f.isDefinedAt('0') 
f('0') // Throws MatchError

"-3+4".collect { case '+' => 1 ; case '-' => -1 }
