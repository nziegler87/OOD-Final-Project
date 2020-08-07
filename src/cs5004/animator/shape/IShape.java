package cs5004.animator.shape;

import java.awt.Color;
import java.awt.Graphics;

import cs5004.animator.model.point2d.IPoint2D;

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
   * Method to set the new coordinates of the object when passed a IPoint2D class.
   *
   * @param endCords the new coordinates of the object
   * @throws IllegalArgumentException if endCords is null
   */
  void setCoordinates(IPoint2D endCords);

  /**
   * Create and return a shape of the same kind as this one, just resized using the provided
   * factor.
   *
   * @param factor a factor used in resizing, a double
   * @return a shape of the same kind as this one, just resized using the provided factor.
   * @throws IllegalArgumentException if scale results in dimensions equal to or less than zero
   */
  IShape scale(double factor) throws IllegalArgumentException;

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
   * @throws IllegalArgumentException if height is not greater than 0
   */
  void setHeight(double height) throws IllegalArgumentException;

  /**
   * Method to create a copy of the object with the same attributes.
   */
  IShape copy();

  /**
   * Method to set the start time of the shape object.
   *
   * @param appearTime the time after which the object should appear on the screen, a double
   * @throws IllegalArgumentException when time is negative
   */
  void setAppearTime(double appearTime) throws IllegalArgumentException;

  /**
   * Method to set the end time of the shape object.
   *
   * @param disappearTime the time after which the object should disappear on the screen, a double
   * @throws IllegalArgumentException when time is negative or less than appear time
   */
  void setDisappearTime(double disappearTime) throws IllegalArgumentException;

  /**
   * Returns the time after which an object should appear on the screen, a double.
   *
   * @return the time after which an object should appear on the screen, a double.
   */
  double getAppearTime();

  /**
   * Returns the time after which an object should not appear on the screen, a double.
   *
   * @return the time after which an object should not appear on the screen, a double.
   */
  double getDisappearTime();

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
   * @throws NullPointerException if the color is null
   */
  void setColor(Color color) throws NullPointerException;

  /**
   * A method for each concrete shape class that contains instructions so that the shape knows
   * how to draw itself for the paintComponent() method.
   *
   * @param g a Graphics object.
   */
  void drawShape(Graphics g);
}
