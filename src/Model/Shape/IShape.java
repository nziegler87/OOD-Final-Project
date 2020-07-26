package Model.Shape;

import java.awt.*;

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
   * @return the color of the object, a Color ENUM value.
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

}