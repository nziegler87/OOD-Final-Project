package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.view.InteractiveView;
import cs5004.animator.view.TextView;

import java.util.Scanner;

public class Parser {

    private Scanner scanner;

    public Parser(String input) {
        scanner = new Scanner(input);
    }

    public AnimatorControllerImpl parse() {

        String animationFile = "";
        String typeOfView = "";
        String outputLocation = "";
        int speed = 1;

        while (scanner.hasNext()) {

            String animation = scanner.next();
            while (scanner.hasNext()) {
                String nextTerm = scanner.next();
                if (nextTerm.equals("-in")) {
                    animationFile = scanner.next();
                }
                if (nextTerm.equals("-view")) {
                    typeOfView = scanner.next();
                }
                if (nextTerm.equals("-out")) {
                    outputLocation = scanner.next();
                }
                if (nextTerm.equals("-speed")) {
                    speed = scanner.nextInt();
                }
            }
        }

        if (animationFile.isBlank() || typeOfView.isBlank() || outputLocation.isBlank()) {
            throw new IllegalArgumentException("Not enough commands to start the animator");
        }

        // if the type is text, return the model and a text view
        if (typeOfView.equals("text")) {
            return new AnimatorControllerImpl(new AnimatorModelImpl(), new TextView(), outputLocation, speed);
        }
        // else return the model and an interactive view
        return new AnimatorControllerImpl(new AnimatorModelImpl(), new InteractiveView(), speed);
    }

}
