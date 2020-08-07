package cs5004.animator.view;

import cs5004.animator.model.commands.ICommand;
import cs5004.animator.shape.IShape;

import java.util.List;

/**
 * The text view class. This implements IView and contains the method render().
 */
public class TextView implements IView {

    /**
     * The visual view constructor.
     */
    public TextView() {
        // constructor is empty because nothing to initialize
    }

    /**
     * A method to render the shapes at their current state of animation.
     *
     * @param shapes a list of IShapes.
     *
     * @throws IllegalArgumentException if called in the TextView
     */
    @Override
    public void render(List<IShape> shapes) throws IllegalArgumentException {
        throw new IllegalArgumentException("Method invalid for text view.");
    }

    /**
     * This is the render method, which in the text view does not return anything.
     *
     * @param shapes but in this case these are not utilized for anything
     */
    @Override
    public String textRender(List<IShape> shapes, List<ICommand> commands) {

        if (shapes.size() == 0) {
            return "No shapes have been added to the inventory, so no animations can be displayed";
        }

        StringBuilder status = new StringBuilder();
        status.append("Shapes:\n");

        for (IShape shape : shapes) {
            status.append(shape.toString()).append("\n\n");
        }

        if ((commands.size() != 0)) {
            for (ICommand command : commands) {
                status.append(command.toString());
            }
        } else {
            status.append("Command list is empty.");
        }

        return status.substring(0, status.length() - 1);
    }
}
