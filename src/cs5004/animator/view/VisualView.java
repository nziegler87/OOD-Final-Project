package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.AncestorListener;

import cs5004.animator.model.commands.ICommand;
import cs5004.animator.shape.IShape;

/**
 * The visual view class. This implements IView and contains the method render().
 */
public class VisualView extends JFrame implements IView {
  private List<IShape> shapeList;

  private final CanvasDrawingPanel drawingCanvas;

  private final ActionButtons controlButtons;
  private final MenuBar bar;
  private final RemoveShapePanel removeShapePanel;

  /**
   * The visual view constructor.
   *
   * @param width  the width of the canvas
   * @param height the height of the canvas
   */
  public VisualView(int width, int height) {
    // call JFrame constructor
    super();

    this.shapeList = new ArrayList<>();

    // create a CanvasDrawingPanel object
    Container frame = this.getContentPane();
    drawingCanvas = new CanvasDrawingPanel(width, height);

    // create scroll bars on panel and add to frame
    JScrollPane scrollPane = new JScrollPane(drawingCanvas);
    frame.add(scrollPane, BorderLayout.CENTER);

    // set default state of window
    setTitle("Easy Animator Visual Display");
    setSize(500, 500);
    setBackground(Color.lightGray);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // add menu bar
    this.bar = new MenuBar();
    this.setJMenuBar(this.bar);

    // add play, pause, restart buttons
    this.controlButtons = new ActionButtons();
    frame.add(controlButtons, BorderLayout.PAGE_START);

    // add remove shape panel
    this.removeShapePanel = new RemoveShapePanel();
    JScrollPane removeShapeScrollPane = new JScrollPane(this.removeShapePanel);
    frame.add(removeShapeScrollPane, BorderLayout.LINE_END);

    // pack window and make visible
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void setListener(ActionListener listener) {
    this.controlButtons.setListener(listener);
    this.bar.setListener(listener);
    this.removeShapePanel.setListener(listener);
  }

  public void setShapeList(List<IShape> shapes) {
    this.shapeList = shapes;
    this.removeShapePanel.updateShapeComboBox(this.shapeList);
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
   */
  @Override
  public String textRender(List<IShape> shapes, List<ICommand> commands) {
    if (shapes.size() == 0) {
      return "No shapes have been added to the inventory, so no animations can be displayed";
    }

    StringBuilder status = new StringBuilder();
    status.append("Shapes:\n");

    for (IShape shape : shapes) {
      status.append(shape.toString()).append("\n\n");
    }

    if ((commands.size() != 0)) {
      for (ICommand command : commands) {
        status.append(command.toString());
      }
    } else {
      status.append("Command list is empty.");
    }

    return status.substring(0, status.length() - 1);
  }

  public String getShapeToRemove() {
    return this.removeShapePanel.getComboBoxSelection();
  }
}
