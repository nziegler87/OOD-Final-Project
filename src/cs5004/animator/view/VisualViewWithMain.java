package cs5004.animator.view;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


import cs5004.animator.model.point2d.Point2D;
import cs5004.animator.model.shape.IShape;

public class VisualViewWithMain extends JPanel {
  private JButton playPauseButton, exitButton;
  private List<IShape> shapes;

  public VisualViewWithMain() {
    setBackground(Color.white);

    shapes = new ArrayList<>();

    IShape trashPanda = new cs5004.animator.model.shape.Rectangle("trash-panda", new Point2D(200,200),
            new Color(Color.RED.getRGB()), 50.0, 100.0, 1, 100);

    IShape nateOval = new cs5004.animator.model.shape.Oval("trash-panda", new Point2D(500, 100),
            new Color(Color.BLUE.getRGB()), 60, 30, 1, 100);

    shapes.add(trashPanda);
    shapes.add(nateOval);

  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (shapes.size() != 0) {
      Graphics2D graphic2d = (Graphics2D) g;
      for (IShape shape : shapes) {
        graphic2d.setColor(shape.getColor());
        graphic2d.setPaint(shape.getColor());
        if (shape.getType().equals("rectangle")) {
          graphic2d.fill(new Rectangle2D.Double(shape.getCoordinates().getX(), shape.getCoordinates().getY(), shape.getWidth(), shape.getHeight()));
        } else if (shape.getType().equals("oval")) {
          graphic2d.fill(new Ellipse2D.Double(shape.getCoordinates().getX(), shape.getCoordinates().getY(), shape.getWidth(), shape.getHeight()));
        } else {
          throw new IllegalArgumentException("Shape is not supported");
        }
      }
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    JPanel drawingCanvas = new VisualViewWithMain();
    frame.getContentPane().add(drawingCanvas);
    JScrollPane scrollPane = new JScrollPane(drawingCanvas);
    frame.add(scrollPane);
    frame.setTitle("Test Paint");
    frame.setSize(800, 800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
