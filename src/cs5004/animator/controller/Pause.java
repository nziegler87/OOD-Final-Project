package cs5004.animator.controller;

import javax.swing.*;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.IView;

public class Pause implements AnimationControllerCommands {

  public Pause() {
  }

  @Override
  public void go(AnimatorModel model, IView view, IController controller) {
    controller.stopTimer();
  }
}
