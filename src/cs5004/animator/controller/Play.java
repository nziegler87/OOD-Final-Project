package cs5004.animator.controller;

import javax.swing.*;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.IView;

public class Play implements AnimationControllerCommands {
  private final Timer timer;

  public Play(Timer timer) {
    this.timer = timer;

  }

  @Override
  public void go(AnimatorModel model, IView view) {
    this.timer.start();
  }
}
