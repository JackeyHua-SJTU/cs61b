package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    private double[] ans;
    private Percolation pc;
    private int time;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        time = T;
        ans = new double[T];
        for (int i = 0; i < T; i += 1) {
            pc = pf.make(N);
            while (!pc.percolates()) {
                int rand1 = StdRandom.uniform(N);
                int rand2 = StdRandom.uniform(N);
                pc.open(rand1, rand2);
            }
            ans[i] = (double) pc.numberOfOpenSites() / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(ans);
    }

    public double stddev() {
        return StdStats.stddev(ans);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(time);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(time);
    }

}
