package cs5004.animator.controller;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.shape.IShape;
import cs5004.animator.view.VisualView;

public class NateTestController {
  private VisualView view;
  private AnimatorModel model;

  public NateTestController(VisualView view, AnimatorModel model) {
    this.view = view;
    this.model = model;
  }

  public void go() {
    while (true) {
      for (int i = 1; i <= 100; i++) {
        try {
          TimeUnit.MILLISECONDS.sleep(50);
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
