package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModelImpl;

import java.util.Scanner;

public class Parser {

    private Scanner scanner;

    public Parser(String input) {
        scanner = new Scanner(input);
    }

    /**
     * The findBoard type method gets the board type from the inputs.
     *
     * @return the model for the controller to use
     */
    public AnimatorModelImpl getDetails() {

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

        // and if there are no items to iterate on...
        return new AnimatorModelImpl();
    }

}
