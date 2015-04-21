/***************************************************************
* file: Line.java
* author: Jacob Buchowiecki
* class: CS 445 – Computer Graphics
*
* assignment: Program 1
* date last modified: 4/5/2015
*
* purpose: This class extends Shape, and is capable of drawing a line with OpenGL.
****************************************************************/
import static org.lwjgl.opengl.GL11.*;

public class Line implements Shape {
    
    private int x0;
    private int y0;
    private int x1;
    private int y1;
    
    //method: constructor
    //purpose: Build this line object according to the following rules:
    //1) The smaller x coordinate of the two is the first point.
    //2) If both x coordinates are the same, the smaller y coordinate of the two
    //   is the first point.
    public Line(int point1X, int point1Y, int point2X, int point2Y) {
        if (point1X != point2X) {
            x0 = (point1X < point2X ? point1X : point2X);
            y0 = (point1X < point2X ? point1Y : point2Y);
            x1 = (point1X < point2X ? point2X : point1X);
            y1 = (point1X < point2X ? point2Y : point1Y);
        } else {
            x0 = (point1Y < point2Y ? point1X : point2X);
            y0 = (point1Y < point2Y ? point1Y : point2Y);
            x1 = (point1Y < point2Y ? point2X : point1X);
            y1 = (point1Y < point2Y ? point2Y : point1Y);
        }
    }

    //method: render
    //purpose: Provide a way to render this line object. Third through sixth octants
    //don't need to be taken into account because we always draw from the smallest x,
    //which is chosen in the constructor. If the line is vertical, we always draw from
    //the smallest y, which is chosen in the constructor. Zero length lines aren't drawn.
    @Override
    public void render() {
        glColor3f(1.0f, 0.0f, 0.0f);
        
        int dx = x1 - x0;
        int dy = y1 - y0;
        
        if (dx == 0 && dy == 0) {
            return;
        }
        
        int m = 0;
        if (dx == 0) {
            m = Integer.MAX_VALUE;
        } else {
            m = dy/dx;
        }
        
        glBegin(GL_POINTS);
            glVertex2f(x0, y0);
            //First octant (x++, y++?)
            if (dy >= 0 && m <= 1) {
                int x = x0 + 1;
                int y = y0;
                int d = 2*dy - dx;
                
                while (x < x1) {
                    if(d > 0) {
                        y++;
                        d = d + 2*dy - 2*dx;
                    } else {
                        d = d + 2*dy;
                    }
                    glVertex2f(x, y);
                    x++;
                }
            //Second octant vertical (y++, x+=0)
            } else if (dy >= 0 && m == Integer.MAX_VALUE) {
                int y = y0 + 1;
                
                while (y < y1) {
                    glVertex2f(x0, y);
                    y++;
                }
            //Second octant non vertical (y++, x++?)
            } else if (dy >= 0 && m > 1) {
                int x = x0;
                int y = y0 + 1;
                int d = dy - 2*dx;
                
                while (x < x1) {
                    if (d > 0) {
                        x++;
                        d = d - 2*dx;
                    } else {
                        d = d + 2*dy - 2*dx;
                    }
                    glVertex2f(x, y);
                    y++;
                }
            //Seventh octant (y--, x++?)
            } else if (dy < 0 && m <= -1) {
                int x = x0;
                int y = y0 - 1;
                int d = dy + 2*dx;
                
                while (x < x1) {
                    if (d > 0) {
                        x++;
                        d = d + 2*dy + 2*dx;
                    } else {
                        d = d + 2*dx;
                    }
                    glVertex2f(x, y);
                    y--;
                }
            //Eight octant (x++, y--?)
            } else if (dy < 0 && m > -1) {
                int x = x0 + 1;
                int y = y0;
                int d = 2*dy + dx;
                
                while (x < x1) {
                    if (d > 0) {
                        y--;
                        d = d + 2*dy;
                    } else {
                        d = d + 2*dy + 2*dx;
                    }
                    glVertex2f(x, y);
                    x++;
                }
            }
        glEnd();
    }

    //method: toString
    //purpose: Provides a human-radable representation of this object.
    @Override
    public String toString() {
        String ret = "Point 1: " + x0 + ", " + y0 + "\nPoint 2: " + x1 + ", " + y1;
        return ret;
    }
}
