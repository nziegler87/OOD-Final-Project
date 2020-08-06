import cs5004.animator.controller.TextController;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.TextView;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.assertEquals;

public class ControllerAndViewTest {
    private TextController controller;
    private TextView textView;


    @Before
    public void setUp() throws IllegalArgumentException {
    }

    private AnimatorModel animationReaderHelper(String file) {
        try {
            return AnimationReader.parseFile(new FileReader(file), new AnimationBuilderImpl());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to read the file.");
        }
    }

    // TODO: issue because it's not pulling a string, it's pulling the System.out which is default.
    @Test
    public void testSmallDemoTextOnly() {
        AnimatorModel model = animationReaderHelper("./smalldemo.txt");
        controller = new TextController(model);
        assertEquals("Shapes:\n" +
                "Name: R\n" +
                "Type: null\n" +
                "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n" +
                "Appears at t=1\n" +
                "Disappears at t=100\n" +
                "\n" +
                "Name: C\n" +
                "Type: null\n" +
                "Center: (440.0,70.0), X radius: 120.0, Y radius: 60.0, Color: (0.0,0.0,1.0)\n" +
                "Appears at t=6\n" +
                "Disappears at t=100\n" +
                "\n" +
                "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n" +
                "Shape C moves from (440.0,70.0) to (440.0,250.0) from t=20 to t=50\n" +
                "Shape C moves from (440.0,250.0) to (440.0,370.0) from t=50 to t=70\n" +
                "Shape C changes color from (0.0,0.0,1.0) to (0.0,0.7,0.3) from t=50 to t=70\n" +
                "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from t=51 to t=70\n" +
                "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n" +
                "Shape C changes color from (0.0,0.7,0.3) to (0.0,1.0,0.0) from t=70 to t=80", controller.animate());
    }
}