import java.awt.Color;
import java.util.Objects;

/**
 * An abstract class that contains all common fields and methods objects that implement IShape.
 */
public abstract class AbstractShape implements IShape {
  protected final String label;
  protected String type;
  protected IPoint2D coordinates;
  public Color color;

  /**
   * Create an abstract shape object when passed object label, coordinates, and color.
   *
   * @param label a label for the object, a string.
   * @param coordinates the coordinates for the object, a IPoint2D object
   * @param color the color of the object, a Color object
   * @throws IllegalArgumentException if either coordinates or color are null
   */
  public AbstractShape(String label, IPoint2D coordinates, Color color)
          throws IllegalArgumentException{

    try {
      Objects.requireNonNull(coordinates);
      Objects.requireNonNull(color);
    } catch (NullPointerException npe) {
      throw new IllegalArgumentException("Coordinates and color cannot be null.");
    }

    this.label = label;
    this.coordinates = coordinates;
    this.color = color;
  }

  /**
   * Returns the label that is assigned to the IShape object.
   *
   * @return the label of the IShape object, a string.
   */
  @Override
  public String getLabel() {
    return this.label;
  }

  /**
   * Returns the type of the IShape object.
   *
   * @return the type of the IShape object, a string.
   */
  @Override
  public String getType() {
    return this.type;
  }

  /**
   * Returns the x, y coordinates of the object as a Point2D object.
   *
   * @return the x, y coordinates of the object as a Point2D object.
   */
  @Override
  public IPoint2D getCoordinates() {
    return new Point2D(this.coordinates);
  }

  /**
   * Set the x, y coordinates of the object.
   *
   * @param x the x coordinate of the object, a double.
   * @param y the y coordinate of the object, a double.
   */
  @Override
  public void setCoordinates(double x, double y) {
    this.coordinates = new Point2D(x, y);
  }

  /**
   * Returns the color of the object.
   *
   * @return the color of the object, a Color object
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Sets the color of the object.
   *
   * @param color the color of the object, a Color object
   * @throws IllegalArgumentException if the passed color is null
   */
  @Override
  public void setColor(Color color) throws IllegalArgumentException {

    try {
      Objects.requireNonNull(color);
    }
    catch (NullPointerException npe) {
      throw new IllegalArgumentException("Color cannot be null.");
    }

    this.color = color;
  }
}
