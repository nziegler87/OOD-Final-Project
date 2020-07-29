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
    this.commandType = "scale";
  }

  @Override
  public IShape execute(double tick) {

    // find the mathematical adjustment
    double adjustment = (tick - startTime) / tickTracker;

    double widthAtTick = shape.getWidth();
    double heightAtTick = shape.getHeight();

    // calculate color deltas
    double widthDelta = this.endWidth - shape.getWidth();
    double heightDelta = this.endHeight - shape.getHeight();

    // find the right condition to follow based on inputs size
    if (endWidth > 0 && endHeight > 0) {
      widthAtTick = (widthDelta * adjustment) + shape.getWidth();
      heightAtTick = (heightDelta * adjustment) + shape.getHeight();
    } else if (endWidth > 0) {
      widthAtTick = (widthDelta * adjustment) + shape.getWidth();
    } else {
      heightAtTick = (heightDelta * adjustment) + shape.getHeight();
    }

    // return a copy of the cloned shape
    IShape shapeSnapshot = this.shape.copy();
    shapeSnapshot.setWidth(widthAtTick);
    shapeSnapshot.setHeight(heightAtTick);
    return shapeSnapshot;
  }

  @Override
  public String toString() {
    if (endWidth > 0 && endHeight > 0) {
      return String.format("%s changes width from %.1f to %.1f and height from %.1f to %.1f from time t=%.0f to t=%.0f\n",
              shape.getLabel(), shape.getWidth(), endWidth, shape.getHeight(), endHeight, startTime, endTime);
    } else if (endWidth > 0) {
      return String.format("%s changes width from %.1f to %.1f from time t=%.0f to t=%.0f\n",
              shape.getLabel(), shape.getWidth(), endWidth, startTime, endTime);
    } else {
      return String.format("%s changes height from %.1f to %.1f from time t=%.0f to t=%.0f\n",
              shape.getLabel(), shape.getHeight(), endHeight, startTime, endTime);
    }
  }
}
