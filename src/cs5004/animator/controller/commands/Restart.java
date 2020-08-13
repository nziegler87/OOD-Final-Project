package cs5004.animator.controller.commands;

import cs5004.animator.controller.IController;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.IView;

/**
 * A class that represents a command to restart playback.
 */
public class Restart implements AnimationControllerCommands {

  /**
   * Creates an instance of a Restart() object.
   */
  public Restart() {
  }

  /**
   * Method that resets the current frame to 0
   *
   * @param model an AnimatorModel object
   * @param view an IView object
   * @param controller and IController object.
   */
  @Override
  public void go(AnimatorModel model, IView view, IController controller) {
    controller.setCurrentFrame(0);
    view.render(model.getSnapshot(0));
  }
}
