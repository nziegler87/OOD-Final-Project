package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs5004.animator.model.commands.ICommand;
import cs5004.animator.shape.IShape;

/**
 * The visual view class. This implements IView and contains the method render().
 */
public class VisualView extends JFrame implements IView {
  private final CanvasDrawingPanel drawingCanvas;
  private final JButton play;
  private final JButton pause;
  private final JButton restart;
  private final JButton save;
  private JMenuBar menuBar;
  private JMenu menu, submenu;
  JMenuItem menuSave;

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

    // set default state of window
    this.setTitle("Easy Animator Visual Display");
    this.setSize(500, 500);
    this.setBackground(Color.lightGray);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create menu bar
    menuBar = new JMenuBar();
    menu = new JMenu("File");
    menuBar.add(menu);
    JMenuItem saveFile = new JMenuItem("Save Text Version");
    menu.add(saveFile);

    this.setJMenuBar(menuBar);

    // create a CanvasDrawingPanel object
    drawingCanvas = new CanvasDrawingPanel(width, height);

    // create scroll bars on panel and add to frame
    JScrollPane scrollPane = new JScrollPane(drawingCanvas);
    frame.add(scrollPane, BorderLayout.CENTER);

    // add top menu bar with buttons. Should this be a class?
    JPanel menuBar = new JPanel();
    GridLayout experimentLayout = new GridLayout(0,4);
    menuBar.setLayout(experimentLayout);
    this.play = new JButton("Play");
    this.pause = new JButton("Pause");
    this.restart = new JButton("Restart");
    this.save = new JButton("Save");
    menuBar.add(play);
    menuBar.add(pause);
    menuBar.add(restart);
    menuBar.add(save);

    frame.add(menuBar, BorderLayout.PAGE_START);

    // for this, add a method that is get shape list and then update the the list


    // pack window and make visible
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void setListener(ActionListener listener) {
    this.play.addActionListener(listener);
    this.pause.addActionListener(listener);
    this.restart.addActionListener(listener);
    this.save.addActionListener(listener);
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
}
