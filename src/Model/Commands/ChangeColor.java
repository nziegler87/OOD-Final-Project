package Model.Commands;

import java.awt.Color;

import Model.Shape.IShape;

/**
 * Create an object that will be used to change the color of an IShape object.
 */
public class ChangeColor extends AbstractCommand {
  private final Color startColor;
  private final Color endColor;

  //TODO: Add in IllegalArg if same type of animation is being already done on a shape...across all three

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
    this.commandType = "changeColor";
  }

  /**
   * Method to execute the class.
   */
  @Override
  public IShape execute(double tick) {

    // find the mathematical adjustment
    double adjustment = (tick - startTime) / tickTracker;

    // calculate color deltas
    int blueDelta = endColor.getBlue() - startColor.getBlue();
    int greenDelta = endColor.getGreen() - startColor.getGreen();
    int redDelta = endColor.getRed() - startColor.getRed();

    // calculate color values at this point in time
    int blueAtTick = (int) (blueDelta * adjustment) + shape.getColor().getBlue();
    int greenAtTick = (int) (greenDelta * adjustment) + shape.getColor().getGreen();
    int redAtTick = (int) (redDelta * adjustment) + shape.getColor().getRed();

    // create color object at this point in time
    Color newColor = new Color(redAtTick, greenAtTick, blueAtTick);

    // return a copy of the cloned shape
    IShape shapeSnapshot = this.shape.copy();
    shapeSnapshot.setColor(newColor);
    return shapeSnapshot;
  }

  /**
   * Returns the command details as a string.
   *
   * @return str the declarative animation details of the command
   */
  public String toString() {
    return String.format("%s changes color from (%d, %d, %d) to (%d, %d, %d) from "
                    + "time t=%.0f to t=%.0f\n",
            shape.getLabel(), startColor.getRed(), startColor.getGreen(), startColor.getBlue(),
            endColor.getRed(), endColor.getGreen(), endColor.getBlue(), startTime, endTime);
  }
}
