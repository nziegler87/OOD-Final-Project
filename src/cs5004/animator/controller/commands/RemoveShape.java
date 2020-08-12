package cs5004.animator.controller.commands;

import java.util.Objects;

import javax.swing.*;

import cs5004.animator.controller.IController;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.shape.IShape;
import cs5004.animator.view.IView;

/**
 * Class that represents a remove shape command.
 */
public class RemoveShape implements AnimationControllerCommands {
  private final JFrame popUpWindow;

  /**
   * Creates an instance of a RemoveShape() object.
   */
  public RemoveShape() {
    this.popUpWindow = new JFrame();
  }

  /**
   * Method that gets the shape to be removed from view, removes it from the model, and then
   * updates the view.
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
