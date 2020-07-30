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
  AnimatorModel model2;

  @Before
  public void setUp() {
    model2 = new AnimatorModelImpl();

    basicRectangle = new Rectangle("nate", new Point2D(0, 0), new Color(0,0,0), 10, 10, 5, 100);
    model2.addShape(basicRectangle);
    model2.addAnimation(new Move(basicRectangle, 30, 40, new Point2D(20, 20), new Point2D(30, 30)));
    model2.addAnimation(new Move(basicRectangle, 10, 20, new Point2D(10, 10), new Point2D(20, 20)));
    model2.addAnimation(new Scale(basicRectangle, 15, 25, 10, 10, 20, 20));
  }

  @Test
  public void testState() {
    System.out.println(model2.getAnimationStatus() + "\n\n");
    for (int i = 0 ; i < 100 ; i++) {
      System.out.println("Snapshot at tick " + i + ":\n" + model2.getSnapshot(i) + "\n");
    }
  }
}
