package cs5004.animator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import cs5004.animator.model.shape.IShape;

/**
 * A panel class that is a blank canvas on which to draw shapes. This class extends JPanel.
 */
public class CanvasDrawingPanel extends JPanel {
  private List<IShape> shapes;

  /**
   * This is the constructor for CanvasDrawingPanel. It takes in a width and height for the canvas.
   *
   * @param width the width of the canvas
   * @param height the height of the canvas
   */
  public CanvasDrawingPanel(int width, int height) {
    super();
    setPreferredSize(new Dimension(width, height));
    setBackground(Color.WHITE);
    this.shapes = new ArrayList<>();
  }

  /**
   * Paints each of the shapes based on the shapes in the list.
   *
   * @param g the graphics class
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (shapes.size() != 0) {
      Graphics2D graphic2d = (Graphics2D) g;
      for (IShape shape : shapes) {
        graphic2d.setColor(shape.getColor());
        graphic2d.setPaint(shape.getColor());
        shape.drawShape(g);
      }
    }
  }

  /**
   * Updates the drawings with the list of shapes provided.
   *
   * @param shapes the list of shapes
   */
  public void updateDrawing(List<IShape> shapes) {
    this.shapes = shapes;
    this.repaint();
  }
}
