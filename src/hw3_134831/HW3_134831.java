/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hw3_134831;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author zaxki
 */
public class HW3_134831 {

    /**
     * name: Al Ruheil Mohammed Ali Al Ruheili, ID: 134831
     *
     * pseudo-code: input: file containing all maze data output: maze map and/or
     * robot path
     *
     * steps: class Robot: abstract class that has only a move method class
     * Aimless Robot: extends Robot and moves the robot randomly (if there is no
     * obstacle) until it reaches the goal or reaches 100 tries class Right Hand
     * Rule Robot: extends Robot and moves the robot accordingly to the Right
     * Hand rule until it reaches the goal or reaches 100 tries. class Left Hand
     * Rule Robot: extends Robot and moves the robot accordingly to the Left
     * Hand rule until it reaches the goal or reaches 100 tries. Tester class:
     * reads from a file and display the maze information and draws it, also
     * launches the robots class Maze: initialize a maze with the columns, rows,
     * entrance and goal
     *
     */
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int choice; //intiialze the choice
        int rows = 5; //sets the rows to be 5 as default
        int cols = 5; //sets the columns to be 5 as default
        Maze maze = null;
        Point entrance = new Point(0, (rows + 2)/2); //entrance is (0, 2)
        Point goal = new Point(cols - 1, (rows + 2)/2); //goal is (4, 2)
        String robotType = "aimless"; //the chosen robot is the aimless as default

        do {
            System.out.println("*** Welcome to Maze Game ***");
            System.out.println("----------------------------");
            System.out.println("Select one option:");
            System.out.println("    Press 1 to load a Maze");
            System.out.println("    Press 2 to display the Maze");
            System.out.println("    Press 3 to select a type of Robot");
            System.out.println("    Press 4 to launch the Robot in the Maze");
            System.out.println("    Press 0 to exit");
            System.out.print("--> ");
            choice = input.nextInt();
            switch (choice) {
                case 1: //if the choice is 1
                    System.out.println("\n\n--> 1");
                    Scanner fileName = new Scanner(System.in);
                    System.out.print("Enter file name: ");
                    String name = fileName.nextLine();
                    Scanner inFile = new Scanner(new File(name)); //searches for a file with the given name
                    int numLine = 0;
                    while (inFile.hasNextLine()) {

                switch (numLine) {
                    case 0:
                        {
                            //if its the first line
                            String[] value = inFile.nextLine().split("=");
                            rows = Integer.parseInt(value[1]);
                            break;
                        }
                    case 1:
                        {
                            //if its the second line
                            String[] value = inFile.nextLine().split("=");
                            cols = Integer.parseInt(value[1]);
                            break;
                        }
                    case 2:
                        {
                            //if its the third line
                            String[] value = inFile.nextLine().split("=");
                            entrance.setLocation(0, Integer.parseInt(value[1]));
                            break;
                        }
                    case 3:
                        {
                            //if its the fourth line
                            String[] value = inFile.nextLine().split("=");
                            goal.setLocation(rows - 1, Integer.parseInt(value[1]));
                            break;
                        }
                    default:
                        //otherwise:
                        int[][] data = new int[rows][cols]; //default grid
                        int[][] newData = new int[rows + 1][cols + 1]; //grid with border
                        for (int i = 0; i < cols - 2; i++) {
                            for (int j = 0; j < rows - 2; j++) {
                                data[i][j] = inFile.nextInt(); //store the values in a grid
                                newData[i + 1][j + 1] = data[i][j]; //store them in a the grid that contains the border
                            }
                        }
                        //inititialize the maze to have each attribute
                        maze = new Maze(cols, rows, entrance, goal, newData);
                        break;
                }

                        numLine++;
                    }
                    System.out.println("\nThe file includes a maze with " + rows + " rows and " + cols + " columns");
                    System.out.println("Entrance at: (" + (int) entrance.getX() + ", " + (int) entrance.getY() + ")");
                    System.out.println("Goal at: (" + (int) goal.getX() + ", " + (int) goal.getY() + ")\n");
                    break;
                case 2: //if the choice is 2
                    System.out.println("\n\n--> 2");
                    System.out.println(maze);
                    break;
                case 3: //if the choice is 3
                    System.out.println("\n\n--> 3");
                    Scanner robotChoice = new Scanner(System.in);
                    System.out.println("Enter the type of the Robot:");
                    System.out.println("    (A)imless Robot");
                    System.out.println("    (R)ight Hand Rule Robot");
                    System.out.println("    (L)eft Hand Rule Robot");
                    System.out.print("        >: ");
                    char theChoice = robotChoice.next().charAt(0);
                    //if the user chooses 'a' or 'A':
                    if (theChoice == 'A' || theChoice == 'a') {
                        robotType = "aimless";
                        System.out.println("An Amiless robot heading south has been created");
                        
                        //if the user chooses 'r' or 'R':
                    } else if (theChoice == 'R' || theChoice == 'r') {
                        robotType = "right hand";
                        System.out.println("A Right Hand Rule robot heading south has been created");
                        
                        //if the user chooses 'l' or 'L':
                    } else if (theChoice == 'L' || theChoice == 'l') {
                        robotType = "left hand";
                        System.out.println("A Left Hand Rule robot heading south has been created");
                    }
                    break;
                case 4: //if the user chooses 4:
                    System.out.println("\n\n--> 4");
                    //check the type of robot:
                    if (robotType.equals("aimless")) {
                        AimlessRobot r = new AimlessRobot();
                        r.move(maze);
                        r.launch();
                    } else if (robotType.equals("right hand")) {
                        RightHandRuleRobot r = new RightHandRuleRobot();
                        r.move(maze);
                        r.launch();
                    } else if (robotType.equals("left hand")) {
                        LeftHandRuleRobot r = new LeftHandRuleRobot();
                        r.move(maze);
                        r.launch();
                    }
                    break;
                default:
                    break;
            }
        } while (choice != 0);

    }

}
