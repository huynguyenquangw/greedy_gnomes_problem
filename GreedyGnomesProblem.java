import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

public class GreedyGnomesProblem {

    public static int maxPathSum(String[][] grid) {
        return dfs(0, 0, grid);
    }

    public static int dfs(int i, int j, String[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        String rock = "-1";
        // return last cell
        if (i == rows - 1 && j == cols - 1 && !grid[i][j].equals("-1")) {
            return Integer.parseInt(grid[i][j]);
        }

        if (i < rows - 1 && j < cols - 1 && !grid[i][j].equals("-1")) {
            int r1 = Integer.parseInt(grid[i][j]) + dfs(i + 1, j, grid);
            int r2 = Integer.parseInt(grid[i][j]) + dfs(i, j + 1, grid);

            return Math.max(r1, r2);
        }

        if (i < rows - 1 && !grid[i][j].equals("-1")) {
            return Integer.parseInt(grid[i][j]) + dfs(i + 1, j, grid);
        }

        if (j < cols - 1 && !grid[i][j].equals("-1")) {
            // System.out.println("right cell" + grid[i][j]);
            return Integer.parseInt(grid[i][j]) + dfs(i, j + 1, grid);
        }

        return 0;
    }

    public static void printAllPath(String grid[][], int currRow, int currCol, String path, int golds, int steps) {

        int rows = grid.length;
        int cols = grid[0].length;

        String rock = "-1";

        // base code
        if (grid[currRow][currCol].equals(rock)) {
            path += "end" + grid[currRow][currCol];

            // ? array += pair.setValue(currRow,currCol)

            System.out.println(path + " Gold: " + golds + " Steps: " + steps);
            return;
        }

        if (currRow == rows - 1 && !grid[currRow][currCol].equals(rock)) {
            for (int i = currCol; i <= cols - 1; i++) {
                path += "-R:" + grid[currRow][i];
                steps += 1;
                golds += Integer.parseInt(grid[currRow][i]);
            }
            System.out.println(path + " Gold: " + (golds + 1) + " Steps: " + (steps - 1));
            return;
        }

        if (currCol == cols - 1 && !grid[currRow][currCol].equals(rock)) {
            for (int i = currRow; i <= rows - 1; i++) {
                path += "-D:" + grid[i][currCol];
                steps += 1;
                golds += Integer.parseInt(grid[i][currCol]);
            }
            System.out.println(path + " Gold: " + (golds + 1) + " Steps: " + (steps - 1));
            return;
        }

        path = path + "-" + grid[currRow][currCol];
        golds += Integer.parseInt(grid[currRow][currCol]);
        steps += 1;

        printAllPath(grid, currRow + 1, currCol, path + "D-", golds, steps);
        printAllPath(grid, currRow, currCol + 1, path + "R-", golds, steps);
    }

    public static void displayMap(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public static String[][] inputProcessing(String[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == ".") {
                    mat[i][j] = "0";
                }
            }
        }
        return mat;

    };

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

    public static void main(String[] args) {

        String[][] mat1 = {
                { ".", "X", ".", ".", "X", ".", ".", ".", ".", ".", ".", ".", ".", ".", "X",
                        ".", ".", ".", ".", ".", ".", ".", "." },
                { ".", ".", ".", ".", ".", ".", "2", ".", ".", ".", ".", ".", ".", ".", ".",
                        ".", ".", ".", ".", ".", ".", ".", "." },
                { ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".",
                        ".", ".", "6", ".", ".", ".", ".", "." },
                { "2", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "3", ".", ".",
                        ".", ".", "1", ".", ".", ".", ".", "." },
                { ".", "X", "4", ".", ".", ".", ".", ".", ".", ".", ".", "X", ".", "X", ".",
                        ".", ".", "1", ".", ".", ".", ".", "." },
                { ".", ".", ".", ".", ".", ".", ".", "2", ".", ".", ".", ".", ".", ".", ".",
                        "X", ".", "2", ".", ".", ".", ".", "." },
                { ".", ".", ".", "X", ".", ".", ".", ".", ".", ".", "X", ".", ".", ".", ".",
                        ".", ".", "2", "X", ".", ".", ".", "." },

                { "2", ".", "2", ".", ".", ".", ".", ".", ".", ".", ".", "X", "4", ".", ".",
                        "1", ".", ".", ".", ".", ".", ".", "." },
                { ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".",
                        ".", ".", ".", ".", ".", ".", ".", "." },
                { ".", ".", ".", "X", ".", ".", "3", ".", ".", ".", ".", ".", ".", ".", ".",
                        ".", "2", ".", ".", ".", ".", ".", "." },
                { "1", ".", ".", "X", ".", ".", ".", ".", ".", ".", "2", ".", ".", ".", ".",
                        ".", ".", "3", ".", ".", "X", ".", "." },
                { "2", "X", "X", "X", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".",
                        "X", ".", ".", ".", ".", "." }
        };

        String mat2[][] = {
                { "0", "3", "X", "6", "X" },
                { "0", "0", "8", "4", "0" },
                { "2", "X", "1", "X", "0" },
                { "X", "3", "6", "X", "0" },
                { "X", "X", "1", "0", "0" },
        };
        // displayMap(mat2);
        // System.out.println(Arrays.deepToString(mat2));
        // printAllPath(mat2, 0, 0, "", 0, 0);

        String mat3[][] = {
                { ".", ".", ".", "13", ".", ".", "2", "." },
                { ".", "X", "2", ".", ".", ".", ".", "." },
                { "X", "2", ".", ".", "X", ".", ".", "." },
                { ".", ".", "1", ".", ".", ".", ".", "3" },
                { "1", "2", ".", "0", "1", ".", ".", "0" },
        };
        // String[][] pMat3 = inputProcessing(mat3);
        // displayMap(pMat3);
        // System.out.println(maxPathSum(pMat3));
        // printAllPath(pMat3, 0, 0, "", 0, 0);

        try {
            String[][] map = parseMap("27_27.txt");
            displayMap(map);
            // printAllPath(map, 0, 0, "", 0, 0);
            int maxPoint = maxPathSum(map);
            System.out.println(maxPoint);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
