package lightsout.models

class Game {
  private val matrix = Array.ofDim[Boolean](5,5)

  def isEmpty() = {
    matrix.flatten.foldLeft(true){(r, n) =>
      if(n) false else r
    }
  }

  def start() = {
    val random = new scala.util.Random
    while(isEmpty && random.nextInt() != 0)
      tap(random.nextInt(6), random.nextInt(6))
  }

  def tap (x: Int, y: Int) = {
    negate(x, y)
    negate(x-1, y)
    negate(x+1, y)
    negate(x, y-1)
    negate(x, y+1)
  }

  private def negate(x: Int, y: Int) = {
    if(x >= 0 && x < 5 && y >= 0 && y < 5)
      matrix(x)(y) = !matrix(x)(y)
  }

  override def toString = "[Game: matrix="+matrix.toString+"]"
}