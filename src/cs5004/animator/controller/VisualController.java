package cs5004.animator.controller;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.shape.IShape;
import cs5004.animator.view.VisualView;

public class VisualController implements IController {
  private VisualView view;
  private AnimatorModel model;
  private int speed;

  /**
   *
   * @param model
   * @param view
   * @param speed
   * @throws IllegalArgumentException if speed is less than or equal to 0
   * @throws NullPointerException if model, view, or appendable is null
   */
  public VisualController(AnimatorModel model, VisualView view, int speed)
          throws IllegalArgumentException, NullPointerException{
    Objects.requireNonNull(model, "Model cannot be null");
    Objects.requireNonNull(view, "View cannot be null.");

    if (speed <= 0) {
      throw new IllegalArgumentException("Speed must be greater than 0");
    }

    this.view = view;
    this.model = model;
    this.speed = speed;
  }

  @Override
  public void animate() {
    while (true) {
      for (int i = 1; i <= 100; i++) {
        try {
          TimeUnit.MILLISECONDS.sleep(this.speed);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Rendering tick: " + i);
        List<IShape> shapes = model.getSnapshot(i);
        view.render(shapes);
      }
    }
  }
}
