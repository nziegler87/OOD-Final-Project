package cs5004.animator.view;

import cs5004.animator.model.AnimatorModelViewOnly;

import java.io.IOException;
import java.util.Objects;

public class TextView implements IView {

    private Appendable out;

    @Override
    public void display(AnimatorModelViewOnly model) {
        Objects.requireNonNull(model, "Model cannot be null.");
        try {
            out.append(model.getAnimationStatus());
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not produce output.");
        }
    }
}
