package Model.Commands;

import Model.Shape.IShape;

/**
 * An interface that contains the command that all command classes should implement.
 */
public interface ICommand {

  /**
   * Method to execute the animation at a given tick in time.
   *
   * @param shape a shape on which to perform the animation
   * @param tick the time considered when running the command
   *
   * @return  an IShape that with the state of the object during this command at the
   *          designated tick
   */
  IShape execute(IShape shape, double tick);

  /**
   * Returns the command type
   *
   * @return the type of command, as a string
   */
  String getCommandType();

  /**
   * Returns a copy of the shape object stored in the command object.
   *
   * @return a copy of the shape object stored in the command object.
   */
  IShape getShape();

  /**
   * Returns the start time of the animation object.
   *
   * @return the start time of the animation object, a double.
   */
  double getStartTime();

  /**
   * Return the end time of the animation object.
   *
   * @return the end time of the animation object, a double.
   */
  double getEndTime();

  /**
   * Returns the command details as a string.
   *
   * @return str the declarative animation details of the command
   */
  String toString();
}
