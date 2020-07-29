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

  /*
  TODO: We need to order our commandHistory by start time. - DONE NZ
        We also need a way to get the last state of the object.
   */

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
  public void removeShape(IShape shape) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(shape, "Shape must not be null.");
    if (!this.inventory.containsKey(shape.getLabel())) {
      throw new IllegalArgumentException("Cannot remove object that does not exist.");
    }
    this.inventory.remove(shape.getLabel());
  }

  //TODO: If this works, need to test
  public void addAnimation(ICommand command)
          throws NullPointerException, IllegalArgumentException{

    // check to make sure objects are not null
    Objects.requireNonNull(command, "Command object cannot be null");

    // check to make sure shape is in inventory
    if (!this.inventory.containsKey(command.getShape().getLabel())) {
      throw new IllegalArgumentException("Shape object must be stored in model "
              + "in order to add command");
    }

    //TODO: Add logic to make sure animations don't overlap
    // check to make sure the same action is not already being performed on it
    for (ICommand historicalCommand : this.commandHistory) {
      if (historicalCommand.getCommandType() == command.getCommandType()
              && historicalCommand.getShape().getLabel().equals(command.getShape().getLabel())) {
        throw new IllegalArgumentException("Cannot assign the same animation to happen on " +
                "the same object at the same time");
      }
    }

    // if none of the above arguments are thrown, add new command to commandHistory and sort
    this.commandHistory.add(command);
    commandHistory.sort(commandComparator);


  }

  /*
  TODO: For this one, we need to queue up multiple commands that may occur on the same object and execute them in order, passing the modified object to the new command each time.
   */
  /**
   * Returns a list of all of the shapes and their state based on the tick input.
   *
   * @return List<IShape> with the summary of shapes and their state
   */
  public List<IShape> getSnapshot(double tick) {
    List<IShape> snapshotList = new ArrayList<>();

    for (ICommand command : this.commandHistory) {
      if (tick > command.getShape().getAppearTime()
              && tick < command.getShape().getDisappearTime()) {
        snapshotList.add(command.execute(tick));
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

      // adds the list of commands to the string
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

  /**
   * A static comparator class to sort the objects by start time.
   */
  public static Comparator<ICommand> commandComparator = new Comparator<ICommand>() {

    @Override
    public int compare(ICommand o1, ICommand o2) {
      double commandOneStart = o1.getStartTime();
      double commandTwoStart = o2.getStartTime();

      return (int) (commandOneStart - commandTwoStart);

    }
  };
}
