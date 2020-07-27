package Model;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

import Model.Commands.ChangeColor;
import Model.Commands.CommandList;
import Model.Commands.ICommandList;
import Model.Point2D.IPoint2D;
import Model.Point2D.Point2D;
import Model.Shape.IShape;

public class AnimatorModelImpl implements AnimatorModel {

  protected HashMap<String, IShape> inventory;
  protected ICommandList commands;

  public AnimatorModelImpl() {
    inventory = new HashMap<>();
    commands = new CommandList();
  }

  //TODO: Should Objects.requireNonNull be in try/catch?

  /**
   * Adds a shape to the model inventory list.
   *
   * @param shape the shape that will be added to the list
   * @throws IllegalArgumentException if the shape already exists
   */
  @Override
  public void addShape(String label, IShape shape) throws IllegalArgumentException {
    Objects.requireNonNull(shape);
    if (inventory.get(label).equals(shape)) {
      throw new IllegalArgumentException("This object has already been added.");
    }
    commands.addShape(shape);
    inventory.put(label, shape);
  }

  /**
   * Removes a shape from the model inventory list.
   *
   * @param label the label associated with the shape
   * @throws IllegalArgumentException when the shape is not found
   */
  @Override
  public void removeShape(String label) throws IllegalArgumentException {
    Objects.requireNonNull(label);
    if (!inventory.containsKey(label)) {
      throw new IllegalArgumentException("Cannot remove object that does not exist.");
    }
    IShape shape = inventory.get(label);
    commands.removeShape(shape);
    inventory.remove(label);
  }

  /**
   * Finds and returns the shape using the label of the shape.
   *
   * @param label the label associated with the shape
   * @return the shape being searched for
   * @throws IllegalArgumentException when the shape is not found
   */
  @Override
  public IShape getShape(String label) {
    Objects.requireNonNull(label);
    if (!inventory.containsKey(label)) {
      throw new IllegalArgumentException("Cannot get object that does not exist.");
    }
    return inventory.get(label);
  }

  /**
   * Moves the shape to a new set of coordinates.
   *
   * @param label the label associated with the shape
   * @param x     the new x coordinate for the shape
   * @param y     the new y coordinate for the shape
   */
  @Override
  public void moveShape(String label, double x, double y) {
    Objects.requireNonNull(label);
    IShape shape = inventory.get(label);
    IPoint2D newCoords = new Point2D(x,y);
    commands.moveShape(shape, shape.getCoordinates(), newCoords);
    // TODO: there might be an error in how I thought through this logic
    getShape(label).setCoordinates(x, y);
  }

  /**
   * Changes the color of the shape.
   *
   * @param label the label associated with the shape
   * @param color the new color for the shape
   */
  @Override
  public void changeColor(String label, Color color) {
    Objects.requireNonNull(label);
    Objects.requireNonNull(color);

    ChangeColor change = new ChangeColor(); // TODO: this needs to be updated for time
    IShape shape = inventory.get(label);
    commands.addToStack(change.toString());
  }

  // TODO: Needed? >> is this not one of the requirements? if not, toss it!
  /**
   * Changes a shape to a new shape type.
   *
   * @param label the label associated with the shape
   * @param shape the new shape to change to
   */
  @Override
  public void changeShape(String label, IShape shape) {
    Objects.requireNonNull(label);
    Objects.requireNonNull(shape);
    IShape OGShape = inventory.get(label);
    commands.changeShape(OGShape, shape);
    inventory.put(label, shape);
  }

  /**
   * Returns a summary of the animation, including the shapes in the list and the animation steps.
   *
   * @return a string of the summary
   */
  @Override
  public String getAnimationStatus() {
    StringBuilder status = new StringBuilder();
    status.append("Shapes:\n");

    // to iterate through the hashmap
    Iterator keySetItr = inventory.keySet().iterator();
    while (keySetItr.hasNext()) {
      IShape shape = (IShape) keySetItr.next();
      status.append(shape.toString());
    }

    status.append("\n").append(commands.toString());
    return status.substring(0, status.length());
  }
}
