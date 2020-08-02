package cs5004.animator.controller;

public interface IController {

    void setBounds();

    void getBounds();

    void play();

    void pause();

    void speed();

    void trackTime();

    void restartAnimation();

    // set bounds, get bounds to pass to view
    // play pause
    // speed
    // track time
    // restart
}
