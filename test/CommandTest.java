import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import Model.Commands.AbstractCommand;
import Model.Commands.ChangeColor;
import Model.Commands.ICommand;
import Model.Commands.Move;
import Model.Commands.Scale;
import Model.Point2D.IPoint2D;
import Model.Point2D.Point2D;
import Model.Shape.IShape;
import Model.Shape.Oval;
import Model.Shape.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

/**
 * A JUnit test class for Command types.
 */
public class CommandTest {

  private IShape bob;
  private IShape pearl;
  private ICommand changeColor;
  private ICommand move;
  private ICommand scale;

  @Before
  public void setUp() {
    IPoint2D coords1 = new Point2D(100, 100);
    IPoint2D coords2 = new Point2D(50, 50);
    bob = new Oval("bob", coords1, Color.white,
            50, 50, 10, 20);
    pearl = new Rectangle("pearl", coords1, Color.white,
            50, 50, 10, 20);

    changeColor = new ChangeColor(bob, 10, 50,
            Color.MAGENTA, Color.PINK);
    move = new Move(pearl, 0, 15,
            coords1, coords2);
    scale = new Scale(bob, 40, 60,
            10, 20, 20, 10);
  }

  // getting the command type for the commands
  @Test
  public void getCommandType() {
    assertEquals("changeColor", changeColor.getCommandType());
    assertEquals("move", move.getCommandType());
    assertEquals("scale", scale.getCommandType());
  }

  // getting the start time for commands
  @Test
  public void getStartTime() {
    assertEquals(10, changeColor.getStartTime(), 0.01);
    assertEquals(0, move.getStartTime(), 0.01);
    assertEquals(40, scale.getStartTime(), 0.01);
  }

  // getting the end time for commands
  @Test
  public void getEndTime() {
    assertEquals(50, changeColor.getEndTime(), 0.01);
    assertEquals(15, move.getEndTime(), 0.01);
    assertEquals(60, scale.getEndTime(), 0.01);
  }

  // getting the shape from the command
  @Test
  public void getShape() {
    assertEquals(bob.toString(), changeColor.getShape().toString());
    assertEquals(pearl.toString(), move.getShape().toString());
    assertEquals(bob.toString(), scale.getShape().toString());
  }

  // string output for commands
  @Test
  public void testToString () {
    assertEquals("bob changes color from (255, 0, 255) to (255, 175, 175) "
            + "from time t=10 to t=50\n", changeColor.toString());
    assertEquals("pearl moves from (100.0,100.0) to (50.0,50.0) "
            + "from time t=0 to t=15\n", move.toString());
    assertEquals("bob changes width from 10.0 to 20.0 and height from 20.0 to 10.0 "
            + "from time t=40 to t=60\n", scale.toString());
  }

  // test equals
  @Test
  public void testEqualsIdenticalObjectsMove() {
    IShape oval1 = new Oval("vido", new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new Move(oval1, 0, 10, new Point2D(0, 0), new Point2D(10, 10));
    ICommand command2 = new Move(oval1, 0, 10, new Point2D(0, 0), new Point2D(10, 10));

    assertEquals(command1, command2);
    assertEquals(command2, command1);
  }

  // test equals
  @Test
  public void testEqualsSameReferenceMove() {
    IShape oval1 = new Oval("vido", new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new Move(oval1, 0, 10, new Point2D(0, 0), new Point2D(10, 10));
    ICommand command2 = command1;

    assertEquals(command1, command2);
    assertEquals(command2, command1);
  }

  // test equals
  @Test
  public void testEqualsNotIdenticalObjectsMove() {
    IShape oval1 = new Oval("vido", new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);
    IShape rectangle1 = new Rectangle("vido", new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new Move(oval1, 0, 10, new Point2D(0, 0), new Point2D(10, 10));
    ICommand command2 = new Move(oval1, 0, 10, new Point2D(0, 0), new Point2D(10, 10));

    ICommand command3 = new Move(rectangle1, 0, 10, new Point2D(0, 0), new Point2D(10, 10));
    ICommand command4 = new Move(oval1, 1, 10, new Point2D(0, 0), new Point2D(10, 10));
    ICommand command5 = new Move(oval1, 0, 11, new Point2D(0, 0), new Point2D(10, 10));
    ICommand command6 = new Move(oval1, 0, 10, new Point2D(0, 2), new Point2D(10, 10));
    ICommand command7 = new Move(oval1, 0, 10, new Point2D(0, 0), new Point2D(13, 10));

    assertEquals(command1, command2);
    assertEquals(command2, command1);

    assertNotEquals(command1, command3);
    assertNotEquals(command3, command1);

    assertNotEquals(command1, command4);
    assertNotEquals(command4, command1);

    assertNotEquals(command1, command5);
    assertNotEquals(command5, command1);

    assertNotEquals(command1, command6);
    assertNotEquals(command6, command1);

    assertNotEquals(command1, command7);
    assertNotEquals(command7, command1);
  }

  // test equals
  @Test
  public void testEqualsIdenticalObjectsScale() {
    IShape oval1 = new Oval("vido", new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new Scale(oval1, 0, 10, 5, 5, 10, 10);
    ICommand command2 = new Scale(oval1, 0, 10, 5, 5, 10, 10);

    assertEquals(command1, command2);
    assertEquals(command2, command1);
  }

  // test equals
  @Test
  public void testEqualsSameReferenceScale() {
    IShape oval1 = new Oval("vido", new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new Scale(oval1, 0, 10, 5, 5, 10, 10);
    ICommand command2 = command1;

    assertEquals(command1, command2);
    assertEquals(command2, command1);
  }

  // test equals
  @Test
  public void testEqualsNotIdenticalObjectsScale() {
    IShape oval1 = new Oval("vido", new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);
    IShape rectangle1 = new Rectangle("vido", new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new Scale(oval1, 0, 10, 5, 5, 10, 10);
    ICommand command2 = new Scale(oval1, 0, 10, 5, 5, 10, 10);

    ICommand command3 = new Scale(rectangle1, 0, 10, 5, 5, 10, 10);
    ICommand command4 = new Scale(oval1, 1, 10, 5, 5, 10, 10);
    ICommand command5 = new Scale(oval1, 0, 11, 5, 5, 10, 10);
    ICommand command6 = new Scale(oval1, 0, 10, 6, 5, 10, 10);
    ICommand command7 = new Scale(oval1, 0, 10, 5, 7, 10, 10);
    ICommand command8 = new Scale(oval1, 0, 10, 5, 5, 11, 10);
    ICommand command9 = new Scale(oval1, 0, 10, 5, 5, 10, 11);

    assertEquals(command1, command2);
    assertEquals(command2, command1);

    assertNotEquals(command1, command3);
    assertNotEquals(command3, command1);

    assertNotEquals(command1, command4);
    assertNotEquals(command4, command1);

    assertNotEquals(command1, command5);
    assertNotEquals(command5, command1);

    assertNotEquals(command1, command6);
    assertNotEquals(command6, command1);

    assertNotEquals(command1, command7);
    assertNotEquals(command7, command1);

    assertNotEquals(command1, command8);
    assertNotEquals(command8, command1);

    assertNotEquals(command1, command9);
    assertNotEquals(command9, command1);
  }

  // test equals
  @Test
  public void testEqualsIdenticalObjectsChangeColor() {
    IShape oval1 = new Oval("vido", new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new ChangeColor(oval1, 0, 10, new Color(100, 100, 100), new Color(50, 50, 50));
    ICommand command2 = new ChangeColor(oval1, 0, 10, new Color(100, 100, 100), new Color(50, 50, 50));

    assertEquals(command1, command2);
    assertEquals(command2, command1);
  }

  // test equals
  @Test
  public void testEqualsSameReferenceChangeColor() {
    IShape oval1 = new Oval("vido", new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new ChangeColor(oval1, 0, 10, new Color(100, 100, 100), new Color(50, 50, 50));
    ICommand command2 = command1;

    assertEquals(command1, command2);
    assertEquals(command2, command1);
  }

  // test equals
  @Test
  public void testEqualsNotIdenticalObjectsChangeColor() {
    IShape oval1 = new Oval("vido", new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);
    IShape rectangle1 = new Rectangle("vido", new Point2D(10, 10), new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new ChangeColor(oval1, 0, 10, new Color(100, 100, 100), new Color(50, 50, 50));
    ICommand command2 = new ChangeColor(oval1, 0, 10, new Color(100, 100, 100), new Color(50, 50, 50));

    ICommand command3 = new ChangeColor(rectangle1, 0, 10, new Color(100, 100, 100), new Color(50, 50, 50));
    ICommand command4 = new ChangeColor(oval1, 1, 10, new Color(100, 100, 100), new Color(50, 50, 50));
    ICommand command5 = new ChangeColor(oval1, 0, 11, new Color(100, 100, 100), new Color(50, 50, 50));
    ICommand command6 = new ChangeColor(oval1, 0, 10, new Color(100, 120, 100), new Color(50, 50, 50));
    ICommand command7 = new ChangeColor(oval1, 0, 10, new Color(100, 100, 100), new Color(50, 53, 50));


    assertEquals(command1, command2);
    assertEquals(command2, command1);

    assertNotEquals(command1, command3);
    assertNotEquals(command3, command1);

    assertNotEquals(command1, command4);
    assertNotEquals(command4, command1);

    assertNotEquals(command1, command5);
    assertNotEquals(command5, command1);

    assertNotEquals(command1, command6);
    assertNotEquals(command6, command1);

    assertNotEquals(command1, command7);
    assertNotEquals(command7, command1);
  }
}
