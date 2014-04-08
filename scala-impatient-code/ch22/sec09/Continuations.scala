// Compile as scalac -P:continuations:enable -Xprint:selectivecps Continuations.scala

import scala.util.continuations._

object Main extends App {
  
  var cont : (Int => Double) = null  
  reset {
    0.5 * shift { 
        k: (Int => Double) => { 
          cont = k 
        } 
    } + 1
  }
  println(cont(10))
  println(cont(20))
}


/*
[[syntax trees at end of selectivecps]]// Scala source: cont3.scala
package <empty> {
  final object Main extends java.lang.Object with App with ScalaObject {
    def this(): object Main = {
      Main.super.this();
      ()
    };
    private[this] var cont: Int => Double = null;
    <accessor> def cont: Int => Double = Main.this.cont;
    <accessor> def cont_=(x$1: Int => Double): Unit = Main.this.cont = x$1;
    scala.util.continuations.`package`.reset[Double, Unit]({
      package.this.shiftR[Int, Double, Unit](((k: Int => Double) => Main.this.cont_=(k))).map[Double](((tmp1: Int) => 0.5.*(tmp1).+(1)))
    });
    scala.this.Predef.println(Main.this.cont.apply(10));
    scala.this.Predef.println(Main.this.cont.apply(20))
  }
}
*/
