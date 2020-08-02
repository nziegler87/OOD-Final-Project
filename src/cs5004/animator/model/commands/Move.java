package cs5004.animator.model.commands;

import java.util.Objects;

import cs5004.animator.model.point2d.IPoint2D;
import cs5004.animator.model.point2d.Point2D;
import cs5004.animator.model.shape.IShape;

/**
 * Create an object that will be used to "move" an IShape object across the screen.
 */
public class Move extends AbstractCommand {
  private final IPoint2D startCords;
  private final IPoint2D endCords;

  /**
   * Creates an object that will move an IShape object across the screen.
   *
   * @param shape      a shape on which to perform the action
   * @param startTime  start time for when animation should start
   * @param endTime    end time for when animation should end
   * @param startCords starting coordinates for the move
   * @param endCords   ending coordinates for the move
   * @throws NullPointerException     if shape, startCoords or endCoords is null
   * @throws IllegalArgumentException if animation time is 0 or if start time is after end time
   */
  public Move(IShape shape, double startTime, double endTime, IPoint2D startCords,
              IPoint2D endCords) throws NullPointerException, IllegalArgumentException {
    super(shape, startTime, endTime);
    Objects.requireNonNull(startCords, "Start cord object cannot be null.");
    Objects.requireNonNull(endCords, "End cord object cannot be null.");
    this.startCords = startCords;
    this.endCords = endCords;
    this.commandType = "move";
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
    Objects.requireNonNull(shape);
    // if the timing is not correct, don't do anything
    if (tick < startTime || tick > endTime) {
      throw new IllegalArgumentException("Tick is before start time or after end time.");
    }

    // find the mathematical adjustment
    double adjustment = (tick - startTime) / tickTracker;

    // find the difference of the x and y based on start and end coordinates
    double xDelta = this.endCords.getX() - this.startCords.getX();
    double yDelta = this.endCords.getY() - this.startCords.getY();

    // multiply them by the point in time we are at
    double xAtTick = (xDelta * adjustment) + startCords.getX();
    double yAtTick = (yDelta * adjustment) + startCords.getY();

    // return a copy of the cloned shape & new coords
    IShape shapeSnapshot = shape.copy();
    shapeSnapshot.setCoordinates(new Point2D(xAtTick, yAtTick));
    return shapeSnapshot;
  }

  /**
   * Returns the command details as a string.
   *
   * @return str the declarative animation details of the command
   */
  @Override
  public String toString() {
    return String.format("Shape %s moves from %s to %s from t=%.0f to t=%.0f\n",
            shape.getLabel(), startCords.toString(), endCords.toString(), startTime, endTime);
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

    if (!(other instanceof Move)) {
      return false;
    }

    Move otherS = (Move) other;

    return (this.shape.equals(otherS.shape))
            && (this.startTime == otherS.startTime)
            && (this.endTime == otherS.endTime)
            && (this.startCords.equals(otherS.startCords))
            && (this.endCords.equals(otherS.endCords))
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
            startCords, endCords,
            commandType);
  }
}


