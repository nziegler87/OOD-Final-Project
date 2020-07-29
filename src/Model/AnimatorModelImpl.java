package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import Model.Commands.ICommand;
import Model.Shape.IShape;

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
   * @param shape the shape that will be added to the map
   * @throws NullPointerException     when the shape is null
   * @throws IllegalArgumentException if the shape already exists
   */
  @Override
  public void addShape(IShape shape)
          throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(shape, "Shape must not be null.");

    if (this.inventory.containsKey(shape.getLabel())) {
      throw new IllegalArgumentException("This object has already been added.");
    }

    this.inventory.put(shape.getLabel(), shape);
  }

  /**
   * Removes a shape from the model inventory map.
   *
   * @param shape the label associated with the shape
   * @throws NullPointerException     when the shape is null
   * @throws IllegalArgumentException when the shape is not found
   */
  @Override
  public void removeShape(IShape shape)
          throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(shape, "Shape must not be null.");
    if (!this.inventory.containsKey(shape.getLabel())) {
      throw new IllegalArgumentException("Cannot remove object that does not exist.");
    }
    this.inventory.remove(shape.getLabel());
  }

  /**
   * Helper method to find if there's a conflict between two commands
   *
   * @param firstCommand the first command under consideration
   * @param secondCommand the second command under consideration
   * @return boolean (T/F)
   */
  private boolean commandConflict(ICommand firstCommand, ICommand secondCommand) {
    Objects.requireNonNull(firstCommand, "First command cannot be null.");
    Objects.requireNonNull(secondCommand, "Second command cannot be null.");
    // return true if same type of command
    return (firstCommand.getCommandType().equals(secondCommand.getCommandType())
            // and same object being modified (by label)
            && firstCommand.getShape().getLabel().equals(secondCommand.getShape().getLabel())
            // and either the start for the first command is within the second command time frame
            && (firstCommand.getStartTime() > secondCommand.getStartTime()
            && firstCommand.getStartTime() < secondCommand.getEndTime())
            // or the start for the second command is within the first command time frame
            || (secondCommand.getStartTime() > firstCommand.getStartTime()
            && secondCommand.getStartTime() < firstCommand.getEndTime()));
  }

  /**
   * A static comparator class to sort the objects by start time.
   */
  private static final Comparator<ICommand> commandComparator = (o1, o2) ->
          (int) (o1.getStartTime() - o2.getStartTime());

  //TODO: If this works, need to test

  /**
   * TODO: FILL THIS OUT
   *
   * @param command
   * @throws NullPointerException
   * @throws IllegalArgumentException
   */
  public void addAnimation(ICommand command)
          throws NullPointerException, IllegalArgumentException {
    // check to make sure objects are not null
    Objects.requireNonNull(command, "Command object cannot be null");
    // check to make sure shape is in inventory
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
    commandHistory.sort(commandComparator);
  }

  /**
   * Returns a list of all of the shapes and their state based on the tick input.
   *
   * @return List<IShape> with the summary of shapes and their state
   */
  public List<IShape> getSnapshot(double tick) {
    List<IShape> snapshotList = new ArrayList<>();

    // go through each shape in the inventory and see what commands are associated with the shape
    for (IShape shape : this.inventory.values()) {

      //TODO: Does this logic make sense?

      // if the shape is on the screen, use animation commands to get its current state
      if (shape.getAppearTime() < tick && shape.getDisappearTime() >= tick) {

        // create a temporary shape on which to create state
        IShape temporaryShape = shape.copy();

        // iterate through commands
        for (ICommand command : this.commandHistory) {

          // if command is associated with shape
          if ((command.getShape().getLabel().equals(shape.getLabel()))
                  && (tick >= command.getShape().getAppearTime())) {
            if (tick >= command.getStartTime())

              // try to execute command
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
   *
   * @return a string of the summary
   */
  @Override
  public String getAnimationStatus() {

    if (this.inventory.size() == 0) {
      return "No shapes have been added to the inventory, so no animations can be displayed";
    }

    StringBuilder status = new StringBuilder();

    // the top of the string description
    status.append("Shapes:\n");

    if (!(this.inventory.size() == 0)) {
      // to iterate through the hashmap
      for (IShape shape : this.inventory.values()) {
        // add the shape and it's details to the string
        status.append(shape.toString());
        status.append("\n\n");
      }

      if (!(this.commandHistory.size() == 0)) {
        for (ICommand command : this.commandHistory) {
          status.append(command.toString());
        }
      } else {
        status.append("Command list is empty.");
      }
    }
    return status.toString();
  }
}
