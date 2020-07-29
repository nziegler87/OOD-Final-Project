import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import Model.AnimatorModel;
import Model.AnimatorModelImpl;
import Model.Commands.Move;
import Model.Commands.Scale;
import Model.Point2D.Point2D;
import Model.Shape.IShape;
import Model.Shape.Rectangle;

public class GetStateTest {
  IShape basicRectangle;
  AnimatorModel model;

  @Before
  public void setUp() {
    model = new AnimatorModelImpl();

    basicRectangle = new Rectangle("nate", new Point2D(0, 0), new Color(0,0,0), 10, 10, 5, 100);
    model.addShape(basicRectangle);
    model.addAnimation(new Move(basicRectangle, 30, 40, new Point2D(20, 20), new Point2D(30, 30)));
    model.addAnimation(new Move(basicRectangle, 10, 20, new Point2D(10, 10), new Point2D(20, 20)));
    model.addAnimation(new Scale(basicRectangle, 15, 25, 10, 10, 20, 20));

  }

  @Test
  public void testState() {
//    System.out.println(model.getAnimationStatus());
    for (int i = 0 ; i < 45 ; i++) {
      System.out.println("Snapshot at tick " + i + ":\n" + model.getSnapshot(i) + "\n");
    }
  }
}
