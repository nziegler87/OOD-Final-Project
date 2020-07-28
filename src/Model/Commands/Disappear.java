//package Model.Commands;
//
//import Model.Shape.IShape;
//
///**
// * Create an object that will be used to change the color of an IShape object.
// */
//public class Disappear extends AbstractCommand {
//
//  /**
//   * Creates an object that will change the color of an IShape object.
//   *
//   * @param shape     a shape on which to perform the action
//   * @param startTime start time for when animation should start
//   * @param endTime   end time for when animation should end
//   */
//  public Disappear(IShape shape, double startTime, double endTime) {
//    super(shape, startTime, endTime);
//  }
//
//  /**
//   * Method to execute the class.
//   */
//  @Override
//  public void execute(IShape shape, double tick) {
//      // this command returns nothing
//  }
//
//  /**
//   * Returns the command details as a string.
//   *
//   * @return str the declarative animation details of the command
//   */
//  public String toString() {
//    return String.format("Create %s %s %s with corner at (%d,%d), width %d and height %d\n",
//            shape.getColor(), shape.getType(), shape.getLabel(), shape.getCoordinates(),
//            shape.getWidth(), shape.getHeight());
//  }
//}
