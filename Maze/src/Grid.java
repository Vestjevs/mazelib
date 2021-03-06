import java.util.Arrays;
import java.util.BitSet;


public class Grid {
    public final int NO_VERTEX = -1;

    public final int numCols;
    public final int numRows;

    private BitSet edges;

    private int bit(int v, Direction dir) {
        return 4 * v + dir.ordinal();
    }

    public Grid(int numCols, int numRows) {
        this.numCols = numCols;
        this.numRows = numRows;
        this.edges = new BitSet(numCols * numRows * 4);
    }

    public int vertex(int col, int row) {
        return this.numCols * col + row;
    }

    public int column(int v) {
        return v % this.numCols;
    }

    public int row(int v) {
        return v / this.numCols;
    }

    public int numEdges() {
        return this.edges.cardinality() / 2;
    }

    public void addEdge(int v, Direction dir) {
        edges.set(bit(v, dir));
        edges.set(bit(neighbor(v, dir), dir.opposite()));
    }

    public boolean hasEdge(int v, Direction dir) {
        return edges.get(bit(v, dir));
    }

    public int neighbor(int v, Direction dir) {
        int column = this.column(v) + dir.x;
        int row = this.row(v) + dir.y;
        return column >= 0 && column < this.numCols && row >= 0 && row < this.numRows ? this.vertex(column, row) : this.NO_VERTEX;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                builder.append(!hasEdge(vertex(col, row), Direction.NORTH) ? "+---" : "+   ");
            }
            builder.append("+\n");
            for (int col = 0; col < numCols; col++) {
                builder.append(!hasEdge(vertex(col, row), Direction.WEST) ? "|   " : "    ");
            }
            builder.append("|\n");
        }
        for (int col = 0; col < numCols; col++) {
            builder.append("+---");
        }
        builder.append("+\n");
        builder.append(String.format("\n\n[%d cols, %d rows, %d edges]\n", numCols, numRows, numEdges()));
        return builder.toString();
    }
}
