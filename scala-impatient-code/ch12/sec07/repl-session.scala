// These are meant to be typed into the REPL. You can also run
// scala -Xnojline < repl-session.scala to run them all at once.

import java.awt.event._
import javax.swing._

var counter = 0

val button = new JButton("Increment")
button.addActionListener(new ActionListener {
  override def actionPerformed(event: ActionEvent) {
    counter += 1
  }
})

implicit def makeAction(action: (ActionEvent) => Unit) =
  new ActionListener {
    override def actionPerformed(event: ActionEvent) { action(event) }
  }

button.addActionListener((event: ActionEvent) => counter += 1)
button.addActionListener((event: ActionEvent) => println(counter))
button.addActionListener((event: ActionEvent) => if (counter > 9) System.exit(0))

val frame = new JFrame
frame.add(button)
frame.pack()
frame.setVisible(true)


