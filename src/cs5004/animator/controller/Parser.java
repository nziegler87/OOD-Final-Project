package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Parser {

    private final Scanner scanner;
    private final AnimatorModel model;


    public Parser(String[] input, AnimatorModel model) {
        scanner = new Scanner(String.valueOf(input));
        this.model = model;
    }

    public IController parse() throws IOException {

        // default settings
        String animationFile = "";
        String typeOfView = "";
        String out = "";
        int speed = 1;

        while (scanner.hasNext()) {

            String nextTerm = scanner.next();

            if (nextTerm.equals("-in")) {
                animationFile = scanner.next();
            }
            if (nextTerm.equals("-view")) {
                typeOfView = scanner.next();
            }
            if (nextTerm.equals("-speed")) {
                speed = scanner.nextInt();
            }
            if (nextTerm.equals("-out")) {
                try {
                    out = scanner.next();
                    File newFile = new File(out);
                    if (!newFile.createNewFile()) {
                        throw new IllegalArgumentException("An error occurred because the file already exists.");
                    }
                } catch (IOException e) {
                    throw new IllegalArgumentException("An error occurred while making the new file.");
                }
            }
        }

        if (animationFile.isBlank() || typeOfView.isBlank()) {
            throw new IllegalArgumentException("Not enough commands to start the animator.");
        }

        //TODO: need to test this

        // if the view is text, return controller with a text view
        if (typeOfView.equals("text")) {
            return new TextController(model, new TextView(), speed, out);
        }

        // else return the controller with a visual view
        return new VisualController(model, new VisualView(500, 500), speed);            //TODO: Vido recommended passing in the panel width and height, which we read from the canvas. Better way to do it?
    }
}
