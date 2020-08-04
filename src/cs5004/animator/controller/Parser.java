package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Parser {

    private final Scanner scanner;

    public Parser(String input) {
        scanner = new Scanner(input);
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
            return new TextController(new AnimatorModelImpl(), new TextView(), speed, out);
        }

        // else return the controller with a visual view
        return new VisualController(new AnimatorModelImpl(), new VisualView(), speed);
    }
}
