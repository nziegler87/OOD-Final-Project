package Model.Shape;

/**
 * An interface that adds ability to set and get width and height dimensions for rectangle objects.
 */
public interface IRectangle extends IShape {

  /**
   * Returns the width of the object.
   *
   * @return the width of the object, a double.
   */
  double getWidth();

  /**
   * Method to set the width of the object when passed a width value.
   *
   * @param width the width of the object, a double.
   *
   * @throws IllegalArgumentException if width is not greater than 0
   */
  void setWidth(double width) throws IllegalArgumentException;

  /**
   * Returns the height of the object.
   *
   * @return the height of the object, a double.
   */
  double getHeight();

  /**
   * Method to set the height of the object when passed a height value.
   *
   * @param height the height of the object, a double.
   *
   * @throws IllegalArgumentException if height is not greater than 0
   */
  void setHeight(double height) throws IllegalArgumentException;
}
