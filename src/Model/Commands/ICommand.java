package Model.Commands;

import Model.Shape.IShape;

/**
 * An interface that contains the command that all command classes should implement.
 */
public interface ICommand {

  /**
   * Method to execute the class.
   * @param shape the shape to by modified
   * @param tick the time considered when running the command
   *
   * @return  an IShape that represents the state of the object during this command at the
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
   * Returns the command details as a string.
   *
   * @return str the declarative animation details of the command
   */
  String toString();
}
