package Model.Commands;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import Model.AnimatorModel;
import Model.AnimatorModelImpl;
import Model.Point2D.IPoint2D;
import Model.Point2D.Point2D;
import Model.Shape.IShape;
import Model.Shape.Oval;
import Model.Shape.Rectangle;

import static org.junit.Assert.*;

public class CommandListTest {

  AnimatorModel model1;
  IShape rex;
  IShape opal;
  IShape bob;
  IShape pearl;
  IPoint2D coords1;
  IPoint2D coords2;
  IPoint2D coords3;
  IPoint2D coords4;

  @Before
  public void setUp() {
    model1 = new AnimatorModelImpl();
    coords1 = new Point2D(100, 100);
    coords4 = new Point2D(75, 75);
    coords2 = new Point2D(50, 50);
    coords3 = new Point2D(25, 25);

    rex = new Rectangle("rex", coords1, Color.white, 50, 50, 10, 20);
    opal = new Oval("opal", coords1, Color.white, 50, 50, 10, 20);
    bob = new Oval("bob", coords1, Color.white, 50, 50, 10, 20);
    pearl = new Rectangle("pearl", coords1, Color.white, 50, 50, 10, 20);
  }

  @Test
  public void getCommandList() {
  }

  @Test
  public void addShape() {
  }

  @Test
  public void removeShape() {
  }

  @Test
  public void moveShape() {
  }

  @Test
  public void changeColor() {
  }

  @Test
  public void changeShape() {
  }

  @Test
  public void testToString() {
  }
}