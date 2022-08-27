package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] bucket = new int[M];
        for (int i : bucket) {
            i = 0;
        }
        for (Oomage tmp : oomages) {
            int index = (tmp.hashCode() & 0x7FFFFFFF) % M;
            bucket[index] += 1;
        }
        for (int i : bucket){
            if (i >= oomages.size() / 50.0 && i <= oomages.size() / 2.5) {
                continue;
            }
            return false;
        }
        return true;
    }
}
