import cs5004.animator.controller.IController;
import cs5004.animator.controller.Parser;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    /**
     * Here is the constructor for the model and controller in the main file. It takes in args which
     * is the string from the user input.
     *
     * @param args - the string input from user
     */
    public static void main(String[] args) throws IOException {

        AnimationBuilder<AnimatorModel> animationBuilder = new AnimationBuilderImpl();
        AnimationReader animationReader = new AnimationReader();
        try {
            AnimatorModel model = animationReader.parseFile(new FileReader("./somefile"), animationBuilder);
            Parser parser = new Parser(args, model);
            IController controller = parser.parse();
            controller.animate();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
