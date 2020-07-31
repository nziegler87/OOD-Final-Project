# CS5004_EasyAnimator

Per the assignment, we designed our model with the following specifications: the model needs 
to be scalable and support various kinds of 2D shapes and various kinds of animations to shapes, 
including moving, changing color, and changing size.

Since we figured this assignment would later require us to expand on the original set of functions,
we divided up the model's functions into different buckets (these are physically represented by 
packages). Within each "bucket" we included an interface and multiple classes - relying on an
abstract class to factor out any common functionality between concrete classes. More specifically:

- Model bucket:
    - In our model we prioritized clean, non-repetitive code, with an emphasis on adaptability. 
    - Our model bucket has two interfaces, AnimatorModel and AnimatorModelIViewOnly, along with one
    concrete class: AnimationModelImpl. We decided to use two interfaces to divide up 
    functionality between the methods that are read-only and other. 
    - To make a working animation software, we decided to include several basic methods such as 
    addShape, removeShape, addAnimation, and removeAnimation; as these modify the model, they are 
    all listed in the regular interface. Methods included in the read-only interface are getSnapshot 
    and getAnimationStatus, as they produce text outputs and do not modify anything in the model.
    - In the concrete class, AnimatorModelImpl, the model creates the basis for the animation: the
    inventory, a HashMap<String, IShape>, and commandHistory, an ArrayList<ICommand>.
    - We used a HashMap to store shapes as it allows one to associate an easy-to-remember string 
    label with a shape object.
    - We used an ArrayList of commands to store all commands that have been added to the model.
    Using an array list allows us to easily iterate through the commands for output and for
    determining which commands need to be executed on the respective shapes
    - Every time the user adds or removes an animation, the command list is sorted with a helper 
    comparator function. This ensures the command list is always in chronological order and ready 
    for output. 
    - Since we had put the commands into classes we were able to shorten the number of methods 
    in our model referencing the commands to just one: commandOnShape. This takes in a command 
    class and shape, and uses the command.execute() function to actually execute the command.
    One can easily add a new command by adding a new class and would not have to edit the
    model code.

- Shapes bucket:
    - We created an IShape interface, an AbstractShape class and two concrete classes: Oval and 
    Rectangle. 
    - Each shape stored common attributes in the Abstract class which implements the interface and 
    is passed into and expanded upon in the relevant concrete class. Common attributes include
    label, coordinates, color, appear time, and disappear time. 
    - Special attributes provided for the Rectangle class include width and height. 
    - Special attributes provided for the Oval class include xRadius and yRadius. 
    - All attributes are obtainable from the model with getters and setters. Other functions
    include outputting a string description of the shape and returning a copy. Additionally,
     we chose to override the .equals() method to verify if two objects are the same. 
    - This design allows someone to add additional shapes by adding new concrete classes that 
    extend the abstract class and inherit basic properties, whilst having functionality separate
    from the other concrete classes.
    - Other note: we decided to utilize Java's built-in Color class to store each shape's color. As
    part of this, we did not override the getRed, getGreen, getBlue toString() methods as they take
    in and are stored as ints. We made an executive decision not to format those as doubles for the
    output, so you will find them different than the example provided in the assignment.
    
 - Command bucket:
    - We created an ICommand interface, an AbstractCommand class and three concrete classes: move, 
    scale, and change color.
    - To reduce duplicate code, we placed getters in the Abstract class which enables us to pull 
    information and perform granular comparisons between command types. 
    - Additionally, each command has four functions: execute(), toString(), equals(), and 
    hashCode(). These functions allow us to modify the code specific to the ICommand class.
    - Similar to the Shape classes, we decided to do it this way so that we can simply add any
    additional commands as needed by extending the Abstract class and modifying the independent 
    methods.
    
 - Coordinate bucket: 
    - We created a IPoint2D interface and Point2D class to store location information as x, y 
    coordinates.
    - The Point2D class contains methods that get and set coordinates, as well as outputting a
    string. Additionally, we overrode the .equals() method, so we could do an equal comparison of 
    two coordinates.
    
Below is a UML of our model:
![UML Model](https://github.ccs.neu.edu/tolliverdanielle/CS5004_EasyAnimator/blob/master/A9%20UML.png)