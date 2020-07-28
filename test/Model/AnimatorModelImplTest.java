package Model;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import Model.Point2D.IPoint2D;
import Model.Point2D.Point2D;
import Model.Shape.IShape;
import Model.Shape.Oval;
import Model.Shape.Rectangle;

import static org.junit.Assert.*;

public class AnimatorModelImplTest {

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
    pearl = new Rectangle("pearl", coords1, Color.white, 50, 50, 10 ,20);

//    model1.addShape("Rex", rex);
//    model1.addShape("Pearl", pearl);
  }

  @Test
  public void addShape() {
    model1.addShape("Bob", bob);
    assertEquals("", model1.getAnimationStatus());
  }

  @Test
  public void removeShape() {
    model1.removeShape("Rex");
    assertEquals("", model1.getAnimationStatus());
  }

  @Test
  public void getShape() {

  }

//  @Test
//  public void moveShape() {
//    model1.moveShape("Pearl", 40, 35);
//    assertEquals("", model1.getAnimationStatus());
//  }
//
//  @Test
//  public void changeColor() {
//    model1.changeColor("Bob", Color.red);
//    assertEquals("", model1.getAnimationStatus());
//  }
//
//  @Test
//  public void changeShape() {
//    model1.changeShape("Pearl", opal);
//    assertEquals("", model1.getAnimationStatus());
//  }

  @Test
  public void getAnimationStatus() {
  }
}