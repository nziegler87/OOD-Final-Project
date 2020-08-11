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
