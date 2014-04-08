// These are meant to be typed into the REPL. You can also run
// scala -Xnojline < repl-session.scala to run them all at once.

val numPattern = "[0-9]+".r

val wsnumwsPattern = """\s+[0-9]+\s+""".r

for (matchString <- numPattern.findAllIn("99 bottles, 98 bottles"))
  println(matchString)

val m1 = wsnumwsPattern.findFirstIn("99 bottles, 98 bottles")

numPattern.findPrefixOf("99 bottles, 98 bottles")

wsnumwsPattern.findPrefixOf("99 bottles, 98 bottles")

numPattern.replaceFirstIn("99 bottles, 98 bottles", "XX")

numPattern.replaceAllIn("99 bottles, 98 bottles", "XX")
