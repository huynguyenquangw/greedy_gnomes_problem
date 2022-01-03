import java.io.FileNotFoundException;

public class GreedyGnomesProblem {

    public static int maxPathSum(String[][] grid) {
        return dfs(0, 0, grid);
    }

    public static int dfs(int i, int j, String[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // return last cell
        if (i == rows - 1 && j == cols - 1 && grid[i][j] != "X") {
            // System.out.println("last cell " + grid[i][j]);
            return parseInt(grid[i][j]);
        }

        if (i < rows - 1 && j < cols - 1 && grid[i][j] != "X") {
            // System.out.println("all possible cell " + grid[i][j]);
            // System.out.println(parseInt(grid[i][j]) + dfs(i + 1, j, grid));
            int r1 = parseInt(grid[i][j]) + dfs(i + 1, j, grid);
            int r2 = parseInt(grid[i][j]) + dfs(i, j + 1, grid);

            // System.out.println(Math.max(r1, r2));
            return Math.max(r1, r2);
        }

        if (i < rows - 1 && grid[i][j] != "X") {

            // System.out.println("down cell " + grid[i][j]);
            return parseInt(grid[i][j]) + dfs(i + 1, j, grid);
        }

        if (j < cols - 1 && grid[i][j] != "X") {
            // System.out.println("right cell" + grid[i][j]);
            return parseInt(grid[i][j]) + dfs(i, j + 1, grid);
        }

        return 0;
    }

    static int parseInt(String n) {
        return Integer.parseInt(n);
    }

    public static void printAllPath(String grid[][], int currRow, int currCol, String path, int golds, int steps) {

        int rows = grid.length;
        int cols = grid[0].length;

        // base code
        if (grid[currRow][currCol] == "X") {
            path += "end" + grid[currRow][currCol];
            System.out.println(path + " Gold: " + golds + " Steps: " + steps);
            return;
        }

        if (currRow == rows - 1) {
            for (int i = currCol; i <= cols - 1; i++) {
                path += "-R:" + grid[currRow][i];
                steps += 1;
                golds += parseInt(grid[currRow][i]);
            }
            System.out.println(path + " Gold: " + golds + " Steps: " + steps);
            return;
        }

        if (currCol == cols - 1) {
            for (int i = currRow; i <= rows - 1; i++) {
                path += "-D:" + grid[i][currCol];
                steps += 1;
                golds += parseInt(grid[i][currCol]);
            }
            System.out.println(path + " Gold: " + golds + " Steps: " + steps);
            return;
        }

        path = path + "-" + grid[currRow][currCol];
        golds += parseInt(grid[currRow][currCol]);
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

    public static void main(String[] args) throws FileNotFoundException {
        // char[][] map1 = {
        // {'.', 'X', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X',
        // '.', '.', '.', '.', '.', '.', '.', '.'},
        // {'.', '.', '.', '.', '.', '.', '2', '.', '.', '.', '.', '.', '.', '.', '.',
        // '.', '.', '.', '.', '.', '.', '.', '.'},
        // {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',
        // '.', '.', '6', '.', '.', '.', '.', '.'},
        // {'2', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '3', '.', '.',
        // '.', '.', '1', '.', '.', '.', '.', '.'},
        // {'.', 'X', '4', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', 'X', '.',
        // '.', '.', '1', '.', '.', '.', '.', '.'},
        // {'.', '.', '.', '.', '.', '.', '.', '2', '.', '.', '.', '.', '.', '.', '.',
        // 'X', '.', '2', '.', '.', '.', '.', '.'},
        // {'.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.',
        // '.', '.', '2', 'X', '.', '.', '.', '.'},
        // {'2', '.', '2', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '4', '.', '.',
        // '1', '.', '.', '.', '.', '.', '.', '.'},
        // {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',
        // '.', '.', '.', '.', '.', '.', '.', '.'},
        // {'.', '.', '.', 'X', '.', '.', '3', '.', '.', '.', '.', '.', '.', '.', '.',
        // '.', '2', '.', '.', '.', '.', '.', '.'},
        // {'1', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '2', '.', '.', '.', '.',
        // '.', '.', '3', '.', '.', 'X', '.', '.'},
        // {'2', 'X', 'X', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',
        // 'X', '.', '.', '.', '.', '.', 'X', '.'},
        // };

        String mat1[][] = {
                { "0", "3", "X", "6", "X" },
                { "0", "0", "8", "4", "0" },
                { "2", "X", "1", "X", "0" },
                { "X", "3", "6", "X", "0" },
                { "1", "2", "X", "0", "1" },
        };

        displayMap(mat1);
        printAllPath(mat1, 0, 0, "", 0, 0);
        System.out.println(maxPathSum(mat1));
    }
}

// String mapFile = "./map.txt";
// FileInputStream fileInputStream = new FileInputStream(mapFile);
// Scanner scanner = new Scanner(fileInputStream);

// class Node {
// int[] data;
// Node left, right;

// public Node(int[] position)
// {
// data = position;
// left = right = null;
// }
// }

// class BinaryTree {
// Node root;

// int size;

// BinaryTree() {root = null;}

// boolean insert(int[] value) {
// if (root == null) root = new Node(value);
// else {
// Node parent = null, current = root;
// while (current != null) {
// if (value[1] > current.data[1]){
// parent = current;
// current = current.left;
// }
// else if (value[0] > current.data[0]){
// parent = current;
// current = current.right;
// }
// else {
// return false;
// }
// }
// if (value < parent.data){
// parent.left = new Node(value);
// }
// else {
// parent.right = new Node(value);
// }
// }
// size++;
// return true;
// }}

//