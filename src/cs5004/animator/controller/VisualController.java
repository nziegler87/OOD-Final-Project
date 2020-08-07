package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

import javax.swing.Timer;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.shape.IShape;
import cs5004.animator.view.IView;

/**
 * The visual controller class. This implements IController and contains the method animate().
 */
public class VisualController implements IController {
  private final Timer timer;

  /**
   * The constructor for the visual controller class.
   *
   * @param model           the model for the animation
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

    int delay = 1000 / framesPerSecond;
    this.timer = new Timer(delay,
            new ActionListener() {
        int currentFrame = 0;
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
      });
  }

  /**
   * The animation method which produces the result to pass into the view. In this case, it starts
   * the timer.
   */
  @Override
  public void animate() {
    this.timer.start();
  }
}
