import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import Model.Commands.AbstractCommand;
import Model.Commands.ChangeColor;
import Model.Commands.Move;
import Model.Commands.Scale;
import Model.Point2D.IPoint2D;
import Model.Point2D.Point2D;
import Model.Shape.IShape;
import Model.Shape.Oval;
import Model.Shape.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for Command types.
 */
public class CommandTest {

  private IShape bob;
  private IShape pearl;
  private AbstractCommand changeColor;
  private AbstractCommand move;
  private AbstractCommand scale;

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
            10, 20, 20 ,10);
  }

  @Test
  public void testChangeColor() {
    assertEquals("bob changes color from (255, 0, 255) to (255, 175, 175) "
            + "from time t=10 to t=50\n", changeColor.toString());
  }

  @Test
  public void testMove() {
    assertEquals("pearl moves from (100.0,100.0) to (50.0,50.0) "
            + "from time t=0 to t=15\n", move.toString());
  }

  @Test
  public void testScale() {
    assertEquals("bob changes width from 10.0 to 20.0 and height from 20.0 to 10.0 "
            + "from time t=40 to t=60\n", scale.toString());
  }

  @Test
  public void getCommandType() {
    assertEquals("changeColor", changeColor.getCommandType());
    assertEquals("move", move.getCommandType());
    assertEquals("scale", scale.getCommandType());
  }

  @Test
  public void getStartTime() {
    assertEquals(10, changeColor.getStartTime(), 0.01);
    assertEquals(0, move.getStartTime(), 0.01);
    assertEquals(40, scale.getStartTime(), 0.01);
  }

  @Test
  public void getEndTime() {
    assertEquals(50, changeColor.getEndTime(), 0.01);
    assertEquals(15, move.getEndTime(), 0.01);
    assertEquals(60, scale.getEndTime(), 0.01);
  }

  @Test
  public void getShape() {
    assertEquals(bob.toString(), changeColor.getShape().toString());
    assertEquals(pearl.toString(), move.getShape().toString());
    assertEquals(bob.toString(), scale.getShape().toString());
  }
}