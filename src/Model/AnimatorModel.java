package Model;

import Model.Commands.ICommand;
import Model.Shape.IShape;

// TODO:
//    - Do we add a addShape(IShape... shapes);
//    - Do we add a addAnimation(ICommmand... comnmands);

/**
 * Interface is for an application that helps to create simple but effective 2D animations from
 * shapes. This interface is for the model of the program. It contains all commands that directly
 * modify the model object.
 */
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
   * Adds a command to the animation list.
   *
   * @param command the command being passed through
   * @throws NullPointerException     if the command being passed through is null
   * @throws IllegalArgumentException if the command has conflict with another command in the list
   */
  void addAnimation(ICommand command) throws NullPointerException, IllegalArgumentException;

  /**
   * Removes a command from the animation list.
   *
   * @param command the command being removed
   * @throws NullPointerException     if the command being passed through is null
   * @throws IllegalArgumentException if the shape associated with command does not exist
   *                                  with in the model inventory
   */
  void removeAnimation(ICommand command) throws NullPointerException, IllegalArgumentException;
}
