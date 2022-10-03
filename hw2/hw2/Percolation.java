package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF percolationGrid;
    private WeightedQuickUnionUF virtualPercolationGrid;
    private boolean[][] checkOpen;
    private int n;
    private int top;
    private int bottom;
    private int openSite = 0;

    private int xyTo1D(int row, int col) {
        return n * row + col;
    }

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        n = N;
        int size = N * N;
        top = size;
        bottom = size + 1;
        percolationGrid = new WeightedQuickUnionUF(size + 2);
        virtualPercolationGrid = new WeightedQuickUnionUF(size + 1);
        checkOpen = new boolean[n][n];
        for (int i = 0; i < N; i++) {
            percolationGrid.union(top, xyTo1D(0, i));
            percolationGrid.union(bottom, xyTo1D(N - 1, i));
            virtualPercolationGrid.union(top, xyTo1D(0, i));
        }
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                checkOpen[r][c] = false;
            }
        }
    }

    public void open(int row, int col) {
        if (row < 0 || col < 0 || row > n - 1 || col > n - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (checkOpen[row][col]) {
            return;
        }
        checkOpen[row][col] = true;
        openSite += 1;
        int[] rows = {0, -1, 0, 1};
        int[] cols = {-1, 0, 1, 0};
        for (int count = 0; count < 4; count++) {
            int newRow = row + rows[count];
            int newCol = col + cols[count];
            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
                if (checkOpen[newRow][newCol]) {
                    int currentCell = xyTo1D(row, col);
                    int aroundCell = xyTo1D(newRow, newCol);
                    percolationGrid.union(currentCell, aroundCell);
                    virtualPercolationGrid.union(currentCell, aroundCell);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row > n - 1 || col > n - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return checkOpen[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row > n - 1 || col > n - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            return false;
        }
        return virtualPercolationGrid.connected(xyTo1D(row, col), top);
    }

    public int numberOfOpenSites() {
        return openSite;
    }

    public boolean percolates() {
        return percolationGrid.connected(top, bottom);
    }
}
