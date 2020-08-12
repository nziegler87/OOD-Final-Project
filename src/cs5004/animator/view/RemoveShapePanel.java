package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import cs5004.animator.shape.IShape;

/**
 * A class that is the Remove Shape Panel for the animator GUI.
 */
public class RemoveShapePanel extends JPanel {
  private final JButton removeButton;
  private final JComboBox<String> shapeList;

  /**
   * Creates an instance of a remove shape panel for the GUI.
   */
  public RemoveShapePanel() {
    super();
    this.setBackground(new Color(246, 246, 246));
    this.setLayout(new BorderLayout());

    // add removeShape label
    JLabel removeShape = new JLabel("Remove Shape:");
    this.add(removeShape, BorderLayout.PAGE_START);

    // add JComboBox
    this.shapeList = new JComboBox();
    this.add(shapeList, BorderLayout.CENTER);

    // add removeButton to panel
    this.removeButton = new JButton("Remove Shape");
    this.add(removeButton, BorderLayout.PAGE_END);
  }

  /**
   * Method to add ActionListener to components of screen and pass to the view.
   *
   * @param listener an ActionListener
   */
  public void setListener(ActionListener listener) {
    this.removeButton.addActionListener(listener);
  }

  /**
   * Method to update the comobo box with a shape list.
   *
   * @param shapes a list of IShapes
   */
  public void updateShapeList(List<IShape> shapes) {
    this.shapeList.removeAllItems();
    this.shapeList.addItem("Select Shape");

    for (IShape shape : shapes ) {
      this.shapeList.addItem(shape.getLabel());
    }
  }

  /**
   * Method to return the selected item in the combo box.
   *
   * @return the selected item in the combo box, a string.
   */
  public String getShapeSelection() {
    return (String) this.shapeList.getSelectedItem();
  }
}
