/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw3_134831;

import java.util.Random;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author zaxki
 */
public class AimlessRobot extends Robot {

    private String direction = "south"; //sets the direction intially to the south
    private Point position = new Point(); //(0, 0)
    private Maze m = null;
    private ArrayList<Point> path = new ArrayList<>(); //stores the path
    private boolean found;

    /**
     * moves the robot inside the maze
     *
     * @param m maze object
     */
    @Override
    void move(Maze m) {
        this.m = m; //initializes the maze
        position.setLocation(m.getEntrance()); //sets the initial position to the entrance
        int counter = 0;
        found = false;
        while (!position.equals(m.getGoal()) && counter < 100 && !found) {
            int xLocation = (int) position.getX();
            int yLocation = (int) position.getY();
            String direction = generateDirection(); //generates a random direction
            int x = xLocation;
            int y = yLocation;

            //the admired point to move on
            Point wantedPoint = new Point();

            if (direction.equals("west")) {
                wantedPoint.setLocation(xLocation, yLocation - 1); //sets the wanted point
                if (wantedPoint.getX() > 0 && wantedPoint.getY() > 0) //checks if its valid
                {
                    //if its not an obstacle
                    if (!isObstacle(wantedPoint)) {
                        position.setLocation(wantedPoint); //move
                        path.add(new Point(position)); //add to the path
                    }
                }
            } else if (direction.equals("east")) {
                wantedPoint.setLocation(xLocation, yLocation + 1);
                if (wantedPoint.getX() > 0 && wantedPoint.getY() > 0) {
                    if (!isObstacle(wantedPoint)) {
                        position.setLocation(wantedPoint);
                        path.add(new Point(position));
                    }
                }
            } else if (direction.equals("north")) {
                wantedPoint.setLocation(xLocation - 1, yLocation);
                if (wantedPoint.getX() > 0 && wantedPoint.getY() > 0) {
                    if (!isObstacle(wantedPoint)) {
                        position.setLocation(wantedPoint);
                        path.add(new Point(position));
                    }
                }
            } else if (direction.equals("south")) {
                wantedPoint.setLocation(xLocation + 1, yLocation);
                if (wantedPoint.getX() > 0 && wantedPoint.getY() > 0) {
                    if (!isObstacle(wantedPoint)) {
                        position.setLocation(wantedPoint);
                        path.add(new Point(position));
                    }
                }
            }

            if (position.equals(m.getGoal())) {
                found = true;
            }
            counter++;

        }

    }

    /**
     * returns the path that the robot took
     *
     * @return path
     */
    
    public String getPath() {
        String output = "Followed path by Aimless robot:\n";
        output += "(" + (int) m.getEntrance().getX() + ", " + (int) m.getEntrance().getY() + ")->";
        for (Point element : path) {
            int x = (int) element.getX();
            int y = (int) element.getY();
            output += "(" + x + ", " + y + ")->";
        }
        return output += "#";
    }

    /**
     * launches the robot in the maze
     */
    public void launch() {
        System.out.println(getPath());
        if (found) {
            System.out.println("Goal found: Yes");
        } else {
            System.out.println("Goal found: No");
        }
    }

    /**
     * checks if a point in the maze is an obstacle or not
     *
     * @param p the point wanted to be checked
     * @return true if yes, false if no
     */
    public boolean isObstacle(Point p) {
        int x = (int) p.getX();
        int y = (int) p.getY();

        if (x <= 0 || y <= 0 || y > m.getCol() - 2 || x > m.getRow() - 2) {
            return true;
        } else {
            return m.getCertainData(x, y) == 1;
        }
    }

    /**
     * generates a direction randomly
     *
     * @return a direction
     */
    public String generateDirection() {
        String[] directions = {"north", "south", "east", "west"};
        Random rnd = new Random();
        int i = rnd.nextInt(4);
        return directions[i];
    }
}
