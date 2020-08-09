package cs5004.animator.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs5004.animator.model.commands.ICommand;
import cs5004.animator.shape.IShape;

/**
 * The visual view class. This implements IView and contains the method render().
 */
public class VisualView extends JFrame implements IView {
  private final CanvasDrawingPanel drawingCanvas;

  /**
   * The visual view constructor.
   *
   * @param width  the width of the canvas
   * @param height the height of the canvas
   */
  public VisualView(int width, int height) {
    // call JFrame constructor
    super();

    Container frame = this.getContentPane();

    MenuBar menuBar = new MenuBar();


    // set default state of window
    this.setTitle("Easy Animator Visual Display");
    this.setSize(500, 500);
    this.setBackground(Color.lightGray);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create a CanvasDrawingPanel object
    drawingCanvas = new CanvasDrawingPanel(width, height);

    // create scroll bars on panel and add to frame
    JScrollPane scrollPane = new JScrollPane(drawingCanvas);
    frame.add(scrollPane, BorderLayout.CENTER);

    // add top menu bar
    frame.add(menuBar, BorderLayout.PAGE_START);

    // add dropdown menu bar
    String[] petStrings = { " ", "Bird", "Cat", "Dog", "Rabbit", "Pig" };
    JComboBox<String> petList = new JComboBox(petStrings);
    petList.setSelectedIndex(0);
    frame.add(petList, BorderLayout.PAGE_END);

    // for this, add a method that is get shape list and then update the the list


    // pack window and make visible
    this.pack();
    this.setVisible(true);
  }

  /**
   * A method to render the shapes at their current state of animation.
   *
   * @param shapes a list of IShapes.
   */
  public void render(List<IShape> shapes) {
    this.drawingCanvas.updateDrawing(shapes);
  }

  /**
   * A method to render the shapes in the animation and their respective commands.
   *
   * @param shapes   list of all shapes in the model
   * @param commands list of all commands in the model
   * @throws UnsupportedOperationException if called in the VisualView
   */
  @Override
  public String textRender(List<IShape> shapes, List<ICommand> commands)
          throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method invalid for visual view");
  }
}
