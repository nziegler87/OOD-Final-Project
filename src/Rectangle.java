import java.awt.Color;

/**
 * A class that represents a rectangle object. The coordinates of a rectangle represent the
 * lower-left corner of the object. In addition to the common parameters of AbstractShape, a
 * rectangle also has width and height parameters, both doubles.
 */
public class Rectangle extends AbstractShape {
  private double width;
  private double height;

  // TODO: confirm that width/height requirements are correct. Also check setters below.
  /**
   * Create a rectangle shape object when passed object label, coordinates, color, width,
   * and height.
   *
   * @param label       a label for the object, a string.
   * @param coordinates the coordinates for the object, a IPoint2D object
   * @param color       the color of the object, a Color ENUM
   * @param width       the width of the object, a double
   * @param height      the height of the object, a double
   *
   * @throws IllegalArgumentException if either coordinates or color objects are null or if either
   *                                  width or height are not greater than 0
   */
  public Rectangle(String label, IPoint2D coordinates, Color color, double width, double height) {
    super(label, coordinates, color);
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
   */
  @Override
  public IShape scale(double factor) {
    double sqrtFactor = Math.sqrt(factor);
    return new Rectangle(this.label, this.coordinates, this.color, this.width * sqrtFactor,
            this.height * sqrtFactor);
  }

  /**
   * Returns a string representation of the object that includes its name, type, min corner
   * coordinates, height, width, and color as RGB.
   *
   * @return a string representation of the object including the information outlined above
   */
  @Override
  public String toString() {
    return String.format("Name: %s\nType: %s\nMin corner: %s, Width: %.1f, Height: %.1f, Color: "
                    + "(%d.0, %d.0, %d.0)", this.label, this.type, this.coordinates.toString(),
            this.width, this.height,
            this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }
}
