
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.controller.IController;
import cs5004.animator.controller.VisualController;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;

public class MainTest {

    public static void main(String[] Args) {
        AnimatorModel model;
        try {
            model = AnimationReader.parseFile(new FileReader("./big-bang-big-crunch.txt"), new AnimationBuilderImpl());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found.");
        }
        IController visualController = new VisualController(model, 24);
        visualController.animate();
    }
}

