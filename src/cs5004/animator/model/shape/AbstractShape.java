package cs5004.animator.model.shape;

import java.awt.Color;
import java.util.Objects;

import cs5004.animator.model.point2d.IPoint2D;
import cs5004.animator.model.point2d.Point2D;

/**
 * An abstract class that contains all common fields and methods objects that implement IShape.
 */
public abstract class AbstractShape implements IShape {
    protected final String label;
    protected String type;
    protected IPoint2D coordinates;
    protected Color color;
    protected double appearTime;
    protected double disappearTime;

    /**
     * Create an abstract shape object when passed object label.
     *
     * @param label a label for the object, a string.
     */
    public AbstractShape(String label) {
        this.label = label;
    }

    /**
     * Create an abstract shape object when passed object label, coordinates, and color.
     *
     * @param label         a label for the object, a string.
     * @param coordinates   the coordinates for the object, a IPoint2D object
     * @param color         the color of the object, a Color object
     * @param appearTime    the time after which the object is visible on the screen
     * @param disappearTime The time after which the object is no longer visible on the screen
     * @throws NullPointerException     if the coordinates or color are null
     * @throws IllegalArgumentException if the appear time or disappear time is negative or if the
     *                                  appear time comes after or equal to the disappear time
     */
    public AbstractShape(String label, IPoint2D coordinates, Color color,
                         double appearTime, double disappearTime) throws NullPointerException,
            IllegalArgumentException {
        Objects.requireNonNull(coordinates, "Coordinates cannot be null.");
        Objects.requireNonNull(color, "Color cannot be null.");
        this.label = label;
        this.coordinates = coordinates;
        this.color = color;
        if (appearTime < 0 || disappearTime < 0 || appearTime >= disappearTime) {
            throw new IllegalArgumentException("Time cannot be negative, and appear time must come " +
                    "before disappear time.");
        }
        this.appearTime = appearTime;
        this.disappearTime = disappearTime;
    }

    /**
     * Returns the label that is assigned to the IShape object.
     *
     * @return the label of the IShape object, a string.
     */
    @Override
    public String getLabel() {
        return this.label;
    }

    /**
     * Returns the type of the IShape object.
     *
     * @return the type of the IShape object, a string.
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Returns the x, y coordinates of the object as a Point2D object.
     *
     * @return the x, y coordinates of the object as a Point2D object.
     */
    @Override
    public IPoint2D getCoordinates() {
        return new Point2D(this.coordinates);
    }

    /**
     * Set the x, y coordinates of the object.
     *
     * @param x the x coordinate of the object, a double.
     * @param y the y coordinate of the object, a double.
     */
    @Override
    public void setCoordinates(double x, double y) {
        this.coordinates = new Point2D(x, y);
    }

    /**
     * Method to set the new coordinates of the object when passed a IPoint2D class.
     *
     * @param endCords the new coordinates of the object
     * @throws NullPointerException if the endCords are null
     */
    @Override
    public void setCoordinates(IPoint2D endCords) throws NullPointerException {
        Objects.requireNonNull(endCords, "End coordinates cannot be null.");
        this.coordinates = endCords;
    }

    /**
     * Method to set the start time of the shape object.
     *
     * @param appearTime the time after which the object should appear on the screen, a double
     * @throws IllegalArgumentException when time is negative
     */
    @Override
    public void setAppearTime(double appearTime) throws IllegalArgumentException {
        if (appearTime < 0) {
            throw new IllegalArgumentException("Time must be positive.");
        }
        this.appearTime = appearTime;
    }

    /**
     * Method to set the end time of the shape object.
     *
     * @param disappearTime the time after which the object should disappear on the screen, a double
     * @throws IllegalArgumentException when time is negative or less than appear time
     */
    @Override
    public void setDisappearTime(double disappearTime) throws IllegalArgumentException {
        if (disappearTime < 0 || disappearTime <= appearTime) {
            throw new IllegalArgumentException("Time must be positive and greater than appear time.");
        }
        this.disappearTime = disappearTime;
    }

    /**
     * Returns the time after which an object should appear on the screen, a double.
     *
     * @return the time after which an object should appear on the screen, a double.
     */
    @Override
    public double getAppearTime() {
        return this.appearTime;
    }

    /**
     * Returns the time after which an object should not appear on the screen, a double.
     *
     * @return the time after which an object should not appear on the screen, a double.
     */
    @Override
    public double getDisappearTime() {
        return this.disappearTime;
    }

    /**
     * Returns the color of the object.
     *
     * @return the color of the object, a Color object
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the object.
     *
     * @param color the color of the object, a Color object
     * @throws NullPointerException if the color is null
     */
    @Override
    public void setColor(Color color) throws NullPointerException {
        Objects.requireNonNull(color, "Color cannot be null.");
        this.color = color;
    }

    /**
     * Indicates whether some object is an oval.
     *
     * @param other an object to check.
     * @return true if the object is an oval, otherwise false
     */
    protected boolean equalsOval(Oval other) {
        return false;
    }

    /**
     * Indicates whether some object is a rectangle.
     *
     * @param other an object to check
     * @return true if the object is a rectangle, otherwise false
     */
    protected boolean equalsRectangle(Rectangle other) {
        return false;
    }
}
