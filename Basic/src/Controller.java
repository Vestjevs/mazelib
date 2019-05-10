import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Deque;
import java.util.Random;

public class Controller {


    public static Grid maze(int numColumns, int numRows) {
        int startColumn = new Random().nextInt(numColumns);
        int startRow = new Random().nextInt(numRows);
        Grid grid = new Grid(numColumns, numRows);
        int startvertex = grid.vertex(startColumn, startRow);
        BitSet visited = new BitSet(numColumns * numRows);
        int choice = new Random().nextInt(100);
        if (choice < 50 && choice >= 0) {
            randomDFSNonrecursive(grid, startvertex, visited);
        } else {
            traverseRecursive(grid, startvertex, visited);
        }
        return grid;
    }

    private static void traverseRecursive(Grid grid, int v, BitSet visited) {
        visited.set(v);
        for (Direction dir = unvisitedDir(grid, v, visited); dir != null; dir = unvisitedDir(grid, v, visited)) {
            grid.addEdge(v, dir);
            traverseRecursive(grid, grid.neighbor(v, dir), visited);
        }

    }

    private static void randomDFSNonrecursive(Grid grid, int v, BitSet visited) {
        Deque<Integer> stack = new ArrayDeque<>();
        visited.set(v);
        stack.push(v);
        while (!stack.isEmpty()) {
            Direction dir = unvisitedDir(grid, v, visited);
            if (dir != null) {
                int neighbor = grid.neighbor(v, dir);
                grid.addEdge(v, dir);
                visited.set(neighbor);
                stack.push(neighbor);
                v = neighbor;
            } else {
                v = stack.pop();
            }
        }
    }

    private static Direction unvisitedDir(Grid grid, int v, BitSet visited) {
        Direction[] candidates = new Direction[4];
        int numCandidates = 0;
        for (Direction dir : Direction.values()) {
            int neighbor = grid.neighbor(v, dir);
            if (neighbor != grid.NO_VERTEX && !visited.get(neighbor)) {
                candidates[numCandidates++] = dir;
            }
        }
        return numCandidates == 0 ? null : candidates[new Random().nextInt(numCandidates)];
    }


}
