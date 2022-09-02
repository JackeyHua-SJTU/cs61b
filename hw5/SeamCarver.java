import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Stack;

public class SeamCarver {
    private Picture picture;
    private int width;
    private int height;
    private double[][] minimumCostPath;
    public SeamCarver(Picture picture) {
        this.picture = picture;
        this.width = picture.width();
        this.height = picture.height();
    }

    private void reverse() {
        int temp = width;
        width = height;
        height = temp;
    }

    private void buildPath() {
        minimumCostPath = new double[width][height];
        for (int i = 0; i < width; i += 1) {
            minimumCostPath[i][0] = energy(i, 0);
        }
        for (int i = 0; i < width; i += 1) {
            for (int j = 1; j < height; j += 1) {
                double left = (!checkValidate(i - 1, j - 1)) ? Double.MAX_VALUE : energy(i - 1, j - 1);
                double middle = (!checkValidate(i, j - 1)) ? Double.MAX_VALUE : energy(i, j - 1);
                double right = (!checkValidate(i + 1, j - 1)) ? Double.MAX_VALUE : energy(i + 1, j - 1);
                minimumCostPath[i][j] = energy(i, j) + Double.min(Double.min(left, middle), right);
            }
        }
    }

    public Picture picture() {
        return picture;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    private boolean checkValidate(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public double energy(int x, int y) {
        if (!checkValidate(x, y)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        double deltaX = 0.0;
        double deltaY = 0.0;
        if (!checkValidate(x - 1, y)) {
            int Rx = picture.get(width - 1, y).getRed() - picture.get(x + 1, y).getRed();
            int Gx = picture.get(width - 1, y).getGreen() - picture.get(x + 1, y).getGreen();
            int Bx = picture.get(width - 1, y).getBlue() - picture.get(x + 1, y).getBlue();
            deltaX = Rx * Rx + Gx * Gx + Bx * Bx;
        } else if (!checkValidate(x + 1, y)) {
            int Rx = picture.get(x - 1, y).getRed() - picture.get(0, y).getRed();
            int Gx = picture.get(x - 1, y).getGreen() - picture.get(0, y).getGreen();
            int Bx = picture.get(x - 1, y).getBlue() - picture.get(0, y).getBlue();
            deltaX = Rx * Rx + Gx * Gx + Bx * Bx;
        } else {
            int Rx = picture.get(x - 1, y).getRed() - picture.get(x + 1, y).getRed();
            int Gx = picture.get(x - 1, y).getGreen() - picture.get(x + 1, y).getGreen();
            int Bx = picture.get(x - 1, y).getBlue() - picture.get(x + 1, y).getBlue();
            deltaX = Rx * Rx + Gx * Gx + Bx * Bx;
        }

        if (!checkValidate(x, y - 1)) {
            int Ry = picture.get(x, height - 1).getRed() - picture.get(x, y + 1).getRed();
            int Gy = picture.get(x, height - 1).getGreen() - picture.get(x, y + 1).getGreen();
            int By = picture.get(x, height - 1).getBlue() - picture.get(x, y + 1).getBlue();
            deltaY = Ry * Ry + Gy * Gy + By * By;
        } else if (!checkValidate(x, y + 1)) {
            int Ry = picture.get(x, y - 1).getRed() - picture.get(x, 0).getRed();
            int Gy = picture.get(x, y - 1).getGreen() - picture.get(x, 0).getGreen();
            int By = picture.get(x, y - 1).getBlue() - picture.get(x, 0).getBlue();
            deltaY = Ry * Ry + Gy * Gy + By * By;
        } else {
            int Ry = picture.get(x, y - 1).getRed() - picture.get(x, y + 1).getRed();
            int Gy = picture.get(x, y - 1).getGreen() - picture.get(x, y + 1).getGreen();
            int By = picture.get(x, y - 1).getBlue() - picture.get(x, y + 1).getBlue();
            deltaY = Ry * Ry + Gy * Gy + By * By;
        }
        return deltaY * deltaY + deltaX * deltaX;
    }

    public int[] findHorizontalSeam() {
        reverse();
        buildPath();
        int[] ret = findVerticalSeam();
        reverse();
        return ret;
    }

    public int[] findVerticalSeam() {
        buildPath();
        int[] ret = new int[height];
        double smallest = Double.MAX_VALUE;
        int colOfSmallest = 0;
        for (int i = 0; i < width; i += 1) {
            smallest = (smallest >= minimumCostPath[i][height - 1]) ? minimumCostPath[i][height - 1] : smallest;
            colOfSmallest = i;
        }
        Stack<Integer> temp = new Stack<>();
        for (int i = height - 1; i > 0; i -= 1) {
            temp.push(colOfSmallest);
            smallest -= energy(colOfSmallest, i);
            colOfSmallest = findSmallest(smallest, colOfSmallest, i);
        }
        temp.push(colOfSmallest);
        int index = 0;
        for (int x : temp) {
            ret[index++] = x;
        }
        return ret;
    }

    private int findSmallest(double db, int col, int line) {
        if (checkValidate(col - 1, line - 1) && energy(col - 1, line - 1) == db) {
            return col - 1;
        }
        if (checkValidate(col, line - 1) && energy(col, line - 1) == db) {
            return col;
        }
        if (checkValidate(col + 1, line - 1) && energy(col + 1, line - 1) == db) {
            return col + 1;
        }
        return -1;
    }

    public void removeHorizontalSeam(int[] seam) {
        SeamRemover.removeHorizontalSeam(this.picture, seam);
    }

    public void removeVerticalSeam(int[] seam) {
        SeamRemover.removeVerticalSeam(this.picture, seam);
    }

}
