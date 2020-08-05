package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.IView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * The text controller class. This implements IController and contains the method animate().
 */
public class TextController implements IController {
    private final AnimatorModel model;
    private Appendable appendable;
    private String out;

    /**
     * @param model the model for the animation
     * @param view  the view for the animation
     * @throws IllegalArgumentException if speed is less than or equal to 0
     * @throws NullPointerException     if model or view is null
     */
    public TextController(AnimatorModel model, IView view, Appendable appendable)
            throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(model, "Model cannot be null.");
        Objects.requireNonNull(view, "View cannot be null.");
        this.appendable = appendable;
        this.model = model;
        this.out = "";
    }

    /**
     * @param model the model for the animation
     * @param view  the view for the animation
     * @throws IllegalArgumentException if speed is less than or equal to 0
     * @throws NullPointerException     if model or view is null
     */
    public TextController(AnimatorModel model, IView view, String out)
            throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(model, "Model cannot be null.");
        Objects.requireNonNull(view, "View cannot be null.");
        this.model = model;
        this.out = out;
    }

    /**
     * The animation method which produces the result to pass into the view.
     *
     * @return appendable when appropriate, otherwise write to outfile and return null
     * @throws IllegalArgumentException when the text output was unable to write to a new file
     */
    @Override
    public Appendable animate() throws IllegalArgumentException {
        if (out.isBlank()) {
            try {
                return appendable.append(model.getAnimationStatus());
            } catch (IOException e) {
                throw new IllegalArgumentException("An error occurred when appending the output.");
            }
        } else {
            try {
                FileWriter myWriter = new FileWriter(out);
                myWriter.write(model.getAnimationStatus());
                myWriter.close();
            } catch (IOException e) {
                throw new IllegalArgumentException("An error occurred when writing the file.");
            }
        }
        return null;
    }
}
