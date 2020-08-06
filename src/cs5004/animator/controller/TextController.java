package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.IView;
import cs5004.animator.view.TextView;

import java.util.Objects;

/**
 * The text controller class. This implements IController and contains the method animate().
 */
public class TextController implements IController {
    private final IView view;

    /**
     * @param model the model for the animation
     * @throws IllegalArgumentException if speed is less than or equal to 0
     * @throws NullPointerException     if model or view is null
     */
    public TextController(AnimatorModel model)
            throws IllegalArgumentException, NullPointerException {
        this(model, "");
    }

    /**
     * @param model the model for the animation
     * @throws IllegalArgumentException if speed is less than or equal to 0
     * @throws NullPointerException     if model or view is null
     */
    public TextController(AnimatorModel model, String out)
            throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(model, "Model cannot be null.");
        Objects.requireNonNull(out, "Outfile cannot be null.");
        this.view = new TextView(out, model.getAnimationStatus());
    }

    /**
     * The animation method which produces the result to pass into the view.
     *
     * @return appendable when appropriate, otherwise write to outfile and return null
     * @throws IllegalArgumentException when the text output was unable to write to a new file
     */
    @Override
    public String animate() throws IllegalArgumentException {
        return view.toString();
    }
}
