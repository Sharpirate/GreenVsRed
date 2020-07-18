package com.sharpirate.greenvsred;

import java.util.Scanner;

public final class InputHandler {

    private static final Scanner scanner = new Scanner(System.in);

    // grid dimensions
    private static int width;
    private static int height;

    // private constructor because this is a utility class
    private InputHandler() {}

    public static void getGridSize() {
        // first line of input (width, height)
        String gridSize = scanner.nextLine();

        // ignore whitespaces
        gridSize = gridSize.replaceAll("\\s", "");

        // convert to int
        width = Integer.parseInt(gridSize.substring(0, gridSize.indexOf(",")));
        height = Integer.parseInt(gridSize.substring(gridSize.indexOf(",") + 1));
    }

    public static void getGrid(Grid grid) {
        // get the one row at a time
        String[] rows = new String[height];
        for (int y = 0; y < height; y++) {
            rows[y] = scanner.nextLine();
        }

        // convert array strings into 1s and 0s
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid.setValue(x, y, Integer.parseInt(String.valueOf(rows[y].charAt(x))));
            }
        }
    }

    public static void getDetails() {
        // last line of input (x1, y1, N)
        String details = scanner.nextLine();

        // ignore whitespaces
        details = details.replaceAll("\\s", "");

        // send the details to the Main class
        Main.setX1(Integer.parseInt(details.substring(0, details.indexOf(","))));
        Main.setY1(Integer.parseInt(details.substring(details.indexOf(",") + 1, details.lastIndexOf(","))));
        Main.setN(Integer.parseInt(details.substring(details.lastIndexOf(",") + 1)));
    }

    public static int getGridWidth() {
        return width;
    }

    public static int getGridHeight() {
        return height;
    }
}
