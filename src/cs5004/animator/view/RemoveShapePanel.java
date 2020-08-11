package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import cs5004.animator.shape.IShape;

public class RemoveShapePanel extends JPanel {
  private final JButton removeButton;
  private final JComboBox<String> shapeComboBox;

  public RemoveShapePanel() {
    super();
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    this.setBackground(new Color(246, 246, 246));
    this.setPreferredSize(new Dimension(200, 200));

    this.add(new JLabel("Remove Shapes:"));

    this.shapeComboBox = new JComboBox<>();
    this.setAlignmentY(CENTER_ALIGNMENT);
    this.setAlignmentX(TOP_ALIGNMENT);
    this.add(this.shapeComboBox);

    this.removeButton = new JButton("Remove Shape");
    this.setAlignmentY(CENTER_ALIGNMENT);
    this.setAlignmentX(TOP_ALIGNMENT);

    this.add(removeButton);
  }

  public void setListener(ActionListener listener) {
    this.removeButton.addActionListener(listener);
  }

  public void updateShapeComboBox(List<IShape> shapes) {
    this.shapeComboBox.removeAllItems();
    this.shapeComboBox.addItem("Select Shape");
    for (IShape shape : shapes ) {
      this.shapeComboBox.addItem(shape.getLabel());
    }
  }

  public String getComboBoxSelection() {
    return (String) this.shapeComboBox.getSelectedItem();
  }
}
