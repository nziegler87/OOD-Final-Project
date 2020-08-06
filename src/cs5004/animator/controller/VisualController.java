package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.shape.IShape;
import cs5004.animator.view.IView;
import cs5004.animator.view.VisualView;

/**
 * The visual controller class. This implements IController and contains the method animate().
 */
public class VisualController implements IController {
    private final Timer timer;
    protected IView view;

    /**
     * The constructor for the visual controller class.
     *
     * @param model the model for the animation
     * @param speed the speed of the animation
     * @throws IllegalArgumentException if speed is less than or equal to 0
     * @throws NullPointerException     if model, view, or appendable is null
     */
    public VisualController(AnimatorModel model, int speed)
            throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(model, "Model cannot be null");

        if (speed <= 0) {
            throw new IllegalArgumentException("Speed must be greater than 0");
        }

        ArrayList<Integer> canvasDetails = model.getCanvas();
        this.view = new VisualView(canvasDetails.get(2), canvasDetails.get(3));

        int delay = 1000 / speed;
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
                }
        );
    }

    /**
     * The animation method which produces the result to pass into the view.
     *
     * @return null
     */
    @Override
    public void animate() {
        this.timer.start();
    }
}
