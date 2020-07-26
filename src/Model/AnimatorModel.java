package Model;

import java.awt.*;

import Model.Shape.IShape;

public interface AnimatorModel {

  /**
   * Adds a shape to the model inventory hashmap.
   *
   * @param label the the label associated with the shape
   * @param shape the shape that will be added to the map
   * @throws IllegalArgumentException if the shape already exists
   */
  void addShape(String label, IShape shape) throws IllegalArgumentException;

  /**
   * Removes a shape from the model inventory map.
   *
   * @param label the label associated with the shape
   * @throws IllegalArgumentException when the shape is not found
   */
  void removeShape(String label);

  /**
   * Finds and returns the shape using the label of the shape.
   *
   * @param label the label associated with the shape
   * @return the shape being searched for
   * @throws IllegalArgumentException when the shape is not found
   */
  IShape getShape(String label);

  /**
   * Moves the shape to a new set of coordinates.
   *
   * @param label the label associated with the shape
   * @param x the new x coordinate for the shape
   * @param y the new y coordinate for the shape
   */
  void moveShape(String label, double x, double y);

  /**
   * Changes the color of the shape.
   *
   * @param label the label associated with the shape
   * @param color the new color for the shape
   */
  void changeColor(String label, Color color);

  /**
   * Changes a shape to a new shape type.
   *
   * @param label the label associated with the shape
   * @param newShape the new shape to change to
   */
  void changeShape(String label, IShape newShape);

  /**
   * Returns a summary of the animation, including the shapes in the list and the animation steps.
   *
   * @return a string of the summary
   */
  String getAnimationStatus();

}
