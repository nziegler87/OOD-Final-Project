import cs5004.animator.controller.IController;
import cs5004.animator.controller.Parser;

/**
 * A main class that takes in the String [] Args, sets up the controller with the model and an
 * appropriate view starts the animation.
 */


public class Main {
  /**
   * Here is the constructor for the model and controller in the main file. It takes in args which
   * is the string from the user input.
   *
   * @param args - the string input from user
   * @throws IllegalArgumentException when there's an IOException building the controller
   */
  public static void main(String[] args) throws IllegalArgumentException {
    Parser parser = new Parser(args);
    IController controller = parser.getController();
    controller.animate();
  }
}