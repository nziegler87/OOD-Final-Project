import java.awt.*;
import java.util.ArrayList;

public class AnimatorModelImpl implements AnimatorModel {

  protected ArrayList<IShape> inventory;

  public void AnimatorModelImpl() {
    inventory = new ArrayList<>();
  }

  @Override
  public void addShape(IShape shape, String label, int width, int height, int x, int y, Color color) {
    IPoint2D coordinates = new Point2D(x, y);
    inventory.add(new IShape(label, width, height, coordinates, color));
  }

  @Override
  public void removeShape(String label) {
    inventory.remove(getShape(label));
  }

  @Override
  public void removeShape(int position) {
    inventory.remove(position);
  }

  @Override
  public IShape getShape(String label) {
    IShape chosenShape;
    for (IShape shape : inventory) {
      if (shape.getLabel().equals(label)) {
        chosenShape = shape;
      }
    }
    return chosenShape;
  }

  @Override
  public IShape getShape(int position) {
    return inventory.get(position);
  }

  @Override
  public void moveShape(String label, double x, double y) {
      getShape(label).setCoordinates(x,y);
  }

  @Override
  public void changeShape(String label, IShape newShape) {
    removeShape(label);
    addShape()
  }

  @Override
  public void changeColor(String label, Color color) {
    getShape(label).setColor(color);
  }

  @Override
  public String getAnimationStatus() {
    StringBuilder status = new StringBuilder();
    status.append("Shapes:\n");
    for (IShape shape : inventory) {
      status.append(shape.toString()).append("")
    }
  }
}
