package Model.Commands;

import Model.Point2D.IPoint2D;
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
   * @param endCords    endinfg coordinates for the move
   */
  public Move(IShape shape, double startTime, double endTime, IPoint2D startCords,
              IPoint2D endCords) {
    super(shape, startTime, endTime);
    this.startCords = startCords;
    this.endCords = endCords;
  }

  @Override
  public void execute() {
    shape.setCoordinates(endCords);
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
