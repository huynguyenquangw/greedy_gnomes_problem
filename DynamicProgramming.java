import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;
import java.io.BufferedReader;

public class DynamicProgramming {

    public static int[][] findMaxPath(int[][] mat) {
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
        //
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

    public static class Pair {
        Integer x, y;

        public Pair() {
        }

        public Pair(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        public Integer getX() {
            return x;
        }

        public Integer getY() {
            return y;
        }

        public String toString() {
            return x + "," + y + " ";
        }
    }

    public static Pair findMaxCord(int[][] mat) {

        int max = mat[0][0];

        Pair maxCord = new Pair();

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

    public static String[][] parseMap(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            String line = br.readLine();
            String[] mapSize;
            int row = 0;
            if (line != null) {
                mapSize = line.split(" ");
                String[][] map = new String[Integer.parseInt(mapSize[0])][Integer.parseInt(mapSize[1])];
                line = br.readLine();
                while (line != null) {
                    int column = 0;
                    for (String character : line.replace("X", "-1").replace(".", "0").split(" ")) {
                        map[row][column] = character;
                        column++;
                    }
                    row++;
                    line = br.readLine();
                }
                return map;
            }
        } finally {
            br.close();
        }
        return null;
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

    static void displayMat(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println();

        }
    }

    public static void printPath(Pair maxCord, int[][] mat) {
        int i = maxCord.x, j = maxCord.y;
        System.out.println("Golds: " + mat[i][j]);
        ArrayList<Pair> pathCord = new ArrayList<>();

        pathCord.add(new Pair(i, j));
        while (i > 0 && j > 0) {
            if (mat[i - 1][j] >= mat[i][j - 1]) {
                pathCord.add(new Pair(i - 1, j));
                i -= 1;
            } else {
                pathCord.add(new Pair(i, j - 1));
                j -= 1;
            }
        }
        pathCord.add(new Pair(0, 0));
        reverseArrayList(pathCord);

        System.out.println("Steps: " + pathCord.size());
        printDirection(pathCord);
    }

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

    public static ArrayList<Pair> reverseArrayList(ArrayList<Pair> array) {
        Collections.reverse(array);
        return array;
    }

    public static void main(String[] args) {
        try {
            String[][] map = parseMap("25_8.txt");
            int[][] mat = parseIntMap(map);

            // start time
            int[][] dp = findMaxPath(mat);
            Pair maxCord = findMaxCord(dp);
            printPath(maxCord, dp);

            // end time
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

}