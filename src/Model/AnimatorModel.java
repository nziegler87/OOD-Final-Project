package Model;

import java.util.List;

import Model.Commands.ICommand;
import Model.Shape.IShape;

public interface AnimatorModel {

  /**
   * Adds a shape to the model inventory hashmap.
   *
   * @param shape the shape being added
   * @throws NullPointerException when the shape is null
   * @throws IllegalArgumentException when the shape is not found
   */
  void addShape(IShape shape) throws NullPointerException, IllegalArgumentException;

  /**
   * Removes a shape from the model inventory map.
   *
   * @param shape the shape being removed
   * @throws NullPointerException when the shape is null
   * @throws IllegalArgumentException when the shape is not found
   */
  void removeShape(IShape shape) throws NullPointerException, IllegalArgumentException;

  /**
   * Finds and copies the shape.
   *
   * @param shape the shape being copied
   * @return the shape being searched for
   * @throws NullPointerException when the shape is null
   * @throws IllegalArgumentException when the shape is not found
   */
  IShape copyShape(IShape shape) throws NullPointerException, IllegalArgumentException;

  /**
   * Implements a command class on a shape.
   *
   * @param command the command class being passed in and executed on
   * @return the shape being searched for
   * @param tick the time considered when running the command
   * @throws NullPointerException when the shape is null
   */
  void commandOnShape(ICommand command, IShape shape, double tick) throws NullPointerException;

  /**
   * Returns a list of all of the shapes and their state based on the tick input.
   *
   * @return List<IShape> with the summary of shapes and their state
   */
  List<IShape> getSnapshot();

  /**
   * Returns a summary of the animation, including the shapes in the list and the animation steps.
   *
   * @return a string of the summary
   */
  String getAnimationStatus();

}
