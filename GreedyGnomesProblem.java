package draft;

import java.io.FileNotFoundException;

public class GreedyGnomesProblem {

    public static int maxPathSum(String[][] grid) {
        return dfs(0,0,grid);
    }
    
    public static int dfs(int i, int j, String[][] grid){
        // if(!grid[i][j].equals("X")){
            if(i==grid.length-1 && j==grid[0].length-1){
                return Integer.parseInt(grid[i][j]);
            }
        
            if(i<grid.length-1 && j<grid[0].length-1){
                int r1 = Integer.parseInt(grid[i][j]) + dfs(i+1, j, grid);
                int r2 = Integer.parseInt(grid[i][j]) + dfs(i, j+1, grid);
                return Math.max(r1,r2);
            }
        
            if(i<grid.length-1){
                return Integer.parseInt(grid[i][j]) + dfs(i+1, j, grid);
            }
        
            if(j<grid[0].length-1){
                return Integer.parseInt(grid[i][j]) + dfs(i, j+1, grid);
            }
        // }

        return 0;
    }

    public static void inputProcessing() {}

    public static void validate() {}

    public static void displayMap(String[][] map){
        for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[i].length; j++) {
            System.out.print(map[i][j]);
        }
        System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // char[][] map1 = {
        // {'.', 'X', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.'},
        // {'.', '.', '.', '.', '.', '.', '2', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
        // {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '6', '.', '.', '.', '.', '.'},
        // {'2', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '3', '.', '.', '.', '.', '1', '.', '.', '.', '.', '.'},
        // {'.', 'X', '4', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', 'X', '.', '.', '.', '1', '.', '.', '.', '.', '.'},
        // {'.', '.', '.', '.', '.', '.', '.', '2', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '2', '.', '.', '.', '.', '.'},
        // {'.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '2', 'X', '.', '.', '.', '.'},
        // {'2', '.', '2', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '4', '.', '.', '1', '.', '.', '.', '.', '.', '.', '.'},
        // {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
        // {'.', '.', '.', 'X', '.', '.', '3', '.', '.', '.', '.', '.', '.', '.', '.', '.', '2', '.', '.', '.', '.', '.', '.'},
        // {'1', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '2', '.', '.', '.', '.', '.', '.', '3', '.', '.', 'X', '.', '.'},
        // {'2', 'X', 'X', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', 'X', '.'},
        // };

        int mat[][] = { { 1, 2, 3 },
                        { 4, 5, 6 } };

        String mat1[][] = { { "0", "3", "X", "5", "0"},
                            { "1", "0", "6", "8", "0"},
                            { "2", "3", "0", "9", "0"},
                            { "0", "8", "6", "0", "0"},
                    };

        System.out.println(
            maxPathSum(mat1)
        );

        // displayMap(mat);

        // String mapFile = "./map.txt";
        // FileInputStream fileInputStream = new FileInputStream(mapFile);
        // Scanner scanner = new Scanner(fileInputStream);
    }
}




















// class Node {
//     int[] data;
//     Node left, right;

//     public Node(int[] position)
//     {
//         data = position;
//         left = right = null;
//     }
// }

// class BinaryTree {
//     Node root;

//     int size;

//     BinaryTree() {root = null;}

//     boolean insert(int[] value) {
//         if (root == null) root = new Node(value);
//         else {
//             Node parent = null, current = root;
//             while (current != null) {
//                 if (value[1] > current.data[1]){
//                     parent = current;
//                     current = current.left;
//                 }
//                 else if (value[0] > current.data[0]){
//                     parent = current;
//                     current = current.right;
//                 }
//                 else {
//                     return false;
//                 }
//             }
//             if (value < parent.data){
//                 parent.left = new Node(value);
//             }
//             else {
//                 parent.right = new Node(value);
//             }
//         }
//         size++;
//         return true;
//     }}