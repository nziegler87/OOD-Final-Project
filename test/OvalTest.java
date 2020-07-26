import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import Model.Point2D.IPoint2D;
import Model.Shape.IShape;
import Model.Shape.Oval;
import Model.Point2D.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

//TODO: Make a Hex code and RGB

public class OvalTest {
  IShape nateOval;
  IShape ovalFromHomework;

  @Before
  public void setUp() {
    nateOval = new Oval("Nate", new Point2D(10.345, -5.232),
            new Color(255, 0, 233), 12.238, 14.233);
    ovalFromHomework = new Oval("c", new Point2D(500.0, 100.0),
            new Color(0, 0, 1), 60.0, 30.0);
  }

  // test getLabel
  @Test
  public void testGetLabel() {
    assertEquals("Nate", nateOval.getLabel());
  }

  // test getType
  @Test
  public void testGetType() {
    assertEquals("oval", nateOval.getType());
  }

  // test getCoordinates() {
  @Test
  public void testGetCoordinates() {
    IPoint2D originalPoint2D = new Point2D(10.345, 10.345);
    IShape testShape = new Oval("Test Oval", originalPoint2D,
            new Color(40, 40, 40), 30, 30);
    IPoint2D returnedPoint2D = testShape.getCoordinates();

    // test that the two point2D are the same
    assertEquals(originalPoint2D, returnedPoint2D);

    // test that they are different objects
    assertNotSame(originalPoint2D, returnedPoint2D);
  }

  // test setCoordinates
  @Test
  public void testSetCoordinates() {
    IShape testShape = new Oval("c", new Point2D(500.0, 100.0),
            new Color(0, 0, 1), 60.0, 30.0);
    assertEquals("Name: c\n"
            + "Type: oval\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0, 0, 1)",
            testShape.toString());

    testShape.setCoordinates(24.2, 56.6);

    assertEquals("Name: c\n"
                    + "Type: oval\n"
                    + "Center: (24.2,56.6), X radius: 60.0, Y radius: 30.0, Color: (0, 0, 1)",
            testShape.toString());
  }

  // test scale throws IllegalArg if factor is 0
  @Test (expected = IllegalArgumentException.class)
  public void testScaleIllegalArg() {
    nateOval.scale(0);
  }

  // test scale works TODO: WORK ON THIS
  @Test
  public void testScale() {
    assertEquals("Name: Nate\n"
                    + "Type: oval\n"
                    + "Center: (10.3,-5.2), X radius: 12.2, Y radius: 14.2, Color: (255, 0, 233)",
            nateOval.toString());

    IShape scaledOval = nateOval.scale(4);

    assertEquals("Name: Nate\n"
                    + "Type: oval\n"
                    + "Center: (10.3,-5.2), X radius: 24.5, Y radius: 28.5, Color: (255, 0, 233)",
            scaledOval.toString());

    assertNotSame(nateOval, scaledOval);

  }

  // test getColor()
  @Test
  public void testGetColor() {
    Color color = nateOval.getColor();
    assertEquals("java.awt.Color[r=255,g=0,b=233]", color.toString());
  }

  // test getXRadius
  @Test
  public void testGetXRadius() {
  }

  // test setColor()
  @Test
  public void testSetColor() {
    assertEquals("Name: Nate\n"
                    + "Type: oval\n"
                    + "Center: (10.3,-5.2), X radius: 12.2, Y radius: 14.2, Color: (255, 0, 233)",
            nateOval.toString());

    nateOval.setColor(new Color(147, 24, 24));

    assertEquals("Name: Nate\n"
                    + "Type: oval\n"
                    + "Center: (10.3,-5.2), X radius: 12.2, Y radius: 14.2, Color: (147, 24, 24)",
            nateOval.toString());
  }

  // test setColor throws IllegalArg
  @Test (expected = IllegalArgumentException.class)
  public void testSetColorIllegalArg() {
    nateOval.setColor(null);
  }

  // test toString
  @Test
  public void testToString() {
    assertEquals("Name: Nate\n"
            + "Type: oval\n"
            + "Center: (10.3,-5.2), X radius: 12.2, Y radius: 14.2, Color: (255, 0, 233)",
            nateOval.toString());
  }

}