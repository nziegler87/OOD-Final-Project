package Model.Commands;

import Model.Point2D.IPoint2D;
import Model.Point2D.Point2D;
import Model.Shape.IShape;

/**
 * Create an object that will be used to "move" an IShape object across the screen.
 */
public class Move extends AbstractCommand {
  private final IPoint2D startCords;
  private final IPoint2D endCords;
  private final double tickTracker;

  /**
   * Creates an object that will move an IShape object across the screen.
   *
   * @param shape       a shape on which to perform the action
   * @param startTime   start time for when animation should start
   * @param endTime     end time for when animation should end
   * @param startCords  starting coordinates for the move
   * @param endCords    endinfg coordinates for the move
   */
  public Move(IShape shape, double startTime, double endTime, IPoint2D startCords,
              IPoint2D endCords) {
    super(shape, startTime, endTime);
    this.startCords = startCords;
    this.endCords = endCords;
    this.tickTracker = endTime - startTime;
  }

  @Override
  public void execute(IShape shape, double tick) {
    // if the timing is not right, don't do anything
    if (tick < startTime || tick > endTime) {
      return;
    }

    // find the difference of the x and y based on start and end coordinates
    double xDelta = this.endCords.getX() - this.startCords.getX();
    double yDelta = this.endCords.getY() - this.startCords.getY();

    // multiply them by the point in time we are at
    double xAtTick = xDelta * this.tickTracker;
    double yAtTick = yDelta * this.tickTracker;

    // create a new Point2D with the updated coordinates
    IPoint2D newCoords = new Point2D(xAtTick, yAtTick);

    // assign the shape the new coordinates
    shape.setCoordinates(newCoords);
  }

  /**
   * Returns the command details as a string.
   *
   * @return str the declarative animation details of the command
   */
  public String toString() {
    return String.format("%s moves from %s to %s from time t=%f to t=%f\n",
            shape.getLabel(), startCords.toString(), endCords.toString(), startTime, endTime);
  }
}
