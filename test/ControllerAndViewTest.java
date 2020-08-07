import cs5004.animator.controller.TextController;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.TextView;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

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

    @Test
    public void testSmallDemoTextOnly() throws IOException {
        AnimatorModel model = animationReaderHelper("./smalldemo.txt");
        controller = new TextController(model, new TextView(), new PrintStream(new File("pickles")));
        controller.animate();
    }
}