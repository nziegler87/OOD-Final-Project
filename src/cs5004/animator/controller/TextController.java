package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.IView;

import java.io.IOException;
import java.util.Objects;

public class TextController implements IController {
    private final AnimatorModel model;
    private final Appendable out;

    /**
     * The constructor for the controller class.
     *
     * @param model the model for the animation
     * @param view  the view for the animation
     * @throws IllegalArgumentException if speed is less than or equal to 0
     * @throws NullPointerException if model, view, or appendable is null
     */
    public TextController(AnimatorModel model, IView view, int speed, Appendable out)
            throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(model, "Model cannot be null.");
        Objects.requireNonNull(view, "View cannot be null.");
        Objects.requireNonNull(out, "Appendable cannot be null.");

        if (speed <= 0) {
            throw new IllegalArgumentException("Speed must be greater than 0");
        }

        this.model = model;
        this.out = out;
    }

    @Override
    public void animate() {
        try {
            this.out.append(model.getAnimationStatus());
        } catch (IOException e) {
            throw new IllegalStateException("Cannot write to appendable");
        }
    }
}
