package cs5004.animator.view;

import cs5004.animator.model.shape.IShape;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The text view class. This implements IView and contains the method render().
 */
public class TextView implements IView {

    private final Appendable appendable;

    /**
     * The visual view constructor.
     *
     * @param outfile         the outfile to create and write in
     * @param animationStatus the text output from the model
     */
    public TextView(String outfile, String animationStatus) {
        appendable = new StringBuilder();
        if (outfile.isBlank()) {
            try {
                appendable.append(animationStatus);
            } catch (IOException e) {
                throw new IllegalArgumentException("An error occurred when appending the output.");
            }
        } else {
            try {
                FileWriter myWriter = new FileWriter(outfile);
                myWriter.write(animationStatus);
                myWriter.close();
            } catch (IOException e) {
                throw new IllegalArgumentException("An error occurred when writing the file.");
            }
        }
    }

    @Override
    public void render(List<IShape> shapes) {
        // this does not do anything because the text view does not render any animations
    }

    @Override
    public String toString() {
        return appendable.toString();
    }
}
