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
    private final String out;

    /**
     * @param model the model for the animation
     * @param view  the view for the animation
     * @throws IllegalArgumentException if speed is less than or equal to 0
     * @throws NullPointerException     if model or view is null
     */
    public TextController(AnimatorModel model, IView view, String out)
            throws IllegalArgumentException, NullPointerException, IOException {
        Objects.requireNonNull(model, "Model cannot be null.");
        Objects.requireNonNull(view, "View cannot be null.");

        this.model = model;
        this.out = out;
    }

    /**
     * The animation method which produces the result to pass into the view.
     */
    @Override
    public void animate() {
        if (!out.isBlank()) {
            try {
                FileWriter myWriter = new FileWriter(out);
                myWriter.write(model.getAnimationStatus());
                myWriter.close();
            } catch (IOException e) {
                throw new IllegalArgumentException("An error occurred when writing the file.");
            }
        } else {
            System.out.append(model.getAnimationStatus());
        }
    }
}
