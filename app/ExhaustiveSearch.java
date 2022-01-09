package app;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.Utils.Map;
import app.Utils.Path;
import app.model.Pair;

public class ExhaustiveSearch {
    public static void main(String[] args) throws IOException{
        // add map txt file
        String[][] map = Map.parseMap("./app/maps/3_3.txt"); // parse to String map
        int[][] intMap = Map.parseIntMap(map); // parse to int map

        Map.displayMap(intMap); // display map
        ArrayList<Pair> myResult = Path.getMinimalPathHasMaxGolds(intMap); // get the result path (contain coordinates)
        Path.displayResult(myResult, intMap); // display result(golds, steps, direction)
    }
}
