package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.IView;
import cs5004.animator.view.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import javax.swing.*;

/**
 * The text controller class. This implements IController and contains the method animate().
 */
public class TextController implements IController {
    private final IView view;
    private String out;
    private final AnimatorModel model;

    /**
     * Create an instance of a TextController when passed a model and a string out.
     *
     * @param model the model for the animation
     * @throws IllegalArgumentException if speed is less than or equal to 0
     * @throws NullPointerException     if model or view is null
     */
    public TextController(AnimatorModel model, IView view, String out)
            throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(model, "Model cannot be null.");
        Objects.requireNonNull(out, "Outfile cannot be null.");
        this.view = view;
        this.out = out;
        this.model = model;
    }

    /**
     * The animation method which produces the result to pass into the view.
     *
     * @return appendable when appropriate, otherwise write to outfile and return null
     * @throws IllegalArgumentException when the text output was unable to write to a new file
     */
    @Override
    public void animate() throws IllegalArgumentException {
        String output = view.textRender(model.getShapeList(), model.getCommandList());
        // if there is not outfile, send to System.out
        if (out.isBlank()) {
                System.out.append(output);
        } else {
            createOutfile();
            try {
                // else try to write to the outfile
                FileWriter myWriter = new FileWriter(out);
                myWriter.write(output);
                        myWriter.close();
            } catch (IOException e) {
                throw new IllegalArgumentException("An error occurred while making and/or writing to the new file.");
            }
        }
    }

    private void createOutfile() throws IllegalArgumentException {
        try {
            File newFile = new File(out);
            while (!newFile.createNewFile()) {
                this.out = JOptionPane.showInputDialog("An error occurred because the outfile already exists. Try again: ");
                newFile = new File(out);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("An error occurred while making the new file.");
        }
    }
}
