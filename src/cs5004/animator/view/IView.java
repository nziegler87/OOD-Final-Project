package cs5004.animator.view;

import java.util.List;

import cs5004.animator.model.shape.IShape;

/**
 * An interface that contains all of the common methods that all implementing classes should
 * contain.
 */
public interface IView {

    /**
     * A method to render the shapes at their current state of animation.
     *
     * @param shapes a list of IShapes.
     */
    void render(List<IShape> shapes);

}
