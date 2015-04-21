/***************************************************************
* file: Shape.java
* author: Jacob Buchowiecki
* class: CS 445 – Computer Graphics
*
* assignment: Program 1
* date last modified: 4/3/2015
*
* purpose: This interface contains the definitions of methods for different shapes.
****************************************************************/
public interface Shape {
    //method: render
    //purpose: Requires the given shape to provide some way of rendering itself.
    public void render();
    //method: toString
    //purpose: Requires the object to provide a toString method for debugging.
    public String toString();
}
