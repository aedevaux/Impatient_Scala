// Compile as scalac -P:continuations:enable -Xprint:selectivecps Visig.scala

import scala util.continuations._

object Main extends App {
  var cont: Unit => String = null

  def visit(a: List[String]): String @cps[String] = {
    if (a.isEmpty) "" else {
      shift {
        k: (Unit => String) => { 
          cont = k
          a.head
        }
      }
      visit(a.tail)
      println("After visit")
      ""
    }
  }

  reset {
    visit(List("Mary", "had", "a", "little", "lamb"))
  }
  println(cont())
  println(cont())
}

/*

[[syntax trees at end of selectivecps]]// Scala source: cont11.scala
package <empty> {
  final object Main extends java.lang.Object with App with ScalaObject {
    def this(): object Main = {
      Main.super.this();
      ()
    };
    private[this] var cont: Unit => String = null;
    <accessor> def cont: Unit => String = Main.this.cont;
    <accessor> def cont_=(x$1: Unit => String): Unit = Main.this.cont = x$1;
    def visit(a: List[String]): scala.util.continuations.ControlContext[String,String,String] = if (a.isEmpty)
      package.this.shiftUnitR[java.lang.String(""), String]("")
    else
      {
        val tmp1$shift: scala.util.continuations.ControlContext[Unit,String,String] = package.this.shiftR[Unit, String, String](((k: Unit => String) => {
          Main.this.cont_=(k);
          a.head
        }));
        if (tmp1$shift.isTrivial)
          {
            val tmp1: Unit = tmp1$shift.getTrivialValue;
            tmp1;
            Main.this.visit(a.tail)
          }
        else
          tmp1$shift.flatMap[String, String, String](((tmp1: Unit) => {
            tmp1;
            Main.this.visit(a.tail)
          }))
      };
    scala.util.continuations.`package`.reset[String, String](Main.this.visit(immutable.this.List.apply[java.lang.String]("Mary", "had", "a", "little", "lamb")));
    scala.this.Predef.println(Main.this.cont.apply(()));
    scala.this.Predef.println(Main.this.cont.apply(()))
  }
}

*/
