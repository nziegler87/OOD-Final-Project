package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * A parser class that is used to read a text string of arguments and determine the input file,
 * the type of view, the output location, and the speed of the render.
 */
public class Parser {
    private String file;
    private String view;
    private String out;
    private int speed;

    /**
     * Creates an instance of a Parser class and initializes default values.
     */
    public Parser() {
        file = "";
        view = "visual";
        out = "";
        speed = 1;
    }

    /**
     * Method to actually parse the text and return an IController object, either a visual or text
     * controller.
     *
     * @param input a string of text arguments.
     *
     * @return an IController object
     *
     * @throws IllegalArgumentException if an error occurs when creating the output file.
     */
    public IController parse(String[] input) throws IllegalArgumentException {

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
                    try {
                        out = input[i + 1];
                        File newFile = new File(out);
                        if (!newFile.createNewFile()) {
                            throw new IllegalArgumentException("An error occurred because the file already exists.");
                        }
                    } catch (IOException e) {
                        throw new IllegalArgumentException("An error occurred while making the new file.");
                    }
                    break;
            }
        }

        AnimatorModel model;
        try {
            model = AnimationReader.parseFile(new FileReader("./" + file), new AnimationBuilderImpl());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found.");
        }

        // if the view is text, return controller with a text view
        if (view.equals("text")) {
            if (out.isBlank()) {
                return new TextController(model);
            } else {
                return new TextController(model, out);
            }
        }

        // else return the controller with a visual view
        return new VisualController(model, speed);
    }
}
