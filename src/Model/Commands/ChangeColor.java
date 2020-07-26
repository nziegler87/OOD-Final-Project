package Model.Commands;

import java.awt.Color;

import Model.Shape.IShape;

/**
 * Create an object that will be used to change the color of an IShape object.
 */
public class ChangeColor extends AbstractCommand {
  private final Color startColor;
  private final Color endColor;

  /**
   * Creates an object that will change the color of an IShape object.
   *
   * @param shape       a shape on which to perform the action
   * @param startTime   start time for when animation should start
   * @param endTime     end time for when animation should end
   * @param startColor  starting color of the object
   * @param endColor    ending color of the object
   */
  public ChangeColor(IShape shape, double startTime, double endTime, Color startColor,
                     Color endColor) {
    super(shape, startTime, endTime);
    this.startColor = startColor;
    this.endColor = endColor;
  }

  @Override
  public void execute() {
    // TODO: Add code here.
  }
}
