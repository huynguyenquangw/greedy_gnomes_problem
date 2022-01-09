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
        String[][] map = Map.parseMap("./app/maps/19_19.txt");
        int[][] intMap = Map.parseIntMap(map);

        Map.displayMap(intMap);
        ArrayList<Pair> myResult = Path.getMinimalPathHasMaxGolds(intMap);
        Path.displayResult(myResult, intMap);
    }
}
