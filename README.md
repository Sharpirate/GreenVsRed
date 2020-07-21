# Main class
This is the class that contains the starting point of the application (the main method).
This is also where the entire program structure is outlined.
It is composed of the following parts:

## 1. Getting the first line of input
In order to achieve abstraction, a whole new class is created, called InputHandler. Its sole purpose is to process the input from the user and pass it to the remaining parts of the program. This is done by using a Scanner object with the standart input stream (System.in).

The first line of input is in the following format "x, y", where x represents the width of the gird and y represents the height. The whole line is stored as a string. Next, all whitespaces are ignored by replacing them with an empty character "". Finally the width and height are obtained by getting the numbers as a substrings and converting them into integers.


## 2. Initializing the gird
The grid has a class of its own which provides a nice layer of abstraction. The actual matrix is represented by a 2D array which makes it easy to access each member by its index.

In order to create a new Grid object, width and height must be provided in the constructor which are used to initialize the 2D array. The InputHandler class supplies the first Grid object (generation zero) with the exact dimensions of the grid.


## 3. Populating the grid from the input
After the first line of input, the next y lines of input represent each row of the grid. Each row in turn has x number of columns, containing the members - 1 (green) or 0 (red).

All of the rows are stored in a string array, containing each row as a string. When iterating over this array, each row is broken down into characters representing each member of the grid. The characters are then parsed to strings and finally parsed to integers, which are used to populate the 2D array. 


## 4. Getting the last line of input
The last line of input is in the following format "x1, y1, N", where x1 and y1 represent the coordinats of the member who should be tracked throughout the next N generations. This line is processed in a similar fashion to the first line of input.


## 5. Calculating the result
The result represents the number of times the tracked member (x1, y1) has been green throughout the N generations.

The first step is to check the initial value of the tracked member (if it's already green in generation zero).

The next step is to form the next generation. This is done by creating a temporary Grid object which is a copy of the current one. This allows the current Grid object to be modified without loosing its present state which is used to calculate whetner or not a member should change in the next generation. A member changes under the following conditions:

A red cell (0) should turn green (1) if it's surrounded by either 3 or 6 green (1) neighbours. A green cell (1) should turn red (0) if it's surrounded by either 0, 1, 4, 5, 7 or 8 green (1) neighbours.

The easiest way to find out the number of neigbours would be to count them all: top-left, top, top-right, left, right, bottom-left, bottom and bottom-right. But this method has a major flaw - members around the edges don't have all of these neighbours and trying to access them would result in a index out of bounds exception in the 2D array. Therefore, a better solution would be to first check for neighbour availability and only count the existing neighbours. Since the temporary Grid object lives only inside the scope of the calculate method, it shouldn't pose a problem for memory management. When the new generation is stored in the current Grid object, the tracked member is checked. This process repeats for all of the N generations. After the last generation, the calculate method returns the result to the Main class.


## 6. Displaying the result
Finally, the result is displayed to the user through the standart output.
