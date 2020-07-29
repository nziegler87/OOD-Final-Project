package Model;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.List;

import Model.Commands.ChangeColor;
import Model.Commands.Move;
import Model.Commands.Scale;
import Model.Point2D.Point2D;
import Model.Shape.IShape;
import Model.Shape.Oval;
import Model.Shape.Rectangle;

public class AnimatorModelImplTest {
  AnimatorModel model;
  AnimatorModel model2;

  IShape penguin;
  IShape trashPanda;
  IShape doggo;
  IShape chairmanMeow;

  @Before
  public void setUp() {
    model = new AnimatorModelImpl();
    trashPanda = new Rectangle("trash-panda", new Point2D(100.0, 100.0), new Color(0, 0, 0), 10.0, 10.0, 1, 100);
    doggo = new Oval("doggo", new Point2D(200, 200.0), new Color(0, 0, 0), 50.0, 50.0, 1, 100);
    chairmanMeow = new Oval("chairman-meow", new Point2D(200, 200.0), new Color(Color.green.getRGB()), 50.0, 50.0, 1, 100);
    penguin = new Rectangle("penguin", new Point2D(0, 0), new Color(0, 0, 0), 10, 10, 5, 100);

  }

  @Test
  public void testAddShape() {
    model.addShape(trashPanda);
    System.out.println(model.getAnimationStatus());
  }

  @Test
  public void testMoveShape() {
    model.addShape(doggo);
    model.addShape(trashPanda);

    model.addAnimation(new Move(trashPanda,
            20, 50, new Point2D(200, 200), new Point2D(400.0, 400.0)));
    model.addAnimation(new Scale(doggo,
            10, 50, 50, 50, 150, 150));
    model.addAnimation(new ChangeColor(trashPanda,
            10, 50, new Color(100, 100, 100),
            new Color(50, 50, 50)));
    model.addAnimation(new ChangeColor(doggo, 15, 30, new Color(Color.red.getRGB()), new Color(Color.yellow.getRGB())));

    List<IShape> snapshotList = model.getSnapshot(30); // this is half way thru animation time
    System.out.println(model.getAnimationStatus());
  }

  @Test
  public void testGetState() {
    model2 = new AnimatorModelImpl();

    model2.addShape(penguin);
    model2.addAnimation(new Move(penguin, 30, 40, new Point2D(20, 20), new Point2D(30, 30)));
    model2.addAnimation(new Move(penguin, 10, 20, new Point2D(10, 10), new Point2D(20, 20)));
    model2.addAnimation(new Scale(penguin, 15, 25, 10, 10, 20, 20));

    for (int i = 0; i < 45; i++) {
      System.out.println("Snapshot at tick " + i + ":\n" + model2.getSnapshot(i) + "\n");
    }
  }
}