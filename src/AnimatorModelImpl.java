import java.awt.*;
import java.util.ArrayList;

public class AnimatorModelImpl implements AnimatorModel {

  protected ArrayList<IShape> inventory;

  public void AnimatorModelImpl() {
    inventory = new ArrayList<>();
  }

  /**
   * Adds a shape to the model inventory list.
   *
   * @param shape the shape that will be added to the list
   */
  @Override
  public void addShape(IShape shape) {
    inventory.add(shape);
  }

  /**
   * Removes a shape from the model inventory list.
   *
   * @param label the label associated with the shape
   */
  @Override
  public void removeShape(String label) {
    inventory.remove(getShape(label));
  }

  /**
   * Removes a shape from the model inventory list.
   *
   * @param position the position in the list that will be removed
   */
  @Override
  public void removeShape(int position) {
    inventory.remove(position);
  }

  /**
   * Finds and returns the shape using the label of the shape.
   *
   * @param label the label associated with the shape
   * @return the shape being searched for
   */
  @Override
  public IShape getShape(String label) {
    for (IShape shape : inventory) {
      if (shape.getLabel().equals(label)) {
        return shape;
      }
    }
    return null;
  }

  /**
   * Finds and returns the shape using the label of the shape.
   *
   * @param position the position of the shape in the list
   * @return the shape being searched for
   */
  @Override
  public IShape getShape(int position) {
    return inventory.get(position);
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
    removeShape(label);
    addShape(shape);
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
    for (IShape shape : inventory) {
      status.append(shape.toString());
      // TODO: here I think we also might want to consider OODing out the time/animation stuff for this assignment.
    }
    // TODO: Once we get the above comment sorted, I will create a summary of the animation queues
    return status.substring(0, status.length());
  }
}
