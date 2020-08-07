import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import cs5004.animator.model.commands.ChangeColor;
import cs5004.animator.model.commands.ICommand;
import cs5004.animator.model.commands.Move;
import cs5004.animator.model.commands.Scale;
import cs5004.animator.model.point2d.IPoint2D;
import cs5004.animator.model.point2d.Point2D;
import cs5004.animator.shape.IShape;
import cs5004.animator.shape.Oval;
import cs5004.animator.shape.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * A JUnit test class for Command types.
 */
public class CommandTest {

  private IShape bob;
  private IShape pearl;
  private ICommand changeColor;
  private ICommand move;
  private ICommand scale;
  private ICommand move2;
  private IPoint2D coords1;
  private IPoint2D coords2;

  @Before
  public void setUp() {
    coords1 = new Point2D(100, 100);
    coords2 = new Point2D(50, 50);
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
    move2 = new Move(pearl, 0, 15,
            coords2, coords1);
  }

  // test that null pointer is thrown when shape passed to constructor is null
  @Test (expected = NullPointerException.class)
  public void nullShapeMove() {
    new Move(null, 50, 70, coords1, coords2);
  }

  // test that null pointer is thrown when shape passed to constructor is null
  @Test (expected = NullPointerException.class)
  public void nullShapeScale() {
    new Scale(null, 50, 20, 50, 50, 100, 100);
  }

  // test that null pointer is thrown when shape passed to constructor is null
  @Test (expected = NullPointerException.class)
  public void nullShapeChangeColor() {
    new ChangeColor(null, 50, 20, Color.RED, Color.BLUE);
  }

  // test where start time is past end time
  @Test(expected = IllegalArgumentException.class)
  public void corruptMove() {
    new Move(bob, 50, 20, coords1, coords2);
  }

  // test where start time == end time
  @Test(expected = IllegalArgumentException.class)
  public void corruptMove2() {
    new Move(bob, 20, 20, coords1, coords2);
  }

  // test where start time is past end time
  @Test(expected = IllegalArgumentException.class)
  public void corruptScale() {
    new Scale(bob, 50, 20, 50, 50, 100, 100);
  }

  // test where start time == end time
  @Test(expected = IllegalArgumentException.class)
  public void corruptScale2() {
    new Scale(bob, 20, 20, 50, 50, 100, 100);
  }

  // test where scale is zero height
  @Test(expected = IllegalArgumentException.class)
  public void corruptScaleZeroHeight2() {
    new Scale(bob, 10, 20, 0, 50, 100, 100);
  }

  // test where scale to zero width
  @Test(expected = IllegalArgumentException.class)
  public void corruptScaleZeroWidth2() {
    new Scale(bob, 10, 20, 0, 50, 100, 100);
  }
  
  // test where scale is zero height
  @Test(expected = IllegalArgumentException.class)
  public void corruptScaleZeroHeight() {
    new Scale(bob, 10, 20, 50, 50, 0, 100);
  }

  // test where scale to zero width
  @Test(expected = IllegalArgumentException.class)
  public void corruptScaleZeroWidth() {
    new Scale(bob, 10, 20, 50, 50, 100, 0);
  }

  // test where star time is past end time
  @Test(expected = IllegalArgumentException.class)
  public void corruptChangeColor() {
    new ChangeColor(bob, 50, 20, Color.RED, Color.BLUE);
  }

  // test where star time == end time
  @Test(expected = IllegalArgumentException.class)
  public void corruptChangeColor2() {
    new ChangeColor(bob, 20, 20, Color.RED, Color.BLUE);
  }

  // test where start color object is null
  @Test(expected = NullPointerException.class)
  public void corruptChangeColor3() {
    new ChangeColor(bob, 20, 30, null, Color.BLUE);
  }

  // test where end color object is null
  @Test(expected = NullPointerException.class)
  public void corruptChangeColor4() {
    new ChangeColor(bob, 20, 30, Color.RED, null);
  }

  // test where start cord is null
  @Test (expected = NullPointerException.class)
  public void nullShapeMove2() {
    new Move(bob, 50, 70, null, coords2);
  }

  // test where end cord is null
  @Test (expected = NullPointerException.class)
  public void nullShapeMove3() {
    new Move(bob, 50, 70, coords1, null);
  }

  // test where end cord is null

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
  public void testToString() {
    assertEquals("Shape bob changes color from (1.0,0.0,1.0) to (1.0,0.7,0.7) "
            + "from t=10 to t=50\n", changeColor.toString());
    assertEquals("Shape pearl moves from (100.0,100.0) to (50.0,50.0) "
            + "from t=0 to t=15\n", move.toString());
    assertEquals("Shape bob scales from Width: 10.0, Height: 20.0 to Width: 20.0, " +
            "Height: 10.0 from t=40 to t=60\n", scale.toString());
    assertEquals("Shape pearl moves from (50.0,50.0) to (100.0,100.0) "
            + "from t=0 to t=15\n", move2.toString());
  }
  
  // test that move throws IllegalArg if invalid time (Move)
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidTimeMove() {
    ICommand command = new Move(bob, -10, 20, new Point2D(10, 10),
            new Point2D(20, 20));
  }

  // test that execute throws IllegalArg if invalid time (Move)
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidTimeMove2() {
    ICommand command = new Move(bob, 10, 20, new Point2D(10, 10),
            new Point2D(20, 20));
    command.execute(bob, 8);
  }

  // test that execute throws IllegalArg if invalid time (Move)
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidTimeMove3() {
    ICommand command = new Move(bob, 10, 20, new Point2D(10, 10),
            new Point2D(20, 20));
    command.execute(bob, 21);
  }
  
  // test that execute throws NullPointer if shape is null (Move)
  @Test (expected = NullPointerException.class)
  public void testNullPointerMove() {
    ICommand command = new Move(bob, 10, 20, new Point2D(10, 10),
            new Point2D(20, 20));
    command.execute(null, 21);
  }

  // test that execute functions
  @Test
  public void testMoveExecute() {
    ICommand command = new Move(bob, 10, 20, new Point2D(10, 10),
            new Point2D(20, 20));
    IShape shape = command.execute(bob, 15);
    System.out.println(shape.toString());
    assertEquals("Name: bob\n"
            + "Type: oval\n"
            + "Center: (15.0,15.0), X radius: 50.0, Y radius: 50.0, Color: (1.0,1.0,1.0)\n"
            + "Appears at t=10\n"
            + "Disappears at t=20", shape.toString());
  }

  // test that execute throws IllegalArg if invalid time (Scale)
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidTimeScale2() {
    ICommand command = new Scale(bob, 10, 20, 10, 10, 20, 20);
    command.execute(bob, 8);
  }

  // test that execute throws IllegalArg if invalid time (Scale)
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidTimeScale3() {
    ICommand command = new Scale(bob, 10, 20, 10, 10, 20, 20);
    command.execute(bob, 21);
  }

  // test that execute throws NullPointer if shape is null (Scale)
  @Test (expected = NullPointerException.class)
  public void testNullPointerScale() {
    ICommand command = new Scale(bob, 10, 20, 10, 10, 20, 20);
    command.execute(null, 21);
  }

  // test that execute functions
  @Test
  public void testScaleExecute() {
    ICommand command = new Scale(bob, 10, 20, 10, 10, 20, 20);

    IShape shape = command.execute(bob, 15);
    System.out.println(shape.toString());
    assertEquals("Name: bob\n"
            + "Type: oval\n"
            + "Center: (100.0,100.0), X radius: 15.0, Y radius: 15.0, Color: (1.0,1.0,1.0)\n"
            + "Appears at t=10\n"
            + "Disappears at t=20", shape.toString());
  }

  // test that execute throws IllegalArg if invalid time (ChangeColor)
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidTimeChangeColor2() {
    ICommand command = new ChangeColor(bob, 10, 20, new Color(Color.RED.getRGB()),
            new Color(Color.BLUE.getRGB()));
    command.execute(bob, 8);
  }

  // test that execute throws IllegalArg if invalid time (ChangeColor)
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidTimeChangeColor3() {
    ICommand command = new ChangeColor(bob, 10, 20, new Color(Color.RED.getRGB()),
            new Color(Color.BLUE.getRGB()));
    command.execute(bob, 21);
  }

  // test that execute throws NullPointer if shape is null (ChangeColor)
  @Test (expected = NullPointerException.class)
  public void testNullPointerChangeColor() {
    ICommand command = new ChangeColor(bob, 10, 20, new Color(Color.RED.getRGB()),
            new Color(Color.BLUE.getRGB()));
    command.execute(null, 21);
  }

  // test that execute functions
  @Test
  public void testChangeColorExecute() {
    ICommand command = new ChangeColor(bob, 10, 20, new Color(0, 0, 0),
            new Color(10, 10, 10));

    IShape shape = command.execute(bob, 15);
    System.out.println(shape.toString());
    assertEquals("Name: bob\n"
            + "Type: oval\n"
            + "Center: (100.0,100.0), X radius: 50.0, Y radius: 50.0, Color: (0.0,0.0,0.0)\n"
            + "Appears at t=10\n"
            + "Disappears at t=20", shape.toString());
  }

  // test equals
  @Test
  public void testEqualsIdenticalObjectsMove() {
    IShape oval1 = new Oval("vido", new Point2D(10, 10),
            new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new Move(oval1, 0, 10, new Point2D(0, 0),
            new Point2D(10, 10));
    ICommand command2 = new Move(oval1, 0, 10, new Point2D(0, 0),
            new Point2D(10, 10));

    assertEquals(command1, command2);
    assertEquals(command2, command1);
  }

  // test equals
  @Test
  public void testEqualsSameReferenceMove() {
    IShape oval1 = new Oval("vido", new Point2D(10, 10),
            new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new Move(oval1, 0, 10, new Point2D(0, 0),
            new Point2D(10, 10));
    ICommand command2 = command1;

    assertEquals(command1, command2);
    assertEquals(command2, command1);
  }

  // test equals
  @Test
  public void testEqualsNotIdenticalObjectsMove() {
    IShape oval1 = new Oval("vido", new Point2D(10, 10),
            new Color(100, 100, 100), 10, 10, 5, 10);
    IShape rectangle1 = new Rectangle("vido", new Point2D(10, 10),
            new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new Move(oval1, 0, 10, new Point2D(0, 0),
            new Point2D(10, 10));
    ICommand command2 = new Move(oval1, 0, 10, new Point2D(0, 0),
            new Point2D(10, 10));

    ICommand command3 = new Move(rectangle1, 0, 10, new Point2D(0, 0),
            new Point2D(10, 10));
    ICommand command4 = new Move(oval1, 1, 10, new Point2D(0, 0),
            new Point2D(10, 10));
    ICommand command5 = new Move(oval1, 0, 11, new Point2D(0, 0),
            new Point2D(10, 10));
    ICommand command6 = new Move(oval1, 0, 10, new Point2D(0, 2),
            new Point2D(10, 10));
    ICommand command7 = new Move(oval1, 0, 10, new Point2D(0, 0),
            new Point2D(13, 10));

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
    IShape oval1 = new Oval("vido", new Point2D(10, 10),
            new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new Scale(oval1, 0, 10, 5, 5, 10, 10);
    ICommand command2 = new Scale(oval1, 0, 10, 5, 5, 10, 10);

    assertEquals(command1, command2);
    assertEquals(command2, command1);
  }

  // test equals
  @Test
  public void testEqualsSameReferenceScale() {
    IShape oval1 = new Oval("vido", new Point2D(10, 10),
            new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new Scale(oval1, 0, 10, 5, 5, 10, 10);
    ICommand command2 = command1;

    assertEquals(command1, command2);
    assertEquals(command2, command1);
  }

  // test equals
  @Test
  public void testEqualsNotIdenticalObjectsScale() {
    IShape oval1 = new Oval("vido", new Point2D(10, 10),
            new Color(100, 100, 100), 10, 10, 5, 10);
    IShape rectangle1 = new Rectangle("vido", new Point2D(10, 10),
            new Color(100, 100, 100), 10, 10, 5, 10);

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
    IShape oval1 = new Oval("vido", new Point2D(10, 10),
            new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new ChangeColor(oval1, 0, 10,
            new Color(100, 100, 100), new Color(50, 50, 50));
    ICommand command2 = new ChangeColor(oval1, 0, 10,
            new Color(100, 100, 100), new Color(50, 50, 50));

    assertEquals(command1, command2);
    assertEquals(command2, command1);
  }

  // test equals
  @Test
  public void testEqualsSameReferenceChangeColor() {
    IShape oval1 = new Oval("vido", new Point2D(10, 10),
            new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new ChangeColor(oval1, 0, 10,
            new Color(100, 100, 100), new Color(50, 50, 50));
    ICommand command2 = command1;

    assertEquals(command1, command2);
    assertEquals(command2, command1);
  }

  // test equals
  @Test
  public void testEqualsNotIdenticalObjectsChangeColor() {
    IShape oval1 = new Oval("vido", new Point2D(10, 10),
            new Color(100, 100, 100), 10, 10, 5, 10);
    IShape rectangle1 = new Rectangle("vido", new Point2D(10, 10),
            new Color(100, 100, 100), 10, 10, 5, 10);

    ICommand command1 = new ChangeColor(oval1, 0, 10,
            new Color(100, 100, 100), new Color(50, 50, 50));
    ICommand command2 = new ChangeColor(oval1, 0, 10,
            new Color(100, 100, 100), new Color(50, 50, 50));

    ICommand command3 = new ChangeColor(rectangle1, 0, 10,
            new Color(100, 100, 100), new Color(50, 50, 50));
    ICommand command4 = new ChangeColor(oval1, 1, 10,
            new Color(100, 100, 100), new Color(50, 50, 50));
    ICommand command5 = new ChangeColor(oval1, 0, 11,
            new Color(100, 100, 100), new Color(50, 50, 50));
    ICommand command6 = new ChangeColor(oval1, 0, 10,
            new Color(100, 120, 100), new Color(50, 50, 50));
    ICommand command7 = new ChangeColor(oval1, 0, 10,
            new Color(100, 100, 100), new Color(50, 53, 50));


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
