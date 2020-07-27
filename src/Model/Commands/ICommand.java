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
   */
  void execute(IShape shape, double tick);

  /**
   * Returns the command details as a string.
   *
   * @return str the declarative animation details of the command
   */
  String toString();
}
