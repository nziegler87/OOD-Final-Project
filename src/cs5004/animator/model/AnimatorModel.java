package cs5004.animator.model;

import cs5004.animator.model.commands.ICommand;
import cs5004.animator.shape.IShape;

import java.util.List;

/**
 * Interface is for an application that helps to create simple but effective 2D animations from
 * shapes. This interface is for the model of the program. It contains all commands that directly
 * modify the model object.
 */
public interface AnimatorModel extends AnimatorModelViewOnly {

  /**
   * Method that sets an array list of four integers, the bounds of the canvas.
   *
   * @param screenSettings an array list of four integers that contains the bounds of the canvas
   */
  void setCanvas(List<Integer> screenSettings);

  /**
   * Adds a shape to the model inventory hashmap.
   *
   * @param shapes the shape(s) being added
   * @throws NullPointerException     when the shape is null
   * @throws IllegalArgumentException when the shape is not found
   */
  void addShape(IShape... shapes) throws NullPointerException, IllegalArgumentException;

  /**
   * Removes a shape from the model inventory map.
   *
   * @param shapes the shape(s) being removed
   * @throws NullPointerException     when the shape is null
   * @throws IllegalArgumentException when the shape is not found
   */
  void removeShape(IShape... shapes) throws NullPointerException, IllegalArgumentException;

  /**
   * Adds a shape to the model inventory hashmap.
   *
   * @param label the name of the shape to find
   * @throws NullPointerException     when the label is null
   * @throws IllegalArgumentException if the shape does not exists
   */
  IShape getShape(String label) throws NullPointerException, IllegalArgumentException;

  /**
   * Adds a command to the animation list.
   *
   * @param commands the command(s) being passed through
   * @throws NullPointerException     if the command being passed through is null
   * @throws IllegalArgumentException if the command has conflict with another command in the list
   */
  void addAnimation(ICommand... commands) throws NullPointerException, IllegalArgumentException;

  /**
   * Removes a command from the animation list.
   *
   * @param commands the command(s) being removed
   * @throws NullPointerException     if the command being passed through is null
   * @throws IllegalArgumentException if the shape associated with command does not exist with in
   *                                  the model inventory
   */
  void removeAnimation(ICommand... commands) throws NullPointerException, IllegalArgumentException;
}
