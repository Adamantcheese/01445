/***************************************************************
* file: WindowContainer.java
* author: Jacob Buchowiecki
* class: CS 445 – Computer Graphics
*
* assignment: Program 1
* date last modified: 4/6/2015
*
* purpose: This class contains all of the methods required to construct and show
* an OpenGL window with the given objects and runtime code.
****************************************************************/
import java.util.ArrayList;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL11.*;

public class WindowContainer {
    
    private ArrayList<Shape> renderList;
    
    //method: constructor
    //purpose: Builds this WindowContainer with a list of shapes to render once ready.
    public WindowContainer(ArrayList<Shape> shapes) {
        renderList = shapes;
        start();
    }
    
    //method: start
    //purpose: Initializes the display window, OpenGL, and begins rendering.
    private void start() {
        try {
            initUI();
            initGL();
            render();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //method: initUI
    //purpose: Initializes the display window.
    private void initUI() throws Exception {
        Display.setFullscreen(false);
        Display.setDisplayMode(new DisplayMode(640, 480));
        Display.setTitle("Project 1");
        Display.create();
    }
    
    //method: initGL
    //purpose: Initializes the OpenGL rendering suite used.
    private void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        
        glOrtho(0, 640, 0, 480, 1, -1);
        glPointSize(2);
        
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    }
    
    //method: render
    //purpose: Renders the objects that were specified when this WindowContainer was made.
    //If the user presses "Esc" or closes the window, the display and controls are destroyed.
    private void render() {
        while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            try {
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                glLoadIdentity();
                
                for(Shape s : renderList) {
                    s.render();
                }
                
                Display.update();
                Display.sync(60);
            } catch (Exception e) {
                
            }
        }
        Display.destroy();
    }
}
