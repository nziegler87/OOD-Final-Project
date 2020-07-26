package Model.Commands;

import java.awt.*;
import java.util.Stack;

import Model.Point2D.Point2D;
import Model.Shape.IShape;

public class CommandList implements ICommandList {

  Stack<String> stack;

  public CommandList() {
    stack = new Stack<String>();
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
   * Pushes the instructions for adding a shape to the stack.
   *
   * @param shape the shape whose details we will pull from
   */
  @Override
  public void addShape(IShape shape) {
    stack.push(String.format("Create %s %s %s with corner at (%d,%d), width %d and height %d\n",
            shape.getColor(), shape.getType(), shape.getLabel(), shape.getCoordinates()
            //shape.getWidth(), shape.getHeight()
            ));
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
   * Pushes the instructions for moving a shape to the stack.
   *
   * @param shape the shape whose details we will pull from
   */
  @Override
  public void moveShape(IShape shape, Point2D coordinates, Point2D newCoordinates) {
    stack.push(String.format("Move %s from %s to %s\n",
            shape.getLabel(), coordinates, newCoordinates));
  }

  /**
   * Pushes the instructions for changing a shape's color to the stack.
   *
   * @param shape the shape whose details we will pull from
   */
  @Override
  public void changeColor(IShape shape, Color color, Color newColor) {
    stack.push(String.format("%s changes from %s to %s\n",
            shape.getLabel(), color, newColor));
  }

  /**
   * Pushes the instructions for changing a respective shape to the stack.
   *
   * @param shape the shape whose details we will pull from
   */
  @Override
  public void changeShape(IShape shape, IShape newShape) {
    stack.push(String.format("Move %s from %s to %s\n",
            shape.getLabel(), shape.getType(), newShape.getType()));
  }
}
