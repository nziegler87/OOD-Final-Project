package Model;

import java.util.HashMap;
import java.util.Objects;

import Model.CommandList.CommandList;
import Model.Commands.ICommand;
import Model.CommandList.ICommandList;
import Model.Shape.IShape;

public class AnimatorModelImpl implements AnimatorModel {

  protected HashMap<String, IShape> inventory;
  protected ICommandList commands;

  public AnimatorModelImpl() {
    inventory = new HashMap<>();
    commands = new CommandList();
  }

  /**
   * Adds a shape to the model inventory hashmap.
   *
   * @param label the the label associated with the shape
   * @param shape the shape that will be added to the map
   * @throws IllegalArgumentException if the shape already exists     // TODO: IllegalArg or NullPointer
   */
  @Override
  public void addShape(String label, IShape shape)
          throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(label, "Label must not be null.");
    Objects.requireNonNull(shape, "Shape must not be null.");

    // TODO: This seems to be throwing a NullPointerException
    if (inventory.get(label).equals(shape)) {
      throw new IllegalArgumentException("This object has already been added.");
    }

    // adds the details of the shape addition to the text list of commands
    commands.addShape(shape);
    // puts the shape in the inventory map
    inventory.put(label, shape);
  }

  /**
   * Removes a shape from the model inventory map.
   *
   * @param label the label associated with the shape
   * @throws NullPointerException     when either the command or the label are null
   * @throws IllegalArgumentException when the shape is not found
   */
  @Override
  public void removeShape(String label) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(label, "Label must not be null.");
    if (!inventory.containsKey(label)) {
      throw new IllegalArgumentException("Cannot remove object that does not exist.");
    }

    // get the shape from the map of shapes
    IShape shape = inventory.get(label);
    // adds the details of the shape removal to the text list of commands
    commands.removeShape(shape);
    // removes the shape from the inventory map
    inventory.remove(label);
  }

  /**
   * Finds and returns the shape using the label of the shape.
   *
   * @param label the label associated with the shape
   * @return the shape being searched for
   * @throws NullPointerException     when either the command or the label are null
   * @throws IllegalArgumentException when the shape is not found
   */
  @Override
  public IShape getShape(String label) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(label, "Label must not be null.");
    if (!inventory.containsKey(label)) {
      throw new IllegalArgumentException("Cannot get object that does not exist.");
    }

    // get the shape from the map of shapes
    return inventory.get(label);
  }

  /**
   * Finds and returns the shape using the label of the shape.
   *
   * @param label the label associated with the shape
   * @return the shape being searched for
   * @throws NullPointerException     when either the command or the label are null
   * @throws IllegalArgumentException when the shape is not found
   */
  @Override
  public IShape copyShape(String label) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(label, "Label must not be null.");
    if (!inventory.containsKey(label)) {
      throw new IllegalArgumentException("Cannot get object that does not exist.");
    }

    // get the shape from the map of shapes and return a copy
    return inventory.get(label).copy();
  }

  /**
   * Implements a command class on a shape.
   *
   * @param command the command class being passed in and executed on
   * @param label   the label associated with the shape
   * @param tick    the time considered when running the command
   * @throws NullPointerException when either the command or the label are null
   */
  @Override
  public void commandOnShape(ICommand command, String label, double tick)
          throws NullPointerException {
    // check for nulls
    Objects.requireNonNull(command, "Command must not be null.");
    Objects.requireNonNull(label, "Label must not be null.");

    // get the shape from the map of shapes
    IShape shape = copyShape(label);
    // add the command toString output to the descriptive animation list
    commands.addToStack(command.toString());
    // execute the command on the shape
    command.execute(shape, tick);
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

    // to iterate through the hashmap
    for (String s : inventory.keySet()) {
      IShape shape = inventory.get(s);
      // add the shape and it's details to the string
      status.append(shape.toString());
    }

    // adds the list of commands to the string
    status.append("\n").append(commands.getCommandList());
    // returns the full string
    return status.toString();
  }
}
