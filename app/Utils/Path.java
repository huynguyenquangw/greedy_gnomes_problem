package app.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.model.Pair;

public class Path {
    /*-----Main-functions-----*/
    /**
     * Display the result
     * 
     * @param pathList
     * @param grid
     * @return newPath
     */
    public static void displayResult(ArrayList<Pair> path, int[][] grid) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("Golds: " + getMaxGolds(path, grid) + " | " + "Steps: " + getSteps(path));
        printDirection(path);
        System.out.println(path);
        System.out.println("------------------------------------------------------------------");
    }

    /**
     * Get result of Algorithm
     * the first path, which is in the list of all paths that have max golds,
     * is the result path
     * 
     * @param pathList
     * @param grid
     * @return newPath
     */
    public static ArrayList<Pair> getMinimalPathHasMaxGolds(List<ArrayList<Pair>> pathList, int[][] grid) {
        List<ArrayList<Pair>> myPathList = getAllPathsHaveMaxGolds(pathList, grid);

        // get the first path in path list above
        ArrayList<Pair> minimalPath = myPathList.get(0);
        Integer minSteps = minimalPath.size() - 1; // Steps is amount of coordinates -1

        // Loop to find the path which has smaller steps
        for (int i = 1; i <= myPathList.size() - 1; i++) {
            int steps = myPathList.get(i).size() - 1; // Steps is amount of coordinates -1
            if (steps < minSteps) {
                minimalPath = myPathList.get(i);
            }
        }
        return minimalPath;
    }

    /**
     * Get all paths have maximum amount of gold they can find
     * 
     * @param pathList
     * @param grid
     * @return newPath
     */
    public static List<ArrayList<Pair>> getAllPathsHaveMaxGolds(List<ArrayList<Pair>> pathList, int[][] grid) {
        List<ArrayList<Pair>> allPathsHaveMaxGolds = new ArrayList<ArrayList<Pair>>();
        Integer maxGolds = 0;

        for (ArrayList<Pair> path : pathList) {
            if (getMaxGolds(path, grid) == maxGolds) {
                allPathsHaveMaxGolds.add(trimPath(path, grid)); // add all paths that has max golds
            } else if (getMaxGolds(path, grid) > maxGolds) {
                allPathsHaveMaxGolds.clear(); // clear arraylist if has new maxGolds value
                allPathsHaveMaxGolds.add(trimPath(path, grid)); // add all paths that has max golds
                maxGolds = getMaxGolds(path, grid); // new maxGolds value
            }
        }
        return allPathsHaveMaxGolds;
    }

    /*-----Sub-functions-----*/
    /**
     * Convert one path in String to path in ArrayList
     * 
     * @param path
     * @return newPath
     */
    public static ArrayList<Pair> convertStringPathToArrayList(String path) {
        ArrayList<Pair> newPath = new ArrayList<Pair>();

        // list of all pairs of path
        List<String> pairList = new ArrayList<String>(Arrays.asList(path.split(" ")));
        for (String pair : pairList) {
            String[] xy = pair.split(",");
            Pair newPair = new Pair(Integer.parseInt(xy[0].trim()), Integer.parseInt(xy[1].trim()));
            newPath.add(newPair);
        }
        return newPath;
    }

    /**
     * Calculate sum golds of 1 path
     * 
     * @param path
     * @param grid
     * @return newPath
     */
    public static Integer getMaxGolds(ArrayList<Pair> path, int[][] grid) {
        Integer sumGoldsOfPath = 0;
        for (Pair pair : path) {
            sumGoldsOfPath += grid[pair.x][pair.y];
        }
        return sumGoldsOfPath;
    }

    /**
     * Calculate steps of 1 path
     * 
     * @param path
     * @return pathSteps
     */
    public static Integer getSteps(ArrayList<Pair> path) {
        int pathSteps = path.size() - 1; // Steps is amount of coordinates -1
        return pathSteps;
    }

    /**
     * Remove position that contain 0 gold at the end of path
     * 
     * @param path
     * @param grid
     * @return path
     */
    public static ArrayList<Pair> trimPath(ArrayList<Pair> path, int[][] grid) {
        for (int i = path.size() - 1; i >= 0; i--) {
            if (grid[path.get(i).x][path.get(i).y] != 0) {
                break;
            } else {
                path.remove(i);
            }
        }
        return path;
    }

    /**
     * Remove duplicates from an ArrayList
     * 
     * @param list
     * @return newList
     */
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
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

    /**
     * From the path of coordinates pair, print out the direction moving in the gold
     * map
     * Compare next to cell coordinates to calculate Right or Down directions
     * 
     * @param path
     */
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
        System.out
                .println("Direction: " + pathDirection.toString().replace(", ", "").replace("[", "").replace("]", ""));
    }
}
