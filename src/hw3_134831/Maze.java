/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw3_134831;

import java.awt.Point;

/**
 *
 * @author zaxki
 */
public class Maze {

    private int col;
    private int row;
    private Point entrance;
    private Point goal;
    private int[][] data;

    /**
     * constructs a maze
     * @param col number of columns
     * @param row number of rows
     * @param entrance location of entrance
     * @param goal location of goal
     * @param data grid of data (0 or 1)
     */
    public Maze(int col, int row, Point entrance, Point goal, int[][] data) {
        this.col = col;
        this.row = row;
        this.entrance = entrance;
        this.goal = new Point((int) goal.getX() - 1, (int) goal.getY());
        this.data = data;
    }

    /**
     * method that returns the entrance
     * @return location of entrance
     */
    public Point getEntrance() {
        return entrance;
    }

    /**
     * method that returns the goal
     * @return location of goal
     */
    public Point getGoal() {
        return goal;
    }

    
    /**
     * method that gets the data from a specific cell
     * @param x x cord
     * @param y y cord
     * @return the data at the specific cell (0 or 1)
     */
    public int getCertainData(int x, int y) {
        return data[x][y];
    }

    /**
     * returns the number columns of the maze
     * @return the number columns of the maze
     */
        public int getCol() {
        return col;
    }

    /**
     * returns the number columns of the maze
     * @return the number columns of the maze
     */
    public int getRow() {
        return row;
    }
    /**
     * draws a border for the maze
     */
    private void setBorder(){
        for(int i = 0; i < data.length; i++){
            data[0][i] = 1;
            for(int j = 0; j < data.length; j++){
                data[j][0] = 1;
                data[j][col - 1] = 1;
            }
            data[row - 1][i] = 1;
        }
    }
    
    
    /**
     * returns the maze as a string
     * @return the maze as a string
     */
    @Override
    public String toString() {
        setBorder();
        String output = "";
        
        for(int i = 0; i < data.length - 1; i++){
            for(int j = 0; j < data.length - 1; j++){
                if(data[i][j] == 1){
                    output += "* ";
                }else{
                    output += ". ";
                }
            }
            output += "\n";
        }
        return output;
    }

}
