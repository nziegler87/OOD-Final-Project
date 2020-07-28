package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

//import Model.CommandList.CommandList;
import Model.Commands.ICommand;
import Model.Shape.IShape;

public class AnimatorModelImpl implements AnimatorModel {
  private final HashMap<String, IShape> inventory;
  private final ArrayList<ICommand> commandHistory;

//  private final ArrayList<IShape> snapshot;
//  protected ICommandList commands;



  public AnimatorModelImpl() {
    this.inventory = new HashMap<>();
//    this.commands = new CommandList();
//    this.snapshot = new ArrayList<>();
    this.commandHistory = new ArrayList<>();
  }

  // TODO: I noticed yesterday the label stored in the list can be different than the label for
  //  the obj, so I recommend we rework this so it pulls the label from the shape.getLabel() function

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

//    // adds the details of the shape addition to the text list of commands
//    this.commands.addShape(shape); // TODO: don't we need this? These are different than appear/disappear...NZ: I think we can remove them because time one screen is now store in our shapes?
    // puts the shape in the inventory map

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
//    // adds the details of the shape removal to the text list of commands
//    this.commands.removeShape(shape); // TODO: don't we need this? These are different than appear/disappear...NZ: Same as above
    // removes the shape from the inventory map
    this.inventory.remove(shape.getLabel());
  }

  //TODO: I don't think this is returning a copy? Is it needed?
  /**
   * Finds and returns the shape using the label of the shape.
   *
   * @param shape the shape being copied
   * @return the shape being searched for
   * @throws NullPointerException     when the shape is null
   * @throws IllegalArgumentException when the shape is not found
   */
  @Override
  public IShape copyShape(IShape shape) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(shape, "Shape must not be null.");
    if (!this.inventory.containsKey(shape.getLabel())) {
      throw new IllegalArgumentException("Cannot get object that does not exist.");
    }

    // get the shape from the map of shapes and return a copy
    return this.inventory.get(shape.getLabel()).copy();
  }

  //TODO: Should we pass in shape name or shape object?
  //TODO: If this works, add to interface and test
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

    // if none of the above arguments are thrown, add new command to commandHistory
    this.commandHistory.add(command);

  }

  // TODO: added in this bit to get the list of states

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


  //  /**
//   * Implements a command class on a shape.
//   *
//   * @param command the command class being passed in and executed on
//   * @param shape   the shape being modified
//   * @param tick    the time considered when running the command
//   * @throws NullPointerException when either the command or the label are null
//   */
//  @Override
//  public void commandOnShape(ICommand command, IShape shape, double tick)
//          throws NullPointerException {
//    // check for nulls
//    Objects.requireNonNull(command, "Command must not be null.");
//    Objects.requireNonNull(shape, "Shape must not be null.");
//
//    // get the shape from the map of shapes
//    IShape copiedShape = copyShape(shape);
//    // add the command toString output to the descriptive animation list
//    this.commands.addToStack(command.toString());
//    // execute the command on the shape
//    command.execute(copiedShape, tick);
//  }

}
