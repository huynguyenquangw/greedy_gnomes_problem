import java.io.FileReader;
import java.util.Arrays;
import java.io.IOException;
import java.io.BufferedReader;

public class DynamicProgramming {

    public static int findMaxPath(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int rock = -1;

        // base code to check null
        if (rows == 0) {
            return 0;
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

        System.out.println(" ");
        System.out.println(Arrays.deepToString(dp).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        // if (mat[rows - 1][cols - 1] == rock) {
        // dp[rows - 1][cols - 1] += 1;
        // }

        return dp[rows - 1][cols - 1];
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
            System.out.println();
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

    public static void main(String[] args) {
        try {
            String[][] map = parseMap("3_3.txt");
            int[][] mat = parseIntMap(map);
            displayMat(mat);

            System.out.println(Arrays.deepToString(mat).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
            System.out.println(findMaxPath(mat));
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

}