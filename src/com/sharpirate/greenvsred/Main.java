package com.sharpirate.greenvsred;

public class Main {

    // calculation details
    private static int x1;
    private static int y1;
    private static int N;

    // public setters
    public static void setX1(int value) { x1 = value; }
    public static void setY1(int value) { y1 = value; }
    public static void setN(int value) { N = value; }

    public static void main(String[] args) {

        // handle the first line of input (width, height)
        InputHandler.getGridSize();

        // initialize the grid
        Grid grid = new Grid(InputHandler.getGridWidth(), InputHandler.getGridHeight());

        // populate the grid from the input
        InputHandler.getGrid(grid);

        // get the last line of input (x1, y1, N)
        InputHandler.getDetails();

        // calculate the next N generations while keeping track of (x1, y1)
        int result = grid.calculate(x1, y1, N);

        // print the result
        System.out.println("result: " + result);
    }
}