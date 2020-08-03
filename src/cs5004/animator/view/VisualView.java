package cs5004.animator.view;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs5004.animator.model.shape.IShape;

public class VisualView extends JFrame implements IView {
  CanvasDrawingPanel drawingCanvas;

  public VisualView() {
    // call JFrame constructor
    super();

    // set default state of window
    this.setTitle("Easy Animator Visual Display");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create a CanvasDrawingPanel object
    drawingCanvas = new CanvasDrawingPanel();

    // create scroll bars on panel and add to frame
    this.add(new JScrollPane(this.drawingCanvas,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));

    // pack window and make visible
    this.pack();
    this.setVisible(true);
  }

  public void render(List<IShape> shapes) {
    this.drawingCanvas.updateDrawing(shapes);
  }
}
