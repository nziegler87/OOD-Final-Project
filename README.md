# CS5004_EasyAnimator

Per the assignment, we have designed our model with the following specifications:

- The model needs to be scalable and support various kinds of 2D shapes:

    - We created an IShape interface, put common code in an AbstractShape class and then implemented
    two concrete classes: Oval and Rectangle. This design allows someone to add additional shapes
    by adding new concrete classes.
    - We utilized Java's built-in Color class to store each shape's color.
    - We wrote a Point2D class to store location information as x, y coordinates.

- The model should support various kinds of animations to shapes:
    - Moving
    - Changing color
    - Changing shape
- The model must be designed with the client's perspective in mind

To achieve these criteria we decided to abstract out most the functionality of the model