package cs5004.animator.model.commands;

import java.util.Objects;

import cs5004.animator.shape.IShape;

/**
 * Abstract class for classes that implement ICommand.
 */
public abstract class AbstractCommand implements ICommand {
  protected final IShape shape;
  protected final double startTime;
  protected final double endTime;
  protected String commandType;
  protected final double tickTracker;

  /**
   * Creates an AbstractCommand object.
   *
   * @param shape     a shape on which to perform the action
   * @param startTime start time for when animation should start
   * @param endTime   end time for when animation should end
   * @throws NullPointerException     if shape is null
   * @throws IllegalArgumentException if animation time is 0 or if start time is after end time
   */
  public AbstractCommand(IShape shape, double startTime, double endTime)
          throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(shape);
    // check to make sure that time entry is valid
    if (startTime < 0 || endTime < 0 || endTime == startTime || startTime > endTime) {
      throw new IllegalArgumentException("Invalid time entry.");
    }
    this.shape = shape;
    this.startTime = startTime;
    this.endTime = endTime;
    this.tickTracker = endTime - startTime;
  }

  /**
   * Returns the command type of the animation object.
   *
   * @return the command type of the animation object, a double.
   */
  @Override
  public String getCommandType() {
    return this.commandType;
  }

  /**
   * Returns a copy of the shape object stored in the command object.
   *
   * @return a copy of the shape object stored in the command object.
   */
  @Override
  public IShape getShape() {
    return this.shape.copy();
  }

  /**
   * Returns the start time of the animation object.
   *
   * @return the start time of the animation object, a double.
   */
  @Override
  public double getStartTime() {
    return this.startTime;
  }

  /**
   * Return the end time of the animation object.
   *
   * @return the end time of the animation object, a double.
   */
  @Override
  public double getEndTime() {
    return this.endTime;
  }
}
