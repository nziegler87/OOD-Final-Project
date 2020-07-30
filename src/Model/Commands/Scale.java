package Model.Commands;

import java.util.HashMap;
import java.util.Objects;

import Model.Shape.IShape;

/**
 * Create an object that will be used to resize an IShape object.
 */
public class Scale extends AbstractCommand {
  private final double startWidth;
  private final double startHeight;
  private final double endWidth;
  private final double endHeight;


  /**
   * Creates an object that will resize an IShape object.
   *
   * @param shape       a shape on which to perform the action
   * @param startTime   start time for when animation should start
   * @param endTime     end time for when animation should end
   * @param startWidth  start width of the object
   * @param startHeight start height of the object
   * @param endWidth    end width of the object
   * @param endHeight   end height of the object
   * @throws NullPointerException if shape is null
   * @throws IllegalArgumentException if animation time is 0
   */
  public Scale(IShape shape, double startTime, double endTime, double startWidth,
               double startHeight, double endWidth, double endHeight)
          throws NullPointerException, IllegalArgumentException {
    super(shape, startTime, endTime);
    this.startWidth = startWidth;
    this.startHeight = startHeight;
    this.endWidth = endWidth;
    this.endHeight = endHeight;
    this.commandType = "scale";
  }

  /**
   * Method to execute the class.
   *
   * @return a modified IShape object with animation applied at this tick in time
   * @throws IllegalArgumentException if tick is before or after end time
   */
  @Override
  public IShape execute(IShape shape, double tick) throws IllegalArgumentException {

    // if the timing is not correct, don't do anything
    if (tick < startTime || tick > endTime) {
      throw new IllegalArgumentException("Tick is before start time or after end time.");
    }

    // find the mathematical adjustment
    double adjustment = (tick - startTime) / tickTracker;

    double widthAtTick = this.startWidth;
    double heightAtTick = this.startHeight;

    // calculate deltas
    double widthDelta = this.endWidth - this.startWidth;
    double heightDelta = this.endHeight - this.startHeight;

    // find the right condition to follow based on inputs size
    if (endWidth > 0 && endHeight > 0) {
      widthAtTick = (widthDelta * adjustment) + this.startWidth;
      heightAtTick = (heightDelta * adjustment) + this.startHeight;
    } else if (endWidth > 0) {
      widthAtTick = (widthDelta * adjustment) + this.startWidth;
    } else {
      heightAtTick = (heightDelta * adjustment) + this.startHeight;
    }

    // return a copy of the cloned shape
    IShape shapeSnapshot = shape.copy();
    shapeSnapshot.setWidth(widthAtTick);
    shapeSnapshot.setHeight(heightAtTick);
    return shapeSnapshot;
  }

  /**
   * Returns the command details as a string.
   *
   * @return str the declarative animation details of the command
   */
  @Override
  public String toString() {
    if (endWidth > 0 && endHeight > 0) {
      return String.format("%s changes width from %.1f to %.1f and height from %.1f to %.1f from time t=%.0f to t=%.0f\n",
              shape.getLabel(), this.startWidth, endWidth, this.startHeight, endHeight, startTime, endTime);
    } else if (endWidth > 0) {
      return String.format("%s changes width from %.1f to %.1f from time t=%.0f to t=%.0f\n",
              shape.getLabel(), this.startWidth, endWidth, startTime, endTime);
    } else {
      return String.format("%s changes height from %.1f to %.1f from time t=%.0f to t=%.0f\n",
              shape.getLabel(), this.startHeight, endHeight, startTime, endTime);
    }
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

    if (!(other instanceof Scale)) {
      return false;
    }

    Scale otherS = (Scale) other;

    return (this.shape.equals(otherS.shape))
            && (this.startTime == otherS.startTime)
            && (this.endTime == otherS.endTime)
            && (this.startWidth == otherS.startWidth)
            && (this.startHeight == otherS.startHeight)
            && (this.endWidth == otherS.endWidth)
            && (this.endHeight == otherS.endHeight)
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
            startWidth, startHeight,
            endWidth, endHeight,
            commandType);
  }
}
