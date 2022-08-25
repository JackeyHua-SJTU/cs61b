import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class disc7 {
    /** 1.1
     *
     * 1 < logN < N < N*logN < N^2*logN < N^3 < 2^N < N! < N^N
     *
     * 1.2
     *
     * True
     * False
     * True
     * False
     * True
     * True
     * False
     *
     * 2.1
     *
     * Worst Case: M + N
     * Best Case: N // break只跳出一个循环
     *
     * 2.2
     * Worst Case: N ^ 2
     * Best Case: N * logN
     *
     * Using priorityQueue to sort the array
     *
     *
     * 2.3
     *
     * Worst Case: M * N
     * Best Case: N * logM
     *
     */

    /** Because the list is sorted, so we can iterate from left and right. */
    public static boolean findSum(int[] A, int x) {
        int left = 0;
        int right = A.length - 1;
        while (left != right) {
            if (A[left] + A[right] > x) {
                right -= 1;
            } else if (A[left] + A[right] < x) {
                left += 1;
            } else {
                break;
            }
        }
        if (left != right) {
            return true;
        }
        return false;
    }

    /** Only Hash-based data structure offers contant time method. */
    public static int[] union(int[] arr1, int[] arr2) {
        Set<Integer> ret = new HashSet<>();
        for (int temp : arr1) {
            ret.add(temp);
        }
        for (int temp : arr2) {
            ret.add(temp);
        }
        int[] array = new int[ret.size()];
        int index = 0;
        for (int i : ret) { // how to have access to all numbers in the set
            array[index] = i;
            index += 1;
        }
        return array;
    }

    public static int[] intersect(int[] arr1, int[] arr2) {
        Set<Integer> temp = new HashSet<>();
        int size = 0;
        for (int i : arr1) {
            temp.add(i);
        }
        for (int i : arr2) {
            if (temp.contains(i)) {
                size += 1;
            }
        }
        int[] ret = new int[size];
        int index = 0;
        for (int i : arr2) {
            if (temp.contains(i)) {
                ret[index] = i;
                index += 1;
            }
        }
        return ret;
    }
    /** it is applicable to use another hashset to store the shared elements. */

}
