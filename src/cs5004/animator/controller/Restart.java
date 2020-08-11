package cs5004.animator.controller;

import javax.swing.*;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.IView;

public class Restart implements AnimationControllerCommands {
  private int currentFrame;

  public Restart() {
  }

  @Override
  public void go(AnimatorModel model, IView view, IController controller) {
    controller.setCurrentFrame(0);
    view.render(model.getSnapshot(currentFrame));
  }
}
