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
    if (model.getShapeList().isEmpty()) {
      JOptionPane.showMessageDialog(popUpWindow, "There are no more shapes left to remove.");
      return;
    }

    if (view.getShapesToRemove().equals("Select Shape")) {
      JOptionPane.showMessageDialog(popUpWindow, "Select a shape to remove.");
      return;
    }

    try {
      IShape shapeToRemove = model.getShape(view.getShapesToRemove());
      model.removeShape(shapeToRemove);
      view.setShapeList(model.getShapeList());
      view.render(model.getSnapshot(controller.getCurrentFrame()));
    }
    catch (IllegalArgumentException IAE) {
      JOptionPane.showMessageDialog(popUpWindow, "Error removing shape.");
    }
  }
}
