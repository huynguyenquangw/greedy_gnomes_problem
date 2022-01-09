package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.Utils.Map;
import app.Utils.Path;
import app.model.Pair;

public class ExhaustiveSearch {

    /**
     * Find all paths after recursive
     * 
     * @param grid
     * @return allPaths
     */
    public static List<ArrayList<Pair>> findAllPaths(int[][] grid) {
        List<ArrayList<Pair>> allPaths = new ArrayList<ArrayList<Pair>>();

        findPathRecursive(0, 0, grid, "", allPaths);

        return allPaths;
    }

    /**
     * Recursive function to find all paths
     * 
     * @param x
     * @param y
     * @param grid
     * @param path
     * @param allPaths
     */
    public static void findPathRecursive(int x, int y, int[][] grid, String path, List<ArrayList<Pair>> allPaths) {
        /* (x, y) is coordinates of the POINTER */
        /* x: current row */
        /* y: current column */
        final int ROCK = -1;

        int rows = grid.length; // rows of the grid
        int cols = grid[0].length; // columns of the grid

        // Pointer hits the WALL on the right -> then go top-down only
        if (y == cols - 1) {
            for (int i = x; i <= rows - 1; i++) {
                // Pointer hits the ROCK
                if (grid[i][y] == ROCK) {
                    // Add the path to path list
                    allPaths.add(Path.convertStringPathToArrayList(path));
                    return; // hits the ROCK -> finish
                }
                path += i + "," + y + " "; // Append coordinates to string
            }
            // Add the path to path list
            allPaths.add(Path.convertStringPathToArrayList(path));

            return;
        }

        // Pointer hits the WALL on the bottom -> then go left-right only
        if (x == rows - 1) {
            for (int i = y; i <= cols - 1; i++) {
                if (grid[x][i] == ROCK) {
                    // Add the path to path list
                    allPaths.add(Path.convertStringPathToArrayList(path));
                    return; // hits the ROCK -> finish
                }
                path += x + "," + i + " "; // Append coordinates to string
            }
            // Add the path to path list
            allPaths.add(Path.convertStringPathToArrayList(path));
            return;
        }

        // Pointer hits the ROCK during normal move
        if (grid[x][y] == ROCK) {
            // Add the path to path list
            allPaths.add(Path.convertStringPathToArrayList(path));
            return; // got the ROCK -> finish
        }

        // Append coordinates to the path
        path += x + "," + y + " ";
        // Normal move
        findPathRecursive(x + 1, y, grid, path, allPaths);
        findPathRecursive(x, y + 1, grid, path, allPaths);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("\nStarting Exhaustive Search...\n");
        long startTime = System.nanoTime();

        // add map txt file
        String[][] map = Map.parseMap("./app/maps/10_10.txt"); // parse to String map
        int[][] intMap = Map.parseIntMap(map); // parse to int map

        List<ArrayList<Pair>> myAllPaths = findAllPaths(intMap);

        Map.displayMap(intMap); // display map
        ArrayList<Pair> myResult = Path.getMinimalPathHasMaxGolds(myAllPaths, intMap); // get the result path (contain
                                                                                       // coordinates)
        Path.displayResult(myResult, intMap); // display result(golds, steps, direction)

        long stopTime = System.nanoTime();
        System.out.println("Running time: " + (stopTime - startTime));
    }
}
