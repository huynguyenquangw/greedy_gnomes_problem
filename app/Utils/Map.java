package app.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Map {
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
                    if ( line.trim().length() == 0 ) {
                        break; // Skip blank lines at the end
                    }
                    int column = 0;
                    for (String character : line.replace(".", "0").replace("X", "-1").split(" ")) {
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
    
    public static void displayMap(String[][] map){
        System.out.println(Arrays.deepToString(map).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

    }
}