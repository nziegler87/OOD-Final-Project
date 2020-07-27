package Model.Commands;

import java.util.Stack;

import Model.Shape.IShape;

public interface ICommandList {

  /**
   * Returns the commandList to the model.
   *
   * @return Stack<String> the commandList
   */
  Stack<String> getCommandList();

  /**
   * Pushes the declarative animation instructions to the stack.
   *
   * @param str the details we will add to the list
   */
  void addToStack(String str);

  /**
   * Pushes the instructions for adding a shape to the stack.
   *
   * @param shape the shape whose details we will pull from
   */
  void addShape(IShape shape);

  /**
   * Pushes the instructions for removing a shape to the stack.
   *
   * @param shape the shape whose details we will pull from
   */
  void removeShape(IShape shape);

  /**
   * Returns a declarative animation summary.
   *
   * @return String a summary of the list of commands
   */
  String toString();
}
