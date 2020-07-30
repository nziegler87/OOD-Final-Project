import org.junit.Before;
import org.junit.Test;

import Model.Point2D.IPoint2D;
import Model.Point2D.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for Point2D.
 */
public class Point2DTest {
  IPoint2D negativePointer;
  IPoint2D zeroPointer;
  IPoint2D positivePointer;

  @Before
  public void setUp() {
    negativePointer = new Point2D(-3.234, -4.675);
    zeroPointer = new Point2D(0, 0);
    positivePointer = new Point2D(3.467, 2.981);
  }

  // test get x - negative
  @Test
  public void getXNegative() {
    assertEquals(-3.23, negativePointer.getX(), .01);
  }

  // test get x - zero
  @Test
  public void getXZero() {
    assertEquals(0.00, zeroPointer.getX(), .01);
  }

  // test get y - positive
  @Test
  public void getXPositive() {
    assertEquals(3.467, positivePointer.getX(), .01);
  }

  // test get y - negative
  @Test
  public void getYNegative() {
    assertEquals(-4.675, negativePointer.getY(), .01);
  }

  // test get y - zero
  @Test
  public void getYZero() {
    assertEquals(0.00, zeroPointer.getY(), .01);
  }

  // test get y - positive
  @Test
  public void getYPositive() {
    assertEquals(2.981, positivePointer.getY(), .01);
  }

  // test toString - negative
  @Test
  public void toStringNegative() {
    assertEquals("(-3.2,-4.7)", negativePointer.toString());
  }

  // test toString - zero
  @Test
  public void toStringZero() {
    assertEquals("(0.0,0.0)", zeroPointer.toString());
  }

  // test toString - positive
  @Test
  public void toStringPositive() {
    assertEquals("(3.5,3.0)", positivePointer.toString());
  }

  // test equals
  @Test
  public void testEqualsIdenticalObjects() {
    IPoint2D samePoint1 = new Point2D(24.44, 28.79);
    IPoint2D samePoint2 = new Point2D(24.44, 28.79);

    assertEquals(samePoint1, samePoint2);
    assertEquals(samePoint2, samePoint1);
  }

  // test equals
  @Test
  public void testEqualsSameReference() {
    IPoint2D samePoint1 = new Point2D(24.44, 28.79);
    IPoint2D samePoint2 = samePoint1;

    assertEquals(samePoint1, samePoint2);
    assertEquals(samePoint2, samePoint1);
  }

  // test equals
  @Test
  public void testEqualsNotIdenticalObjects() {
    IPoint2D samePoint1 = new Point2D(24.44, 28.79);
    IPoint2D samePoint2 = new Point2D(24.45, 28.79);
    assertNotEquals(samePoint1, samePoint2);
    assertNotEquals(samePoint2, samePoint1);
  }
}