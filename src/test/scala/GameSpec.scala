package lightsout.test

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class GameSpec extends FunSuite with ShouldMatchers {

    private val game = new lightsout.models.Game

    test("Game is empty when just created") {
      game should be ('empty)
    }

    test("Game should not be empty after it started") {
      game.start()
      game should(not) be ('empty)
    }

}