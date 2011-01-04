package lightsout.controllers

import scala.swing.Publisher

class Game(N: Int) extends Publisher {
  private val matrix = Array.ofDim[Boolean](N,N)
  private val transform = List((0,0), (-1,0), (1,0), (0,-1), (0,1))
  
  def this() = this(5)

  def isEmpty() = size == 0
  
  def size() = {
    matrix.flatten.foldLeft(0){(r, n) =>
      if(n) r+1 else r
    }
  }

  def start() = {
    val random = new scala.util.Random
    while(isEmpty)
      (0 to 9).foreach { _ => tap(random.nextInt(N), random.nextInt(N)) }
  }

  def tap(t: Tuple2[Int, Int]): Unit = tap(t._1, t._2)

  def tap(x: Int, y: Int): Unit = {
    assert(validCoords(x, y), "("+ x +", "+ y +") invalid tap coordinates")
    transform.foreach( l => {
      val (tx, ty) = l
      negate(x+tx, y+ty)
    })
  }
  
  def get(x: Int, y: Int) = matrix(x)(y)

  private def negate(x: Int, y: Int) = {
    if(validCoords(x, y)){
      matrix(x)(y) = !matrix(x)(y)
      publish(new lightsout.events.LightChanged(x, y, get(x, y)))
    }
  }

  private def validCoords(x: Int, y: Int) = x >= 0 && x < N && y >= 0 && y < N

  override def toString = "[Game: matrix="+matrix.toString+"]"
}