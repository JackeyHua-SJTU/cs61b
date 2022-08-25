package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int size;
    private WeightedQuickUnionUF uf;
    private boolean[] open;
    private int openSites;
    private int top;
    private int bottom;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.size = N;
        this.uf = new WeightedQuickUnionUF(N * N + 2);
        this.open = new boolean[N * N];
        this.top = N * N;
        this.bottom = N * N + 1;
        for (int i = 0; i < N * N; i += 1) {
            open[i] = false;
        }
        for (int i = 0; i < N; i += 1) {
            uf.union(top, i);
        }
        this.openSites = 0;
    }
    private int xyTo1d(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return -1;
        }
        return size * row + col;
    }

    private void checkBottom(int num) {
        if (isLastRow(num) && uf.connected(num, top)) {
            uf.union(num, bottom);
        }
    }

    private boolean isLastRow(int num) {
        for (int i = size * (size - 1); i < size * size; i += 1) {
            if (uf.connected(num, i)) {
                return true;
            }
        }
        return false;
    }

    public void open(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }

        int xy = xyTo1d(row, col);
        open[xy] = true;
        openSites += 1;
        checkBottom(xy);
        int[] temp = new int[4];
        temp[0] = xyTo1d(row - 1, col);
        temp[1] = xyTo1d(row, col - 1);
        temp[2] = xyTo1d(row, col + 1);
        temp[3] = xyTo1d(row + 1, col);
        for (int i : temp) {
            if (i <= 0) {
                continue;
            }
            if (open[i] && open[xy]) {
                uf.union(i, xy);
                checkBottom(i);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return open[xyTo1d(row, col)];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return uf.connected(xyTo1d(row, col), top) && isOpen(row, col);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    public static void main(String[] args) {
        Percolation a = new Percolation(3);
        a.open(0, 0);
        System.out.println(a.isFull(0, 0));
        a.open(1, 2);
        System.out.println(a.isFull(1, 0));
        a.open(1, 1);
        System.out.println(a.isFull(1, 1));
        a.open(2, 1);
        System.out.println(a.isFull(2, 1));
        a.open(0, 2);
        System.out.println(a.uf.connected(0, 9));
        System.out.println(a.numberOfOpenSites());
        System.out.print(a.percolates());
    }

}