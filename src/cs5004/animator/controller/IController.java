package cs5004.animator.controller;

public interface IController {

    /**
     * Method to start the animation. For a visual controller the method returns null; however, for
     * a text-based controller, the returned string is then passed to the view.
     *
     * @return a string description of the animation.
     */
    String animate();
}

//TODO: I watched the end of the video and Vido is saying that the view should get the raw data and
//      then make the description. You would need the Shapes and transformations. "If you run out
//      of time, then just pass the string to the view." Pass the arguments to the constructor.
//      he did public TextView(Readable readable, Appendable appendable).
//      TextView textView = new TextView(new StringReader(model.toString(), new StringBuilder());
//      Write a class that takes in a message and outputs the message to JOptionPane.
//      If you look the, the output for this assignment is slightly different..he doesn't care
//      of the format but the text has to be generated in the view.

//TODO: Can we delete this comment?
// set bounds, get bounds to pass to view


// FINAL:
//  He is going to give us a model and ask us to add a functionality. He will see how we do it.
//  it will be similar to the midterm, but he will expect some design patterns in there.
//  You will have to extend interfaces and think of design patterns, be it the command, strategy,
//  adapter pattern, inheritance, or delegation to make the change.
//
//  He will have a review session on Monday, not 3 hrs.
//
//  You need to understand how to do design patterns, especially inheritance or delegation.
//
//  The final will be a system from real-life and easier to understand. There will also be testing
//  on the final.
//
// Order of Importance: Delegation vs Inheritance --> you MUST know that for the final

