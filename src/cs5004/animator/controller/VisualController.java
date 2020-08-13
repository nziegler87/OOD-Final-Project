package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import javax.swing.*;

import cs5004.animator.controller.commands.AnimationControllerCommands;
import cs5004.animator.controller.commands.Pause;
import cs5004.animator.controller.commands.PlayAnimation;
import cs5004.animator.controller.commands.RemoveShape;
import cs5004.animator.controller.commands.Restart;
import cs5004.animator.controller.commands.SaveTextVersion;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.shape.IShape;
import cs5004.animator.view.IView;

/**
 * The visual controller class. This implements IController and contains the method animate().
 */
public class VisualController implements IController, ActionListener {
  private final Timer timer;
  private final IView view;
  private final AnimatorModel model;
  private int currentFrame;
  private final JFrame fileWindow;

  /**
   * The constructor for the visual controller class.
   *
   * @param model           the model for the animation
   * @param view            the view for the animation
   * @param framesPerSecond the framesPerSecond of the animation
   * @throws IllegalArgumentException if framesPerSecond is less than or equal to 0
   * @throws NullPointerException     if model is null
   */
  public VisualController(AnimatorModel model, IView view, int framesPerSecond)
          throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(model, "Model cannot be null");
    if (framesPerSecond <= 0) {
      throw new IllegalArgumentException("Speed must be greater than 0");
    }

    this.currentFrame = 0;
    this.view = view;
    this.model = model;
    this.fileWindow = new JFrame();
    view.setListener(this);

    int delay = 1000 / framesPerSecond;
    this.timer = new Timer(delay, new Play());
  }

  /**
   * The animation method which produces the result to pass into the view. In this case, it starts
   * the timer.
   */
  @Override
  public void animate() {
    List<IShape> shapesSnapshot = model.getSnapshot(currentFrame);
    view.render(shapesSnapshot);
    List<IShape> shapes = model.getShapeList();
    view.setShapeList(shapes);
  }


  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Map<String, Function<ActionEvent, AnimationControllerCommands>> knownCommands = new HashMap<>();
    knownCommands.put("Play", (ActionEvent event) -> { return new PlayAnimation(); });
    knownCommands.put("Pause", (ActionEvent event) -> { return new Pause(); });
    knownCommands.put("Restart", (ActionEvent event) -> { return new Restart(); });
    knownCommands.put("Save Text Version", (ActionEvent event) -> {return new SaveTextVersion(); });
    knownCommands.put("Remove Shape", (ActionEvent event) -> {return new RemoveShape(); });

    AnimationControllerCommands c;
    Function<ActionEvent, AnimationControllerCommands> cmd = knownCommands.getOrDefault(e.getActionCommand(),null);

    if (cmd == null) {
      JOptionPane.showMessageDialog(new JFrame(), "Unsupported Operation.");
    } else {
      c = cmd.apply(e);
      c.go(model, view, this);
    }
  }

  /**
   * A Play class that is passed when creating the timer object.
   */
  public class Play implements ActionListener {
    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (currentFrame > model.findDuration()) {
        currentFrame = 0;
      }
      List<IShape> shapes = model.getSnapshot(currentFrame);
      view.render(shapes);
      currentFrame++;
    }
  }


  /**
   * Returns the current tick, or frame, from the controller.
   *
   * @return the current tick, or frame.
   */
  @Override
  public int getCurrentFrame() {
    return this.currentFrame;
  }

  /**
   * Sets the current tick, or frame, for the controller.
   *
   * @param frame the current tick, or frame.
   */
  public void setCurrentFrame(int frame) {
    this.currentFrame = 0;
  }

  /**
   * Method to start the controller's timer.
   */
  public void startTimer() {
    this.timer.start();
  }

  /**
   * Method to stop the controller's timer.
   */
  public void stopTimer() {
    this.timer.stop();
  }
}

