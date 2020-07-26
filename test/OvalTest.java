import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import Model.Shape.IShape;
import Model.Shape.Oval;
import Model.Point2D.Point2D;


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

  // test toString
  @Test
  public void testToString() {
    System.out.println(nateOval.toString());
    System.out.println();
    System.out.println(ovalFromHomework.toString());

  }
}