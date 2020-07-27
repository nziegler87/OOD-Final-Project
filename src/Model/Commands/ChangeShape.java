package Model.Commands;

import Model.Shape.IShape;

/**
 * Create an object that will be used to change the color of an IShape object.
 */
public class ChangeShape extends AbstractCommand {

  protected IShape endShape;

  /**
   * Creates an object that will change the color of an IShape object.
   *
   * @param shape     a shape on which to perform the action
   * @param startTime start time for when animation should start
   * @param endTime   end time for when animation should end
   * @param endShape  ending shape of the object
   */
  public ChangeShape(IShape shape, double startTime, double endTime, IShape endShape) {
    super(shape, startTime, endTime);
    this.endShape = endShape;
  }

  /**
   * Method to execute the class.
   */
  @Override
  public void execute() {
    // this command returns nothing
  }

  /**
   * Returns the command details as a string.
   *
   * @return str the declarative animation details of the command
   */
  public String toString() {
    return String.format("%s changes from %s to %s\n",
            shape.getLabel(), shape.getType(), endShape.getType());
  }
}
