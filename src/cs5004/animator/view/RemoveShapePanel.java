package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import cs5004.animator.shape.IShape;

public class RemoveShapePanel extends JPanel {
  private final JButton removeButton;
  private final JList<String> shapeList;
  private final DefaultListModel<String> model;

  public RemoveShapePanel() {
    super();
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    this.setBackground(new Color(246, 246, 246));
    this.setAlignmentY(CENTER_ALIGNMENT);
    this.model = new DefaultListModel();

    // add removeShape label
    JLabel removeShape = new JLabel("Remove Shapes:");
    this.add(removeShape);

    // add JList to panel
    this.shapeList = new JList<>(this.model);
    this.shapeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    JScrollPane listWithScroll = new JScrollPane();
    listWithScroll.setViewportView(this.shapeList);
    this.shapeList.setLayoutOrientation(JList.VERTICAL);
    this.add(listWithScroll);

    // add removeButton to panel
    this.removeButton = new JButton("Remove Shape");
    this.add(removeButton);
  }

  public void setListener(ActionListener listener) {
    this.removeButton.addActionListener(listener);
  }

  public void updateShapeList(List<IShape> shapes) {
    this.model.removeAllElements();

    for (IShape shape : shapes ) {
      this.model.addElement(shape.getLabel());
    }
  }

  public List<String> getShapeSelection() {
    return this.shapeList.getSelectedValuesList();
  }
}
