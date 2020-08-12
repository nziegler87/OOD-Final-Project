package cs5004.animator.view;

import java.awt.event.ActionListener;
import java.util.List;

import cs5004.animator.controller.VisualController;
import cs5004.animator.model.commands.ICommand;
import cs5004.animator.shape.IShape;

/**
 * An interface that contains all of the common methods that all implementing classes should
 * contain.
 */
public interface IView {

  /**
   * A method to render the shapes at their current state of animation.
   *
   * @param shapes a list of IShapes.
   */
  void render(List<IShape> shapes);

  /**
   * A method to render the shapes in the animation and their respective commands.
   *
   * @param shapes a list of IShapes.
   */
  String textRender(List<IShape> shapes, List<ICommand> commands);

  /**
   * Method to make the controller the listener for objects and panels in the view.
   *
   * @param listener an ActionListener
   */
  void setListener(ActionListener listener);

  /**
   * Method to pass a list of shapes to the view.
   *
   * @param shapes list of IShape objects.
   */
  void setShapeList(List<IShape> shapes);


  /**
   * Method to return the name of a shape to be removed from the view to the controller.
   *
   * @return the name of a shape to be removed, a string.
   */
  String getShapesToRemove();
}
