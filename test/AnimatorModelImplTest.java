import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Model.AnimatorModel;
import Model.AnimatorModelImpl;
import Model.Commands.ChangeColor;
import Model.Commands.ICommand;
import Model.Commands.Move;
import Model.Commands.Scale;
import Model.Point2D.Point2D;
import Model.Shape.IShape;
import Model.Shape.Oval;
import Model.Shape.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the Animator Model.
 */
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
    trashPanda = new Rectangle("trash-panda", new Point2D(100.0, 100.0),
            new Color(0, 0, 0), 10.0, 10.0, 1, 100);
    doggo = new Oval("doggo", new Point2D(200, 200.0),
            new Color(0, 0, 0), 50.0, 50.0, 1,
            100);
    chairmanMeow = new Oval("chairman-meow", new Point2D(200, 200.0),
            new Color(Color.green.getRGB()), 50.0, 50.0, 1,
            100);
    penguin = new Rectangle("penguin", new Point2D(0, 0),
            new Color(0, 0, 0), 10, 10, 5, 100);
    model.addShape(doggo);
  }

  // null shape in addShape
  @Test(expected = NullPointerException.class)
  public void testNullShape() {
    model.addShape(null);
  }

  // adding a shape that's already in the list
  @Test(expected = IllegalArgumentException.class)
  public void testAlreadyAddedShape() {
    model.addShape(doggo);
  }

  // adding a shape to the model
  @Test
  public void testAddShape() {
    model.addShape(trashPanda);
    assertEquals("Shapes:\n" +
            "Name: doggo\n" +
            "Type: oval\n" +
            "Center: (200.0,200.0), X radius: 50.0, Y radius: 50.0, Color: (0, 0, 0)\n" +
            "Appears at t=1\n" +
            "Disappears at t=100\n" +
            "\n" +
            "Name: trash-panda\n" +
            "Type: rectangle\n" +
            "Min corner: (100.0,100.0), Width: 10.0, Height: 10.0, Color: (0, 0, 0)\n" +
            "Appears at t=1\n" +
            "Disappears at t=100\n" +
            "\n" +
            "Command list is empty.", model.getAnimationStatus());
  }

  // removing a null shape
  @Test(expected = NullPointerException.class)
  public void testRemoveShapeNull() {
    model.removeShape(null);
  }

  // removing a shape that's not in the list
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveShapeNotInList() {
    model.removeShape(chairmanMeow);
  }

  // removing a shape from the model
  @Test
  public void testRemoveShape() {
    model.addShape(trashPanda);
    model.removeShape(doggo);
    assertEquals("Shapes:\n" +
            "Name: trash-panda\n" +
            "Type: rectangle\n" +
            "Min corner: (100.0,100.0), Width: 10.0, Height: 10.0, Color: (0, 0, 0)\n" +
            "Appears at t=1\n" +
            "Disappears at t=100\n" +
            "\n" +
            "Command list is empty.", model.getAnimationStatus());
  }

  // remove a shape so there's no shapes left
  @Test
  public void testRemoveShapeNoShapesLeft() {
    model.removeShape(doggo);
    assertEquals("No shapes have been added to the inventory, so no animations can be " +
            "displayed", model.getAnimationStatus());
  }

  // removing a shape that's not in the list
  @Test(expected = NullPointerException.class)
  public void testAddAnimationNull() {
    model.addAnimation(null);
  }

  // adding animation to a shape that is not in the list
  @Test(expected = IllegalArgumentException.class)
  public void testAddAnimationNotInList() {
    ICommand move = new Move(chairmanMeow, 10, 50,
            new Point2D(40, 50), new Point2D(100, 100));
    model.addAnimation(move);
  }

  // adding in an animation with a conflict with another
  @Test(expected = IllegalArgumentException.class)
  public void testAddAnimationConflict() {
    ICommand move = new Move(chairmanMeow, 10, 50,
            new Point2D(40, 50), new Point2D(100, 100));
    ICommand moveConflict = new Move(chairmanMeow, 10, 50,
            new Point2D(40, 50), new Point2D(300, 200));
    model.addAnimation(move);
    model.addAnimation(moveConflict);
  }

  // adding in an animation with a conflict with another
  @Test
  public void testAddAnimationSameShapeAllCommandsNoConflict() {
    model.addShape(chairmanMeow);

    ICommand move = new Move(chairmanMeow, 10, 50,
            new Point2D(40, 50), new Point2D(100, 100));
    ICommand color = new ChangeColor(chairmanMeow, 10, 50,
            Color.PINK, Color.BLUE);
    ICommand scale = new Scale(chairmanMeow, 10, 50,
            10, 10, 50, 50);

    model.addAnimation(move);
    model.addAnimation(color);
    model.addAnimation(scale);

    assertEquals("Shapes:\n" +
            "Name: doggo\n" +
            "Type: oval\n" +
            "Center: (200.0,200.0), X radius: 50.0, Y radius: 50.0, Color: (0, 0, 0)\n" +
            "Appears at t=1\n" +
            "Disappears at t=100\n" +
            "\n" +
            "Name: chairman-meow\n" +
            "Type: oval\n" +
            "Center: (200.0,200.0), X radius: 50.0, Y radius: 50.0, Color: (0, 255, 0)\n" +
            "Appears at t=1\n" +
            "Disappears at t=100\n" +
            "\n" +
            "chairman-meow moves from (40.0,50.0) to (100.0,100.0) from time t=10 to t=50\n" +
            "chairman-meow changes color from (255, 175, 175) to (0, 0, 255) from time t=10 to t=50\n" +
            "chairman-meow changes width from 10.0 to 50.0 and height from 10.0 to 50.0 from time t=10 to t=50\n", model.getAnimationStatus());
  }

  // adding in multiple animations
  @Test
  public void testAddAnimationOutOfOrderIntoOrder() {
    model.addShape(chairmanMeow);
    model.addShape(trashPanda);

    ICommand moveMeow = new Move(chairmanMeow, 30, 50, new Point2D(40, 50), new Point2D(100, 100));
    ICommand colorMeow = new ChangeColor(chairmanMeow, 55, 80, Color.MAGENTA, Color.BLUE);
    ICommand scaleMeow = new Scale(chairmanMeow, 30, 60, 50, 50, 100, 100);

    ICommand moveDoggo = new Move(doggo, 5, 50, new Point2D(40, 50), new Point2D(300, 200));
    ICommand colorDoggo = new ChangeColor(doggo, 60, 100, Color.YELLOW, Color.RED);
    ICommand scaleDoggo = new Scale(doggo, 15, 75, 5, 5, 10, 55);

    ICommand moveTrashPanda = new Move(trashPanda, 10, 40, new Point2D(40, 50), new Point2D(200, 400));
    ICommand colorTrashPanda = new ChangeColor(trashPanda, 90, 100, Color.PINK, Color.ORANGE);
    ICommand scaleTrashPanda = new Scale(trashPanda, 10, 25, 70, 35, 40, 50);

    model.addAnimation(moveMeow);
    model.addAnimation(moveDoggo);
    model.addAnimation(moveTrashPanda);

    model.addAnimation(colorMeow);
    model.addAnimation(colorDoggo);
    model.addAnimation(colorTrashPanda);

    model.addAnimation(scaleMeow);
    model.addAnimation(scaleDoggo);
    model.addAnimation(scaleTrashPanda);

    assertEquals("Shapes:\n" +
                    "Name: doggo\n" +
                    "Type: oval\n" +
                    "Center: (200.0,200.0), X radius: 50.0, Y radius: 50.0, Color: (0, 0, 0)\n" +
                    "Appears at t=1\n" +
                    "Disappears at t=100\n" +
                    "\n" +
                    "Name: trash-panda\n" +
                    "Type: rectangle\n" +
                    "Min corner: (100.0,100.0), Width: 10.0, Height: 10.0, Color: (0, 0, 0)\n" +
                    "Appears at t=1\n" +
                    "Disappears at t=100\n" +
                    "\n" +
                    "Name: chairman-meow\n" +
                    "Type: oval\n" +
                    "Center: (200.0,200.0), X radius: 50.0, Y radius: 50.0, Color: (0, 255, 0)\n" +
                    "Appears at t=1\n" +
                    "Disappears at t=100\n" +
                    "\n" +
                    "doggo moves from (40.0,50.0) to (300.0,200.0) from time t=5 to t=50\n" +
                    "trash-panda moves from (40.0,50.0) to (200.0,400.0) from time t=10 to t=40\n" +
                    "trash-panda changes width from 70.0 to 40.0 and height from 35.0 to 50.0 from time t=10 to t=25\n" +
                    "doggo changes width from 5.0 to 10.0 and height from 5.0 to 55.0 from time t=15 to t=75\n" +
                    "chairman-meow moves from (40.0,50.0) to (100.0,100.0) from time t=30 to t=50\n" +
                    "chairman-meow changes width from 50.0 to 100.0 and height from 50.0 to 100.0 from time t=30 to t=60\n" +
                    "chairman-meow changes color from (255, 0, 255) to (0, 0, 255) from time t=55 to t=80\n" +
                    "doggo changes color from (255, 255, 0) to (255, 0, 0) from time t=60 to t=100\n" +
                    "trash-panda changes color from (255, 175, 175) to (255, 200, 0) from time t=90 to t=100\n",
            model.getAnimationStatus());
  }

  // removing a null animation
  @Test(expected = NullPointerException.class)
  public void testRemoveAnimationNull() {
    model.removeAnimation(null);
  }

  // removing animation from a shape that is not in the list
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveAnimationNotInList() {
    ICommand move = new Move(chairmanMeow, 10, 50,
            new Point2D(40, 50), new Point2D(100, 100));
    model.removeAnimation(move);
  }

  // removing an animation
  @Test
  public void testRemoveAnimation() {
    model.addShape(chairmanMeow);
    model.addShape(trashPanda);

    ICommand colorMeow = new ChangeColor(chairmanMeow, 55, 80, Color.MAGENTA, Color.BLUE);
    ICommand scaleTrashPanda = new Scale(trashPanda, 10, 25, 70, 35, 40, 50);

    model.addAnimation(colorMeow);
    model.addAnimation(scaleTrashPanda);

    assertEquals("Shapes:\n" +
                    "Name: doggo\n" +
                    "Type: oval\n" +
                    "Center: (200.0,200.0), X radius: 50.0, Y radius: 50.0, Color: (0, 0, 0)\n" +
                    "Appears at t=1\n" +
                    "Disappears at t=100\n" +
                    "\n" +
                    "Name: trash-panda\n" +
                    "Type: rectangle\n" +
                    "Min corner: (100.0,100.0), Width: 10.0, Height: 10.0, Color: (0, 0, 0)\n" +
                    "Appears at t=1\n" +
                    "Disappears at t=100\n" +
                    "\n" +
                    "Name: chairman-meow\n" +
                    "Type: oval\n" +
                    "Center: (200.0,200.0), X radius: 50.0, Y radius: 50.0, Color: (0, 255, 0)\n" +
                    "Appears at t=1\n" +
                    "Disappears at t=100\n" +
                    "\n" +
                    "trash-panda changes width from 70.0 to 40.0 and height from 35.0 to 50.0 from time t=10 to t=25\n" +
                    "chairman-meow changes color from (255, 0, 255) to (0, 0, 255) from time t=55 to t=80\n",
            model.getAnimationStatus());

    model.removeAnimation(colorMeow);

    assertEquals("Shapes:\n" +
                    "Name: doggo\n" +
                    "Type: oval\n" +
                    "Center: (200.0,200.0), X radius: 50.0, Y radius: 50.0, Color: (0, 0, 0)\n" +
                    "Appears at t=1\n" +
                    "Disappears at t=100\n" +
                    "\n" +
                    "Name: trash-panda\n" +
                    "Type: rectangle\n" +
                    "Min corner: (100.0,100.0), Width: 10.0, Height: 10.0, Color: (0, 0, 0)\n" +
                    "Appears at t=1\n" +
                    "Disappears at t=100\n" +
                    "\n" +
                    "Name: chairman-meow\n" +
                    "Type: oval\n" +
                    "Center: (200.0,200.0), X radius: 50.0, Y radius: 50.0, Color: (0, 255, 0)\n" +
                    "Appears at t=1\n" +
                    "Disappears at t=100\n" +
                    "\n" +
                    "trash-panda changes width from 70.0 to 40.0 and height from 35.0 to 50.0 from time t=10 to t=25\n",
            model.getAnimationStatus());
  }

  // getting the animation status
  @Test
  public void testGetAnimationState() {
    model2 = new AnimatorModelImpl();

    model2.addShape(penguin);
    model2.addAnimation(new Move(penguin, 30, 40, new Point2D(20, 20),
            new Point2D(30, 30)));
    model2.addAnimation(new Move(penguin, 10, 20, new Point2D(10, 10),
            new Point2D(20, 20)));
    model2.addAnimation(new Scale(penguin, 15, 25, 10, 10,
            20, 20));

    StringBuilder str = new StringBuilder();

    for (int i = 0; i < 45; i++) {
      str.append("Snapshot at tick ").append(i).append(":\n").append(model2.getSnapshot(i))
              .append("\n\n");
    }

    assertEquals("Snapshot at tick 0:\n" +
            "[]\n" +
            "\n" +
            "Snapshot at tick 1:\n" +
            "[]\n" +
            "\n" +
            "Snapshot at tick 2:\n" +
            "[]\n" +
            "\n" +
            "Snapshot at tick 3:\n" +
            "[]\n" +
            "\n" +
            "Snapshot at tick 4:\n" +
            "[]\n" +
            "\n" +
            "Snapshot at tick 5:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0), Width: 10.0, Height: 10.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 6:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0), Width: 10.0, Height: 10.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 7:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0), Width: 10.0, Height: 10.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 8:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0), Width: 10.0, Height: 10.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 9:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0), Width: 10.0, Height: 10.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 10:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (10.0,10.0), Width: 10.0, Height: 10.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 11:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (11.0,11.0), Width: 10.0, Height: 10.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 12:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (12.0,12.0), Width: 10.0, Height: 10.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 13:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (13.0,13.0), Width: 10.0, Height: 10.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 14:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (14.0,14.0), Width: 10.0, Height: 10.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 15:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (15.0,15.0), Width: 10.0, Height: 10.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 16:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (16.0,16.0), Width: 11.0, Height: 11.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 17:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (17.0,17.0), Width: 12.0, Height: 12.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 18:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (18.0,18.0), Width: 13.0, Height: 13.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 19:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (19.0,19.0), Width: 14.0, Height: 14.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 20:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (20.0,20.0), Width: 15.0, Height: 15.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 21:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (20.0,20.0), Width: 16.0, Height: 16.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 22:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (20.0,20.0), Width: 17.0, Height: 17.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 23:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (20.0,20.0), Width: 18.0, Height: 18.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 24:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (20.0,20.0), Width: 19.0, Height: 19.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 25:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (20.0,20.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 26:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (20.0,20.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 27:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (20.0,20.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 28:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (20.0,20.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 29:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (20.0,20.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 30:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (20.0,20.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 31:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (21.0,21.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 32:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (22.0,22.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 33:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (23.0,23.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 34:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (24.0,24.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 35:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (25.0,25.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 36:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (26.0,26.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 37:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (27.0,27.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 38:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (28.0,28.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 39:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (29.0,29.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 40:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (30.0,30.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 41:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (30.0,30.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 42:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (30.0,30.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 43:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (30.0,30.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n" +
            "\n" +
            "Snapshot at tick 44:\n" +
            "[Name: penguin\n" +
            "Type: rectangle\n" +
            "Min corner: (30.0,30.0), Width: 20.0, Height: 20.0, Color: (0, 0, 0)\n" +
            "Appears at t=5\n" +
            "Disappears at t=100]\n\n", str.toString());
  }

  // getting the snapshot at tick 30
  @Test
  public void testGetSnapshot() {
    AnimatorModel modelSnapshot = new AnimatorModelImpl();
    modelSnapshot.addShape(doggo);
    modelSnapshot.addShape(chairmanMeow);
    modelSnapshot.addShape(trashPanda);

    ICommand moveMeow = new Move(chairmanMeow, 10, 50, new Point2D(50, 50), new Point2D(100, 100));
    ICommand colorDoggo = new ChangeColor(doggo, 20, 30, Color.YELLOW, Color.RED);
    ICommand scaleTrashPanda = new Scale(trashPanda, 10, 40, 10, 10, 40, 40);

    modelSnapshot.addAnimation(moveMeow);
    modelSnapshot.addAnimation(colorDoggo);
    modelSnapshot.addAnimation(scaleTrashPanda);

    List<IShape> testSnapshotList = new ArrayList<>();

    IShape meowCopy = chairmanMeow.copy();
    meowCopy.setCoordinates(new Point2D(75, 75));

    IShape doggoCopy = doggo.copy();
    doggoCopy.setColor(Color.RED);

    IShape trashPandaCopy = trashPanda.copy();
    trashPandaCopy.setHeight(30);
    trashPandaCopy.setWidth(30);

    testSnapshotList.add(doggoCopy);
    testSnapshotList.add(trashPandaCopy);
    testSnapshotList.add(meowCopy);

    assertEquals(testSnapshotList.toString(), modelSnapshot.getSnapshot(30).toString());
  }
}