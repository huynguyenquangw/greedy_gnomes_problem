package app.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.model.Pair;

public class Path {
    public static void displayAllPaths(ArrayList<String> pathList) throws IOException {
        ArrayList<String> removedDuplicatePathList = removeDuplicates(pathList);
        System.out.println();
        for(String path : removedDuplicatePathList){
            System.out.println(path);
        }
        System.out.println("This map has: " + removedDuplicatePathList.size() + " paths" + "\n");
    }

    public static List<ArrayList<Pair>> convertStringPathToList(ArrayList<String> pathList) {
        ArrayList<String> removedDuplicatePathList = removeDuplicates(pathList);

        List<ArrayList<Pair>> myPathList = new ArrayList<ArrayList<Pair>>();

        for(String path : removedDuplicatePathList){
            List<String> newPath = new ArrayList<String>(Arrays.asList(path.split(" ")));

            ArrayList<Pair> myPath = new ArrayList<Pair>();

            for(String pair : newPath){
                String[] p1p2 = pair.split(",");
                Pair myPair = new Pair(Integer.parseInt(p1p2[0].trim()), Integer.parseInt(p1p2[1].trim()));
                myPath.add(myPair);
            }
            myPathList.add(myPath);
        }
        return myPathList;
    }

    // public static printGoldsAndSteps(ArrayList<Pair>)

    // public static ArrayList<Pair> getMinimalPathHasMaxGolds(List<ArrayList<Pair>> allPathsHaveMaxGolds){
    //     ArrayList<Pair> path = allPathsHaveMaxGolds.get(0);
    //     Integer minSteps = path.size();

    //     for(int i = 1; i <= allPathsHaveMaxGolds.size() - 1; i++){
    //         int steps = allPathsHaveMaxGolds.get(i).size();
    //         if(steps < minSteps){
    //             path = allPathsHaveMaxGolds.get(i);
    //         }
    //     }
    //     System.out.println(minSteps);
    //     return path;
    // }

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
        System.out.println();

    }

    /* OK */
    public static ArrayList<Pair> getResult(List<ArrayList<Pair>> myPathList, int[][] grid){
        List<ArrayList<Pair>> allPathsHaveMaxGolds = new ArrayList<ArrayList<Pair>>();
        Integer maxGolds = 0;

        for(ArrayList<Pair> eachPath : myPathList){
            if(getSumGoldsOfPath(eachPath, grid) == maxGolds){
                allPathsHaveMaxGolds.add(trimPath(eachPath, grid));
            } else if(getSumGoldsOfPath(eachPath, grid) > maxGolds){
                allPathsHaveMaxGolds.clear();
                allPathsHaveMaxGolds.add(trimPath(eachPath, grid));
                maxGolds = getSumGoldsOfPath(eachPath, grid);
            } 
        }

        // get the first path in path list above
        ArrayList<Pair> path = allPathsHaveMaxGolds.get(0);
        Integer minSteps = path.size();

        for(int i = 1; i <= allPathsHaveMaxGolds.size() - 1; i++){
            int steps = allPathsHaveMaxGolds.get(i).size();
            if(steps < minSteps){
                path = allPathsHaveMaxGolds.get(i);
            }
        }
        System.out.println();
        System.out.println("Golds: " + maxGolds + " | " + "Steps: " + minSteps);
        return path;
    }

    /* OK */
    public static Integer getSumGoldsOfPath(ArrayList<Pair> path, int[][] grid){
        Integer sumGoldsOfPath = 0;
        for(Pair pair : path){
            sumGoldsOfPath += grid[pair.x][pair.y];
        }
        return sumGoldsOfPath;
    }

    /* OK */
    // Function to remove coordinate
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

    /* OK */
    // Function to remove duplicates from an ArrayList
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list){
        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();
        // Traverse through the first list
        for (T element : list) {
            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        // return the new list
        return newList;
    }
}
