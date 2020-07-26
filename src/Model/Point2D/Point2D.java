package Model.Point2D;

/**
 * A class that represents a 2D point. This point is denoted with x and y coordinates.
 */
public class Point2D implements IPoint2D {
  private final double x;
  private final double y;

  /**
   * Constructs a Point2D object when passed x and y coordinates, both doubles.
   *
   * @param x x coordinate of 2D Pointer object, a double
   * @param y y coordinate of 2D Pointer object, a double
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  // TODO: Danielle, do we need this?
  /**
   * A constructor to make a copy of the object.
   *
   * @param point2D a Point2D object
   */
  public Point2D(IPoint2D point2D) {
    this(point2D.getX(), point2D.getY());
  }

  /**
   * Return the x value of the Point2D object.
   *
   * @return the x value of the Point2D object.
   */
  @Override
  public double getX() {
    return this.x;
  }

  /**
   * Return the y value of the Point2D object.
   *
   * @return the y value of the Point2D object.
   */
  @Override
  public double getY() {
    return this.y;
  }

  @Override
  public String toString() {
    return String.format("(%.1f,%.1f)", this.x, this.y);
  }
}
