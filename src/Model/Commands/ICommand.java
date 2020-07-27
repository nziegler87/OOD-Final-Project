package Model.Commands;

/**
 * An interface that contains the command that all command classes should implement.
 */
public interface ICommand {

  /**
   * Method to execute the class.
   */
  void execute();

  /**
   * Returns the command details as a string.
   *
   * @return str the declarative animation details of the command
   */
  String toString();
}
