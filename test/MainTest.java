
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.controller.IController;
import cs5004.animator.controller.VisualController;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.commands.ChangeColor;
import cs5004.animator.model.commands.ICommand;
import cs5004.animator.model.commands.Move;
import cs5004.animator.model.commands.Scale;
import cs5004.animator.model.point2d.Point2D;
import cs5004.animator.model.shape.IShape;
import cs5004.animator.model.shape.Oval;
import cs5004.animator.model.shape.Rectangle;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.VisualView;

public class MainTest {

  public static void main(String [] Args) {
    AnimatorModel model = new AnimatorModelImpl();

    // add shapes
    IShape rectangle = new Rectangle("R", new Point2D(200, 200),
            new Color(Color.RED.getRGB()),50, 100, 1, 100);
    IShape oval = new Oval("C", new Point2D(500, 100),
            new Color(Color.BLUE.getRGB()), 60, 30, 6, 100);
    IShape testShape = new Rectangle("N", new Point2D(500, 500), new Color(Color.YELLOW.getRGB()), 40, 40, 10, 80);

    model.addShape(rectangle, oval, testShape);

    // add commands
    ICommand rCommand1 = new Move(rectangle, 10, 50, new Point2D(200, 200),
            new Point2D(300, 300));
    ICommand oCommand1 = new Move(oval, 20, 70, new Point2D(500, 100),
            new Point2D(500, 400));
    ICommand oCommmand2 = new ChangeColor(oval, 50, 80, new Color(Color.BLUE.getRGB()),
            new Color(Color.GREEN.getRGB()));
    ICommand rCommand2 = new Move(rectangle, 70, 100, new Point2D(300, 300),
            new Point2D(200, 200));
    ICommand rCommand3 = new Scale(rectangle, 51, 70, 50, 100, 25, 100);
    ICommand tCommand1 = new Move(testShape, 0, 20, new Point2D(500, 500), new Point2D(400, 400));
    ICommand tCommand2 = new Move(testShape, 20, 50, new Point2D(400, 400), new Point2D(400, 200));

    ICommand tCommand3 = new ChangeColor(testShape, 0, 30, new Color(Color.YELLOW.getRGB()), new Color(47, 0, 137));
    ICommand tCommand4 = new ChangeColor(testShape, 30, 50, new Color(47, 0, 137), new Color(Color.BLACK.getRGB()));


    model.addAnimation(rCommand1, oCommand1, oCommmand2, rCommand2, rCommand3, tCommand1, tCommand2, tCommand3, tCommand4);

    AnimatorModel model2;
    AnimationBuilder builder = new AnimationBuilderImpl();
    AnimationReader reader = new AnimationReader();
    try {
      model2 = (AnimatorModel) reader.parseFile(new FileReader("./toh-3.txt"), builder);      //TODO: Why do I have to cast it to AnimatorModel?
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found.");
    }

    VisualView view = new VisualView(500, 500);

    IController visualController = new VisualController(model2, view, 50);
    visualController.animate();

  }
}

