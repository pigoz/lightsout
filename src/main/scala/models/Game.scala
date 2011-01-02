package lightsout.models

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
    while(isEmpty && random.nextInt() != 0)
      tap(random.nextInt(6), random.nextInt(6))
  }

  def tap(x: Int, y: Int) = {
    transform.foreach( l => {
      val (tx, ty) = l
      negate(x+tx, y+ty)
    })
  }
  
  def get(x: Int, y: Int) = matrix(x)(y)

  private def negate(x: Int, y: Int) = {
    if(x >= 0 && x < 5 && y >= 0 && y < 5)
      matrix(x)(y) = !matrix(x)(y)
  }

  override def toString = "[Game: matrix="+matrix.toString+"]"
}