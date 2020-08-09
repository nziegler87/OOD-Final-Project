package cs5004.animator.view;

import java.awt.*;

import javax.swing.*;

public class MenuBar extends JPanel {

  public MenuBar() {
    GridLayout experimentLayout = new GridLayout(0,3);
    this.setLayout(experimentLayout);
    JButton play = new JButton("Play");
    JButton pause = new JButton("Pause");
    JButton open = new JButton("Open");
    this.add(play);
    this.add(pause);
    this.add(open);

  }
}
