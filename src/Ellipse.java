/***************************************************************
* file: Ellipse.java
* author: Jacob Buchowiecki
* class: CS 445 – Computer Graphics
*
* assignment: Program 1
* date last modified: 4/6/2015
*
* purpose: This class extends Shape, and is capable of drawing a ellipse with OpenGL.
****************************************************************/
import static org.lwjgl.opengl.GL11.*;

public class Ellipse implements Shape {
    
    private int centerX;
    private int centerY;
    private int radiusX;
    private int radiusY;
    
    //method: constructor
    //purpose: Builds this ellipse object.
    public Ellipse(int cx, int cy, int rx, int ry) {
        centerX = cx;
        centerY = cy;
        radiusX = rx;
        radiusY = ry;
    }

    //method: render
    //purpose: Provide a way to render this ellipse object. Zero length radii
    //ellipses are not rendered.
    @Override
    public void render() {
        glColor3f(0.0f, 1.0f, 0.0f);
        
        if(radiusX == 0 && radiusY == 0) {
            return;
        }
        
        glBegin(GL_POINTS);
        for(double i = 0.0; i < 360; i += .01) {
            double x = radiusX*Math.cos(Math.toRadians(i)) + centerX;
            double y = radiusY*Math.sin(Math.toRadians(i)) + centerY;
            
            glVertex2f((float) x, (float) y);
        }
        glEnd();
    }

    //method: toString
    //purpose: Provides a human-radable representation of this object.
    @Override
    public String toString() {
        String ret = "Center: " + centerX + ", " + centerY + "\nRadii: " + radiusX + ", " + radiusY;
        return ret;
    }
}
