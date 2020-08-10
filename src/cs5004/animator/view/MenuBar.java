package cs5004.animator.view;

import java.awt.event.ActionListener;

import javax.swing.*;

//TODO: Cannot get this to work, so thinking of deleting

public class MenuBar extends JMenuBar {
  private JMenu file, playbackControl;
  private JMenuItem menuSave, play, pause, restart;


  public MenuBar() {
    super();
    // create a save menu option
    this.file = new JMenu("File");
    this.menuSave = new JMenuItem("Save Text Version");
    this.file.add(menuSave);
    this.add(file);

    //TODO: We can get rid of this just thought it was fun to add duplicate controls
    this.playbackControl = new JMenu("Playback Controls");
    this.play = new JMenuItem("Play");
    this.playbackControl.add(play);
    this.pause = new JMenuItem("Pause");
    this.playbackControl.add(pause);
    this.restart = new JMenuItem("Restart");
    this.playbackControl.add(restart);
    this.add(playbackControl);

  }

  public void setListener(ActionListener listener) {
    this.menuSave.addActionListener(listener);
    this.play.addActionListener(listener);
    this.pause.addActionListener(listener);
    this.restart.addActionListener(listener);
  }
}
