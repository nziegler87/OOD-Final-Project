package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    private String file;
    private String view;
    private String out;
    private int speed;

    public Parser() {
        file = "";
        view = "visual";
        out = "";
        speed = 1;
    }

    public IController parse(String[] input) throws IllegalArgumentException, IOException {

        for (int i = 0; i < input.length; i++) {
            switch (input[i]) {
                case "-in":
                    file = input[i + 1];
                    break;
                case "-view":
                    view = input[i + 1];
                    break;
                case "-speed":
                    speed = Integer.getInteger(input[i + 1]);
                    break;
                case "-out":
                    try {
                        out = input[i + 1];
                        System.out.println("-out is " + out);
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

        System.out.println("file is: " + file + "\nview is: " + view + "\nspeed is: " + speed);

        AnimatorModel model;
        try {
            model = AnimationReader.parseFile(new FileReader("./" + file), new AnimationBuilderImpl());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found.");
        }

        // if the view is text, return controller with a text view
        if (view.equals("text")) {
            return new TextController(model, new TextView(), speed, out);
        }

        // else return the controller with a visual view
        return new VisualController(model, new VisualView(800, 800), speed);            //TODO: Vido recommended passing in the panel width and height, which we read from the canvas. Better way to do it?
    }
}
