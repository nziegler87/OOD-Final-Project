package cs5004.animator.controller;

import javax.swing.*;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.shape.IShape;
import cs5004.animator.view.IView;

public class RemoveShape implements AnimationControllerCommands {
  private final JFrame popUpWindow;

  public RemoveShape() {
    this.popUpWindow = new JFrame();
  }

  @Override
  public void go(AnimatorModel model, IView view, IController controller) {
    if (view.getShapesToRemove().isEmpty() && model.getShapeList().isEmpty()) {
      JOptionPane.showMessageDialog(popUpWindow, "There are no more shapes left to remove.");
      return;
    }

    if (view.getShapesToRemove().isEmpty()) {
      JOptionPane.showMessageDialog(popUpWindow, "Select a shape or shapes to remove.");
      return;
    }

    try {
      for (String shapeDescription : view.getShapesToRemove()) {
        IShape shapeToRemove = model.getShape(shapeDescription);
        model.removeShape(shapeToRemove);
      }
      view.setShapeList(model.getShapeList());
      view.render(model.getSnapshot(controller.getCurrentFrame()));
      return;
    } catch (IllegalArgumentException IAE) {
      JOptionPane.showMessageDialog(popUpWindow, "Error removing shape.");
    }
  }
}
