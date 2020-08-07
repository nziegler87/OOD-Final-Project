package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.IView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;

import javax.swing.JOptionPane;
import java.io.*;

/**
 * A parser class that is used to read a text string of arguments and determine the input file,
 * the type of view, the output location, and the speed of the render.
 */
public class Parser {
    private final AnimatorModel model;
    private String file;
    private String view;
    private String out;
    private int speed;

    /**
     * Creates an instance of a Parser class and initializes default values.
     */
    public Parser(String[] input) {
        file = "";
        view = "visual";
        out = "";
        speed = 1;

        getValues(input);

        //TODO: Changed these to only take in what is needed and to force that is a valid file or either text or visual
        while (!checkValidFile()) {
            this.file = JOptionPane.showInputDialog
                    ("An error occurred because the file does not exist. Try again: ");
        }


        while (!(this.view.equals("text") || this.view.equals("visual"))){
            this.view = JOptionPane.showInputDialog
                    ("An error occurred because the view type was not specified. Try again: ");
        }

        //TODO: Added in this
        while (this.out.isBlank()){
            this.view = JOptionPane.showInputDialog
                    ("An error occurred because the out file is not specified. Try again: ");
        }

        this.model = getModel();
    }

    /**
     * Returns either a text or visual controller based on the values stored in the Parser class.
     *
     * @return the controller for the Animator
     */
    public IController getController() throws IllegalArgumentException {
        if (view.equals("text")) {
            if (out.isBlank()) {
                return new TextController(model, new TextView(), System.out);
            } else {
                try {
                    return new TextController(model, new TextView(), new PrintStream(new File(out)));
                } catch (FileNotFoundException fnf) {
                    throw new IllegalArgumentException("There is no file.");
                }
            }
        } else {
            IView view = new VisualView(model.getCanvas().get(2), model.getCanvas().get(2));        //TODO: Because the controller now takes in a view, I had to update this
            return new VisualController(model, view, speed);
        }
    }

    /**
     * Iterates through the string and updates the file, view, speed, and out values.
     *
     * @param input an array of strings
     *
     * @throws IllegalArgumentException if the speed value is not an integer
     */
    private void getValues(String[] input) throws IllegalArgumentException {
        for (int i = 0; i < input.length; i++) {
            String word = input[i];
            switch (word) {
                case "-in":
                    file = input[i + 1];
                    break;
                case "-view":
                    view = input[i + 1];
                    break;
                case "-speed":
                    try {
                        speed = Integer.parseInt(input[i + 1]);
                        break;
                    }
                    catch (NumberFormatException nfe) {
                        throw new IllegalArgumentException("Speed value is not an integer.");
                    }
                case "-out":
                    out = input[i + 1];
                    break;
            }
        }
    }

    /**
     * Returns the AnimatorModel.
     *
     * @return an instance of an AnimatorModel.
     *
     * @throws IllegalArgumentException if the file is not found.
     */
    private AnimatorModel getModel() throws IllegalArgumentException {
        try {
            return AnimationReader.parseFile(new FileReader("./" + file), new AnimationBuilderImpl());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found.");
        }
    }

    /**
     * A method to check that a file with the specified name and extension exists.
     *
     * @return true if the file exists otherwise false
     */
    private boolean checkValidFile() {
        File file = new File(this.file);
        return file.exists();
    }
}





