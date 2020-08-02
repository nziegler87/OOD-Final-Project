package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import cs5004.animator.model.commands.ICommand;
import cs5004.animator.model.shape.IShape;
import cs5004.animator.model.shape.Oval;
import cs5004.animator.model.shape.Rectangle;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;


/**
 * This class is the model (MVC architecture) in an application that helps one to create simple but
 * effective 2D animations from shapes. It includes operations to add and remove shapes to the model
 * inventory, add and remove animation commands to the shapes in the inventory, get a summary of all
 * stored shapes (sorted by appear time), and return a snapshot of all objects at a given tick in
 * time at the current animation state and visibility.
 */
public class AnimatorModelImpl implements AnimatorModel {
    private final HashMap<String, IShape> inventory;
    private final ArrayList<ICommand> commandHistory;

    public AnimatorModelImpl() {
        this.inventory = new HashMap<>();
        this.commandHistory = new ArrayList<>();
    }

    /**
     * Adds a shape to the model inventory hashmap.
     *
     * @param shapes the shape(s) that will be added to the map
     * @throws NullPointerException     when the shape is null
     * @throws IllegalArgumentException if the shape already exists
     */
    @Override
    public void addShape(IShape... shapes)
            throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(shapes, "Shape must not be null.");
        for (IShape shape : shapes) {
            if (this.inventory.containsKey(shape.getLabel())) {
                throw new IllegalArgumentException("This object has already been added.");
            }
            this.inventory.put(shape.getLabel(), shape);
        }
    }

    /**
     * Removes a shape from the model inventory map.
     *
     * @param shapes the shape(s) that will be removed
     * @throws NullPointerException     when the shape is null
     * @throws IllegalArgumentException when the shape is not found
     */
    @Override
    public void removeShape(IShape... shapes)
            throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(shapes, "Shape must not be null.");
        for (IShape shape : shapes) {
            if (!this.inventory.containsKey(shape.getLabel())) {
                throw new IllegalArgumentException("Cannot remove object that does not exist.");
            }
            this.inventory.remove(shape.getLabel());
        }
    }

    /**
     * Adds a shape to the model inventory hashmap.
     *
     * @param label the name of the shape to find
     * @throws NullPointerException     when the label is null
     * @throws IllegalArgumentException if the shape does not exists
     */
    @Override
    public IShape getShape(String label)
            throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(label, "Label must not be null.");
        if (this.inventory.containsKey(label)) {
            return this.inventory.get(label);
        }
        throw new IllegalArgumentException("This object does not exist.");
    }

    /**
     * Helper method to find if there's a conflict between two commands.
     *
     * @param firstCommand  the first command under consideration
     * @param secondCommand the second command under consideration
     * @return boolean (T/F)
     */
    private boolean commandConflict(ICommand firstCommand, ICommand secondCommand)
            throws NullPointerException {
        Objects.requireNonNull(firstCommand, "First command cannot be null.");
        Objects.requireNonNull(secondCommand, "Second command cannot be null.");
        // return true if same type of command
        return (firstCommand.getCommandType().equals(secondCommand.getCommandType())
                // and same object being modified (by label)
                && firstCommand.getShape().getLabel().equals(secondCommand.getShape().getLabel())
                // and either the start for the first command is within the second command time frame
                && (firstCommand.getStartTime() >= secondCommand.getStartTime()
                && firstCommand.getEndTime() <= secondCommand.getEndTime()
                // or the start for the second command is within the first command time frame
                || secondCommand.getStartTime() >= firstCommand.getStartTime()
                && secondCommand.getEndTime() <= firstCommand.getEndTime()));
    }

    /**
     * Adds a command to the animation list.
     *
     * @param commands the command being passed through
     * @throws NullPointerException     if the command being passed through is null
     * @throws IllegalArgumentException if the command has conflict with another command in the list
     */
    public void addAnimation(ICommand... commands)
            throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(commands, "Command object cannot be null");
        for (ICommand command : commands) {
            if (!this.inventory.containsKey(command.getShape().getLabel())) {
                throw new IllegalArgumentException("Shape object must be stored in model "
                        + "in order to add command");
            }
            // look through all of the commands for a conflict; if so, throw exception
            for (ICommand historicalCommand : this.commandHistory) {
                if (commandConflict(historicalCommand, command)) {
                    throw new IllegalArgumentException("Cannot assign the same animation to happen on " +
                            "the same object at the same time");
                }
            }
            // if no arguments are thrown, add new command to commandHistory and sort
            this.commandHistory.add(command);
            commandHistory.sort((o1, o2) -> (int) (o1.getStartTime() - o2.getStartTime()));
        }
    }

    /**
     * Removes a command from the animation list.
     *
     * @param commands the command being removed
     * @throws NullPointerException     if the command being passed through is null
     * @throws IllegalArgumentException if the shape associated with command does not exist within the
     *                                  model inventory
     */
    public void removeAnimation(ICommand... commands)
            throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(commands, "Command object cannot be null");
        for (ICommand command : commands) {
            if (!this.inventory.containsKey(command.getShape().getLabel())) {
                throw new IllegalArgumentException("Shape object must be stored in model "
                        + "in order to remove command");
            }
            // if no arguments are thrown, add new command to commandHistory and sort
            this.commandHistory.remove(command);
            commandHistory.sort((o1, o2) -> (int) (o1.getStartTime() - o2.getStartTime()));
        }
    }

    /**
     * Returns a list of all of the shapes and their current state based at a given tick of time.
     *
     * @param tick a tick in the animation where you want to return the state of all objects visible
     *             on the screen
     * @return {@code List<IShape>} with the summary of shapes and their state
     * @throws IllegalArgumentException if tick is not greater or equal to 0
     */
    @Override
    public List<IShape> getSnapshot(double tick) throws IllegalArgumentException {
        if (tick < 0) {
            throw new IllegalArgumentException("Tick must be greater or equal to 0");
        }

        List<IShape> snapshotList = new ArrayList<>();
        // go through each shape in the inventory and see what commands are associated with the shape
        for (IShape shape : this.inventory.values()) {
            // if the shape is on the screen, use animation commands to get its current state
            if (shape.getAppearTime() <= tick && shape.getDisappearTime() >= tick) {
                IShape temporaryShape = shape.copy();
                // iterate through commands
                for (ICommand command : this.commandHistory) {
                    // if command is associated with shape && tick is greater or equal to command start time,
                    if (command.getShape().getLabel().equals(shape.getLabel())
                            && tick >= command.getShape().getAppearTime()
                            && tick >= command.getStartTime()) {
                        try {
                            temporaryShape = command.execute(temporaryShape, tick);
              /*
              If tick is past command time, it will throw an exception, so get the state of the
              objects at the end of the command
              */
                        } catch (IllegalArgumentException iae) {
                            temporaryShape = command.execute(temporaryShape, command.getEndTime());
                        }
                    }
                }
                snapshotList.add(temporaryShape);
            }
        }
        return snapshotList;
    }

    /**
     * Returns a summary of the animation, including the shapes in the list and the animation steps.
     * Shape list is sorted by appear time and animation steps are sorted by start time.
     *
     * @return a string of the summary
     */
    @Override
    public String getAnimationStatus() {

        if (this.inventory.size() == 0) {
            return "No shapes have been added to the inventory, so no animations can be displayed";
        }

        StringBuilder status = new StringBuilder();
        status.append("Shapes:\n");

        List<IShape> sortedShapeList = this.getSortedShapeList();
        for (IShape shape : sortedShapeList) {
            status.append(shape.toString()).append("\n\n");
        }

        if ((this.commandHistory.size() != 0)) {
            for (ICommand command : this.commandHistory) {
                status.append(command.toString());
            }
        } else {
            status.append("Command list is empty.");
        }

        String draft = status.toString();
        return draft.substring(0, draft.length() - 1);
    }

    /**
     * Method to return an array list of the shapes in the inventory sorted by appear time.
     *
     * @return a sorted list of shapes, sorted by appear time
     */
    private List<IShape> getSortedShapeList() {
        List<IShape> shapeList = new ArrayList<>(this.inventory.values());
        shapeList.sort((o1, o2) -> (int) (o1.getAppearTime() - o2.getAppearTime()));
        return shapeList;
    }
}
