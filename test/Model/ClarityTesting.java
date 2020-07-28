package Model;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.List;

import Model.Commands.ChangeColor;
import Model.Commands.ICommand;
import Model.Commands.Move;
import Model.Commands.Scale;
import Model.Point2D.IPoint2D;
import Model.Point2D.Point2D;
import Model.Shape.IShape;
import Model.Shape.Oval;
import Model.Shape.Rectangle;

public class ClarityTesting {
  AnimatorModel model;
  IShape rectangleFromHomework;
  IShape ovalFromHomework;

  @Before
  public void setUp(){
    model = new AnimatorModelImpl();

    rectangleFromHomework = new Rectangle("R", new Point2D(200.0, 200.0),
            new Color(1, 0 ,0), 50.0, 100.0, 1, 100);

    ovalFromHomework = new Oval("C", new Point2D(500.0, 100.0),
            new Color(0, 0, 1), 60.0, 30.0, 6, 100);
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
    IPoint2D newCoordinates = new Point2D(400.0, 400.0);
    ICommand moveCommand = new Move(rectangleFromHomework, 10, 50, newCoordinates);
    model.addAnimation(moveCommand);

    //TODO: Do we check to make sure an object is visible before we apply an animation?
    model.addAnimation(new Scale(ovalFromHomework, 4, 23, 400, 400));
    model.addAnimation(new ChangeColor(rectangleFromHomework, 4, 23, new Color(48, 134, 156)));

    List<IShape> snapshotList = model.getSnapshot(6);

    System.out.println(model.getAnimationStatus());

    System.out.println(snapshotList.toString());
  }

}
