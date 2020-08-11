
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.controller.IController;
import cs5004.animator.controller.VisualController;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.IView;
import cs5004.animator.view.VisualView;

/**
 * A test main class.
 */
public class MainTest {

  /**
   * Create a main object when passed String[] Args.
   *
   * @param args an array of string arguments.
   */
  public static void main(String [] args) {
    AnimatorModel model;
    IView view;
    try {
      model = AnimationReader.parseFile(new FileReader("./toh-12.txt"),
              new AnimationBuilderImpl());
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found.");
    }

    view = new VisualView(model.getCanvas().get(2), model.getCanvas().get(2));
    IController visualController = new VisualController(model, view, 24);
    visualController.animate();
  }
}

