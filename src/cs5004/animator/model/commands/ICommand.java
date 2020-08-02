package cs5004.animator.model.commands;

import cs5004.animator.model.shape.IShape;

/**
 * An interface that contains the commands that all command classes should implement.
 */
public interface ICommand {

  /**
   * Method to execute the animation on the passed IShape object and return the object as it would
   * appear during the animation at the given time state, or tick.
   *
   * @param shape a shape on which to perform the animation
   * @param tick  the time considered when running the command
   * @return an IShape that with the state of the object during this command at the designated tick
   * @throws NullPointerException if shape is null
   * @throws IllegalArgumentException if the command has issues executing the function
   */
  IShape execute(IShape shape, double tick) throws NullPointerException, IllegalArgumentException;

  // tested
  /**
   * Returns the command type.
   *
   * @return the type of command, as a string
   */
  String getCommandType();

  // tested
  /**
   * Returns a copy of the shape object stored in the command object.
   *
   * @return a copy of the shape object stored in the command object.
   */
  IShape getShape();

  // tested
  /**
   * Returns the start time of the animation object.
   *
   * @return the start time of the animation object, a double.
   */
  double getStartTime();

  // tested
  /**
   * Return the end time of the animation object.
   *
   * @return the end time of the animation object, a double.
   */
  double getEndTime();

  // tested
  /**
   * Returns the command details as a string.
   *
   * @return str the declarative animation details of the command
   */
  String toString();
}
