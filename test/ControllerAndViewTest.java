import cs5004.animator.controller.IController;
import cs5004.animator.controller.Parser;
import cs5004.animator.controller.TextController;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.TextView;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for the controllers and views.
 */
public class ControllerAndViewTest {
  private TextController controller;

  private AnimatorModel animationReaderHelper(String file) {
    try {
      return AnimationReader.parseFile(new FileReader(file), new AnimationBuilderImpl());
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Unable to read the file.");
    }
  }

  @Test
  public void testSmallDemoTextOnlyWriteFile() throws IOException {
    AnimatorModel model = animationReaderHelper("./smalldemo.txt");
    controller = new TextController(model, new TextView(), new PrintStream(new File("output.txt")));
    controller.animate();
    File checkFile = new File("output.txt");
    assertTrue(checkFile.exists());
  }

  @Test
  public void testSmallDemoTextOnlyWriteFile2() throws IOException {
    AnimatorModel model = animationReaderHelper("./toh-3.txt");
    controller = new TextController(model, new TextView(),
            new PrintStream(new File("text-transcript.txt")));
    controller.animate();
    File checkFile = new File("output.txt");
    assertTrue(checkFile.exists());
  }

  @Test(expected = IllegalStateException.class)
  public void testAppendableThatReturnsNull() {
    MyAppendable appendable = new MyAppendable();
    AnimatorModel model = animationReaderHelper("./smalldemo.txt");
    controller = new TextController(model, new TextView(), appendable);
    controller.animate();
  }

  @Test
  public void testSmallDemoAppendable() {
    StringBuilder output = new StringBuilder();
    AnimatorModel model = animationReaderHelper("./smalldemo.txt");
    controller = new TextController(model, new TextView(), output);
    controller.animate();
    assertEquals("Shapes:\n" +
                    "Name: R\n" +
                    "Type: rectangle\n" +
                    "Min corner: (0.0,130.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n" +
                    "Appears at t=1\n" +
                    "Disappears at t=100\n" +
                    "\n" +
                    "Name: C\n" +
                    "Type: oval\n" +
                    "Center: (240.0,0.0), X radius: 120.0, Y radius: 60.0, Color: (0.0,0.0,1.0)\n" +
                    "Appears at t=6\n" +
                    "Disappears at t=100\n" +
                    "\n" +
                    "Shape R moves from (0.0,130.0) to (100.0,230.0) from t=10 to t=50\n" +
                    "Shape C moves from (240.0,0.0) to (240.0,180.0) from t=20 to t=50\n" +
                    "Shape C moves from (240.0,180.0) to (240.0,300.0) from t=50 to t=70\n" +
                    "Shape C changes color from (0.0,0.0,1.0) to (0.0,0.7,0.3) from" +
                    " t=50 to t=70\n" +
                    "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, " +
                    "Height: 100.0 " +
                    "from t=51 to t=70\n" +
                    "Shape R moves from (100.0,230.0) to (0.0,130.0) from t=70 to t=100\n" +
                    "Shape C changes color from (0.0,0.7,0.3) to (0.0,1.0,0.0) from t=70 to t=80",
            output.toString());
  }

  // test that parser creates file
  @Test
  public void testParser() {
    String[] args = {"-in", "smalldemo.txt", "-view", "text", "-out",
                     "parserTest.txt", "-speed", "2"};
    Parser parser = new Parser(args);
    IController controller = parser.getController();
    controller.animate();
    File checkFile = new File("parserTest.txt");

    assertTrue(checkFile.exists());
  }

  // test that parser triggers pop up when in is missing
  @Test
  public void testParserInMissing() {
    String[] args = {"-in", "", "-view", "text", "-out",
                     "noIn.txt", "-speed", "2"};
    Parser parser = new Parser(args);
    IController controller = parser.getController();
    controller.animate();
    assertNotEquals("", args);
  }

  // test that parser triggers pop up when View is missing
  @Test
  public void testParserViewMissing() {
    String[] args = {"-in", "smalldemo.txt", "-view", "", "-out",
                     "noView.txt", "-speed", "2"};
    Parser parser = new Parser(args);
    IController controller = parser.getController();
    controller.animate();
    assertNotEquals("", args);
  }
}