package Model;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import Model.Commands.ICommand;
import Model.Commands.Move;
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

    rectangleFromHomework = new Rectangle("c", new Point2D(200.0, 200.0),
            new Color(1, 0 ,0), 50.0, 100.0);

    ovalFromHomework = new Oval("c", new Point2D(500.0, 100.0),
            new Color(0, 0, 1), 60.0, 30.0);
  }

  @Test
  public void testAddShape() {
    model.addShape("rectangle", rectangleFromHomework);
    System.out.println(model.getAnimationStatus());
  }

  @Test
  public void testMoveShape() {
    model.addShape("rectangle", rectangleFromHomework);
    IPoint2D newCoordinates = new Point2D(400.0, 400.0);
    ICommand moveCommand = new Move(rectangleFromHomework, 10, 50, rectangleFromHomework.getCoordinates(), newCoordinates);
    model.commandOnShape(moveCommand, "rectangle", 30);

    System.out.println(model.getAnimationStatus());
  }

}
