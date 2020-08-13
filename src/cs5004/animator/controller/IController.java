package cs5004.animator.controller;

/**
 * Interface that contains the commands that all classes implementing the interface should
 * implement.
 */
public interface IController {

  /**
   * Method to start the animation.
   **/
  void animate();

  int getCurrentFrame();

  void setCurrentFrame(int frame);

  public void startTimer();

  public void stopTimer();
}

