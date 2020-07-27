# CS5004_EasyAnimator

Per the assignment, we have designed our model with the following specifications:

- The model needs to be scalable and support various kinds of 2D shapes:
- The model should support various kinds of animations to shapes:
    - Moving
    - Changing color
    - Changing shape
- The model must be designed with the client's perspective in mind

Since we figured this assignment would later require us to expand on the animation functionality,
we divided up the project into different buckets of functionality (also physically divided by 
packages). Within each realm of functionality we included an interface and multiple classes, both
concrete and abstract. More specifically:

- Regarding shapes:
    - We created an IShape interface, put common code in an AbstractShape class and then implemented 
    two concrete classes: Oval and Rectangle. 
    - This design allows someone to add additional shapes by adding new concrete classes.
    - Utilized Java's built-in Color class to store each shape's color.
 - Regarding coordinates: 
    - Created a IPoint2D interface and Point2D class to store location information as x, y 
    coordinates.
 - Regarding commands:
    - We created an ICommand interface, put common code in the AbstractCommand class and then
    implemented concrete classes for the move, scale, and change color commands required in the 
    assignment. 
    - Each command has two functions: execute(IShape shape) and toString(); these functions
    will allow us to execute the commands specific to the ICommand class, as well as provide
    a specific text description like the examples provided in the assignment.
    - We did it this way so that we can add on additional commands as needed by adding a new 
    concrete class for them.
  - Regarding command lists:
    - We created an ICommandList interface with a few methods to control the descriptive list 
    of actions that will be stored as a history log for the animations. 
    - We decided to use a stack to store these text commands as it would allow us to continually
    push back the functions, retaining a chronologically accurate list.
  - Regarding the model:
    - Our model had the most design implementation strategy in it. We prioritized clean
     non-repetitive code, with an emphasis on adaptability. 
    - The model is the home of both the shape and command list. The shape list keeps track of the
     shapes in the animation, and the command list contains a chronological list of directive 
     commands. 
    - Since we had put the commands into classes we were able to shorten the number of methods 
    in our model referencing the commands to just one: commandOnShape. This takes in a command 
    class and shape, and uses the command.execute(shape) function to actually take the command.

