package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import Model.CommandList.CommandList;
import Model.Commands.ICommand;
import Model.CommandList.ICommandList;
import Model.Shape.IShape;

public class AnimatorModelImpl implements AnimatorModel {

  protected HashMap<String, IShape> inventory;
  protected ICommandList commands;
  protected ArrayList<IShape> snapshot;
  private final double tick;

  public AnimatorModelImpl(double tick) {
    this.inventory = new HashMap<>();
    this.commands = new CommandList();
    this.snapshot = new ArrayList<>();
    this.tick = tick;
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

    // adds the details of the shape addition to the text list of commands
    this.commands.addShape(shape); // TODO: don't we need this? These are different than appear/disappear
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
    // adds the details of the shape removal to the text list of commands
    this.commands.removeShape(shape); // TODO: don't we need this? These are different than appear/disappear
    // removes the shape from the inventory map
    this.inventory.remove(shape.getLabel());
  }

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

  /**
   * Implements a command class on a shape.
   *
   * @param command the command class being passed in and executed on
   * @param shape   the shape being modified
   * @param tick    the time considered when running the command
   * @throws NullPointerException when either the command or the label are null
   */
  @Override
  public void commandOnShape(ICommand command, IShape shape, double tick)
          throws NullPointerException {
    // check for nulls
    Objects.requireNonNull(command, "Command must not be null.");
    Objects.requireNonNull(shape, "Shape must not be null.");

    // get the shape from the map of shapes
    IShape copiedShape = copyShape(shape);
    // add the command toString output to the descriptive animation list
    this.commands.addToStack(command.toString());
    // execute the command on the shape
    command.execute(copiedShape, tick);
  }

  // TODO: added in this bit to get the list of states

  /**
   * Returns a list of all of the shapes and their state based on the tick input.
   *
   * @return List<IShape> with the summary of shapes and their state
   */
  public List<IShape> getSnapshot() {
    for (String s : this.inventory.keySet()) {
      IShape shape = this.inventory.get(s);
      if (this.tick > shape.getAppearTime()
              && this.tick < shape.getDisappearTime()) {
        this.snapshot.add(shape);
      }
    }
    return this.snapshot;
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
      for (String s : this.inventory.keySet()) {
        IShape shape = this.inventory.get(s);
        // add the shape and it's details to the string
        status.append(shape.toString());
        status.append("\n\n");
      }
      // adds the list of commands to the string
      status.append("\n\n").append(this.commands.getCommandList());
    }

    return status.toString();
  }
}
