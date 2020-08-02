package cs5004.animator.model.shape;

import java.awt.Color;
import java.util.Objects;

import cs5004.animator.model.point2d.IPoint2D;

/**
 * A class that represents an Oval object. The coordinates of an oval represent the object's center
 * point. In addition to the common parameters of AbstractShape, an oval also has xRadius and
 * yRadius parameters, both doubles.
 */
public class Oval extends AbstractShape {
  private double xRadius;
  private double yRadius;

  /**
   * Create an Oval object when passed object label, coordinates, color, xRadius, and yRadius.
   *
   * @param label       a label for the object, a string.
   * @param coordinates the coordinates for the object, a IPoint2D object
   * @param color       the color of the object, a Color object
   * @param xRadius     the xRadius of the object, a double
   * @param yRadius     the yRadius of the object, a double
   * @throws NullPointerException if the coordinates or color are null
   * @throws IllegalArgumentException if either coordinates or color objects are null or if either
   *                                  xRadius or yRadius are not greater than 0
   */
  public Oval(String label, IPoint2D coordinates, Color color, double xRadius, double yRadius,
              double appearTime, double disappearTime)
          throws NullPointerException, IllegalArgumentException {
    super(label, coordinates, color, appearTime, disappearTime);
    if (xRadius <= 0 || yRadius <= 0) {
      throw new IllegalArgumentException("xRadius or yRadius must be greater than 0.");
    }
    this.type = "oval";
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  /**
   * Returns the xRadius of the object.
   *
   * @return the xRadius of the object, a double.
   */
  @Override
  public double getWidth() {
    return this.xRadius;
  }

  /**
   * Returns the yRadius of the object.
   *
   * @return the yRadius of the object, a double.
   */
  public double getHeight() {
    return this.yRadius;
  }

  /**
   * Method to set the xRadius of the object when passed a xRadius value.
   *
   * @param xRadius the xRadius of the object, a double.
   * @throws IllegalArgumentException if xRadius is not greater than 0
   */
  public void setWidth(double xRadius) throws IllegalArgumentException {
    if (xRadius <= 0) {
      throw new IllegalArgumentException("Width cannot be less than 0.");
    }

    this.xRadius = xRadius;
  }

  /**
   * Method to set the yRadius of the object when passed a yRadius value.
   *
   * @param yRadius the yRadius of the object, a double.
   * @throws IllegalArgumentException if yRadius is not greater than 0
   */
  public void setHeight(double yRadius) throws IllegalArgumentException {
    if (yRadius <= 0) {
      throw new IllegalArgumentException("Height cannot be less than 0.");
    }
    this.yRadius = yRadius;
  }

  /**
   * Create and return a shape of the same kind as this one, just resized using the provided
   * factor.
   *
   * @param factor a factor used in resizing, a double
   * @return a shape of the same kind as this one, just resized using the provided factor.
   * @throws IllegalArgumentException if scale results in dimensions equal to or less than zero
   */
  @Override
  public IShape scale(double factor) throws IllegalArgumentException {
    double sqrtFactor = Math.sqrt(factor);
    return new Oval(this.label, this.coordinates, this.color, this.xRadius * sqrtFactor,
            this.yRadius * sqrtFactor, this.appearTime, this.disappearTime);
  }

  /**
   * Returns a string representation of the object that includes its name, type, min corner
   * coordinates, xRadius, yRadius, color as RGB, appearTime, and disappearTime.
   *
   * @return a string representation of the object including the information outlined above
   */
  @Override
  public String toString() {

    // convert RGB to percentages
    double red = (double) this.color.getRed() / 255;
    double green = (double) this.color.getGreen() / 255;
    double blue = (double) this.color.getBlue() / 255;

    return String.format("Name: %s\nType: %s\nCenter: %s, X radius: %.1f, Y radius: %.1f, Color: "
                    + "(%.1f,%.1f,%.1f)\nAppears at t=%.0f\nDisappears at t=%.0f", this.label,
            this.type, this.coordinates.toString(), this.xRadius, this.yRadius,
            red, green, blue, this.appearTime, this.disappearTime);
  }

  /**
   * Method to create a copy of the object with the same attributes.
   */
  @Override
  public IShape copy() {
    return new Oval(this.label, this.coordinates, this.color, this.xRadius, this.yRadius,
            this.appearTime, this.disappearTime);
  }

  /**
   * Indicates whether some object is an oval.
   *
   * @param other an object to check.
   * @return true if the object is an oval, otherwise false
   */
  @Override
  protected boolean equalsOval(Oval other) {
    return (this.label.equals(other.label)) && (this.coordinates.equals(other.coordinates))
            && this.color.equals(other.color) && (this.xRadius == other.xRadius)
            && (this.yRadius == other.yRadius) && (this.appearTime == other.appearTime)
            && (this.disappearTime == other.disappearTime);
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @param other the reference object with which to compare.
   * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof AbstractShape) {
      AbstractShape aShape = (AbstractShape) other;
      return aShape.equalsOval(this);
    }
    return false;
  }

  /**
   * Returns a hash code value for the object.
   *
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(label, coordinates, color, xRadius, yRadius, appearTime, disappearTime);
  }
}

