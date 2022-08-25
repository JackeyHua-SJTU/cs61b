package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    private double[] ans;
    private Percolation pc;
    private double mean;
    private double stddev;
    private int time;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        time = T;
        ans = new double[T];
        int index = 0;
        for (int i = 0; i < T; i += 1) {
            pc = pf.make(N);
            while (!pc.percolates()) {
                int rand1 = StdRandom.uniform(N);
                int rand2 = StdRandom.uniform(N);
                pc.open(rand1, rand2);
            }
            ans[index] = (double) pc.numberOfOpenSites() / (N * N);
        }
        mean = StdStats.mean(ans);
        stddev = StdStats.stddev(ans);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLow() {
        return mean - 1.96 * stddev / Math.sqrt(time);
    }

    public double confidenceHigh() {
        return mean + 1.96 * stddev / Math.sqrt(time);
    }

}
