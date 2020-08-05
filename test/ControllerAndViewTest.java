import cs5004.animator.controller.TextController;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.TextView;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.*;

public class ControllerAndViewTest {
    private TextController controller;
    private TextView textView;


    @Before
    public void setUp() throws IllegalArgumentException {
        AnimationBuilderImpl builder = new AnimationBuilderImpl();
        textView = new TextView();
    }

    private AnimatorModel animationReaderHelper(String file) {
        try {
            return AnimationReader.parseFile(new FileReader(file), new AnimationBuilderImpl());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to read the file.");
        }
    }

    @Test
    public void testBigBandCrunchTextOnly() {
        AnimatorModel model = animationReaderHelper("./big-bang-big-crunch.txt");
        controller = new TextController(model, textView, new StringBuilder());
        assertEquals("Shapes:\n" +
                "Name: P1\n" +
                "Type: null\n" +
                "Center: (396.0,396.0), X radius: 8.0, Y radius: 8.0, Color: (0.9,0.5,0.4)\n" +
                "Appears at t=1\n" +
                "Disappears at t=500\n" +
                "\n" +
                "Name: P2\n" +
                "Type: null\n" +
                "Center: (395.0,395.0), X radius: 10.0, Y radius: 10.0, Color: (1.0,0.0,0.4)\n" +
                "Appears at t=1\n" +
                "Disappears at t=500\n" +
                "\n" +
                "Name: P3\n" +
                "Type: null\n" +
                "Center: (397.0,397.0), X radius: 6.0, Y radius: 6.0, Color: (0.6,0.5,0.1)\n" +
                "Appears at t=1\n" +
                "Disappears at t=500\n" +
                "\n" +
                "Name: P4\n" +
                "Type: null\n" +
                "Center: (396.0,396.0), X radius: 8.0, Y radius: 8.0, Color: (0.5,0.0,0.6)\n" +
                "Appears at t=1\n" +
                "Disappears at t=500\n" +
                "\n" +
                "Name: P5\n" +
                "Type: null\n" +
                "Center: (397.0,397.0), X radius: 6.0, Y radius: 6.0, Color: (0.8,0.3,0.9)\n" +
                "Appears at t=1\n" +
                "Disappears at t=500\n" +
                "\n" +
                "Name: P6\n" +
                "Type: null\n" +
                "Center: (397.0,397.0), X radius: 6.0, Y radius: 6.0, Color: (0.8,0.9,0.8)\n" +
                "Appears at t=1\n" +
                "Disappears at t=500\n" +
                "\n" +
                "Name: P7\n" +
                "Type: null\n" +
                "Center: (396.0,396.0), X radius: 8.0, Y radius: 8.0, Color: (1.0,0.7,0.0)\n" +
                "Appears at t=1\n" +
                "Disappears at t=500\n" +
                "\n" +
                "Name: P8\n" +
                "Type: null\n" +
                "Center: (396.0,396.0), X radius: 8.0, Y radius: 8.0, Color: (0.6,0.0,0.9)\n" +
                "Appears at t=1\n" +
                "Disappears at t=500\n" +
                "\n" +
                "Name: P9\n" +
                "Type: null\n" +
                "Center: (395.0,395.0), X radius: 10.0, Y radius: 10.0, Color: (0.5,0.1,0.9)\n" +
                "Appears at t=1\n" +
                "Disappears at t=500\n" +
                "\n" +
                "Name: P10\n" +
                "Type: null\n" +
                "Center: (396.0,396.0), X radius: 8.0, Y radius: 8.0, Color: (0.9,0.0,0.2)\n" +
                "Appears at t=1\n" +
                "Disappears at t=500\n" +
                "\n" +
                "Name: P11\n" +
                "Type: null\n" +
                "Center: (396.0,396.0), X radius: 8.0, Y radius: 8.0, Color: (0.0,0.4,0.8)\n" +
                "Appears at t=1\n" +
                "Disappears at t=500\n" +
                "\n" +
                "Name: P12\n" +
                "Type: null\n" +
                "Center: (396.0,396.0), X radius: 8.0, Y radius: 8.0, Color: (0.8,0.2,0.4)\n" +
                "Appears at t=1\n" +
                "Disappears at t=500\n" +
                "\n" +
                "Name: P13\n" +
                "Type: null\n" +
                "Center: (395.0,395.0), X radius: 10.0, Y radius: 10.0, Color: (0.8,0.5,0.2)\n" +
                "Appears at t=1\n" +
                "Disappears at t=500\n" +
                "\n" +
                "Name: P14\n" +
                "Type: null\n" +
                "Center: (396.0,396.0), X radius: 8.0, Y radius: 8.0, Color: (0.2,0.8,0.6)\n" +
                "Appears at t=1\n" +
                "Disappears at t=500\n" +
                "\n" +
                "Name: P15\n" +
                "Type: null\n" +
                "Center: (396.0,396.0), X radius: 8.0, Y radius: 8.0, Color: (0.8,0.7,0.3)\n" +
                "Appears at t=1\n" +
                "Disappear<...> from t=269 to t=307\n" +
                "Shape P405 moves from (707.0,498.0) to (396.0,396.0) from t=269 to t=309\n" +
                "Shape P465 moves from (228.0,-1.0) to (397.0,397.0) from t=269 to t=344\n" +
                "Shape P683 moves from (227.0,158.0) to (397.0,397.0) from t=269 to t=379\n" +
                "Shape P905 moves from (561.0,25.0) to (397.0,397.0) from t=269 to t=293\n" +
                "Shape P961 moves from (-81.0,320.0) to (395.0,395.0) from t=269 to t=307\n" +
                "Shape P1014 moves from (582.0,464.0) to (397.0,397.0) from t=269 to t=297\n" +
                "Shape P1256 moves from (135.0,614.0) to (395.0,395.0) from t=269 to t=476\n" +
                "Shape P1641 moves from (648.0,406.0) to (397.0,397.0) from t=269 to t=289\n" +
                "Shape P1716 moves from (235.0,862.0) to (396.0,396.0) from t=269 to t=366\n" +
                "Shape P1853 moves from (548.0,873.0) to (395.0,395.0) from t=269 to t=372\n" +
                "Shape P2031 moves from (872.0,241.0) to (395.0,395.0) from t=269 to t=421\n" +
                "Shape P2033 moves from (233.0,-35.0) to (395.0,395.0) from t=269 to t=441\n" +
                "Shape P2135 moves from (650.0,2.0) to (396.0,396.0) from t=269 to t=467\n" +
                "Shape P2222 moves from (262.0,340.0) to (395.0,395.0) from t=269 to t=313\n" +
                "Shape P2606 moves from (197.0,723.0) to (397.0,397.0) from t=269 to t=340\n" +
                "Shape P2666 moves from (826.0,227.0) to (396.0,396.0) from t=269 to t=408\n" +
                "Shape P2702 moves from (595.0,660.0) to (397.0,397.0) from t=269 to t=326\n" +
                "Shape P3331 moves from (109.0,256.0) to (395.0,395.0) from t=269 to t=402\n" +
                "Shape P3481 moves from (1.0,75.0) to (396.0,396.0) from t=269 to t=413\n" +
                "Shape P3643 moves from (246.0,352.0) to (397.0,397.0) from t=269 to t=360\n" +
                "Shape P3645 moves from (507.0,600.0) to (396.0,396.0) from t=269 to t=453\n" +
                "Shape P3764 moves from (890.0,638.0) to (397.0,397.0) from t=269 to t=318\n" +
                "Shape P3824 moves from (454.0,-1.0) to (395.0,395.0) from t=269 to t=407\n" +
                "Shape P3854 moves from (483.0,328.0) to (396.0,396.0) from t=269 to t=313\n" +
                "Shape P4494 moves from (311.0,554.0) to (397.0,397.0) from t=269 to t=411\n" +
                "Shape P4579 moves from (536.0,-4.0) to (397.0,397.0) from t=269 to t=458\n" +
                "Shape P4814 moves from (290.0,322.0) to (397.0,397.0) from t=269 to t=366", controller.animate());
    }

    @Test
    public void testHanoiTextOnly() {
        AnimatorModel model = animationReaderHelper("./hanoi.txt");
        controller = new TextController(model, textView, new StringBuilder());
        assertEquals("", controller.animate());
    }

    @Test
    public void testBuildingsTextOnly() {
        AnimatorModel model = animationReaderHelper("./buildings.txt");
        controller = new TextController(model, textView, new StringBuilder());
        assertEquals("", controller.animate());
    }

    @Test
    public void testSmallDemoTextOnly() {
        AnimatorModel model = animationReaderHelper("./smalldemo.txt");
        controller = new TextController(model, textView, new StringBuilder());
        assertEquals("", controller.animate());
    }
}