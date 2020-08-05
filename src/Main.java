import cs5004.animator.controller.IController;
import cs5004.animator.controller.Parser;

import java.io.IOException;

public class Main {

    /**
     * Here is the constructor for the model and controller in the main file. It takes in args which
     * is the string from the user input.
     *
     * @param args - the string input from user
     * @throws IllegalArgumentException when there's an IOException building the controller
     */
    public static void main(String[] args) throws IllegalArgumentException {
        try {
            Parser parser = new Parser();
            IController controller = parser.parse(args);
            controller.animate();
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found.");
        }
    }
}
