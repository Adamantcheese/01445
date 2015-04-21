/***************************************************************
* file: Circle.java
* author: Jacob Buchowiecki
* class: CS 445 – Computer Graphics
*
* assignment: Program 1
* date last modified: 4/6/2015
*
* purpose: This class extends Shape, and is capable of drawing a circle with OpenGL.
****************************************************************/
import static org.lwjgl.opengl.GL11.*;

public class Circle implements Shape {
    
    private int centerX;
    private int centerY;
    private int radius;
    
    //method: constructor
    //purpose: Builds this circle object.
    public Circle(int cx, int cy, int r) {
        centerX = cx;
        centerY = cy;
        radius = r;
    }

    //method: render
    //purpose: Provide a way to render this circle object. Zero length radius
    //cirles are not rendered.
    @Override
    public void render() {
        glColor3f(0.0f, 0.0f, 1.0f);
        
        if(radius == 0) {
            return;
        }
        
        glBegin(GL_POINTS);
        for(double i = 0.0; i < 360; i += .01) {
            double x = radius*Math.cos(Math.toRadians(i)) + centerX;
            double y = radius*Math.sin(Math.toRadians(i)) + centerY;
            
            glVertex2f((float) x, (float) y);
        }
        glEnd();
    }

    //method: toString
    //purpose: Provides a human-radable representation of this object.
    @Override
    public String toString() {
        String ret = "Center: " + centerX + ", " + centerY + "\nRadius: " + radius;
        return ret;
    }
}
