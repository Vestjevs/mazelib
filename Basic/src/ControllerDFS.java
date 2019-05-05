import java.util.BitSet;
import java.util.Random;

public class ControllerDFS {


    public static Surface maze(int numColumns, int numRows, int startColumn, int startRow) {
        Surface surface = new Surface(numColumns, numRows);
        int startvertex = surface.vertex(startColumn, startRow);
        BitSet visited = new BitSet(numColumns * numRows);
        traverseRecursive(surface, startvertex, visited);
        return surface;
    }

    private static void traverseRecursive(Surface surface, int v, BitSet visited) {
        visited.set(v);
        for (Direction dir = unvisitedDir(surface, v, visited); dir != null; dir = unvisitedDir(surface, v, visited)) {
            surface.addEdge(v, dir);
            traverseRecursive(surface, surface.neighbor(v, dir), visited);
        }

    }

    private static Direction unvisitedDir(Surface surface, int v, BitSet visited) {
        Direction[] candidates = new Direction[4];
        int numCandidates = 0;
        for (Direction dir : Direction.values()) {
            int neighbor = surface.neighbor(v, dir);
            if (neighbor != surface.NO_VERTEX && !visited.get(neighbor)) {
                candidates[numCandidates++] = dir;
            }
        }
        return numCandidates == 0 ? null : candidates[new Random().nextInt(numCandidates)];
    }



}
