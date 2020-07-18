package com.sharpirate.greenvsred;

public class Grid {
    // grid dimensions
    private final int width;
    private final int height;

    // a 2D array used for the grid
    private final int[][] array;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;

        // rows in the first dimension and columns in the second
        array = new int[height][width];
    }

    public void setValue(int x, int y, int value) {
        array[y][x] = value;
    }

    public int getValue(int x, int y) {
        return array[y][x];
    }

    private int countNeighbours(int x, int y) {
        int neighbours = 0;

        // has a top-left neighbour
        if (x != 0 && y != 0) {
            neighbours += this.getValue(x-1, y-1);
        }

        // has a top neighbour
        if (y != 0) {
            neighbours += this.getValue(x, y-1);
        }

        // has a top-right neighbour
        if (x != width-1 && y != 0) {
            neighbours += this.getValue(x+1, y-1);
        }

        // has a left neighbour
        if (x != 0) {
            neighbours += this.getValue(x-1, y);
        }

        // has a right neighbour
        if (x != width-1) {
            neighbours += this.getValue(x+1, y);
        }

        // has a bottom-left neighbour
        if (x != 0 && y != height-1) {
            neighbours += this.getValue(x-1, y+1);
        }

        // has a bottom neighbour
        if (y != height-1) {
            neighbours += this.getValue(x, y+1);
        }

        // has a bottom-right neighbour
        if (x != width-1 && y != height-1) {
            neighbours += this.getValue(x+1, y+1);
        }

        return neighbours;
    }

    private boolean hasToChange(int x, int y) {
        int neighbours = this.countNeighbours(x, y);

        // apply rules for red (0)
        if (this.getValue(x, y) == 0) {
            return (neighbours == 3 || neighbours == 6);
        }
        // apply rules for green (0)
        else {
            return (neighbours != 2 && neighbours != 3 && neighbours != 6);
        }
    }

    public int nextValue(int x, int y) {
        // change the next value if necessary
        if (!hasToChange(x, y)) {
            return this.getValue(x, y);
        }
        else {
            if (this.getValue(x, y) == 1) {
                return 0;
            }
            else {
                return 1;
            }
        }
    }

    public int calculate(int x1, int y1, int N) {
        int result = 0;

        // check initial value of (x1, y1)
        if (this.getValue(x1, y1) == 1) {
            result++;
        }

        // generate the next N generations
        for(int i = 0; i < N; i++) {
            // copy the current grid
            Grid tempGrid = new Grid(this.width, this.height);
            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.width; x++) {
                    tempGrid.setValue(x, y, this.getValue(x, y));
                }
            }

            // update the current grid based on the copy
            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.width; x++) {
                    this.setValue(x, y, tempGrid.nextValue(x, y));
                }
            }

            // check (x1, y1) in the this generation
            if (this.getValue(x1, y1) == 1) result++;
        }

        return result;
    }
}
