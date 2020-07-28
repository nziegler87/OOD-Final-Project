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
    model = new AnimatorModelImpl(10.0);

    rectangleFromHomework = new Rectangle("R", new Point2D(200.0, 200.0),
            new Color(1, 0 ,0), 50.0, 100.0, 1, 100);

    ovalFromHomework = new Oval("C", new Point2D(500.0, 100.0),
            new Color(0, 0, 1), 60.0, 30.0, 6, 100);
  }

  @Test
  public void testAddShape() {
    model.addShape("rectangle", rectangleFromHomework);
    System.out.println(model.getAnimationStatus());
  }

  @Test
  public void testMoveShape() {
    model.addShape("oval", ovalFromHomework);
    model.addShape("rectangle", rectangleFromHomework);
    IPoint2D newCoordinates = new Point2D(400.0, 400.0);
    ICommand moveCommand = new Move(rectangleFromHomework, 10, 50, newCoordinates);
    model.commandOnShape(moveCommand, "rectangle", 30);

    System.out.println(model.getAnimationStatus());
  }

}
