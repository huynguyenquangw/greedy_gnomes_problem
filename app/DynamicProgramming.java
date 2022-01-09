package app;

import java.util.ArrayList;
import java.util.Collections;

import app.model.Pair;
import app.Utils.Map;

import java.io.IOException;

public class DynamicProgramming {

    /***
     * @param matrix
     * @return a filled matrix with max cost value from starting point to each cell
     */
    public static int[][] fillDP(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int rock = -1;

        // base code to check null
        if (rows == 0) {
            return new int[0][0];
        }

        // create a Dynamic Programming matrix with same size as original matrix
        int[][] dp = new int[rows][cols];
        dp[0][0] = mat[0][0];

        // fill first row of dp with the max cost
        // if the pointer meet a rock, the remaining cell after that rock are treated as
        // rock as well
        for (int i = 1; i < cols; i++) {
            if (mat[0][i] == rock || dp[0][i - 1] == rock) {
                dp[0][i] = rock;
            } else {
                dp[0][i] = dp[0][i - 1] + mat[0][i];
            }
        }

        // continue filling the first column with the same approach with first row
        for (int j = 1; j < rows; j++) {
            if (mat[j][0] == rock || dp[j - 1][0] == rock) {
                dp[j][0] = rock;
            } else {
                dp[j][0] = dp[j - 1][0] + mat[j][0];
            }
        }

        // fill remaining cell with values from above first row and column
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {

                // for cell in matrix which is not rock
                // check whether the previous left cell AND the previous up cell is rock
                // if yes, fill that dp cell as rock
                // if no, fill dp cell with max cost from both previous left and up cell
                if (mat[i][j] != rock) {
                    if (dp[i - 1][j] == rock && dp[i][j - 1] == rock) {
                        dp[i][j] = rock;
                    } else {
                        dp[i][j] = mat[i][j] + Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
                // if cell in matrix is rock, fill dp cell as rock too
                else {
                    dp[i][j] = rock;
                }
            }
        }

        // return filled dp matrix
        return dp;
    }

    /**
     * 
     * @param matrix
     * @return a Pair of coordinates in matrix, that has the max cost value from
     *         starting point of matrix
     */
    public static Pair findMaxCord(int[][] mat) {

        int max = mat[0][0];
        Pair maxCord = new Pair();

        // Loop through the matrix
        // Finding the max value cell
        // Set row cord of cell is x of Pair, column crod of cell is y of Pair
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] >= max) {
                    max = mat[i][j];
                    maxCord.x = i;
                    maxCord.y = j;
                }
            }
        }

        return maxCord;
    }

    /**
     * Printing the path from starting point to the max gold cell
     * Backtracking from the max gold cell to starting point, recording the steps
     * and directions
     * 
     * @param maxCord
     * @param matrix
     */
    public static void printPath(Pair maxCord, int[][] mat) {
        ArrayList<Pair> path = new ArrayList<>();
        int i = maxCord.x, j = maxCord.y;

        System.out.println("Golds: " + mat[i][j]);

        // Adding the max gold cell into path first
        path.add(new Pair(i, j));

        // Reverse loop
        while (i > 0 && j > 0) {

            // if previous up cell cost is greater than previous left cell cost
            // adding that coordinates into path
            if (mat[i - 1][j] >= mat[i][j - 1]) {
                path.add(new Pair(i - 1, j));
                i -= 1;
            } else {
                path.add(new Pair(i, j - 1));
                j -= 1;
            }
        }

        // Adding the starting point coordinates
        path.add(new Pair(0, 0));

        // Due to backtracking, path is needed to reverse in right order
        reverseArrayList(path);

        // Steps as the path size
        System.out.println("Steps: " + path.size());

        printDirection(path);
    }

    /**
     * From the path of coordinates pair, print out the direction moving in the gold
     * map
     * Compare next to cell coordinates to calculate Right or Down directions
     * 
     * @param path
     */
    public static void printDirection(ArrayList<Pair> array) {
        ArrayList<String> path = new ArrayList<String>();
        Pair prevPair = array.get(0);

        for (int i = 1; i < array.size(); i++) {
            Pair curPair = array.get(i);
            if (curPair.x > prevPair.x) {
                path.add("D");
                prevPair = curPair;
            } else if (curPair.y > prevPair.y) {
                path.add("R");
                prevPair = curPair;
            }
        }

        System.out.println(path);
    }

    /**
     * Using Collections to reverse ArrayList order
     * 
     * @param array
     * @return
     */
    public static ArrayList<Pair> reverseArrayList(ArrayList<Pair> array) {
        Collections.reverse(array);
        return array;
    }

    static void displayMat(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println();

        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        try {

            // Map input processing and validation
            String[][] map = Map.parseMap("./app/maps/19_19.txt");

            // Processing input map to int[][] mat
            int[][] mat = Map.parseIntMap(map);
            Map.displayMap(mat);

            // Using DP to fill in input matrix with max cost values
            int[][] dp = fillDP(mat);

            // Figure out the max cost coordinates in dp matrix
            Pair maxCord = findMaxCord(dp);

            // Backtracking from the max cost coordinates to starting point
            // Print out the path that have the most mined golds.
            printPath(maxCord, dp);

        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        long stopTime = System.nanoTime();
        System.out.println("Running time: " + (stopTime - startTime));
    }

}