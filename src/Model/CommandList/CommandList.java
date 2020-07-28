package Model.CommandList;

import java.util.Stack;

import Model.Shape.IShape;

public class CommandList implements ICommandList {

  Stack<String> stack;

  public CommandList() {
    stack = new Stack<>();
  }

  /**
   * Returns the commandList to the model.
   *
   * @return Stack<String> the commandList
   */
  @Override
  public Stack<String> getCommandList() {
    return stack;
  }

  /**
   * Pushes the declarative animation instructions to the stack.
   *
   * @param str the details we will add to the list
   */
  @Override
  public void addToStack(String str) {
    stack.push(str);
  }

  // TODO: Color doesn't print correctly because color object toString() isn't pretty.
  //  Can we just use the shape.toString() method for this?
  /**
   * Pushes the instructions for adding a shape to the stack.
   *
   * @param shape the shape whose details we will pull from
   */
  @Override
  public void addShape(IShape shape) {
    stack.push(String.format("Create %s %s %s with corner at %s, width %f.1 and height %f.1\n",
            shape.getColor(), shape.getType(), shape.getLabel(), shape.getCoordinates().toString(),
            shape.getWidth(), shape.getHeight()));
  }

  /**
   * Pushes the instructions for removing a shape to the stack.
   *
   * @param shape the shape whose details we will pull from
   */
  @Override
  public void removeShape(IShape shape) {
    stack.push(String.format("Remove %s %s %s\n",
            shape.getColor(), shape.getType(), shape.getLabel()));
  }

  /**
   * Returns a declarative animation summary.
   *
   * @return String a summary of the list of commands
   */
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    for (String strings : stack) {
      str.append(strings);
    }
    return str.toString();
  }
}
