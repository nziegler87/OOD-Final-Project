package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.shape.IShape;
import cs5004.animator.view.IView;

import java.util.List;
import java.util.Objects;

public class AnimatorControllerImpl implements IController {
    private AnimatorModel model;
    private IView view;
    private String output;
    private int speed;
    private final int tick;

    /**
     * The constructor for the controller class.
     *
     * @param model the model for the animation
     * @param view  the view for the animation
     * @throws NullPointerException when model or view is null
     */
    public AnimatorControllerImpl(AnimatorModel model, IView view, String output, int speed) throws NullPointerException {
        Objects.requireNonNull(model, "Model cannot be null.");
        Objects.requireNonNull(view, "View cannot be null.");
        this.model = model;
        this.view = view;
        this.output = output;
        this.speed = speed;
        this.tick = 0;
        // start time = begin ticker 
    }

    public List<IShape> play(int tick) {
        if (this.view.equals("text")) {
            model.getAnimationStatus(); //TODO: make the getAnimationStatus return a text file (output.txt)
        } else {
            return model.getSnapshot(tick);
        }
        return null;
    }

    @Override
    public void pause() {
        if (this.view.equals("text")) {
        } else {
            // get tick to stop here.
        }
    }

    @Override
    public void speed(int speed) {
        this.tick *= speed;
    }

    @Override
    public void trackTime() {

    }

    @Override
    public void restartAnimation() {
        model.getSnapshot(1);
    }
}
