package cs5004.animator.view;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs5004.animator.model.commands.ICommand;
import cs5004.animator.model.shape.IShape;

/**
 * The visual view class. This implements IView and contains the method render().
 */
public class VisualView extends JFrame implements IView {
    private final CanvasDrawingPanel drawingCanvas;

    /**
     * The visual view constructor.
     *
     * @param width the width of the canvas
     * @param height the height of the canvas
     */
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

    /**
     * A method to render the shapes at their current state of animation.
     *
     * @param shapes a list of IShapes.
     */
    public void render(List<IShape> shapes) {
        this.drawingCanvas.updateDrawing(shapes);
    }

    /**
     * A method to render the shapes in the animation and their respective commands.
     *
     * @param shapes   a list of IShapes.
     * @param commands
     */
    @Override
    public String textRender(List<IShape> shapes, List<ICommand> commands)
            throws IllegalArgumentException {
        throw new IllegalArgumentException("Method invalid for visual view");
    }
}
