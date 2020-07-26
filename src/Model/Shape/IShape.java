package Model.Shape;

import java.awt.Color;

import Model.Point2D.IPoint2D;

/**
 * A class that contains the methods that all shapes should support.
 */
public interface IShape {

  /**
   * Returns the label that is assigned to the IShape object.
   *
   * @return the label of the IShape object, a string.
   */
  String getLabel();

  /**
   * Returns the type of the IShape object.
   *
   * @return the type of the IShape object, a string.
   */
  String getType();

  /**
   * Returns the x, y coordinates of the object as a Point2D object.
   *
   * @return the x, y coordinates of the object as a Point2D object.
   */
  IPoint2D getCoordinates();

  /**
   * Set the x, y coordinates of the object.
   *
   * @param x the x coordinate of the object, a double.
   * @param y the y coordinate of the object, a double.
   */
  void setCoordinates(double x, double y);

  /**
   * Create and return a shape of the same kind as this one, just resized using the provided factor.
   *
   * @param factor a factor used in resizing, a double
   *
   * @return a shape of the same kind as this one, just resized using the provided factor.
   *
   * @throws IllegalArgumentException if factor is not greater than zero
   */
  IShape scale(double factor);


  /**
   * Returns the color of the object.
   *
   * @return the color of the object, a Color object.
   */
  Color getColor();

  /**
   * Sets the color of the object.
   *
   * @param color the color of the object, a Color ENUM value
   *
   * @throws IllegalArgumentException if the passed color is null
   */
  void setColor(Color color) throws IllegalArgumentException;

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
