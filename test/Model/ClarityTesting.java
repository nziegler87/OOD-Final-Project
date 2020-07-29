package Model;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.List;

import Model.Commands.ChangeColor;
import Model.Commands.Move;
import Model.Commands.Scale;
import Model.Point2D.Point2D;
import Model.Shape.IShape;
import Model.Shape.Oval;
import Model.Shape.Rectangle;

public class ClarityTesting {
  AnimatorModel model;
  IShape rectangleFromHomework;
  IShape ovalFromHomework;
  IShape testOval;

  @Before
  public void setUp(){
    model = new AnimatorModelImpl();

    rectangleFromHomework = new Rectangle("R", new Point2D(100.0, 100.0),
            new Color(0, 0 ,0), 10.0, 10.0, 1, 100);
    ovalFromHomework = new Oval("C", new Point2D(200, 200.0),
            new Color(0, 0, 0), 50.0, 50.0, 1, 100);

    testOval = new Oval("C", new Point2D(200, 200.0),
            new Color(Color.green.getRGB()), 50.0, 50.0, 1, 100);
  }

  @Test
  public void testAddShape() {
    model.addShape(rectangleFromHomework);
    System.out.println(model.getAnimationStatus());
  }

  @Test
  public void testMoveShape() {
    model.addShape(ovalFromHomework);
    model.addShape(rectangleFromHomework);

    //TODO: Do we check to make sure an object is visible before we apply an animation?
    model.addAnimation(new Move(rectangleFromHomework,
            20, 50, new Point2D(200, 200), new Point2D(400.0, 400.0)));
    model.addAnimation(new Scale(ovalFromHomework,
            10, 50, 50, 50,150, 150));
    model.addAnimation(new ChangeColor(rectangleFromHomework,
            10, 50, new Color(100, 100, 100),
            new Color(50, 50, 50)));
    model.addAnimation(new ChangeColor(ovalFromHomework, 15, 30, new Color (Color.red.getRGB()), new Color (Color.yellow.getRGB())));

    List<IShape> snapshotList = model.getSnapshot(30); // this is half way thru animation time
    System.out.println(model.getAnimationStatus());
  }
}
