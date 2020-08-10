package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ActionButtons extends JPanel {
  JButton play;
  JButton pause;
  JButton restart;

  public ActionButtons() {
    super();
    GridLayout experimentLayout = new GridLayout(0, 3);
    this.setLayout(experimentLayout);
    this.play = new JButton("Play");
    this.pause = new JButton("Pause");
    this.restart = new JButton("Restart");
    this.add(play);
    this.add(pause);
    this.add(restart);
  }

  public void setListener(ActionListener listener) {
    this.play.addActionListener(listener);
    this.pause.addActionListener(listener);
    this.restart.addActionListener(listener);
  }
}

/*

package cs5004.animator.view;

        import java.awt.*;
        import java.awt.event.ActionListener;
        import java.util.HashMap;
        import java.util.List;

        import javax.swing.*;

        import cs5004.animator.model.commands.ICommand;
        import cs5004.animator.shape.IShape;

*/
/**
 * The visual view class. This implements IView and contains the method render().
 * <p>
 * The visual view constructor.
 *
 * @param width  the width of the canvas
 * @param height the height of the canvas
 * <p>
 * Helper method to remove the designated shapes from the list.
 * @param shapes the shapes to search through
 * @return the list of modified shapes
 * <p>
 * A method to render the shapes at their current state of animation.
 * @param shapes a list of IShapes.
 * <p>
 * A method to render the shapes in the animation and their respective commands.
 * @param shapes   list of all shapes in the model
 * @param commands list of all commands in the model
 *//*

public class VisualView extends JFrame implements IView {
  private final CanvasDrawingPanel drawingCanvas;

  private final JButton play;
  private final JButton pause;
  private final JButton restart;
  private final JButton save;
  private final JComboBox<String> animationList;
  private final JComboBox<String> removeShapes;

  private JMenu menu;
  private JMenuItem menuSave;

  private java.util.List<IShape> modifyShapes;
  private HashMap<String, IShape> removedShapes;

  */
/**
 * The visual view constructor.
 *
 * @param width  the width of the canvas
 * @param height the height of the canvas
 *//*

  public VisualView(int width, int height) {

    // call JFrame constructor
    super();

    // create a CanvasDrawingPanel object
    drawingCanvas = new CanvasDrawingPanel(width, height);

    // create menu bar
    MenuBar bar = new MenuBar();
    JMenu menu = new JMenu("File");
    bar.add(menu);
    JMenuItem menuSave = new JMenuItem("Save Text Version");
    menu.add(menuSave);
    this.setJMenuBar(bar);

    // create scroll bars on panel and add to frame
    Container frame = this.getContentPane();
    frame.add(new JScrollPane(drawingCanvas), BorderLayout.CENTER);
    frame.add(bar, BorderLayout.PAGE_START);

    // set default state of window
    this.setTitle("Easy Animator Visual Display");
    this.setSize(500, 500);
    this.setBackground(Color.lightGray);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    String[] textFiles = {"buildings.txt", "big-bang-big-crunch.txt", "hanoi.txt", "smalldemo.txt",
            "toh-3.txt", "toh-5.txt", "toh-8.txt", "toh-12.txt"};

    // add top menu bar with buttons. Should this be a class?
    JPanel menuBar = new JPanel();
    menuBar.setLayout(new GridLayout(0, 6));
    menuBar.add(play = new JButton("Play"));
    menuBar.add(pause = new JButton("Pause"));
    menuBar.add(restart = new JButton("Restart"));
    menuBar.add(save = new JButton("Save"));
    menuBar.add(animationList = new JComboBox<>(textFiles));
    menuBar.add(removeShapes = new JComboBox<>(removedShapes.keySet().toArray(new String[0])));

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
    this.animationList.addActionListener(listener);
    this.removeShapes.addActionListener(listener);
  }

  */
/**
 * Helper method to remove the designated shapes from the list.
 *
 * @param shapes the shapes to search through
 * @return the list of modified shapes
 *//*

  private java.util.List<IShape> removeShapes(java.util.List<IShape> shapes) {
    for (IShape shape : shapes) {
      if (!this.removedShapes.containsKey(shape.getLabel())) {
        modifyShapes.add(shape);
      }
    }
    return modifyShapes;
  }

  */
/**
 * A method to render the shapes at their current state of animation.
 *
 * @param shapes a list of IShapes.
 *//*

  public void render(java.util.List<IShape> shapes) {
    this.drawingCanvas.updateDrawing(removeShapes(shapes));
  }

  */
/**
 * A method to render the shapes in the animation and their respective commands.
 *
 * @param shapes   list of all shapes in the model
 * @param commands list of all commands in the model
 *//*

  @Override
  public String textRender(java.util.List<IShape> shapes, List<ICommand> commands) {
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
*/

