import scala.math._

class Fraction(n: Int, d: Int) {
  val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d);
  val den: Int = if (d == 0) 0 else d * sign(d) / gcd(n, d);
  override def toString = num + "/" + den
  def sign(a: Int) = if (a > 0) 1 else if (a < 0) -1 else 0
  def gcd(a: Int, b: Int): Int = if (b == 0) abs(a) else gcd(b, a % b)

  def *(other: Fraction) = new Fraction(num * other.num, den * other.den)
}

object Fraction {
  def apply(n: Int, d: Int) = new Fraction(n, d)
}

object FractionConversions {
  implicit def int2Fraction(n: Int) = Fraction(n, 1)
  implicit def fraction2Double(f: Fraction) = f.num * 1.0 / f.den
  implicit def fraction2String(f: Fraction) = f.toString
}

object Main extends App {
  import FractionConversions._

  println(sqrt(Fraction(1, 4))) // calls fraction2Double
  println(Fraction(1, 4).replace("/", "÷")) // calls fraction2Float
  println(3 * Fraction(4, 5)) // Calls int2Fraction(3)
}

/*
$ scalac -Xprint:typer MyProg.scala

  final object Main extends java.lang.Object with App with ScalaObject {
    def this(): object Main = {
      Main.super.this();
      ()
    };
    import FractionConversions._;
    scala.this.Predef.println(scala.math.`package`.sqrt(FractionConversions.fraction2Double(Fraction.apply(1, 4))));
    scala.this.Predef.println(FractionConversions.fraction2String(Fraction.apply(1, 4)).replace("/", "÷"));
    scala.this.Predef.println(3.*(FractionConversions.fraction2Double(Fraction.apply(4, 5))))
  }
*/

