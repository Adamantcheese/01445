/***************************************************************
* file: Project1.java
* author: Jacob Buchowiecki
* class: CS 445 – Computer Graphics
*
* assignment: Program 1
* date last modified: 4/5/2015
*
* purpose: This is the driver program for the project. It takes an input file path
* from the standard input and displays the objects in that file in a new window.
****************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Project1 {
    //method: main
    //purpose: Drives this program.
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("coordinates.txt");
        Scanner sc = new Scanner(f);
       
        String circleRegex = "^c -?\\d*,-?\\d* \\d*$";
        String ellipseRegex = "^e -?\\d*,-?\\d* \\d*,\\d*$";
        String lineRegex = "^l -?\\d*,-?\\d* -?\\d*,-?\\d*$";
        String validRegex = circleRegex + '|' + ellipseRegex + '|' + lineRegex;
       
        Pattern valid = Pattern.compile(validRegex);
        
        ArrayList<Shape> renderList = new ArrayList<Shape>();
        int i = 1;
       
        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            Matcher matcher = valid.matcher(input);
            if(matcher.find()) {
                switch(input.charAt(0)) {
                    case 'c':
                        input = input.substring(2);
                        String csCenter = input.substring(0, input.indexOf(' '));
                        String csRadius = input.substring(input.indexOf(' ') + 1);
                       
                        String csCenterX = csCenter.substring(0, csCenter.indexOf(','));
                        String csCenterY = csCenter.substring(csCenter.indexOf(',') + 1);
                       
                        int cRadius = Integer.parseInt(csRadius);
                        int cCenterX = Integer.parseInt(csCenterX);
                        int cCenterY = Integer.parseInt(csCenterY);
                       
                        Shape circle = new Circle(cCenterX, cCenterY, cRadius);
                        renderList.add(circle);
                       
                        break;
                    case 'e':
                        input = input.substring(2);
                        String eCenter = input.substring(0, input.indexOf(' '));
                        String eRadii = input.substring(input.indexOf(' ') + 1);
                       
                        String esCenterX = eCenter.substring(0, eCenter.indexOf(','));
                        String esCenterY = eCenter.substring(eCenter.indexOf(',') + 1);
                       
                        String esRadiusX = eRadii.substring(0, eRadii.indexOf(','));
                        String esRadiusY = eRadii.substring(eRadii.indexOf(',') + 1);
                       
                        int eCenterX = Integer.parseInt(esCenterX);
                        int eCenterY = Integer.parseInt(esCenterY);
                        int eRadiusX = Integer.parseInt(esRadiusX);
                        int eRadiusY = Integer.parseInt(esRadiusY);
                       
                        Shape ellipse = new Ellipse(eCenterX, eCenterY, eRadiusX, eRadiusY);
                        renderList.add(ellipse);
                       
                        break;
                    case 'l':
                        input = input.substring(2);
                        String p1 = input.substring(0, input.indexOf(' '));
                        String p2 = input.substring(input.indexOf(' ') + 1);
                       
                        String sP1X = p1.substring(0, p1.indexOf(','));
                        String sP1Y = p1.substring(p1.indexOf(',') + 1);
                       
                        String sP2X = p2.substring(0, p2.indexOf(','));
                        String sP2Y = p2.substring(p2.indexOf(',') + 1);
                       
                        int p1X = Integer.parseInt(sP1X);
                        int p1Y = Integer.parseInt(sP1Y);
                        int p2X = Integer.parseInt(sP2X);
                        int p2Y = Integer.parseInt(sP2Y);
                       
                        Shape line = new Line(p1X, p1Y, p2X, p2Y);
                        renderList.add(line);
                       
                        break;
                }
            } else {
                System.out.println("Invalid format on line " + i + ", skipping.");
            }
            i++;
        }
        sc.close();
       
        WindowContainer window = new WindowContainer(renderList);
    }
}
