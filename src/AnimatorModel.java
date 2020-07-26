import java.awt.*;

public interface AnimatorModel {

  void addShape(IShape shape);

  void removeShape(String label);

  void removeShape(int position);

  IShape getShape(String label);

  IShape getShape(int position);

  void moveShape(String label, double x, double y);

  void changeColor(String label, Color color);

  void changeShape(String label, IShape newShape);

  String getAnimationStatus();

}
