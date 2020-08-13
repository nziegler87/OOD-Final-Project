package cs5004.animator.controller.commands;

import java.util.Objects;

import cs5004.animator.controller.IController;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.IView;

/**
 * A class that is a command to pause the animation.
 */
public class Pause implements AnimationControllerCommands {

  /**
   * Creates an instance of a Pause() object.
   */
  public Pause() {
  }

  /**
   * Method to that stops the timer in the controller.
   *
   * @param model an AnimatorModel object
   * @param view an IView object
   * @param controller and IController object.
   * @throws NullPointerException if model, view, or controller are null.
   */
  @Override
  public void go(AnimatorModel model, IView view, IController controller)
          throws NullPointerException {
    Objects.requireNonNull(model, "Model cannot be null.");
    Objects.requireNonNull(view, "View cannot be null.");
    Objects.requireNonNull(controller, "Controller cannot be null.");
    controller.stopTimer();
  }
}
