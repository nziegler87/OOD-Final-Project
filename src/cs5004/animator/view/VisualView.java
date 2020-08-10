package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;
import javax.swing.JMenuBar;

import cs5004.animator.model.commands.ICommand;
import cs5004.animator.shape.IShape;

/**
 * The visual view class. This implements IView and contains the method render().
 */
public class VisualView extends JFrame implements IView {

  private final CanvasDrawingPanel drawingCanvas;

//  private final ActionButtons controlButtons;
  private final JButton play;
  private final JButton pause;
  private final JButton restart;
  private JMenuBar bar;
  private JMenu menu;
  private JMenuItem menuSave;
//  private final JComboBox<String> animationList;
  //private final JComboBox<String> removeShapes;



  private List<IShape> modifyShapes;
  private HashMap<String, IShape> removedShapes;

  /**
   * The visual view constructor.
   *
   * @param width  the width of the canvas
   * @param height the height of the canvas
   */
  public VisualView(int width, int height) {
    // call JFrame constructor
    super();

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

    this.createMenuBar();

    String[] textFiles = {"buildings.txt", "big-bang-big-crunch.txt", "hanoi.txt", "smalldemo.txt",
            "toh-3.txt", "toh-5.txt", "toh-8.txt", "toh-12.txt"};

//    this.controlButtons = new ActionButtons(); // TODO: If we use a class such as this, we can decouple editing the class from the view

    // add top menu bar with buttons
    JPanel menuBar = new JPanel();
    menuBar.setLayout(new GridLayout(0, 6));

    // add the buttons to the menu
    this.play = new JButton("Play"); //TODO: Comment out this if we use the class
    this.pause = new JButton("Pause");
    this.restart = new JButton("Restart");
//    this.animationList = new JComboBox(textFiles);

    //this.removeShapes = new JComboBox(removedShapes.keySet());
    //List<IShape> remove = removedShapes.keySet();

    // add the buttons and such
    menuBar.add(play);
    menuBar.add(pause);
    menuBar.add(restart);
//    menuBar.add(animationList); //TODO: Uncomment this but comment out the above if we use the class
    //menuBar.add(removeShapes);

    frame.add(menuBar, BorderLayout.PAGE_START);
//    frame.add(controlButtons, BorderLayout.PAGE_START);

    // for this, add a method that is get shape list and then update the the list
    // save removed shapes in a list here... then use that list with removeShapes(shapes)

    // pack window and make visible
    this.pack();
    this.setVisible(true);
  }

/*  public String getFile() {

  }*/

  @Override
  public void setListener(ActionListener listener) {
    this.play.addActionListener(listener);
    this.pause.addActionListener(listener);
    this.restart.addActionListener(listener);
//    this.controlButtons.setListener(listener); //TODO: Uncomment this and comment above for the action buttons
    this.menuSave.addActionListener(listener);
    //this.animationList.addActionListener(listener) {
      /*public void actionPerformed (ActionEvent e){ // TODO: how do you pass this?
        if (animationList.getSelectedIndex() != -1) {
          String file = animationList.getItemAt(animationList.getSelectedIndex());
        }
      }*/
  }

  /**
   * Helper method to remove the designated shapes from the list.
   *
   * @param shapes the shapes to search through
   * @return the list of modified shapes
   */
  private List<IShape> removeShapes(List<IShape> shapes) {
    for (IShape shape : shapes) {
      if (!this.removedShapes.containsKey(shape.getLabel())) {
        modifyShapes.add(shape);
      }
    }
    return modifyShapes;
  }

  /**
   * A method to render the shapes at their current state of animation.
   *
   * @param shapes a list of IShapes.
   */
  public void render(List<IShape> shapes) {
    // TODO: eventually pass in removeShapes(shapes))
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

  private void createMenuBar() {
    // create a save menu option
    this.menu = new JMenu("File");
    this.menuSave = new JMenuItem("Save Text Version");
    this.menu.add(this.menuSave);
    this.bar = new javax.swing.JMenuBar();
    this.bar.add(this.menu);
    this.setJMenuBar(this.bar);
  }
}
