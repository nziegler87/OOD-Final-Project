package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.shape.IShape;
import cs5004.animator.view.IView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class TextController implements IController {                // removed implementation
    private AnimatorModel model;
    private IView view;
    private String output;
    private int speed;
    private Appendable out;

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
        this.view = view;
        this.speed = speed;
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


//    public List<IShape> play(int tick) {
//        if (this.view.equals("text")) {
//            model.getAnimationStatus(); //TODO: make the getAnimationStatus return a text file (output.txt)
//        } else {
//            return model.getSnapshot(tick);
//        }
//        return null;
//    }
//
//    public void pause() {
//        if (this.view.equals("text")) {
//        } else {
//            // get tick to stop here.
//        }
//    }
//
//    public void speed(int speed) {
//        this.tick *= speed;
//    }
//
//    public void trackTime() {
//
//    }
//
//    public void restartAnimation() {
//        model.getSnapshot(1);
//    }
//
//    /**
//     * Invoked when an action occurs.
//     *
//     * @param e the event to be processed
//     */
//    public void actionPerformed(ActionEvent e) {
//        switch(e.getActionCommand()) {
//            case "Play/Pause":
//                // do stuff
//                break;
//            case "Exit":
//                System.exit(0);
//                break;
//        }
//    }
}
