package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.IView;

public interface AnimationControllerCommands {
  public void go(AnimatorModel model, IView view, IController controller);
}
