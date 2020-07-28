package Model.Commands;

import Model.Shape.IShape;

/**
 * Create an object that will be used to resize an IShape object.
 */
public class Scale extends AbstractCommand {
  private final double endWidth;
  private final double endHeight;


  /**
   * Creates an object that will resize an IShape object.
   *
   * @param shape        a shape on which to perform the action
   * @param startTime    start time for when animation should start
   * @param endTime      end time for when animation should end
   * @param endWidth     end width of the object
   * @param endHeight    end height of the object
   * @throws IllegalArgumentException if animation time is 0
   */
  public Scale(IShape shape, double startTime, double endTime, double endWidth,
               double endHeight) throws IllegalArgumentException {
    super(shape, startTime, endTime);
    this.endWidth = endWidth;
    this.endHeight = endHeight;
  }

  @Override
  public void execute(IShape shape, double tick) {
    // if the timing is not right, don't do anything
    if (tick < startTime || tick > endTime) {
      return;
    }

    // find the mathematical adjustment
    double adjustment = (tick - startTime) / tickTracker;

    double widthAtTick = shape.getWidth();
    double heightAtTick = shape.getHeight();

    // find the right condition to follow based on inputs size
    if (endWidth > 0 && endHeight > 0) {
      widthAtTick = (this.endWidth - this.shape.getWidth() * adjustment) + shape.getWidth();
      heightAtTick = (this.endHeight - this.shape.getHeight() * adjustment) + shape.getHeight();
    } else if (endWidth > 0) {
      widthAtTick = (this.endWidth - this.shape.getWidth() * adjustment) + shape.getWidth();
    } else {
      heightAtTick = (this.endWidth - this.shape.getWidth() * adjustment) + shape.getHeight();
    }

    // reassign the widths of the cloned shape
    shape.setWidth(widthAtTick);
    shape.setHeight(heightAtTick);
  }

  @Override
  public String toString() {
    if (endWidth > 0 && endHeight > 0) {
      return String.format("%s changes width from %f to %f and height from %f to %f from time t=%f to t=%f\n",
              shape.getLabel(), shape.getWidth(), endWidth, shape.getHeight(), endHeight, startTime, endTime);
    } else if (endWidth > 0) {
      return String.format("%s changes width from %f to %f from time t=%f to t=%f\n",
              shape.getLabel(), shape.getWidth(), endWidth, startTime, endTime);
    } else {
      return String.format("%s changes height from %f to %f from time t=%f to t=%f\n",
              shape.getLabel(), shape.getHeight(), endHeight, startTime, endTime);
    }
  }
}
