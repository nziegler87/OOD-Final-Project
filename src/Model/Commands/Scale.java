package Model.Commands;

import Model.Shape.IShape;

/**
 * Create an object that will be used to resize an IShape object.
 */
public class Scale extends AbstractCommand {
  private final double widthChange;
  private final double heightChange;

  /**
   * Creates an object that will resize an IShape object.
   *
   * @param shape        a shape on which to perform the action
   * @param startTime    start time for when animation should start
   * @param endTime      end time for when animation should end
   * @param widthChange  factor by which to change width
   * @param heightChange factor by which to change height
   */
  public Scale(IShape shape, double startTime, double endTime, double widthChange,
               double heightChange) {
    super(shape, startTime, endTime);
    this.widthChange = widthChange;
    this.heightChange = heightChange;
  }

  @Override
  public void execute(IShape shape) {
    if (widthChange > 0 && heightChange > 0) {
      shape.setWidth(widthChange);
      shape.setHeight(heightChange);
    } else if (widthChange < 0) {
      shape.setWidth(widthChange);
    } else {
      shape.setHeight(heightChange);
    }
  }

  @Override
  public String toString() {
    if (widthChange > 0 && heightChange > 0) {
      return String.format("%s changes width from %f to %f and height from %f to %f from time t=%f to t=%f\n",
              shape.getLabel(), shape.getWidth(), widthChange, shape.getHeight(), heightChange, startTime, endTime);
    } else if (widthChange < 0) {
      return String.format("%s changes width from %f to %f from time t=%f to t=%f\n",
              shape.getLabel(), shape.getWidth(), widthChange, startTime, endTime);
    } else {
      return String.format("%s changes height from %f to %f from time t=%f to t=%f\n",
              shape.getLabel(), shape.getHeight(), heightChange, startTime, endTime);
    }
  }
}
