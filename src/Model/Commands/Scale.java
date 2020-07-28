package Model.Commands;

import Model.Shape.IShape;

/**
 * Create an object that will be used to resize an IShape object.
 */
public class Scale extends AbstractCommand {
  private final double endWidth;
  private final double endHeight;
  private final double tickTracker;


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
    this.tickTracker = endTime - startTime;
  }

  @Override
  public void execute(IShape shape, double tick) {
    // if the timing is not right, don't do anything
    if (tick < startTime || tick > endTime) {
      return;
    }

    double widthDelta = 1;
    double heightDelta = 1;
    double widthAtTick = 0;
    double heightAtTick = 0;

    if (endWidth > 0 && endHeight > 0) {
      // find the difference of the x and y based on start and end coordinates
      widthDelta = this.endWidth - this.shape.getWidth();
      heightDelta = this.endHeight - this.shape.getHeight();
      // multiply them by the point in time we are at
      widthAtTick = widthDelta * this.tickTracker;
      heightAtTick = heightDelta * this.tickTracker;
    } else if (endWidth > 0) {
      widthDelta = this.endWidth - this.shape.getWidth();
      widthAtTick = widthDelta * this.tickTracker;
    } else {
      widthDelta = this.endWidth - this.shape.getWidth();
      widthAtTick = widthDelta * this.tickTracker;
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
