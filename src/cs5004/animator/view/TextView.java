package cs5004.animator.view;

import cs5004.animator.model.shape.IShape;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The text view class. This implements IView and contains the method render().
 */
public class TextView implements IView {

    private final Appendable appendable;

    /**
     * The visual view constructor.
     *
     * @param outfile the outfile to create and write in
     * @param shapes  the shapes to output from the model
     */
    public TextView(String outfile, ArrayList<IShape> shapes) {

        this.appendable = System.out;

        // if there is not outfile, send to System.out
        if (outfile.isBlank()) {
            try {
                appendable.append(//shapes stuff);
            } catch (IOException e) {
                throw new IllegalArgumentException("An error occurred when appending the output.");
            }
        } else {
            try {
                // else try to write to the outfile
                FileWriter myWriter = new FileWriter(outfile);
                myWriter.write(//shapes stuff to string);
                myWriter.close();
            } catch (IOException e) {
                throw new IllegalArgumentException("An error occurred while making and/or writing to the new file.");
            }
        }
    }

    /**
     * This is the render method, which in the text view does not return anything.
     *
     * @param shapes but in this case these are not utilized for anything
     */
    @Override
    public void render(List<IShape> shapes) {
        // this does not do anything because the text view does not render any animations
    }

    //TODO: if the default is system.out how do we test? Should we convert to string? (started that process below)

/*    *//**
     * The toString method overwrites the original toString to produce the relevant string for output. This is either
     * put into a newly created file or passed into System.out (the default).
     *
     * @return a string with the entire animation status for output
     *//*
    @Override
    public String toString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(baos);
        System.setOut(System.out);
        return baos.toString();
    }*/
}
