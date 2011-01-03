package lightsout.test

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import lightsout.controllers.Game

class GameSpec extends FunSuite with ShouldMatchers {

  private var game: Game = _ 
  
  test("Game is empty when just created") {
    game = new Game
    game should be ('empty)
  }

  test("Tap on blank Game turns on 5 lights") {
    game = new Game
    game.tap(2,2)
    game should have size (5)
  }
  
  test("Tap should have turned on the correct lights") {
    game.get(2,2) should be (true)
    game.get(1,2) should be (true)
    game.get(3,2) should be (true)
    game.get(2,1) should be (true)
    game.get(2,3) should be (true)
  }
  
  test("Tap again should turn off 5 lights") {
    game.tap(2,2)
    game should be ('empty)
  }
  
  test("Tap should work on angle border cases") {
    game.tap(0,0)
    game should have size (3)
    game.get(0,0) should be (true)
    game.get(1,0) should be (true)
    game.get(0,1) should be (true)
    game.tap(0,0)
    game should be ('empty)
  }

  test("Tap should work on edge border cases") {
    game.tap(2,0)
    game should have size (4)
    game.get(2,0) should be (true)
    game.get(1,0) should be (true)
    game.get(3,0) should be (true)
    game.get(2,1) should be (true)
    game.tap(2,0)
    game should be ('empty)
  }

  test("Game should not be empty after it started") {
    game = new Game
    game.start()
    game should(not) be ('empty)
  }
}