package cs5004.animator.model.commands;

import java.awt.Color;
import java.util.Objects;

import cs5004.animator.shape.IShape;

/**
 * Create an object that will be used to change the color of an IShape object.
 */
public class ChangeColor extends AbstractCommand {
  private final Color startColor;
  private final Color endColor;

  /**
   * Creates an object that will change the color of an IShape object.
   *
   * @param shape      a shape on which to perform the action
   * @param startTime  start time for when animation should start
   * @param endTime    end time for when animation should end
   * @param startColor starting color of the object
   * @param endColor   ending color of the object
   * @throws NullPointerException     if shape, startColor or endColor is null
   * @throws IllegalArgumentException if animation time is 0 or if start time is after end time
   */
  public ChangeColor(IShape shape, double startTime, double endTime, Color startColor,
                     Color endColor) throws NullPointerException, IllegalArgumentException {
    super(shape, startTime, endTime);
    Objects.requireNonNull(startColor);
    Objects.requireNonNull(endColor);
    this.startColor = startColor;
    this.endColor = endColor;
    this.commandType = "changeColor";
  }

  /**
   * Method to execute the class.
   *
   * @return a modified IShape object with animation applied at this tick in time
   * @throws NullPointerException     if shape is null
   * @throws IllegalArgumentException if tick is before or after end time
   */
  @Override
  public IShape execute(IShape shape, double tick)
          throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(shape, "Shape object cannot be null");

    // if the timing is not correct, don't do anything
    if (tick < startTime || tick > endTime) {
      throw new IllegalArgumentException("Tick is before start time or after end time.");
    }

    // find the mathematical adjustment
    double adjustment = (tick - startTime) / tickTracker;

    // calculate color deltas
    int blueDelta = endColor.getBlue() - startColor.getBlue();
    int greenDelta = endColor.getGreen() - startColor.getGreen();
    int redDelta = endColor.getRed() - startColor.getRed();

    // calculate color values at this point in time
    int blueAtTick = (int) (blueDelta * adjustment) + startColor.getBlue();
    int greenAtTick = (int) (greenDelta * adjustment) + startColor.getGreen();
    int redAtTick = (int) (redDelta * adjustment) + startColor.getRed();

    // return a copy of the cloned shape and new color
    IShape shapeSnapshot = shape.copy();
    shapeSnapshot.setColor(new Color(redAtTick, greenAtTick, blueAtTick));
    return shapeSnapshot;
  }

  /**
   * Returns the command details as a string.
   *
   * @return str the declarative animation details of the command
   */
  @Override
  public String toString() {

    // convert start RGB to percentages
    double startRed = (double) startColor.getRed() / 255;
    double startGreen = (double) startColor.getGreen() / 255;
    double startBlue = (double) startColor.getBlue() / 255;

    // convert endRGB to percentages
    double endRed = (double) endColor.getRed() / 255;
    double endGreen = (double) endColor.getGreen() / 255;
    double endBlue = (double) endColor.getBlue() / 255;

    return String.format("Shape %s changes color from (%.1f,%.1f,%.1f) to (%.1f,%.1f,%.1f) from "
                    + "t=%.0f to t=%.0f\n",
            shape.getLabel(), startRed, startGreen, startBlue, endRed, endGreen, endBlue,
            startTime, endTime);
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @param other the reference object with which to compare.
   * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof ChangeColor)) {
      return false;
    }

    ChangeColor otherS = (ChangeColor) other;

    return (this.shape.equals(otherS.shape))
            && (this.startTime == otherS.startTime)
            && (this.endTime == otherS.endTime)
            && (this.startColor.equals(otherS.startColor))
            && (this.endColor.equals(otherS.endColor))
            && (this.commandType.equals(otherS.commandType));
  }

  /**
   * Returns a hash code value for the object.
   *
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(shape,
            startTime, endTime,
            startColor, endColor,
            commandType);
  }
}
