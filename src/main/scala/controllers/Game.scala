package lightsout.controllers

class Game {
  private val matrix = Array.ofDim[Boolean](5,5)
  private val transform = List((0,0), (-1,0), (1,0), (0,-1), (0,1))

  def isEmpty() = size == 0
  
  def size() = {
    matrix.flatten.foldLeft(0){(r, n) =>
      if(n) r+1 else r
    }
  }

  def start() = {
    val random = new scala.util.Random
    while(isEmpty && random.nextInt(5) != 0)
      tap(random.nextInt(5), random.nextInt(5))
  }

  def tap(x: Int, y: Int) = {
    assert(validCoords(x, y), "("+ x +", "+ y +") invalid tap coordinates")
    transform.foreach( l => {
      val (tx, ty) = l
      negate(x+tx, y+ty)
    })
  }
  
  def get(x: Int, y: Int) = matrix(x)(y)

  private def negate(x: Int, y: Int) = {
    if(validCoords(x, y))
      matrix(x)(y) = !matrix(x)(y)
  }

  private def validCoords(x: Int, y: Int) = x >= 0 && x < 5 && y >= 0 && y < 5

  override def toString = "[Game: matrix="+matrix.toString+"]"
}