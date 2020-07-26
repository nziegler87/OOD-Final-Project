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
   * Pushes the instructions for moving a shape to the stack.
   *
   * @param shape the shape whose details we will pull from
   */
  void moveShape(IShape shape);

  /**
   * Pushes the instructions for changing a shape's color to the stack.
   *
   * @param shape the shape whose details we will pull from
   */
  void changeColor(IShape shape);

  /**
   * Pushes the instructions for changing a respective shape to the stack.
   *
   * @param shape the shape whose details we will pull from
   */
  void changeShape(IShape shape);
}
