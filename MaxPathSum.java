import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class MaxPathSum {

    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        // initialize top row
        for (int i = 1; i < n - 1; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        // initialize left column
        for (int j = 1; j < m - 1; j++) {
            dp[j][0] = dp[0][j - 1] + grid[j][0];
        }

        // fill up the dp table
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {

                if (dp[i + 1][j] > dp[i][j + 1]) {
                    dp[i][j] = dp[i + 1][j] + grid[i][j];
                } else {
                    dp[i][j] = dp[i][j + 1] + grid[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // public static int maxPathSum(int[][] mat, int row, int col) {
    // if (mat == null || mat.length == 0) {
    // return 0;
    // }

    // int[][] dp = new int[row][col];

    // for (int i = 0; i < dp.length - 1; i++) {
    // for (int j = 0; j < dp[i].length - 1; j++) {
    // dp[i][j] += mat[i][j];
    // if (i < dp.length - 1 && j < dp[i].length - 1) {
    // dp[i][j] += Math.max(dp[i + 1][j], dp[i][j + 1]);
    // }
    // if (i < dp.length - 1) {
    // dp[i][j] += dp[i + 1][j];
    // } else if (j < dp[i].length - 1) {
    // dp[i][j] += dp[i][j + 1];
    // }
    // }
    // }

    // return dp[dp.length - 1][dp[0].length - 1];
    // }

    public static int maxPathSum(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int rock = -1;
        if (rows == 0) {
            return 0;
        }

        int[][] dp = new int[rows][cols];
        dp[0][0] = mat[0][0];

        // fill first row
        for (int i = 1; i < cols; i++) {
            if (mat[0][i] == rock || dp[0][i - 1] == rock) {
                dp[0][i] = -1;
            } else {
                dp[0][i] = dp[0][i - 1] + mat[0][i];
            }
        }

        // fill first column
        for (int j = 1; j < rows; j++) {
            if (mat[j][0] == rock || dp[j - 1][0] == rock) {
                dp[j][0] = -1;
            } else {
                dp[j][0] = dp[j - 1][0] + mat[j][0];
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (mat[i][j] != rock) {
                    if (dp[i - 1][j] == rock && dp[i][j - 1] == rock) {
                        dp[i][j] = -1;
                    } else {
                        dp[i][j] = mat[i][j] + printMax(dp[i][j - 1], dp[i - 1][j]);
                    }
                } else {
                    dp[i][j] = -1;
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
                // displayMap(map);
                return map;
            }
        } finally {
            br.close();
        }
        return null;
    }

    static int printMax(int right, int down) {
        if (right > down) {
            System.out.println("-R" + right);
        } else {
            System.out.println("-D" + down);
        }

        return Math.max(right, down);
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
            String[][] map = parseMap("27_27.txt");
            int[][] mat = parseIntMap(map);
            displayMat(mat);

            int row = mat.length;
            int col = mat[0].length;
            System.out.println(Arrays.deepToString(mat).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
            System.out.println(maxPathSum(mat));
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

}
