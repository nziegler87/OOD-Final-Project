package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import Model.Commands.ICommand;
import Model.Shape.IShape;

/**
 * This class is the model (MVC architecture) in an application that helps one to create simple
 * but effective 2D animations from shapes. It includes operations to add and remove shapes
 * to the model inventory, add and remove animation commands to the shapes in the inventory, get
 * a summary of all stored shapes (sorted by appear time), and return a snapshot of all objects
 * at a given tick in time at the current animation state and visibility.
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
            && (firstCommand.getStartTime() > secondCommand.getStartTime()
            && firstCommand.getEndTime() <= secondCommand.getEndTime()
            // or the start for the second command is within the first command time frame
            || secondCommand.getStartTime() > firstCommand.getStartTime()
            && secondCommand.getEndTime() <= firstCommand.getEndTime()));
  }

  /**
   * Adds a command to the animation list.
   *
   * @param command the command being passed through
   * @throws NullPointerException     if the command being passed through is null
   * @throws IllegalArgumentException if the command has conflict with another command in the list
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
    commandHistory.sort((o1, o2) -> (int) (o1.getStartTime() - o2.getStartTime()));
  }

  /**
   * Removes a command from the animation list.
   *
   * @param command the command being removed
   * @throws NullPointerException     if the command being passed through is null
   * @throws IllegalArgumentException if the shape associated with command does not exist
   *                                  with in the model inventory
   */
  public void removeAnimation(ICommand command)
          throws NullPointerException, IllegalArgumentException {
    // check to make sure objects are not null
    Objects.requireNonNull(command, "Command object cannot be null");

    //TODO: Do we need this check for this method?

    // check to make sure shape is in inventory
    if (!this.inventory.containsKey(command.getShape().getLabel())) {
      throw new IllegalArgumentException("Shape object must be stored in model "
              + "in order to add command");
    }
    // if no arguments are thrown, add new command to commandHistory and sort
    this.commandHistory.remove(command);
    commandHistory.sort((o1, o2) -> (int) (o1.getStartTime() - o2.getStartTime()));
  }

  //TODO: Review addition of an IllegalArg for tick
  /**
   * Returns a list of all of the shapes and their current state based at a given tick of time.
   *
   * @param tick  a tick in the animation where you want to return the state of all objects
   *              visible on the screen
   *
   * @return List<IShape> with the summary of shapes and their state
   *
   * @throws IllegalArgumentException if tick is not greater or equal to 0
   */
  public List<IShape> getSnapshot(double tick) throws IllegalArgumentException {
    if (tick < 0) {
      throw new IllegalArgumentException("Tick must be greater or equal to 0");
    }
    List<IShape> snapshotList = new ArrayList<>();

    // go through each shape in the inventory and see what commands are associated with the shape
    for (IShape shape : this.inventory.values()) {

      // if the shape is on the screen, use animation commands to get its current state
      if (shape.getAppearTime() <= tick && shape.getDisappearTime() >= tick) {

        // create a temporary shape on which to create state
        IShape temporaryShape = shape.copy();

        // iterate through commands
        for (ICommand command : this.commandHistory) {

          // if command is associated with shape && tick is greater or equal to command start time,
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
   * Shape list is sorted by appear time and animation steps are sorted by start time.
   *
   * @return a string of the summary
   */
  @Override
  public String getAnimationStatus() {

    // if the inventory is empty, send a message about it
    if (this.inventory.size() == 0) {
      return "No shapes have been added to the inventory, so no animations can be displayed";
    }

    StringBuilder status = new StringBuilder();

    // the top of the string description
    status.append("Shapes:\n");

    // TODO: Because we return the above string if the inventory is 0, I think we can get rid of this
    if (!(this.inventory.size() == 0)) {
      // to iterate through the hashmap
      List<IShape> sortedShapeList = this.getSortedShapeList();
      for (IShape shape : sortedShapeList) {
        // add the shape and it's details to the string
        status.append(shape.toString());        //TODO: I think we can make this status.append(shape.toString()).append("\n\n");
        status.append("\n\n");
      }

      // if the command history has stuff in it, add it to the bottom of the string
      if (!(this.commandHistory.size() == 0)) {
        for (ICommand command : this.commandHistory) {
          status.append(command.toString());
        }
      } else {
        // otherwise state that it is empty
        status.append("Command list is empty.");
      }
    }
    return status.toString();
  }

  /**
   * Method to return an array list of the shapes in the inventory sorted by appear time
   *
   * @return a sorted list of shapes, sorted by appear time
   */
  private List<IShape> getSortedShapeList() {
    List<IShape> shapeList = new ArrayList<>(this.inventory.values());
    shapeList.sort((o1, o2) -> (int) (o1.getAppearTime() - o2.getAppearTime()));
    return shapeList;
  }
}
