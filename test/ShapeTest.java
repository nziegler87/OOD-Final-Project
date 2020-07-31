import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import Model.Point2D.IPoint2D;
import Model.Point2D.Point2D;
import Model.Shape.IShape;
import Model.Shape.Oval;
import Model.Shape.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

/**
 * A JUnit test class for the shape classes
 */
public class ShapeTest {
  IShape nateOval;
  IShape ovalFromHomework;
  IShape danielleRectangle;
  IShape rectangleFromHomework;

  @Before
  public void setUp() {
    nateOval = new Oval("Nate", new Point2D(10.345, -5.232),
            new Color(255, 0, 233), 12.238, 14.233, 10, 20);
    ovalFromHomework = new Oval("c", new Point2D(500.0, 100.0),
            new Color(0, 0, 1), 60.0, 30.0, 30, 50);
    danielleRectangle = new Rectangle("Danielle", new Point2D(11.23, 12.11),
            new Color(29, 255, 229), 20, 50, 10, 20);
    rectangleFromHomework = new Rectangle("c", new Point2D(200.0, 200.0),
            new Color(1, 0, 0), 50.0, 100.0, 30, 50);
  }

  @Test
  public void getAppearTime() {
    assertEquals(10, nateOval.getAppearTime(), 0.01);
    assertEquals(30, ovalFromHomework.getAppearTime(), 0.01);
    assertEquals(10, danielleRectangle.getAppearTime(), 0.01);
    assertEquals(30, rectangleFromHomework.getAppearTime(), 0.01);
  }

  @Test
  public void getDisappearTime() {
    assertEquals(20, nateOval.getDisappearTime(), 0.01);
    assertEquals(50, ovalFromHomework.getDisappearTime(), 0.01);
    assertEquals(20, danielleRectangle.getDisappearTime(), 0.01);
    assertEquals(50, rectangleFromHomework.getDisappearTime(), 0.01);
  }

  // test getLabel
  @Test
  public void testGetLabel() {
    assertEquals("Nate", nateOval.getLabel());
    assertEquals("Danielle", danielleRectangle.getLabel());

  }

  // test getType
  @Test
  public void testGetType() {
    assertEquals("oval", nateOval.getType());
    assertEquals("rectangle", danielleRectangle.getType());
  }

  // test getCoordinates() {
  @Test
  public void testGetCoordinates() {
    IPoint2D originalPoint2D = new Point2D(10.345, 10.345);
    IShape testShape = new Oval("Test Oval", originalPoint2D,
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
    IShape testShapeOval = new Oval("c", new Point2D(500.0, 100.0),
            new Color(0, 0, 1), 60.0, 30.0, 10, 20);
    assertEquals("Name: c\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,0.0)"
                    + "\nAppears at t=10\nDisappears at t=20",
            testShapeOval.toString());

    testShapeOval.setCoordinates(24.2, 56.6);

    assertEquals("Name: c\n"
                    + "Type: oval\n"
                    + "Center: (24.2,56.6), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,0.0)"
                    + "\nAppears at t=10\nDisappears at t=20",
            testShapeOval.toString());

    IShape testShapeRectangle = new Rectangle("r", new Point2D(500.0, 100.0),
            new Color(0, 0, 1), 60.0, 30.0, 10, 20);
    assertEquals("Name: r\n"
                    + "Type: rectangle\n"
                    + "Min corner: (500.0,100.0), Width: 60.0, Height: 30.0, Color: (0.0,0.0,0.0)"
                    + "\nAppears at t=10\nDisappears at t=20",
            testShapeRectangle.toString());

    testShapeRectangle.setCoordinates(24.2, 56.6);
    assertEquals("Name: r\n"
                    + "Type: rectangle\n"
                    + "Min corner: (24.2,56.6), Width: 60.0, Height: 30.0, Color: (0.0,0.0,0.0)"
                    + "\nAppears at t=10\nDisappears at t=20",
            testShapeRectangle.toString());
  }

  // test that setCoordinates throws NullPointer
  @Test (expected = NullPointerException.class)
  public void testSetCoordinatesNullPointer() {
    nateOval.setCoordinates(null);
    danielleRectangle.setCoordinates(null);
  }

  // test scale
  @Test
  public void testScale() {
    nateOval.scale(2);
    danielleRectangle.scale(2);

    assertEquals("Name: Nate\n" +
            "Type: oval\n" +
            "Center: (10.3,-5.2), X radius: 12.2, Y radius: 14.2, Color: (1.0,0.0,0.9)\n" +
            "Appears at t=10\n" +
            "Disappears at t=20", nateOval.toString());

    assertEquals("Name: Danielle\n" +
            "Type: rectangle\n" +
            "Min corner: (11.2,12.1), Width: 20.0, Height: 50.0, Color: (0.1,1.0,0.9)\n" +
            "Appears at t=10\n" +
            "Disappears at t=20", danielleRectangle.toString());
  }


  // test scale throws IllegalArg if factor is 0
  @Test (expected = IllegalArgumentException.class)
  public void testScaleIllegalArg() {
    nateOval.scale(0);
  }


  // test getColor()
  @Test
  public void testGetColor() {
    Color colorOval = nateOval.getColor();
    assertEquals("java.awt.Color[r=255,g=0,b=233]", colorOval.toString());
    Color colorRectangle = danielleRectangle.getColor();
    assertEquals("java.awt.Color[r=29,g=255,b=229]", colorRectangle.toString());
  }


  // test setColor()
  @Test
  public void testSetColor() {
    assertEquals("Name: Nate\n"
                    + "Type: oval\n"
                    + "Center: (10.3,-5.2), X radius: 12.2, Y radius: 14.2, Color: (1.0,0.0,0.9)"
                    + "\nAppears at t=10\nDisappears at t=20",
            nateOval.toString());

    nateOval.setColor(new Color(147, 24, 24));

    assertEquals("Name: Nate\n"
                    + "Type: oval\n"
                    + "Center: (10.3,-5.2), X radius: 12.2, Y radius: 14.2, Color: (0.6,0.1,0.1)"
                    + "\nAppears at t=10\nDisappears at t=20",
            nateOval.toString());

    assertEquals("Name: Danielle\n"
                    + "Type: rectangle\n"
                    + "Min corner: (11.2,12.1), Width: 20.0, Height: 50.0, Color: (0.1,1.0,0.9)"
                    + "\nAppears at t=10\nDisappears at t=20",
            danielleRectangle.toString());

    danielleRectangle.setColor(new Color(147, 24, 24));

    assertEquals("Name: Danielle\n"
                    + "Type: rectangle\n"
                    + "Min corner: (11.2,12.1), Width: 20.0, Height: 50.0, Color: (0.6,0.1,0.1)"
                    + "\nAppears at t=10\nDisappears at t=20",
            danielleRectangle.toString());
  }

  // test setColor throws IllegalArg
  @Test(expected = NullPointerException.class)
  public void testSetColorOvalIllegalArg() {
    nateOval.setColor(null);
  }

  // test setColor throws IllegalArg
  @Test(expected = NullPointerException.class)
  public void testSetColorRectangleIllegalArg() {
    danielleRectangle.setColor(null);
  }


  // test toString
  @Test
  public void testToString() {
    assertEquals("Name: Nate\n"
                    + "Type: oval\n"
                    + "Center: (10.3,-5.2), X radius: 12.2, Y radius: 14.2, Color: (1.0,0.0,0.9)"
                    + "\nAppears at t=10\nDisappears at t=20",
            nateOval.toString());
    assertEquals("Name: Danielle\n"
                    + "Type: rectangle\n"
                    + "Min corner: (11.2,12.1), Width: 20.0, Height: 50.0, Color: (0.1,1.0,0.9)"
                    + "\nAppears at t=10\nDisappears at t=20",
            danielleRectangle.toString());
  }

  // test getWidth()
  @Test
  public void testGetWidth() {
    assertEquals(12.238, nateOval.getWidth(), .01);
    assertEquals(20.0, danielleRectangle.getWidth(), .01);
  }

  // test getHeight()
  @Test
  public void testGetHeight() {
    assertEquals(14.233, nateOval.getHeight(), .01);
    assertEquals(50.0, danielleRectangle.getHeight(), .01);
  }

  // test setWidth
  @Test
  public void testSetWidth() {
    assertEquals(12.238, nateOval.getWidth(), .01);
    nateOval.setWidth(10.0);
    assertEquals(10.0, nateOval.getWidth(), .01);

    assertEquals(20.0, danielleRectangle.getWidth(),.01);
    danielleRectangle.setWidth(10.0);
    assertEquals(10.0, danielleRectangle.getWidth(),.01);
  }

  // test setWidth IllegalArg
  @Test(expected = IllegalArgumentException.class)
  public void testSetWidthOvalIllegalArg() {
    assertEquals(12.238, nateOval.getWidth(), .01);
    nateOval.setWidth(0);
  }

  // test setWidth IllegalArg
  @Test (expected = IllegalArgumentException.class)
  public void testSetWidthRectangleIllegalArg() {
    assertEquals(20.00, danielleRectangle.getWidth(),.01);
    danielleRectangle.setWidth(0);
  }

  // test setHeight
  @Test
  public void testSetHeight() {
    assertEquals(14.233, nateOval.getHeight(), .01);
    nateOval.setHeight(10.0);
    assertEquals(10.0, nateOval.getHeight(), .01);

    assertEquals(50.0, danielleRectangle.getHeight(),.01);
    danielleRectangle.setHeight(10.0);
    assertEquals(10.0, danielleRectangle.getHeight(),.01);
  }

  // test setHeight IllegalArg
  @Test(expected = IllegalArgumentException.class)
  public void testSetHeightOvalIllegalArg() {
    assertEquals(14.233, nateOval.getHeight(), .01);
    nateOval.setHeight(0);
  }

  // test setHeight IllegalArg
  @Test (expected = IllegalArgumentException.class)
  public void testSetHeightRectangleIllegalArg() {
    assertEquals(50.0, danielleRectangle.getHeight(),.01);
    danielleRectangle.setHeight(0);
  }

  // test copy
  @Test
  public void testCopy() {
    IShape newOval = nateOval.copy();
    assertEquals(nateOval.toString(), newOval.toString());

    IShape newRect = danielleRectangle.copy();
    assertEquals(danielleRectangle.toString(), newRect.toString());
  }

  // test setAppearTime() {
  @Test
  public void testAppearTime() {
    nateOval.setAppearTime(20);
    danielleRectangle.setAppearTime((12));
    assertEquals("Name: Nate\n"
                    + "Type: oval\n"
                    + "Center: (10.3,-5.2), X radius: 12.2, Y radius: 14.2, Color: (1.0,0.0,0.9)"
                    + "\nAppears at t=20\nDisappears at t=20",
            nateOval.toString());
    assertEquals("Name: Danielle\n"
                    + "Type: rectangle\n"
                    + "Min corner: (11.2,12.1), Width: 20.0, Height: 50.0, Color: (0.1,1.0,0.9)"
                    + "\nAppears at t=12\nDisappears at t=20",
            danielleRectangle.toString());
  }

  // test setAppearTimeIllegalArg
  @Test (expected = IllegalArgumentException.class)
  public void testAppearTimeIllegalArg() {
    nateOval.setAppearTime(-1);
    assertEquals("Name: Nate\n"
                    + "Type: oval\n"
                    + "Center: (10.3,-5.2), X radius: 12.2, Y radius: 14.2, Color: (255, 0, 233)"
                    + "\nAppears at t=20\nDisappears at t=20",
            nateOval.toString());
  }

  // test setDisappearTime
  @Test
  public void testDisappearTime() {
    nateOval.setDisappearTime(30);
    assertEquals("Name: Nate\n"
                    + "Type: oval\n"
                    + "Center: (10.3,-5.2), X radius: 12.2, Y radius: 14.2, Color: (1.0,0.0,0.9)"
                    + "\nAppears at t=10\nDisappears at t=30",
            nateOval.toString());
  }

  // test setDisappearTimeIllegalArg Negative
  @Test (expected = IllegalArgumentException.class)
  public void testDisappearTimeIllegalArg() {
    nateOval.setDisappearTime(-2);
    assertEquals("Name: Nate\n"
                    + "Type: oval\n"
                    + "Center: (10.3,-5.2), X radius: 12.2, Y radius: 14.2, Color: (255, 0, 233)"
                    + "\nAppears at t=10\nDisappears at t=-2",
            nateOval.toString());
  }

  // test setDisappearTimeIllegalArg disappear overlaps start
  @Test (expected = IllegalArgumentException.class)
  public void testDisappearTimeIllegalArg2() {
    nateOval.setDisappearTime(8);
    assertEquals("Name: Nate\n"
                    + "Type: oval\n"
                    + "Center: (10.3,-5.2), X radius: 12.2, Y radius: 14.2, Color: (255, 0, 233)"
                    + "\nAppears at t=10\nDisappears at t=8",
            nateOval.toString());
  }

  // test equals
  @Test
  public void testEqualsIdenticalObjectsOval() {
    IShape oval1 = new Oval("vido" ,new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);
    IShape oval2 = new Oval("vido" ,new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);

    assertEquals(oval1, oval2);
    assertEquals(oval1, oval2);
  }

  // test equals
  @Test
  public void testEqualsSameReferenceOval() {
    IShape oval1 = new Oval("vido" ,new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);
    IShape oval2 = oval1;

    assertEquals(oval1, oval2);
    assertEquals(oval2, oval1);
  }

  // test equals
  @Test
  public void testEqualsNotIdenticalObjectsOval() {
    IShape oval1 = new Oval("vido" ,new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);
    IShape oval2 = new Oval("Vido" ,new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);

    IShape oval4 = new Oval("vido" ,new Point2D(8, 10), new Color(100, 100, 100), 10, 10, 5, 10);
    IShape oval5 = new Oval("vido" ,new Point2D(10, 10), new Color(90, 100, 100), 10, 10, 5, 10);
    IShape oval3 = new Oval("vido" ,new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 6, 10);


    assertNotEquals(oval1, oval2);
    assertNotEquals(oval2, oval1);

    assertNotEquals(oval1, oval3);
    assertNotEquals(oval3, oval1);

    assertNotEquals(oval1, oval4);
    assertNotEquals(oval4, oval1);

    assertNotEquals(oval1, oval5);
    assertNotEquals(oval5, oval1);
  }

  @Test
  public void testEqualsIdenticalObjectsRectangle() {
    IShape rectangle1 = new Rectangle("vido" ,new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);
    IShape rectangle2 = new Rectangle("vido" ,new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);

    assertEquals(rectangle1, rectangle2);
    assertEquals(rectangle1, rectangle2);
  }

  // test equals
  @Test
  public void testEqualsSameReferenceRectangle() {
    IShape rectangle1 = new Rectangle("vido" ,new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);
    IShape rectangle2 = rectangle1;

    assertEquals(rectangle1, rectangle2);
    assertEquals(rectangle2, rectangle1);
  }

  // test equals
  @Test
  public void testEqualsNotIdenticalObjectsRectangle() {
    IShape rectangle1 = new Rectangle("vido" ,new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);
    IShape rectangle2 = new Rectangle("Vido" ,new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);

    IShape rectangle4 = new Rectangle("vido" ,new Point2D(8, 10), new Color(100, 100, 100), 10, 10, 5, 10);
    IShape rectangle5 = new Rectangle("vido" ,new Point2D(10, 10), new Color(90, 100, 100), 10, 10, 5, 10);
    IShape rectangle3 = new Rectangle("vido" ,new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 6, 10);


    assertNotEquals(rectangle1, rectangle2);
    assertNotEquals(rectangle2, rectangle1);

    assertNotEquals(rectangle1, rectangle3);
    assertNotEquals(rectangle3, rectangle1);

    assertNotEquals(rectangle1, rectangle4);
    assertNotEquals(rectangle4, rectangle1);

    assertNotEquals(rectangle1, rectangle5);
    assertNotEquals(rectangle5, rectangle1);
  }

  // test equals
  @Test
  public void testEqualsDifferentShapeObjects() {
    IShape rectangle1 = new Rectangle("vido" ,new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);
    IShape oval1 = new Oval("vido" ,new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);

    assertNotEquals(rectangle1, oval1);
  }

  // test rectangle constructor throws NullPointer for null coordinates
  @Test (expected = NullPointerException.class)
  public void testRectangleConstructorNullPointer() {
    IShape testRectangle = new Rectangle("Danielle", null,
            new Color(29, 255, 229), 20, 50, 10, 20);
  }

  // test rectangle constructor throws NullPointer for color
  @Test (expected = NullPointerException.class)
  public void testRectangleConstructorNullPointer2() {
    IShape testRectangle = new Rectangle("Danielle", new Point2D(11.23, 12.11),
            null, 20, 50, 10, 20);
  }

  // test rectangle constructor throws IllegalArg when appear time is negative
  @Test (expected = IllegalArgumentException.class)
  public void testRectangleConstructorIllegalArg() {
    IShape testRectangle = new Rectangle("Danielle", new Point2D(11.23, 12.11),
            new Color(29, 255, 229), 20, 50, -2, 20);
  }

  // test rectangle constructor throws IllegalArg when disappear time is negative
  @Test (expected = IllegalArgumentException.class)
  public void testRectangleConstructorIllegalArg2() {
    IShape testRectangle = new Rectangle("Danielle", new Point2D(11.23, 12.11),
            new Color(29, 255, 229), 20, 50, 10, -3);
  }

  // test rectangle constructor throws Illegal arg when appear time comes after disappear time
  @Test (expected = IllegalArgumentException.class)
  public void testRectangleConstructorIllegalArg3() {
    IShape testRectangle = new Rectangle("Danielle", new Point2D(11.23, 12.11),
            new Color(29, 255, 229), 20, 50, 10, 10);
  }

  // test rectangle constructor throws IllegalArg when width is 0
  @Test (expected = IllegalArgumentException.class)
  public void testRectangleConstructorIllegalArg4() {
    IShape testRectangle = new Rectangle("Danielle", new Point2D(11.23, 12.11),
            new Color(29, 255, 229), 0, 50, 10, 20);
  }

  // test that rectangle constructor throws Illegal Arg when height is 0
  @Test (expected = IllegalArgumentException.class)
  public void testRectangleConstructorIllegalArg5() {
    IShape testRectangle = new Rectangle("Danielle", new Point2D(11.23, 12.11),
            new Color(29, 255, 229), 20, 0, 10, 20);
  }
  
  // test oval constructor throws NullPointer for null coordinates
  @Test (expected = NullPointerException.class)
  public void testOvalConstructorNullPointer() {
    IShape testOval = new Oval("Danielle", null,
            new Color(29, 255, 229), 20, 50, 10, 20);
  }

  // test oval constructor throws NullPointer for color
  @Test (expected = NullPointerException.class)
  public void testOvalConstructorNullPointer2() {
    IShape testOval = new Oval("Danielle", new Point2D(11.23, 12.11),
            null, 20, 50, 10, 20);
  }

  // test oval constructor throws IllegalArg when appear time is negative
  @Test (expected = IllegalArgumentException.class)
  public void testOvalConstructorIllegalArg() {
    IShape testOval = new Oval("Danielle", new Point2D(11.23, 12.11),
            new Color(29, 255, 229), 20, 50, -2, 20);
  }

  // test oval constructor throws IllegalArg when disappear time is negative
  @Test (expected = IllegalArgumentException.class)
  public void testOvalConstructorIllegalArg2() {
    IShape testOval = new Oval("Danielle", new Point2D(11.23, 12.11),
            new Color(29, 255, 229), 20, 50, 10, -3);
  }

  // test oval constructor throws Illegal arg when appear time comes after disappear time
  @Test (expected = IllegalArgumentException.class)
  public void testOvalConstructorIllegalArg3() {
    IShape testOval = new Oval("Danielle", new Point2D(11.23, 12.11),
            new Color(29, 255, 229), 20, 50, 10, 10);
  }

  // test oval constructor throws IllegalArg when x radius is 0
  @Test (expected = IllegalArgumentException.class)
  public void testOvalConstructorIllegalArg4() {
    IShape testOval = new Oval("Danielle", new Point2D(11.23, 12.11),
            new Color(29, 255, 229), 0, 50, 10, 20);
  }

  // test that oval constructor throws Illegal Arg when y radius is 0
  @Test (expected = IllegalArgumentException.class)
  public void testOvalConstructorIllegalArg5() {
    IShape testOval = new Oval("Danielle", new Point2D(11.23, 12.11),
            new Color(29, 255, 229), 20, 0, 10, 20);
  }

}