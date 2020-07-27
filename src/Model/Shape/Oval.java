package Model.Shape;

import java.awt.Color;

import Model.Point2D.IPoint2D;

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
   *
   * @throws IllegalArgumentException if either coordinates or color objects are null or if either
   *                                  xRadius or yRadius are not greater than 0
   */
  public Oval(String label, IPoint2D coordinates, Color color, double xRadius, double yRadius) {
    super(label, coordinates, color);

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
   *
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
   *
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
    return new Oval(this.label, this.coordinates, this.color, this.xRadius * sqrtFactor,
            this.yRadius * sqrtFactor);
  }

  /**
   * Returns a string representation of the object that includes its name, type, min corner
   * coordinates, xRadius, yRadius, and color as RGB.
   *
   * @return a string representation of the object including the information outlined above
   */
  @Override
  public String toString() {
    return String.format("Name: %s\nType: %s\nCenter: %s, X radius: %.1f, Y radius: %.1f, Color: "
                    + "(%d, %d, %d)", this.label, this.type, this.coordinates.toString(),
            this.xRadius, this.yRadius,
            this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }

  /**
   * Method to create a copy of the object with the same attributes.
   */
  @Override
  public IShape copy() {
    return new Rectangle(this.label, this.coordinates, this.color, this.xRadius, this.yRadius);
  }
}
