/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        String[] ret = asciis.clone();
        int longestLen = 0;
        for (String s : asciis) {
            longestLen = (s.length() > longestLen) ? s.length() : longestLen;
        }
        for (int i = 0; i < longestLen; i += 1) {
            sortHelperLSD(ret, i);
        }
        return ret;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] counts = new int[256];
        for (String s : asciis) {
            if (s.length() <= index) {
                counts[0] += 1;
            } else {
                counts[s.charAt(index)] += 1;
            }
        }

        int[] starts = new int[counts.length];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] tmp = asciis.clone();
        for (int i = 0; i < asciis.length; i += 1) {
            int item = (tmp[i].length() <= index) ? 0 : tmp[i].charAt(index);
            String initial = tmp[i];
            int place = starts[item];
            asciis[place] = initial;
            starts[item] += 1;
        }
        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    /**
    public static void main(String[] args) {
        String[] test = new String[]{"a", "b", "ab", "ac"};
        String[] ans = sort(test);
        for (String x : ans) {
            System.out.print(x);
        }
    }
    */
}
