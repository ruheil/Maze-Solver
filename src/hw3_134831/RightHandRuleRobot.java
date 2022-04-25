
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw3_134831;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author zaxki
 */
public class RightHandRuleRobot extends Robot {

    private String direction = "";
    private Point position = new Point(); 
    private Maze m = null;
    private ArrayList<Point> path = new ArrayList<>(); //the path that the robot will walk in
    private boolean found; //flag (not neccessary)

    /**
     * moves the robot in the maze
     *
     * @param m an object of type maze
     */
    @Override
    void move(Maze m) {
        this.m = m;

        //evaluate the goal
        Point goal = m.getGoal();

        position.setLocation(m.getEntrance()); //sets the initial position to the entrance
        direction = "south"; //the direction initially is heading south
        int counter = 0;
        found = false;
        while (!position.equals(goal) && counter < 100 && !found) {
            //gets each cordinate of the position
            int x = (int) position.getX();
            int y = (int) position.getY();

            /*
             * for each direction there exist three conditions, for example a
             * robot heading 'north' would face the three conditions where its:
             * front: (x - 1, y) back: (x + 1, y) right: (x, y + 1) left: (x, y
             * - 1) but a robot heading 'south' would face the three conditions
             * where its: front: (x + 1, y) back: (x - 1, y) right: (x, y - 1)
             * left: (x, y + 1) and a robot heading 'east' or 'west' will face
             * the three conditions but with a different values for each
             * possible way
             */
            
            //if the direction is north
            if (direction.equals("north")) {
                //if the point in front is not an obstacle a/nd the point in right is an obstacle
                if (!isObstacle(new Point(x - 1, y)) && isObstacle(new Point(x, y + 1))) {
                    position.setLocation(new Point(x - 1, y)); //move forward
                    path.add(new Point(position)); //add the position to the path

                    //if there is no obstacle in the right of the robot
                } else if (!isObstacle(new Point(x, y + 1))) {
                    direction = "east"; //set the direction to east
                    position.setLocation(x, y + 1); //move forward of east
                    path.add(new Point(position)); //add the position to the path

                    //if there is an obstacle in front and an obstacle in right (corner)
                } else if (isObstacle(new Point(x - 1, y)) && isObstacle(new Point(x, y + 1))) {
                    direction = "west"; //set the direction to west
                }

                /**
                 *
                 * <h1> and the rest would be the same for each direction </h1>
                 *
                 */
            } else if (direction.equals("south")) {
                if (!isObstacle(new Point(x + 1, y)) && isObstacle(new Point(x, y - 1))) {
                    position.setLocation(new Point(x + 1, y));
                    path.add(new Point(position));
                } else if (!isObstacle(new Point(x, y - 1))) {
                    direction = "west";
                    position.setLocation(x, y - 1);
                    path.add(new Point(position));
                } else if (isObstacle(new Point(x + 1, y)) && isObstacle(new Point(x, y - 1))) {
                    direction = "east";
                }
            } else if (direction.equals("east")) {
                if (!isObstacle(new Point(x, y + 1)) && isObstacle(new Point(x + 1, y))) {
                    position.setLocation(new Point(x, y + 1));
                    path.add(new Point(position));
                } else if (!isObstacle(new Point(x + 1, y))) {
                    direction = "south";
                    position.setLocation(x + 1, y);
                    path.add(new Point(position));
                } else if (isObstacle(new Point(x, y + 1)) && isObstacle(new Point(x + 1, y))) {
                    direction = "north";
                }
            } else if (direction.equals("west")) {
                if (!isObstacle(new Point(x, y - 1)) && isObstacle(new Point(x - 1, y))) {
                    position.setLocation(new Point(x, y - 1));
                    path.add(new Point(position));
                } else if (!isObstacle(new Point(x - 1, y))) {
                    direction = "north";
                    position.setLocation(x, y + 1);
                    path.add(new Point(position));
                } else if (isObstacle(new Point(x, y - 1)) && isObstacle(new Point(x - 1, y))) {
                    direction = "south";
                }
            }

            //if the position is the goal
            if (position.equals(m.getGoal())) {
                found = true;
            }

            counter++;
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
     * returns the path of the robot
     *
     * @return path
     */
    public String getPath() {
        String output = "Followed path by Right Hand Rule robot:\n";
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
}
