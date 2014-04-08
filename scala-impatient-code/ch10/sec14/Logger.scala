trait Logger {
  def log(msg: String)
}

trait ConsoleLogger extends Logger {
  def log(msg: String) { println(msg) }
}

trait ShortLogger extends Logger {
  val maxLength = 15 // A concrete field
}

/*

$ javap Logger
Compiled from "Logger.scala"
public interface Logger {
  public abstract void log(java.lang.String);
}

$ javap ConsoleLogger
Compiled from "Logger.scala"
public interface ConsoleLogger extends Logger,scala.ScalaObject {
  public abstract void log(java.lang.String);
}

$ javap ConsoleLogger\$class
Compiled from "Logger.scala"
public abstract class ConsoleLogger$class {
  public static void log(ConsoleLogger, java.lang.String);
  public static void $init$(ConsoleLogger);
}

$ javap ShortLogger
Compiled from "Logger.scala"
public interface ShortLogger extends Logger,scala.ScalaObject {
  public abstract void ShortLogger$_setter_$maxLength_$eq(int);

  public abstract int maxLength();
}

$ javap -c ShortLogger\$class
Compiled from "Logger.scala"
public abstract class ShortLogger$class {
  public static void $init$(ShortLogger);
    Code:
       0: aload_0       
       1: bipush        15
       3: invokeinterface #12,  2           // InterfaceMethod ShortLogger.ShortLogger$_setter_$maxLength_$eq:(I)V
       8: return        
}

*/
