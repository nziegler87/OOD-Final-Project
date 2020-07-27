package Model;

import java.awt.*;

import Model.Commands.ICommand;
import Model.Shape.IShape;

public interface AnimatorModel {

  /**
   * Adds a shape to the model inventory hashmap.
   *
   * @param label the the label associated with the shape
   * @param shape the shape that will be added to the map
   * @throws IllegalArgumentException if the shape already exists
   */
  void addShape(String label, IShape shape) throws NullPointerException, IllegalArgumentException;

  /**
   * Removes a shape from the model inventory map.
   *
   * @param label the label associated with the shape
   * @throws NullPointerException when either the command or the label are null
   * @throws IllegalArgumentException when the shape is not found
   */
  void removeShape(String label) throws NullPointerException, IllegalArgumentException;

  /**
   * Finds and returns the shape using the label of the shape.
   *
   * @param label the label associated with the shape
   * @return the shape being searched for
   * @throws NullPointerException when either the command or the label are null
   * @throws IllegalArgumentException when the shape is not found
   */
  IShape getShape(String label) throws NullPointerException, IllegalArgumentException;

  /**
   * Implements a command class on a shape.
   *
   * @param command the command class being passed in and executed on
   * @param label the label associated with the shape
   * @throws NullPointerException when either the command or the label are null
   */
  void commandOnShape(ICommand command, String label) throws NullPointerException;

  /**
   * Returns a summary of the animation, including the shapes in the list and the animation steps.
   *
   * @return a string of the summary
   */
  String getAnimationStatus();

}
