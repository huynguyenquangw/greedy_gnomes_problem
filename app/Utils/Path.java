package app.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.model.Pair;

public class Path {
    /* ----------------------------------------- Main functions ----------------------------------------- */
    // Function to display the result
    public static void displayResult(ArrayList<Pair> path, int[][] grid){
        System.out.println("------------------------------------------------------------------");
        System.out.println("Golds: " + getMaxGolds(path, grid) + " | " + "Steps: " + getSteps(path));
        printDirection(path);
        System.out.println(path);
        System.out.println("------------------------------------------------------------------");
    }
    
    // Function to get result of Algorithm
    // The first one, which is in the list of all paths that have max golds, is the RESULT
    public static ArrayList<Pair> getMinimalPathHasMaxGolds(int[][] grid){
        List<ArrayList<Pair>> myPathList = getAllPathsHaveMaxGolds(grid);

        // get the first path in path list above
        ArrayList<Pair> minimalPath = myPathList.get(0);
        Integer minSteps = minimalPath.size() - 1; // Steps is amount of coordinates -1 

        // Loop to find the path which has smaller steps
        for(int i = 1; i <= myPathList.size() - 1; i++){
            int steps = myPathList.get(i).size() - 1; // Steps is amount of coordinates -1 
            if(steps < minSteps){
                minimalPath = myPathList.get(i);
            }
        }
        return minimalPath;
    }

    // Function to get all paths have maximum amount of gold they can find
    public static List<ArrayList<Pair>> getAllPathsHaveMaxGolds(int[][] grid){
        List<ArrayList<Pair>> pathList = findAllPaths(grid);
        List<ArrayList<Pair>> allPathsHaveMaxGolds = new ArrayList<ArrayList<Pair>>();
        Integer maxGolds = 0;

        for(ArrayList<Pair> path : pathList){
            if(getMaxGolds(path, grid) == maxGolds){
                allPathsHaveMaxGolds.add(trimPath(path, grid)); // add all paths that has max golds
            } else if(getMaxGolds(path, grid) > maxGolds){
                allPathsHaveMaxGolds.clear();                   // clear arraylist if has new maxGolds value
                allPathsHaveMaxGolds.add(trimPath(path, grid)); // add all paths that has max golds
                maxGolds = getMaxGolds(path, grid); // new maxGolds value
            } 
        }
        return allPathsHaveMaxGolds;
    }

    /* -------------------------------------- Recursive functions -------------------------------------- */
    // Function to find all paths after recursive
    public static List<ArrayList<Pair>> findAllPaths(int[][] grid){
        List<ArrayList<Pair>> allPaths = new ArrayList<ArrayList<Pair>>();

        findPathRecursive(0, 0, grid, "", allPaths);

        return allPaths;
    }

    // Recursive function to find all paths
    public static void findPathRecursive(int x, int y, int[][] grid, String path, List<ArrayList<Pair>> allPaths){
        /* (x, y) is coordinates of the POINTER */
        /* x: current row                       */
        /* y: current column                    */
        final int ROCK = -1;

        int rows = grid.length;     // rows of the grid
        int cols = grid[0].length;  // columns of the grid

        // Pointer hits the WALL on the right -> then go top-down only
        if(y == cols - 1){
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
        if(x == rows - 1){
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

    // Function to convert path in String to path in ArrayList
    public static ArrayList<Pair> convertStringPathToArrayList(String path){
        ArrayList<Pair> newPath = new ArrayList<Pair>();

        // list of all pairs of path
        List<String> pairList = new ArrayList<String>(Arrays.asList(path.split(" ")));
        for(String pair : pairList){
            String[] xy = pair.split(",");
            Pair newPair = new Pair(Integer.parseInt(xy[0].trim()), Integer.parseInt(xy[1].trim()));
            newPath.add(newPair);
        }
        return newPath;
    }

    /* ----------------------------------------- Sub-functions ----------------------------------------- */
    // Function to calculate sum golds of 1 path
    public static Integer getMaxGolds(ArrayList<Pair> path, int[][] grid){
        Integer sumGoldsOfPath = 0;
        for(Pair pair : path){
            sumGoldsOfPath += grid[pair.x][pair.y];
        }
        return sumGoldsOfPath;
    }

    // Function to calculate sum golds of 1 path
    public static Integer getSteps(ArrayList<Pair> path){
        int pathSteps = path.size() - 1; // Steps is amount of coordinates -1 
        return pathSteps;
    }

    // Function to remove position that contain 0 gold at the end of path
    public static ArrayList<Pair> trimPath(ArrayList<Pair> path, int[][] grid) {
        for(int i = path.size() -1; i >= 0; i--) {
            if(grid[path.get(i).x][path.get(i).y] != 0){
                break;
            } else {
                path.remove(i);
            }
        }
        return path;
    }

    // Function to remove duplicates from an ArrayList
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list){
        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();
        // Traverse through the first list
        for (T element : list) {
            // If this element is not present in newList, then add it
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    // Function to print the direction of path
    public static void printDirection(ArrayList<Pair> path) {
        ArrayList<String> pathDirection = new ArrayList<String>();
        Pair prevPair = path.get(0);

        for (int i = 1; i < path.size(); i++) {
            Pair curPair = path.get(i);
            if (curPair.x > prevPair.x) {
                pathDirection.add("D");
                prevPair = curPair;
            } else if (curPair.y > prevPair.y) {
                pathDirection.add("R");
                prevPair = curPair;
            }
        }
        System.out.println("Direction: " + pathDirection.toString().replace(", ", "").replace("[", "").replace("]", ""));
    }
    /* ------------------------------------------------------------------------------------------------- */
}
