package Model.Shape;

import java.awt.Color;
import java.util.Objects;

import Model.Point2D.IPoint2D;

/**
 * A class that represents a rectangle object. The coordinates of a rectangle represent the
 * lower-left corner of the object. In addition to the common parameters of AbstractShape, a
 * rectangle also has width and height parameters, both doubles.
 */
public class Rectangle extends AbstractShape {
  private double width;
  private double height;

  /**
   * Create a rectangle shape object when passed object label, coordinates, color, width,
   * and height.
   *
   * @param label       a label for the object, a string.
   * @param coordinates the coordinates for the object, a IPoint2D object
   * @param color       the color of the object, a Color object
   * @param width       the width of the object, a double
   * @param height      the height of the object, a double
   *
   * @throws IllegalArgumentException if either coordinates or color objects are null or if either
   *                                  width or height are not greater than 0
   */
  public Rectangle(String label, IPoint2D coordinates, Color color, double width, double height,
                   double appearTime, double disappearTime) {
    super(label, coordinates, color, appearTime, disappearTime);
    this.type = "rectangle";
    this.width = width;
    this.height = height;
  }

  /**
   * Returns the width of the object.
   *
   * @return the width of the object, a double.
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * Method to set the width of the object when passed a width value.
   *
   * @param width the width of the object, a double.
   *
   * @throws IllegalArgumentException if width is not greater than 0
   */
  public void setWidth(double width) throws IllegalArgumentException {
    if (width <= 0) {
      throw new IllegalArgumentException("Width cannot be less than 0.");
    }

    this.width = width;
  }

  /**
   * Returns the height of the object.
   *
   * @return the height of the object, a double.
   */
  public double getHeight() {
    return this.height;
  }

  /**
   * Method to set the height of the object when passed a height value.
   *
   * @param height the height of the object, a double.
   *
   * @throws IllegalArgumentException if height is not greater than 0
   */
  public void setHeight(double height) throws IllegalArgumentException {
    if (height <= 0) {
      throw new IllegalArgumentException("Height cannot be less than 0.");
    }

    this.height = height;
  }

  /**
   * Create and return a shape of the same kind as this one, just resized using the provided
   * factor.
   *
   * @param factor a factor used in resizing, a double
   *
   * @return a shape of the same kind as this one, just resized using the provided factor.
   *
   * @throws IllegalArgumentException if factor is not greater than zero
   */
  @Override
  public IShape scale(double factor) throws IllegalArgumentException {
    if (factor <= 0) {
      throw new IllegalArgumentException("Factor must be greater than zero");
    }
    double sqrtFactor = Math.sqrt(factor);
    return new Rectangle(this.label, this.coordinates, this.color, this.width * sqrtFactor,
            this.height * sqrtFactor, this.appearTime, this.disappearTime);
  }

  /**
   * Returns a string representation of the object that includes its name, type, min corner
   * coordinates, height, width, color as RGB, appearTime, and disappearTime.
   *
   * @return a string representation of the object including the information outlined above
   */
  @Override
  public String toString() {
    return String.format("Name: %s\nType: %s\nMin corner: %s, Width: %.1f, Height: %.1f, Color: "
                    + "(%d, %d, %d)\nAppears at t=%.0f\nDisappears at t=%.0f", this.label,
            this.type, this.coordinates.toString(), this.width, this.height,
            this.color.getRed(), this.color.getGreen(), this.color.getBlue(),
            this.appearTime, this.disappearTime);
  }

  /**
   * Method to create a copy of the object with the same attributes.
   */
  @Override
  public IShape copy() {
    return new Rectangle(this.label, this.coordinates, this.color, this.width, this.height,
            this.appearTime, this.disappearTime);
  }

  /**
   * Allows one to compare any shape to another shape and see if they are equal.
   *
   * @param other a shape to compare
   * @return true if they are equal, otherwise false.
   */
  @Override
  public boolean equalsShape(IShape other) {
    if (other instanceof AbstractShape) {
      AbstractShape aShape = (AbstractShape) other;
      return aShape.equalsRectangle(this);
    }
    return false;
  }

  /**
   * Indicates whether some object is a rectangle.
   *
   * @param other an object to check
   * @return true if the object is a rectangle, otherwise false
   */
  @Override
  protected boolean equalsRectangle(Rectangle other) {
    return (this.label.equals(other.label)) && (this.coordinates.equals(other.coordinates))
            && this.color.equals(other.color) && (this.width == other.width)
            && (this.height == other.height) && (this.appearTime == other.appearTime)
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
      return aShape.equalsRectangle(this);
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
    return Objects.hash(label, coordinates, color, width, height, appearTime, disappearTime);
  }

}


