package cs5004.animator.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.IView;

public class SaveTextVersion implements AnimationControllerCommands {
  JFrame popUpWindow;

  public void SaveTextVersion(){
    this.popUpWindow = new JFrame();
  }

  @Override
  public void go(AnimatorModel model, IView view, IController controller) {
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
      } catch (IOException IOE) {
        JOptionPane.showMessageDialog(popUpWindow, "Unable to save file.");
      }
    }
    else {
      // do nothing
    }
  }
}
