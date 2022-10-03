package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int N;
    private int T;
    private double[] avgOpenTillPercolate;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.T = T;
        this.N = N;
        avgOpenTillPercolate = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                percolation.open(row, col);
            }
            avgOpenTillPercolate[i] = (double) (percolation.numberOfOpenSites() / (N * N));
        }
    }

    public double mean() {
        return StdStats.mean(avgOpenTillPercolate);
    }

    public double stddev() {
        return StdStats.stddev(avgOpenTillPercolate);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
}
