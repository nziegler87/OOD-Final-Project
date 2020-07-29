package Model.Shape;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import Model.Point2D.IPoint2D;
import Model.Point2D.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 * A JUnit test class for the Rectangle class.
 */
public class RectangleTest {
  IShape danielleRectangle;
  IShape rectangleFromHomework;

  @Before
  public void setUp() {
    danielleRectangle = new Rectangle("Danielle", new Point2D(11.23, 12.11),
            new Color(29, 255, 229), 20, 50, 10 ,20);
    rectangleFromHomework = new Rectangle("c", new Point2D(200.0, 200.0),
            new Color(1, 0 ,0), 50.0, 100.0, 30, 50);
  }

  @Test
  public void getAppearTime() {
    assertEquals(10, danielleRectangle.getAppearTime(), 0.01);
    assertEquals(30, rectangleFromHomework.getAppearTime(), 0.01);
  }

  @Test
  public void getDisappearTime() {
    assertEquals(20, danielleRectangle.getDisappearTime(), 0.01);
    assertEquals(50, rectangleFromHomework.getDisappearTime(), 0.01);
  }


  // test getLabel
  @Test
  public void testGetLabel() {
    assertEquals("Danielle", danielleRectangle.getLabel());
  }

  // test getType
  @Test
  public void testGetType() {
    assertEquals("rectangle", danielleRectangle.getType());
  }

  // test getCoordinates() {
  @Test
  public void testGetCoordinates() {
    IPoint2D originalPoint2D = new Point2D(10.345, 10.345);
    IShape testShape = new Rectangle("Test Rectangle", originalPoint2D,
            new Color(40, 40, 40), 30, 30, 10, 20);
    IPoint2D returnedPoint2D = testShape.getCoordinates();

    // test that the two point2D are the same
    assertEquals(originalPoint2D, returnedPoint2D);
    // test that they are different objects
    assertNotSame(originalPoint2D, returnedPoint2D);
  }

  // test setCoordinates
  @Test
  public void testSetCoordinates() {
    IShape testShape = new Rectangle("r", new Point2D(500.0, 100.0),
            new Color(0, 0, 1), 60.0, 30.0, 10, 20);
    assertEquals("Name: r\n"
                    + "Type: rectangle\n"
                    + "Min corner: (500.0,100.0), Width: 60.0, Height: 30.0, Color: (0, 0, 1)"
                    + "\nAppears at t=10\nDisappears at t=20",
            testShape.toString());

    testShape.setCoordinates(24.2, 56.6);
    assertEquals("Name: r\n"
                    + "Type: rectangle\n"
                    + "Min corner: (24.2,56.6), Width: 60.0, Height: 30.0, Color: (0, 0, 1)"
                    + "\nAppears at t=10\nDisappears at t=20",
            testShape.toString());
  }


  // test getColor()
  @Test
  public void testGetColor() {
    Color color = danielleRectangle.getColor();
    assertEquals("java.awt.Color[r=29,g=255,b=229]", color.toString());
  }

  // test setColor()
  @Test
  public void testSetColor() {
    assertEquals("Name: Danielle\n"
                    + "Type: rectangle\n"
                    + "Min corner: (11.2,12.1), Width: 20.0, Height: 50.0, Color: (29, 255, 229)"
                    + "\nAppears at t=10\nDisappears at t=20",
            danielleRectangle.toString());

    danielleRectangle.setColor(new Color(147, 24, 24));

    assertEquals("Name: Danielle\n"
                    + "Type: rectangle\n"
                    + "Min corner: (11.2,12.1), Width: 20.0, Height: 50.0, Color: (147, 24, 24)"
                    + "\nAppears at t=10\nDisappears at t=20",
            danielleRectangle.toString());
  }

  // test setColor throws IllegalArg
  @Test (expected = IllegalArgumentException.class)
  public void testSetColorIllegalArg() {
    danielleRectangle.setColor(null);
  }

  // test toString
  @Test
  public void testToString() {
    assertEquals("Name: Danielle\n"
                    + "Type: rectangle\n"
                    + "Min corner: (11.2,12.1), Width: 20.0, Height: 50.0, Color: (29, 255, 229)"
                    + "\nAppears at t=10\nDisappears at t=20",
            danielleRectangle.toString());
  }

  // test getWidth()
  @Test
  public void testGetWidth() {
    assertEquals(20.0, danielleRectangle.getWidth(),.01);
  }

  // test getHeight()
  @Test
  public void testGetHeight() {
    assertEquals(50.0, danielleRectangle.getHeight(), .01);
  }

  // test setWidth
  @Test
  public void testSetWidth() {
    assertEquals(20.0, danielleRectangle.getWidth(),.01);

    danielleRectangle.setWidth(10.0);

    assertEquals(10.0, danielleRectangle.getWidth(),.01);
  }

  // test setWidth IllegalArg
  @Test (expected = IllegalArgumentException.class)
  public void testSetWidthIllegalArg() {
    assertEquals(20.00, danielleRectangle.getWidth(),.01);

    danielleRectangle.setWidth(0);
  }

  // test setHeight
  @Test
  public void testSetHeight() {
    assertEquals(50.0, danielleRectangle.getHeight(),.01);

    danielleRectangle.setHeight(10.0);

    assertEquals(10.0, danielleRectangle.getHeight(),.01);
  }

  // test setHeight IllegalArg
  @Test (expected = IllegalArgumentException.class)
  public void testSetHeightIllegalArg() {
    assertEquals(50.0, danielleRectangle.getHeight(),.01);

    danielleRectangle.setHeight(0);
  }

  @Test
  public void testCopy() {
    IShape newRect = danielleRectangle.copy();
    assertEquals(danielleRectangle.toString(), newRect.toString());
  }
}