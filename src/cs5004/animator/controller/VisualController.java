package cs5004.animator.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.swing.*;

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
    view.setShapeList(model.getShapeList());
    List<IShape> shapes = model.getSnapshot(currentFrame);
    view.render(shapes);
    view.setShapeList(model.getShapeList());
  }

  @Override
  public int getCurrentFrame() {
    return this.currentFrame;
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

  public void setCurrentFrame(int frame) {
    this.currentFrame = 0;
  }

  public void startTimer() {
    this.timer.start();
  }

  public void stopTimer() {
    this.timer.stop();
  }
}

