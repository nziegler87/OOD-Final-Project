package Model.Commands;

import Model.Shape.IShape;

/**
 * Abstract class for classes that implement ICommand.
 */
public abstract class AbstractCommand implements ICommand {
  protected IShape shape;
  protected double startTime;
  protected double endTime;
  protected String commandType;

  /**
   * Creates an AbstractCommand object.
   *
   * @param shape       a shape on which to perform the action
   * @param startTime   start time for when animation should start
   * @param endTime     end time for when animation should end
   * @throws IllegalArgumentException if animation time is 0 or if start time is after end time
   */
  public AbstractCommand(IShape shape, double startTime, double endTime)
          throws IllegalArgumentException {

    // check to make sure that time entry is valid
    if (endTime == startTime || startTime > endTime) {
      throw new IllegalArgumentException("Invalid time entry.");
    }

    this.shape = shape;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public String getCommandType() {
    return this.commandType;
  }
}
