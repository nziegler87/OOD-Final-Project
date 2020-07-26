import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import Model.Shape.IShape;
import Model.Point2D.Point2D;
import Model.Shape.Rectangle;


public class RectangleTest {
  IShape danielleRectangle;
  IShape rectangleFromHomework;

  @Before
  public void setUp() {
    danielleRectangle = new Rectangle("Danielle", new Point2D(11.23, 12.11),
            new Color(29, 255, 229), 20, 50);
    rectangleFromHomework = new Rectangle("c", new Point2D(200.0, 200.0),
            new Color(1, 0 ,0), 50.0, 100.0);
  }

  // test toString
  @Test
  public void testToString() {
    System.out.println(danielleRectangle.toString());
    System.out.println();
    System.out.println(rectangleFromHomework.toString());
  }

}