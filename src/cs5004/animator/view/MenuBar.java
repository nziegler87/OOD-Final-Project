package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * A class that for the menu bar in our frame.
 *
 */
public class MenuBar extends JMenuBar {
  private final JMenuItem menuSave, play, pause, restart;

  /**
   * Creates an instance of our menu bar.
   */
  public MenuBar() {
    super();
    // create a save menu option
    JMenu file = new JMenu("File");
    this.menuSave = new JMenuItem("Save Text Version");
    file.add(menuSave);
    this.add(file);

    JMenu playbackControl = new JMenu("Playback Controls");
    this.play = new JMenuItem("Play");
    playbackControl.add(play);
    this.pause = new JMenuItem("Pause");
    playbackControl.add(pause);
    this.restart = new JMenuItem("Restart");
    playbackControl.add(restart);
    this.add(playbackControl);
  }

  /**
   * Method to set Controller as listener.
   *
   * @param listener an Action listener.
   */
  public void setListener(ActionListener listener) {
    this.menuSave.addActionListener(listener);
    this.play.addActionListener(listener);
    this.pause.addActionListener(listener);
    this.restart.addActionListener(listener);
  }
}
