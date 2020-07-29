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

  /**
   * Creates an object that will move an IShape object across the screen.
   *
   * @param shape       a shape on which to perform the action
   * @param startTime   start time for when animation should start
   * @param endTime     end time for when animation should end
   * @param startCords  starting coordinates for the move
   * @param endCords    ending coordinates for the move
   * @throws IllegalArgumentException if animation time is 0 or if start time is after end time
   */

  public Move(IShape shape, double startTime, double endTime, IPoint2D startCords,
              IPoint2D endCords)
          throws IllegalArgumentException {
    super(shape, startTime, endTime);
    this.startCords = startCords;
    this.endCords = endCords;
    this.commandType = "move";
  }

  @Override
  public IShape execute(double tick) {

    // TODO: Move this logic to the get game state
//    // if the timing is not right, don't do anything
//    if (tick < startTime || tick > endTime) {
//      throw new IllegalArgumentException("")
//    }

    // find the mathematical adjustment
    double adjustment = (tick - startTime) / tickTracker;

    // find the difference of the x and y based on start and end coordinates
    double xDelta = this.endCords.getX() - this.startCords.getX();
    double yDelta = this.endCords.getY() - this.startCords.getY();

    // multiply them by the point in time we are at
    double xAtTick = (xDelta * adjustment) + startCords.getX();
    double yAtTick = (yDelta * adjustment) + startCords.getY();

    // create a new Point2D with the updated coordinates
    IPoint2D newCoords = new Point2D(xAtTick, yAtTick);

    // return a copy of the cloned shape
    IShape shapeSnapshot = this.shape.copy();
    shapeSnapshot.setCoordinates(newCoords);
    return shapeSnapshot;

  }

  /**
   * Returns the command details as a string.
   *
   * @return str the declarative animation details of the command
   */
  public String toString() {
    return String.format("%s moves from %s to %s from time t=%.0f to t=%.0f\n",
            shape.getLabel(), startCords.toString(), endCords.toString(), startTime, endTime);
  }
}


