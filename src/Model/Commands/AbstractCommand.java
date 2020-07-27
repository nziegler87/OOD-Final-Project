package Model.Commands;

import Model.Shape.IShape;

/**
 * Abstract class for classes that implement ICommand.
 */
public abstract class AbstractCommand implements ICommand {
  protected IShape shape;
  protected double startTime;
  protected double endTime;

  /**
   * Creates an AbstractCommand object.
   *
   * @param shape       a shape on which to perform the action
   * @param startTime   start time for when animation should start
   * @param endTime     end time for when animation should end
   */
  public AbstractCommand(IShape shape, double startTime, double endTime) {
    this.shape = shape;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public void execute(IShape shape) {
    // nothing here
  }
}
