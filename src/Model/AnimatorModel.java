package Model;

import Model.Commands.ICommand;
import Model.Shape.IShape;

public interface AnimatorModel extends AnimatorModelViewOnly {

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
   * Adds an animation to the animation history list.
   *
   * @param command
   */
  void addAnimation(ICommand command);
}
