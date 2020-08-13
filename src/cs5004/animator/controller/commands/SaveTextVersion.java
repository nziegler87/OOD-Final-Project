package cs5004.animator.controller.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import cs5004.animator.controller.IController;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.IView;

/**
 * Class that represents a command to save a text version of the animation.
 */
public class SaveTextVersion implements AnimationControllerCommands {
  JFrame popUpWindow;

  /**
   * Creates an instance of SaveTextVersion().
   */
  public void SaveTextVersion(){
    this.popUpWindow = new JFrame();
  }

  /**
   * Method that asks the user to pick their file save location and name. Then, uses the view's
   * textRender() method to get a description of the text, which is saved to a file.
   *
   * @param model an AnimatorModel object
   * @param view an IView object
   * @param controller and IController object.
   * @throws NullPointerException if model, view, or controller are null.
   */
  @Override
  public void go(AnimatorModel model, IView view, IController controller)
          throws NullPointerException {
    Objects.requireNonNull(model, "Model cannot be null.");
    Objects.requireNonNull(view, "View cannot be null.");
    Objects.requireNonNull(controller, "Controller cannot be null.");

    JFileChooser fileChooser = new JFileChooser();
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));

    if (fileChooser.showSaveDialog(popUpWindow) == JFileChooser.APPROVE_OPTION) {
      String outText = view.textRender(model.getShapeList(), model.getCommandList());
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
      }
      catch (IOException IOE) {
        JOptionPane.showMessageDialog(popUpWindow, "Unable to save file.");
      }
    }
    else {
      // do nothing
    }
  }
}
