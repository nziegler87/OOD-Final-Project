package cs5004.animator.controller;

public interface IController {

    /**
     * The animation method which produces the result to pass into the view.
     * @return
     */
    Appendable animate();

    // set bounds, get bounds to pass to view

}
