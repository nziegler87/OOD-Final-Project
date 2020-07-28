package Model.Commands;

import java.awt.Color;
import java.util.Objects;

import Model.Shape.IShape;

/**
 * Create an object that will be used to change the color of an IShape object.
 */
public class ChangeColor extends AbstractCommand {
  private final Color startColor;
  private final Color endColor;
  private final double tickTracker;

  // TODO: Do we need to add in the start color or should that just get it from the shape?
  // TODO: Depending on what we decide here, we may need to update Scale. I prefer it getting from the shape

  /**
   * Creates an object that will change the color of an IShape object.
   *
   * @param shape       a shape on which to perform the action
   * @param startTime   start time for when animation should start
   * @param endTime     end time for when animation should end
   * @param endColor    ending color of the object
   * @throws IllegalArgumentException if animation time is 0 or if start time is after end time
   */
  public ChangeColor(IShape shape, double startTime, double endTime,
                     Color endColor) throws IllegalArgumentException {
    super(shape, startTime, endTime);
    this.startColor = shape.getColor();
    this.endColor = endColor;
    this.tickTracker = endTime - startTime;
  }

  /**
   * Method to execute the class.
   */
  @Override
  public void execute(IShape shape, double tick) {
    // if the timing is not right, don't do anything
    if (tick < startTime || tick > endTime) {
      return;
    }

    // calculate color deltas
    int blueDelta = endColor.getBlue() - startColor.getBlue();
    int greenDelta = endColor.getGreen() - startColor.getGreen();
    int redDelta = endColor.getRed() - startColor.getRed();

    // calculate color values at this point in time
    int blueAtTick = (int) (blueDelta * tickTracker);
    int greenAtTick = (int) (greenDelta * tickTracker);
    int redAtTick = (int) (redDelta * tickTracker);

    // create color object at this point in time
    Color currentColor = new Color(redAtTick, greenAtTick, blueAtTick);

    // reassign the color of the cloned shape
    shape.setColor(currentColor);
  }

  /**
   * Returns the command details as a string.
   *
   * @return str the declarative animation details of the command
   */
  public String toString() {
    return String.format("%s changes from %s to %s from time t=%f to t=%f\n",
            shape.getLabel(), startColor, endColor, startTime, endTime);
  }
}
