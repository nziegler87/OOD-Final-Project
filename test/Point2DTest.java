import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
    assertEquals("x cord: -3.23 | y cord: -4.68", negativePointer.toString());
  }

  // test toString - zero
  @Test
  public void toStringZero() {
    assertEquals("x cord: 0.00 | y cord: 0.00", zeroPointer.toString());
  }

  // test toString - positive
  @Test
  public void toStringPositive() {
    assertEquals("x cord: 3.47 | y cord: 2.98", positivePointer.toString());

  }

}