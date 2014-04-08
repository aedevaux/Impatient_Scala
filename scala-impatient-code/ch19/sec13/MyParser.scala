import scala.util.parsing.combinator.syntactical._
import scala.util.parsing.input._

case class Ident(name: String) extends Positional
case class Var(left: Ident, right: Any)

class MyParser extends StandardTokenParsers {
  lexical.reserved += ("var", "true", "false")
  lexical.delimiters += "="

  def value: Parser[Any] = numericLit | "true" | "false" |
    failure("Not a valid value")

  def vardecl: Parser[Var] = ("var" ~> positioned(ident ^^ { Ident(_) }) <~ "=") ~ value ^^ { case i ~ v => Var(i, v) }

  def parseAll[T](p: Parser[T], in: String): ParseResult[T] =
    phrase(p)(new lexical.Scanner(in))
}

object Main extends App {
  val parser = new MyParser
  // Showing the location of an error
  val result = parser.parseAll(parser.value, "Fred")
  result match {
    case f: parser.Failure => println(f.next.pos.line + "." + f.next.pos.column + ": " + f.msg + ": " + f.next.first.chars)
    case _ => println(result.get)
  }

  // Showing the location of an item in a successful parse
  val result2 = parser.parseAll(parser.vardecl, "var n = 42").get
  println(result2)
  result2 match {
    case Var(id, v) => println(id.name + " is at position " + id.pos)
  }
}
