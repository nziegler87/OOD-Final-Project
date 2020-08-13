package cs5004.animator.controller.commands;

import cs5004.animator.controller.IController;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.IView;

/**
 * Interface that contains one method that all classes implementing AnimationControllerCommands
 * should have.
 */
public interface AnimationControllerCommands {

  /**
   * Method to modify the model, view, and/or controller to perform action.
   *
   * @param model an AnimatorModel object
   * @param view an IView object
   * @param controller and IController object.
   * @throws NullPointerException if model, view, or controller are null.
   */
  void go(AnimatorModel model, IView view, IController controller) throws NullPointerException;
}
