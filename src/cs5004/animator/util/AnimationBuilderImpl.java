package cs5004.animator.util;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.commands.ChangeColor;
import cs5004.animator.model.commands.ICommand;
import cs5004.animator.model.commands.Move;
import cs5004.animator.model.commands.Scale;
import cs5004.animator.model.point2d.Point2D;
import cs5004.animator.model.shape.IShape;
import cs5004.animator.model.shape.Oval;
import cs5004.animator.model.shape.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class AnimationBuilderImpl implements AnimationBuilder<AnimatorModel> {

    private final AnimatorModel model;
    private LinkedHashMap<String, IShape> shapes;
    private ArrayList<ICommand> commands;
    private ArrayList<Integer> screenDetails;
    private int canvasRightBound;
    private int canvasTopBound;

    public AnimationBuilderImpl(){
        this.model = new AnimatorModelImpl();
        this.shapes = new LinkedHashMap<>();
        this.commands = new ArrayList<>();
        this.screenDetails = new ArrayList<>();

    }

    /**
     * Constructs a final document.
     *
     * @return the newly constructed document
     */
    @Override
    public AnimatorModel build() {
        // set up the screen details
        model.setCanvas(screenDetails);

        // add in all the shapes
        for (IShape shape : shapes.values()) {
            model.addShape(shape);
        }

        // add in all the commands
        for (ICommand command : commands) {
            model.addAnimation(command);
        }

        return model;
    }

    /**
     * Specify the bounding box to be used for the animation.
     *
     * @param x      The leftmost x value
     * @param y      The topmost y value
     * @param width  The width of the bounding box
     * @param height The height of the bounding box
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<AnimatorModel> setBounds(int x, int y, int width, int height) {
        screenDetails.add(x);
        screenDetails.add(y);
        screenDetails.add(width);
        screenDetails.add(height);

        this.canvasRightBound = x + width;
        this.canvasTopBound = y + height;
        return this;
    }

    /**
     * Adds a new shape to the growing document.
     *
     * @param name The unique name of the shape to be added. No shape with this name should already
     *             exist.
     * @param type The type of shape (e.g. "ellipse", "rectangle") to be added. The set of supported
     *             shapes is unspecified, but should include "ellipse" and "rectangle" as a
     *             minimum.
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<AnimatorModel> declareShape(String name, String type) throws IllegalArgumentException {
        if (type.equals("ellipse")) {
            shapes.put(name, new Oval(name));
        } else if (type.equals("rectangle")) {
            shapes.put(name, new Rectangle(name));
        } else {
            throw new IllegalArgumentException("The type is invalid.");
        }
        return this;
    }

    /**
     * Adds a transformation to the growing document.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t1   The start time of this transformation
     * @param x1   The initial x-position of the shape
     * @param y1   The initial y-position of the shape
     * @param w1   The initial width of the shape
     * @param h1   The initial height of the shape
     * @param r1   The initial red color-value of the shape
     * @param g1   The initial green color-value of the shape
     * @param b1   The initial blue color-value of the shape
     * @param t2   The end time of this transformation
     * @param x2   The final x-position of the shape
     * @param y2   The final y-position of the shape
     * @param w2   The final width of the shape
     * @param h2   The final height of the shape
     * @param r2   The final red color-value of the shape
     * @param g2   The final green color-value of the shape
     * @param b2   The final blue color-value of the shape
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<AnimatorModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1,
                                                     int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2,
                                                     int g2, int b2) throws IllegalArgumentException {


        // check if shape settings have been saved and update accordingly
        if (shapes.containsKey(name)) {
            if ((shapes.get(name).getAppearTime() == -1 && shapes.get(name).getDisappearTime() == -1)
                || (shapes.get(name).getAppearTime() > t1)) {
                shapes.get(name).setAppearTime(t1);
                shapes.get(name).setDisappearTime(t2);
                shapes.get(name).setCoordinates(x1, y1);
                shapes.get(name).setWidth(w1);
                shapes.get(name).setHeight(h1);
                shapes.get(name).setColor(new Color(r1, g1, b1));
            }

            // check if disappear time is greater than saved record
            if (shapes.get(name).getDisappearTime() < t2) {
                shapes.get(name).setDisappearTime(t2);
            }
        }


        // stuff for commands
        if (x1 != x2 || y1 != y2) {
            if (x2 < canvasRightBound && y2 < canvasTopBound) {
                commands.add(new Move(shapes.get(name), t1, t2, new Point2D(x1, y1), new Point2D(x2, y2)));
            } else throw new IllegalArgumentException("New coordinates must be within the canvas bounds.");
        }
        if (w1 != w2 || h1 != h2) {
            commands.add(new Scale(shapes.get(name), t1, t2, w1, h1, w2, h2));
        }
        if (!new Color(r1, g1, b1).equals(new Color(r2, g2, b2))) {
            commands.add(new ChangeColor(shapes.get(name), t1, t2, new Color(r1, g1, b1), new Color(r2, g2, b2)));
        }
        return this;
    }
}
