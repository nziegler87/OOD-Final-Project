package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

//TODO: Cannot get this to work, so thinking of deleting

public class MenuBar extends JMenuBar {
  private final JMenuItem menuSave, play, pause, restart;

  public MenuBar() {
    super();
    // create a save menu option
    JMenu file = new JMenu("File");
    this.menuSave = new JMenuItem("Save Text Version");
    file.add(menuSave);
    this.add(file);

    //TODO: We can get rid of this just thought it was fun to add duplicate controls
    JMenu playbackControl = new JMenu("Playback Controls");
    this.play = new JMenuItem("Play");
    playbackControl.add(play);
    this.pause = new JMenuItem("Pause");
    playbackControl.add(pause);
    this.restart = new JMenuItem("Restart");
    playbackControl.add(restart);
    this.add(playbackControl);
  }

  public void setListener(ActionListener listener) {
    this.menuSave.addActionListener(listener);
    this.play.addActionListener(listener);
    this.pause.addActionListener(listener);
    this.restart.addActionListener(listener);
  }
}
