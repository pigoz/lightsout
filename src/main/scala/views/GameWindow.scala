package lightsout.views

import scala.swing._
import scala.swing.event._
import scala.collection.mutable.HashMap

import lightsout.controllers.Game
import lightsout.events.LightChanged

object GameWindow extends SimpleSwingApplication {
  def gameWindow = this
  val buttons = new HashMap[(Int, Int), ToggleButton]
  
  val frame = new MainFrame {
    contents = new GridPanel(5,5) {
      contents ++= (0 to 24).map({ n =>
        val button = new ToggleButton(n.toString)
        buttons.put((n/5, n%5), button)
        gameWindow.listenTo(button)
        button
      })
    }
  }
  
  val coords = buttons map {_.swap}
  
  val controller = new Game
  gameWindow.listenTo(controller)

  reactions += {
    case ButtonClicked(b:ToggleButton) => coords.get(b).map(controller.tap(_))
    case LightChanged(x, y, s) => buttons.get((x, y)).map(_.selected = s)
  }

  override def top() = {
    frame.title = "lightsout"
    frame.size = new Dimension(350, 350)
    frame.setLocationRelativeTo(frame)
    frame
  }
  
  override def startup(args: Array[String]) {
    super.startup(args)
    controller.start()
  }
}