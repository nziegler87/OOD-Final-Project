package cs5004.animator.view;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs5004.animator.model.shape.IShape;

public class VisualView extends JFrame implements IView {
  private final CanvasDrawingPanel drawingCanvas;

  public VisualView(int width, int height) {
    // call JFrame constructor
    super();

    // set default state of window
    this.setTitle("Easy Animator Visual Display");
    this.setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create a CanvasDrawingPanel object
    drawingCanvas = new CanvasDrawingPanel(width, height);

    // create scroll bars on panel and add to frame
    JScrollPane scrollPane = new JScrollPane(drawingCanvas);
    this.add(scrollPane);

    // pack window and make visible
    this.pack();
    this.setVisible(true);
  }

  public void render(List<IShape> shapes) {
    this.drawingCanvas.updateDrawing(shapes);
  }
}
