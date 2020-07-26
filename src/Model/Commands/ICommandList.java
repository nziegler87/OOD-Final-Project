package Model.Commands;

import java.awt.Color;
import java.util.Stack;

import Model.Point2D.IPoint2D;
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
  void moveShape(IShape shape, IPoint2D coordinates, IPoint2D newCoordinates);

  /**
   * Pushes the instructions for changing a shape's color to the stack.
   *
   * @param shape the shape whose details we will pull from
   */
  void changeColor(IShape shape, Color color, Color newColor);

  /**
   * Pushes the instructions for changing a respective shape to the stack.
   *
   * @param shape the shape whose details we will pull from
   */
  void changeShape(IShape shape, IShape newShape);

  /**
   * Returns a declarative animation summary.
   *
   * @return String a summary of the list of commands
   */
  String toString();
}
