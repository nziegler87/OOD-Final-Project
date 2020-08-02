package cs5004.animator.model.point2d;

import java.util.Objects;

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

  /**
   * Compares a Point2D object to another Point2D object to see if they are equal.
   *
   * @param other another IPoint2D object
   * @return true if the objects are the same, otherwise false
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof Point2D)) {
      return false;
    }

    Point2D otherPoint = (Point2D) other;

    return (this.x == otherPoint.x) && (this.y == otherPoint.y);
  }

  /**
   * Returns the hash code of a Point2D object using the x and y coordinates.
   *
   * @return the hash code of a Point2D object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }

  /**
   * Return a string representation of a Point2D object as (x, y).
   *
   * @return return a string representation of a Point2D object
   */
  @Override
  public String toString() {
    return String.format("(%.1f,%.1f)", this.x, this.y);
  }
}
