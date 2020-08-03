package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.IView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AnimatorControllerImpl implements IController, ActionListener {
    private AnimatorModel model;
    private IView view;

    /**
     * The constructor for the controller class.
     *
     * @param model the model for the animation
     * @param view the view for the animation
     * @throws NullPointerException when model or view is null
     */
    public AnimatorControllerImpl(AnimatorModel model, IView view) throws NullPointerException {
        Objects.requireNonNull(model, "Model cannot be null.");
        Objects.requireNonNull(view, "View cannot be null.");
        this.model = model;
        this.view = view;
    }

    @Override
    public void setBounds() {

    }

    @Override
    public void getBounds() {

    }

    @Override
    public void play() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void speed() {

    }

    @Override
    public void trackTime() {

    }

    @Override
    public void restartAnimation() {

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Play/Pause":
                // do stuff
                break;
            case "Exit":
                System.exit(0);
                break;
        }

    }
}
