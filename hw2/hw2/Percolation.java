package hw2;

import static org.junit.Assert.*;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;
    private int openSites;
    private boolean[] isOpened;
    private int N;
    private int top, bottom;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        uf = new WeightedQuickUnionUF(N * N + 2);
        uf2 = new WeightedQuickUnionUF(N * N + 2);
        this.N = N;
        top = N * N;
        bottom = N * N + 1;
        openSites = 0;
        isOpened = new boolean[N * N + 2];
    }

    private int getId(int row, int col) {
        return row * N + col;
    }

    private boolean isPositionValid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

    public void open(int row, int col) {
        if (!isPositionValid(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        if (isOpen(row, col)) {
            return;
        }

        int id = getId(row, col);
        isOpened[id] = true;
        openSites += 1;

        int[][] neibors = {
                {row - 1, col},
                {row + 1, col},
                {row, col + 1},
                {row, col - 1}
        };
        for (int[] nei : neibors) {
            if (isPositionValid(nei[0], nei[1]) && isOpen(nei[0], nei[1])) {
                int id2 = getId(nei[0], nei[1]);
                uf.union(id, id2);
                uf2.union(id, id2);
            }
        }

        if (row == 0) {
            uf.union(top, id);
            uf2.union(top, id);
        }
        if (row == N - 1) {
            uf.union(bottom, id);
        }
    }

    public boolean isOpen(int row, int col) {
        if (!isPositionValid(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        return isOpened[getId(row, col)];
    }

    public boolean isFull(int row, int col) {
        if (!isPositionValid(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        return uf2.connected(top, getId(row, col));
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        assertFalse(percolation.percolates());
        percolation.open(0, 0);
        percolation.open(1, 1);
        percolation.open(0, 1);
        assertFalse(percolation.isOpen(1, 0));
        percolation.open(3, 4);
        percolation.open(2, 4);
        percolation.open(2, 2);
        percolation.open(2, 2);
        percolation.open(2, 3);
        percolation.open(0, 2);
        percolation.open(1, 2);
        assertTrue(percolation.isFull(2, 2));
        percolation.open(4, 4);
        assertTrue(percolation.percolates());
    }
}