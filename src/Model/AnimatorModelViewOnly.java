package Model;


import java.util.List;

import Model.Shape.IShape;

public interface AnimatorModelViewOnly {

  /**
   * Returns a list of all of the shapes and their state based on the tick input.
   *
   * @param tick  a tick in the animation where you want to return the state of all objects
   *              visible on the screen
   *
   * @return List<IShape> with the summary of shapes and their state
   */
  List<IShape> getSnapshot(double tick);

  /**
   * Returns a summary of the animation, including the shapes in the list and the animation steps.
   *
   * @return a string of the summary
   */
  String getAnimationStatus();
}
