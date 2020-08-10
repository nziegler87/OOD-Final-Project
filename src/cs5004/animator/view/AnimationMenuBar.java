package cs5004.animator.view;

import javax.swing.*;

//TODO: Cannot get this to work, so thinking of deleting

public class AnimationMenuBar extends JMenuBar {
  private javax.swing.JMenuBar bar;
  private JMenu menu;
  private JMenuItem menuSave;


  public AnimationMenuBar() {
    super();
    // create a save menu option
    this.menu = new JMenu("File");
    menuSave = new JMenuItem("Save Text Version");
    menu.add(menuSave);
    // create menu bar
    this.bar = new JMenuBar();
    this.bar.add(menu);
  }

  public JMenuBar returnMenuBar() {
    return this;
  }
}
