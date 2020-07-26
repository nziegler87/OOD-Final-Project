package Model.Shape;

/**
 * An interface that adds the ability to set and get X Radius and Y Radius of oval objects.
 */
public interface IOval extends IShape {

  /**
   * Returns the xRadius of the object.
   *
   * @return the xRadius of the object, a double.
   */
  double getXRadius();

  /**
   * Returns the yRadius of the object.
   *
   * @return the yRadius of the object, a double.
   */
  double getYRadius();


  /**
   * Method to set the xRadius of the object when passed a xRadius value.
   *
   * @param xRadius the xRadius of the object, a double.
   *
   * @throws IllegalArgumentException if xRadius is not greater than 0
   */
  void setXRadius(double xRadius) throws IllegalArgumentException;


  /**
   * Method to set the yRadius of the object when passed a yRadius value.
   *
   * @param yRadius the yRadius of the object, a double.
   *
   * @throws IllegalArgumentException if yRadius is not greater than 0
   */
  void setYRadius(double yRadius) throws IllegalArgumentException;
}
