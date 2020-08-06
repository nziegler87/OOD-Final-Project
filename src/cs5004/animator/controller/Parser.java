package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
        while (file.isBlank()) {
            String[] newInput = JOptionPane.showInputDialog
                    ("An error occurred because the file does not exist. Try again: ").split(" ");
            getValues(newInput);
        }
        this.model = getModel();
    }

    /**
     * Returns either a text or visual controller based on the values stored in the Parser class.
     *
     * @return the controller for the Animator
     */
    public IController getController() {
        if (view.equals("text")) {
            if (out.isBlank()) {
                return new TextController(model);
            } else {
                return new TextController(model, out);
            }
        } else {
            return new VisualController(model, speed);
        }
    }

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
                    speed = Integer.parseInt(input[i + 1]);
                    break;
                case "-out":
                    out = input[i + 1];
                    createOutfile();
                    break;
            }
        }
    }

    private void createOutfile() throws IllegalArgumentException {
        try {
            File newFile = new File(out);
            while (!newFile.createNewFile()) {
                out = JOptionPane.showInputDialog("An error occurred because the outfile already exists. Try again: ");
                new File(out);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("An error occurred while making the new file.");
        }
    }

    private AnimatorModel getModel() throws IllegalArgumentException {
        try {
            return AnimationReader.parseFile(new FileReader("./" + file), new AnimationBuilderImpl());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found.");
        }
    }
}
