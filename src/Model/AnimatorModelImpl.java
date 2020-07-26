package Model;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;

import Model.Shape.IShape;

public class AnimatorModelImpl implements AnimatorModel {

  protected HashMap<String, IShape> inventory;
  protected Stack<String> stack;


  public void AnimatorModelImpl() {
    inventory = new HashMap<String, IShape>();
  }

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
    getShape(label).setColor(color);
  }

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

    // we need to create the list of instructions

    return status.substring(0, status.length());
  }
}
