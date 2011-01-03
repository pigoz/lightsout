package lightsout.views

import scala.swing._
import scala.swing.event._

object GameWindow extends SimpleSwingApplication {
  val frame = new MainFrame {
    contents = new GridPanel(5,5) {
      contents ++= (1 to 25).map({ n =>
        val button = new Button(n.toString)
        gameWindow.listenTo(button)
        button
      })
    }
  }
  
  def gameWindow = this
  
  override def top() = {
    reactions += {
      case ButtonClicked(b) => println("Clicked "+b.text)
    }
    
    frame.title = "lightsout"
    frame.size = new Dimension(500, 500)
    frame.setLocationRelativeTo(frame)
    frame
  }
}