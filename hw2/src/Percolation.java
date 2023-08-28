import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] netOpenStat;
    private WeightedQuickUnionUF netWaterStat;
    private int size;
    private int openSize;

    public Percolation(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Illegal value");
        }
        netOpenStat = new int[n][n];
        size = n;
        netWaterStat = new WeightedQuickUnionUF(n * n + 2);
        openSize = 0;
    }

    public void waterFlow(int row, int col) {
        if (row == 0) {
            netWaterStat.union(position2Index(row, col), 0);
        } else {
            if (isOpen(row - 1, col)) {
                netWaterStat.union(position2Index(row, col), position2Index(row - 1, col));
            }
        }
        if (row == size - 1) {
            netWaterStat.union(position2Index(row, col), 1);
        } else {
            if (isOpen(row + 1, col)) {
                netWaterStat.union(position2Index(row, col), position2Index(row + 1, col));
            }
        }
        if (col > 0) {
            if (isOpen(row, col - 1)) {
                netWaterStat.union(position2Index(row, col), position2Index(row, col - 1));
            }
        }
        if (col < size - 1) {
            if (isOpen(row, col + 1)) {
                netWaterStat.union(position2Index(row, col), position2Index(row, col + 1));
            }

        }
    }

    public void open(int row, int col) {
        positionCheckException(row, col);
        netOpenStat[row][col] = 1;
        openSize += 1;
        waterFlow(row,col);
    }

    public boolean isOpen(int row, int col) {
        positionCheckException(row, col);
        return netOpenStat[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        return netWaterStat.connected(position2Index(row,col),0);
    }

    public int numberOfOpenSites() {
        return openSize;
    }

    public boolean percolates() {
        return netWaterStat.connected(0,1);
    }
    public void positionCheckException(int row, int col) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            return;
        }
        throw new IllegalArgumentException("Position Error");
    }
    public boolean positionCheckBoolean(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }
    public int position2Index(int row, int col) {
        positionCheckException(row, col);
        return row * size + col + 2;
    }

}
