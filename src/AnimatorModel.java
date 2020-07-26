import java.awt.*;

public interface AnimatorModel {

  /**
   * Adds a shape to the model inventory list.
   *
   * @param shape the shape that will be added to the list
   */
  void addShape(IShape shape);

  /**
   * Removes a shape from the model inventory list.
   *
   * @param label the label associated with the shape
   */
  void removeShape(String label);

  /**
   * Removes a shape from the model inventory list.
   *
   * @param position the position in the list that will be removed
   */
  void removeShape(int position);

  /**
   * Finds and returns the shape using the label of the shape.
   *
   * @param label the label associated with the shape
   * @return the shape being searched for
   */
  IShape getShape(String label);

  /**
   * Finds and returns the shape using the label of the shape.
   *
   * @param position the position of the shape in the list
   * @return the shape being searched for
   */
  IShape getShape(int position);

  /**
   * Moves the shape to a new set of coordinates.
   *
   * @param label the label associated with the shape
   * @param x the new x coordinate for the shape
   * @param y the new y coordinate for the shape
   */
  void moveShape(String label, double x, double y);

  /**
   * Changes the color of the shape.
   *
   * @param label the label associated with the shape
   * @param color the new color for the shape
   */
  void changeColor(String label, Color color);

  /**
   * Changes a shape to a new shape type.
   *
   * @param label the label associated with the shape
   * @param newShape the new shape to change to
   */
  void changeShape(String label, IShape newShape);

  /**
   * Returns a summary of the animation, including the shapes in the list and the animation steps.
   *
   * @return a string of the summary
   */
  String getAnimationStatus();

}
