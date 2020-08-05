package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.shape.IShape;
import cs5004.animator.view.VisualView;

/**
 * The visual controller class. This implements IController and contains the method animate().
 */
public class VisualController implements IController {
    private final VisualView view;
    private final AnimatorModel model;
    private final int delay;
    private final Timer timer;

    /**
     * The constructor for the visual controller class.
     *
     * @param model the model for the animation
     * @param view  the view for the animation
     * @param speed the speed of the animation
     * @throws IllegalArgumentException if speed is less than or equal to 0
     * @throws NullPointerException     if model, view, or appendable is null
     */
    public VisualController(AnimatorModel model, VisualView view, int speed)
            throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(model, "Model cannot be null");
        Objects.requireNonNull(view, "View cannot be null.");

        if (speed <= 0) {
            throw new IllegalArgumentException("Speed must be greater than 0");
        }

        this.view = view;
        this.model = model;
        this.delay = speed;           //TODO: HALP with this conversion
        this.timer = new Timer(this.delay, new ActionListener() {
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
     * The animation method which produces the result to pass into the view.
     */
    @Override
    public void animate() {
        this.timer.start();
    }
}
