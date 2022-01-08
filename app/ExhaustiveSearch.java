package app;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.Utils.Map;
import app.Utils.Path;
import app.model.Pair;

public class ExhaustiveSearch {
    public static final String ROCK = "-1";
    public static ArrayList<String> pathList = new ArrayList<String>();
    
    public static void findPath(String[][] grid){        
        findPathRecursive(0, 0, grid, "");
    }

    public static void findPathRecursive(int x, int y, String[][] grid, String path){

        /* (x, y) is coordinates of the POINTER */
        /* x: current row                       */
        /* y: current column                    */

        int rows = grid.length;     // rows of the grid
        int cols = grid[0].length;  // columns of the grid

        // Pointer hits the WALL on the right
        if(y == cols - 1){
            for (int i = x; i <= rows - 1; i++) {
                // Pointer hits the ROCK
                if (grid[i][y].equals(ROCK)) {
                    pathList.add(path);
                    return; // hits the ROCK -> finish
                }              
                path += (new Pair(i, y)).toString(); // Append coordinates
            }
            // Add the path path list
            pathList.add(path);
            return;
        }

        // Pointer hits the WALL on the bottom
        if(x == rows - 1){
            for (int i = y; i <= cols - 1; i++) {
                if (grid[x][i].equals(ROCK)) {
                    pathList.add(path);
                    return; // hits the ROCK -> finish
                }
                path += (new Pair(x, i)).toString(); // Append coordinates
            }
            // Add the path path list
            pathList.add(path);
            return;
        }
        
        // Pointer hits the ROCK in normal move
        if (grid[x][y].equals(ROCK)) {
            pathList.add(path);
            return; // got the ROCK -> finish
        }

        // Append coordinates to the path
        path += (new Pair(x, y)).toString();

        // Normal move recursive
        findPathRecursive(x + 1, y, grid, path);
        findPathRecursive(x, y + 1, grid, path);
    }

    public static int[][] parseIntMap(String[][] map) {
        int[][] mat = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                mat[i][j] = Integer.parseInt(map[i][j]);
            }
        }
        return mat;
    }

    public static void main(String[] args) throws IOException{

        String[][] map = Map.parseMap("./app/maps/19_19.txt");
        int[][] intMap = parseIntMap(map);
        Map.displayMap(map);
        findPath(map);
        
        List<ArrayList<Pair>> myPathList = Path.convertStringPathToList(pathList);
        Path.getResult(myPathList, intMap);
    }
}
