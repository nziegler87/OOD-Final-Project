package cs5004.animator.model;

import java.util.ArrayList;
import java.util.List;

import cs5004.animator.model.commands.ICommand;
import cs5004.animator.shape.IShape;

/**
 * Interface is for an application that helps to create simple but effective 2D animations from
 * shapes. This interface is for the model of the program. It contains only commands that read data
 * from any object that implements the interface.
 */
public interface AnimatorModelViewOnly {

  /**
   * Returns a list of all of the shapes and their current state based at a given tick of time.
   *
   * @param tick a tick in the animation where you want to return the state of all objects visible
   *             on the screen
   * @return {@code List<IShape>} with the summary of shapes and their state
   * @throws IllegalArgumentException if tick is not greater or equal to 0
   */
  List<IShape> getSnapshot(double tick) throws IllegalArgumentException;

  /**
   * Returns a summary of the animation, including the shapes in the list and the animation steps.
   * Shape list is sorted by appear time and animation steps are sorted by start time.
   *
   * @return a string of the summary
   */
  String getAnimationStatus();

  //TODO: Test these newly made public methods
  /**
   * Method that returns an ArrayList of four integers that contains the bounds of the canvas.
   *
   * @return an array list of four integers containing the bounds of the canvas.
   */
  List<Integer> getCanvas();

  /**
   * Method that looks through all of the animation commands and finds the greatest end value.
   *
   * @return the command with the greatest end value, which is how long the animation should run.
   */
  double findDuration();

  /**
   * Method to return an array list of the shapes in the inventory sorted by appear time.
   *
   * @return a sorted list of shapes, sorted by appear time
   */
  List<IShape> getShapeList();

  /**
   * Return a list of all commands that have been added to the model.
   *
   * @return a list of all commands that have been added to the model.
   */
  List<ICommand> getCommandList();
}
