package hw4.puzzle;

import java.util.ArrayList;
import java.util.List;

public class Board implements WorldState {
    private int size;
    private int[][]tile;

    /**
     * A really tricky problem: can you create a new 2d Array using clone()?
     * The answer seems to be no!
     * You only create a new array where every item in that points to the same item of the initial array.
     * Namely, it is mutable.
     * But it applies to 1d Array.
     * @param tiles
     */
    public Board(int[][] tiles) {
        this.size = tiles.length;
        tile = new int[size][size];
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                tile[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (!checkValidate(i, j)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return tile[i][j];
    }

    public int size() {
        return size;
    }

    public Iterable<WorldState> neighbors() {
        List<WorldState> ls = new ArrayList<>();
        int[] ans = findBlank();
        int iIndexOfZero = ans[0];
        int jIndexOfZero = ans[1];

        if (checkValidate(iIndexOfZero - 1, jIndexOfZero)) {
            int[][] copy1 = new int[size][size];
            for (int i = 0; i < size; i += 1) {
                for (int j = 0; j < size; j += 1) {
                    copy1[i][j] = tile[i][j];
                }
            }
            copy1[iIndexOfZero][jIndexOfZero] = copy1[iIndexOfZero - 1][jIndexOfZero];
            copy1[iIndexOfZero - 1][jIndexOfZero] = 0;
            ls.add(new Board(copy1));
        }

        if (checkValidate(iIndexOfZero + 1, jIndexOfZero)) {
            int[][] copy2 = new int[size][size];
            for (int i = 0; i < size; i += 1) {
                for (int j = 0; j < size; j += 1) {
                    copy2[i][j] = tile[i][j];
                }
            }
            copy2[iIndexOfZero][jIndexOfZero] = copy2[iIndexOfZero + 1][jIndexOfZero];
            copy2[iIndexOfZero + 1][jIndexOfZero] = 0;
            ls.add(new Board(copy2));
        }

        if (checkValidate(iIndexOfZero, jIndexOfZero - 1)) {
            int[][] copy3 = new int[size][size];
            for (int i = 0; i < size; i += 1) {
                for (int j = 0; j < size; j += 1) {
                    copy3[i][j] = tile[i][j];
                }
            }
            copy3[iIndexOfZero][jIndexOfZero] = copy3[iIndexOfZero][jIndexOfZero - 1];
            copy3[iIndexOfZero][jIndexOfZero - 1] = 0;
            ls.add(new Board(copy3));
        }

        if (checkValidate(iIndexOfZero, jIndexOfZero + 1)) {
            int[][] copy4 = new int[size][size];
            for (int i = 0; i < size; i += 1) {
                for (int j = 0; j < size; j += 1) {
                    copy4[i][j] = tile[i][j];
                }
            }
            copy4[iIndexOfZero][jIndexOfZero] = copy4[iIndexOfZero][jIndexOfZero + 1];
            copy4[iIndexOfZero][jIndexOfZero + 1] = 0;
            ls.add(new Board(copy4));
        }
        return ls;
    }

    private int[] findBlank() {
        int[] ret = new int[2];
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                if (tileAt(i, j) == 0) {
                    ret[0] = i;
                    ret[1] = j;
                }
            }
        }
        return ret;
    }

    private boolean checkValidate(int i, int j) {
        return i >= 0 && i < size && j >= 0 && j < size;
    }

    public int hamming() {
        int sum = 0;
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                sum += hamDeviation(i, j);
            }
        }
        return sum;
    }

    private int hamDeviation(int i, int j) {
        int correct = i * size + j + 1;
        int current = tileAt(i, j);
        if (current == 0) {
            return 0;
        }
        return (current == correct) ? 0 : 1;
    }

    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                sum += mahDeviation(i, j);
            }
        }
        return sum;
    }

    private int mahDeviation(int i, int j) {
        if (tileAt(i, j) == 0) {
            return 0;
        }
        int correctIIndex = (tileAt(i, j) - 1) / size;
        int correctJIndex = (tileAt(i, j) - 1) % size;
        return Math.abs(correctJIndex - j) + Math.abs(correctIIndex - i);
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /**
     * Really Important Note !!!!!!!
     * 1. When you override equals, you must spontaneously rewrite hashCode().
     * 2. When we write equals, please bear in mind three steps below.
     *    first, a shortcut for y == this
     *    second, we should never equal to null or a different class variable.
     *    third, specific compares. Remember to cast Object to corresponding class.
     * @param y
     * @return
     */
    @Override
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }
        Board other = (Board) y;
        if (this.size != other.size) {
            return false;
        }
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                if (this.tileAt(i, j) != other.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        return super.hashCode();
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
