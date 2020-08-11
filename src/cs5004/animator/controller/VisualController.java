package cs5004.animator.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.shape.IShape;
import cs5004.animator.view.IView;

/**
 * The visual controller class. This implements IController and contains the method animate().
 */
public class VisualController implements IController, ActionListener {
  private final Timer timer;
  private final IView view;
  private final AnimatorModel model;
  private int currentFrame;
  private final JFrame fileWindow;

  /**
   * The constructor for the visual controller class.
   *
   * @param model           the model for the animation
   * @param view            the view for the animation
   * @param framesPerSecond the framesPerSecond of the animation
   * @throws IllegalArgumentException if framesPerSecond is less than or equal to 0
   * @throws NullPointerException     if model is null
   */
  public VisualController(AnimatorModel model, IView view, int framesPerSecond)
          throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(model, "Model cannot be null");
    if (framesPerSecond <= 0) {
      throw new IllegalArgumentException("Speed must be greater than 0");
    }

    this.currentFrame = 1;
    this.view = view;
    this.model = model;
    this.fileWindow = new JFrame();
    view.setListener(this);

    int delay = 1000 / framesPerSecond;
    this.timer = new Timer(delay, new Play());
  }

  /**
   * The animation method which produces the result to pass into the view. In this case, it starts
   * the timer.
   */
  @Override
  public void animate() {
    List<IShape> shapes = model.getSnapshot(currentFrame);
    view.setShapeList(model.getShapeList());
    view.render(shapes);
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Play":
        timer.start();
        break;
      case "Pause":
        timer.stop();
        break;
      case "Restart":
        timer.stop();
        this.currentFrame = 1;
        this.view.render(model.getSnapshot(currentFrame));
        break;
      case "Save Text Version":
        this.saveFile();
        break;
      case "Remove Shape":
        if (this.view.getShapeToRemove().equals("Select Shape") && this.model.getShapeList().isEmpty()) {
          JOptionPane.showMessageDialog(this.fileWindow, "There are no more shapes left to remove.");
          break;
        }

        if (this.view.getShapeToRemove().equals("Select Shape")) {
          JOptionPane.showMessageDialog(this.fileWindow, "Select a shape to remove.");
          break;
        }

        try {
          IShape shapeToRemove = model.getShape(this.view.getShapeToRemove());
          model.removeShape(shapeToRemove);
          view.setShapeList(model.getShapeList());
          this.view.render(model.getSnapshot(currentFrame));
          break;
        } catch (IllegalArgumentException IAE) {
          JOptionPane.showMessageDialog(this.fileWindow, "Error removing shape.");
        }
      default:
        break;
    }
  }

  public class Play implements ActionListener {
    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (currentFrame > model.findDuration()) {
        currentFrame = 1;
      }
      List<IShape> shapes = model.getSnapshot(currentFrame);
      view.render(shapes);
      currentFrame++;
    }
  }

  private void saveFile() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));
    if (fileChooser.showSaveDialog(this.fileWindow) == JFileChooser.APPROVE_OPTION) {
      String outText = view.textRender(this.model.getShapeList(), this.model.getCommandList());
      try {
        File outFile = fileChooser.getSelectedFile();
        if (outFile.getName().endsWith(".txt")) {
          // do nothing because file is all set
        } else {
          outFile = new File(outFile.toString() + ".txt");
        }
        FileWriter fileWriter = new FileWriter(outFile);
        fileWriter.write(outText);
        fileWriter.close();
      } catch (IOException IOE) {
        JOptionPane.showMessageDialog(this.fileWindow, "Unable to save file.");
      }
    }
  }
}
