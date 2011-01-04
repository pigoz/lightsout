package lightsout.events
import scala.swing.event.Event

case class LightChanged(x: Int, y: Int, state: Boolean) extends Event