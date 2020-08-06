package cs5004.animator.model.shape;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

import cs5004.animator.model.point2d.IPoint2D;

/**
 * A class that represents a rectangle object. The coordinates of a rectangle represent the
 * lower-left corner of the object. In addition to the common parameters of AbstractShape, a
 * rectangle also has width and height parameters, both doubles.
 */
public class Rectangle extends AbstractShape {
    private double width;
    private double height;

    /**
     * Create a rectangle shape object when passed object label.
     *
     * @param label a label for the object, a string.
     */
    public Rectangle(String label) {
        super(label);
        this.type = "rectangle";
    }

    /**
     * Create a rectangle shape object when passed object label, coordinates, color, width, and
     * height.
     *
     * @param label       a label for the object, a string.
     * @param coordinates the coordinates for the object, a IPoint2D object
     * @param color       the color of the object, a Color object
     * @param width       the width of the object, a double
     * @param height      the height of the object, a double
     * @throws NullPointerException     if the coordinates or color are null
     * @throws IllegalArgumentException if the appear time or disappear time is negative, if the
     *                                  appear time comes after or equal to the disappear time, or if
     *                                  width or height are not greater than 0
     */
    public Rectangle(String label, IPoint2D coordinates, Color color, double width, double height,
                     double appearTime, double disappearTime)
            throws NullPointerException, IllegalArgumentException {
        super(label, coordinates, color, appearTime, disappearTime);
        this.type = "rectangle";
        this.width = width;
        this.height = height;
        if (this.width <= 0 || this.height <= 0) {
            throw new IllegalArgumentException("Width and height must be greater than 0.");
        }
    }

    /**
     * Returns the width of the object.
     *
     * @return the width of the object, a double.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Method to set the width of the object when passed a width value.
     *
     * @param width the width of the object, a double.
     * @throws IllegalArgumentException if width is not greater than 0
     */
    public void setWidth(double width) throws IllegalArgumentException {
        if (width <= 0) {
            throw new IllegalArgumentException("Width cannot be less than 0.");
        }
        this.width = width;
    }

    /**
     * Returns the height of the object.
     *
     * @return the height of the object, a double.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Method to set the height of the object when passed a height value.
     *
     * @param height the height of the object, a double.
     * @throws IllegalArgumentException if height is not greater than 0
     */
    public void setHeight(double height) throws IllegalArgumentException {
        if (height <= 0) {
            throw new IllegalArgumentException("Height cannot be less than 0.");
        }
        this.height = height;
    }

    /**
     * Create and return a shape of the same kind as this one, just resized using the provided
     * factor.
     *
     * @param factor a factor used in resizing, a double
     * @return a shape of the same kind as this one, just resized using the provided factor.
     * @throws IllegalArgumentException if scale results in dimensions equal to or less than zero
     */
    @Override
    public IShape scale(double factor) throws IllegalArgumentException {
        double sqrtFactor = Math.sqrt(factor);
        return new Rectangle(this.label, this.coordinates, this.color, this.width * sqrtFactor,
                this.height * sqrtFactor, this.appearTime, this.disappearTime);
    }

    /**
     * Returns a string representation of the object that includes its name, type, min corner
     * coordinates, height, width, color as RGB, appearTime, and disappearTime.
     *
     * @return a string representation of the object including the information outlined above
     */
    @Override
    public String toString() {

        // convert RGB to percentages
        double red = (double) this.color.getRed() / 255;
        double green = (double) this.color.getGreen() / 255;
        double blue = (double) this.color.getBlue() / 255;

        return String.format("Name: %s\nType: %s\nMin corner: %s, Width: %.1f, Height: %.1f, Color: "
                        + "(%.1f,%.1f,%.1f)\nAppears at t=%.0f\nDisappears at t=%.0f", this.label,
                this.type, this.coordinates.toString(), this.width, this.height,
                red, green, blue, this.appearTime, this.disappearTime);
    }

    /**
     * Method to create a copy of the object with the same attributes.
     */
    @Override
    public IShape copy() {
        return new Rectangle(this.label, this.coordinates, this.color, this.width, this.height,
                this.appearTime, this.disappearTime);
    }

    /**
     * Indicates whether some object is a rectangle.
     *
     * @param other an object to check
     * @return true if the object is a rectangle, otherwise false
     */
    @Override
    protected boolean equalsRectangle(Rectangle other) {
        return (this.label.equals(other.label)) && (this.coordinates.equals(other.coordinates))
                && this.color.equals(other.color) && (this.width == other.width)
                && (this.height == other.height) && (this.appearTime == other.appearTime)
                && (this.disappearTime == other.disappearTime);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param other the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof AbstractShape) {
            AbstractShape aShape = (AbstractShape) other;
            return aShape.equalsRectangle(this);
        }
        return false;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(label, coordinates, color, width, height, appearTime, disappearTime);
    }

    @Override
    public void drawShape(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.fill(new Rectangle2D.Double(this.getCoordinates().getX(), this.getCoordinates().getY(),
                this.width, this.height));
    }
}


