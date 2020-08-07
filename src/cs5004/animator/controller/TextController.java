package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.IView;

import java.io.IOException;
import java.util.Objects;

/**
 * The text controller class. This implements IController and contains the method animate().
 */
public class TextController implements IController {
    private final AnimatorModel model;
    private final IView view;
    private final Appendable appendable;

    /**
     * Create an instance of a TextController when passed a model, a view, and an appendable.
     *
     * @param model the model for the animation
     * @param view the view, where output information will be displayed
     * @param appendable an object to which to write output
     *
     * @throws NullPointerException     if model, view, or appendable are null
     */
    public TextController(AnimatorModel model, IView view, Appendable appendable)
            throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(model, "Model cannot be null.");
        Objects.requireNonNull(view, "Outfile cannot be null.");
        Objects.requireNonNull(appendable, "Outfile cannot be null.");
        this.model = model;
        this.view = view;
        this.appendable = appendable;
    }

    /**
     * The animation method which produces the result to pass into the view.
     *
     * @throws IllegalArgumentException when the text output was unable to write to a new file
     */
    @Override
    public void animate() throws IllegalArgumentException {
        String output = view.textRender(model.getShapeList(), model.getCommandList());
        try {
            appendable.append(output);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not append to the output.");
        }
    }
}
