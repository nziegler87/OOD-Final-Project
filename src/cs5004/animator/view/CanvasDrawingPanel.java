package cs5004.animator.view;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs5004.animator.model.shape.IShape;

//TODO: it is my preference that this is combined with the visual view, but I am OK leaving if you feel strongly.

/**
 * A panel class that is a blank canvas on which to draw shapes.
 */
public class CanvasDrawingPanel extends JPanel {
  private List<IShape> shapes = new ArrayList<>();

  public CanvasDrawingPanel() {
    super();
    setBackground(Color.BLACK);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (shapes.size() != 0) {
      Graphics2D graphic2d = (Graphics2D) g;
      for (IShape shape : shapes) {
        graphic2d.setColor(shape.getColor());
        graphic2d.setPaint(shape.getColor());
        if (shape.getType().equals("rectangle")) {
          graphic2d.fill(new Rectangle2D.Double(shape.getCoordinates().getX(), shape.getCoordinates().getY(),
                  shape.getWidth(), shape.getHeight()));
        } else if (shape.getType().equals("oval")) {
          graphic2d.fill(new Ellipse2D.Double(shape.getCoordinates().getX(), shape.getCoordinates().getY(),
                  shape.getWidth(), shape.getHeight()));
        } else {
          throw new IllegalArgumentException("Shape is not supported");
        }
      }
    }
  }

  public void updateDrawing(List<IShape> shapes) {
    this.shapes = shapes;
    this.repaint();
  }
}
