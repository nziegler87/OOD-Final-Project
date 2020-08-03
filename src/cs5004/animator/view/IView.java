package cs5004.animator.view;

import java.util.List;

import cs5004.animator.model.shape.IShape;

public interface IView {

    void render(List<IShape> shapes);

}
