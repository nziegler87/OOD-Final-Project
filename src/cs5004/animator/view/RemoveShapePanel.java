package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import cs5004.animator.shape.IShape;

public class RemoveShapePanel extends JPanel {
  private final JButton removeButton;
  private final JComboBox<String> shapeList2;

  public RemoveShapePanel() {
    super();
    this.setBackground(new Color(246, 246, 246));
    this.setLayout(new BorderLayout());

    // add removeShape label
    JLabel removeShape = new JLabel("Remove Shape:");
    this.add(removeShape, BorderLayout.PAGE_START);

    // add JComboBox
    this.shapeList2 = new JComboBox();
    this.add(shapeList2, BorderLayout.CENTER);

    // add removeButton to panel
    this.removeButton = new JButton("Remove Shape");
    this.add(removeButton, BorderLayout.PAGE_END);
  }

  public void setListener(ActionListener listener) {
    this.removeButton.addActionListener(listener);
  }

  public void updateShapeList(List<IShape> shapes) {
    this.shapeList2.removeAllItems();
    this.shapeList2.addItem("Select Shape");

    for (IShape shape : shapes ) {
      this.shapeList2.addItem(shape.getLabel());
    }
  }

  public String getShapeSelection() {
    return (String) this.shapeList2.getSelectedItem();
  }
}
