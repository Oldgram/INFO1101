import java.util.function.Predicate;
import java.util.stream.IntStream;


/** Seems to be for LinkedListWithFilter
 * Question:
 *
 * You are asked to complete the two TODO belows:
 *
 * - the add method appending an element at the end of the linked-list
 * - the filter method that returns a new LinkedListWithFilter containing
 *   the same elements in the same order but only the ones satisfying the predicate (condition)
 *   are kept.
 *
 *   Once it is done, copy-paste the complete class in Inginious also with the imports
 *
 */
public class CovidStatistics {


    /**
     * It must be computed in Theta(n) where n is the length of data
     * Each entry is the average computed on the window days before
     * @param data the input array
     * @param window the number of days to compute the average > 1
     * @return an array res that contains the rolling average
     *         on the window days before computed on data
     *         More formally, res has the same length as data
     *         res[i] = average(data[0:i]) for i < window
     *         res[i] = average(data[i-window+1:i]) for i >= window
     *
     */
    public static double[] rollingAverage(int [] data, int window) {
        double[] res = new double[data.length];

        for (int i = 0; i < data.length; i++) {
            // if i < window, res[i] = average(data[0:1]
            // else res[i] = average(data[i-window+1:i])
            res[i] = (i < window) ? average(data, 0, i) : average(data, i-window+1, i);
        }

        return res;
    }

    /*
    * This methods compute the average of a given array from a given index to another given index
    * @param data : an array of integers
    * @param from : an index such as from <= to < data.length
    * @param to : an index such as from <= to < data.length
    * @return the average of the given array between the two given indexes
     */
    private static double average(int[] data, int from, int to) {
        int[] array = new int[to - from + 1];
        System.arraycopy(data, from, array, 0, array.length);
        return (double) IntStream.of(array).sum() / array.length;
    }

    /**
     * It must be computed in Theta(n) where n is the length of data
     * Each entry is the the sum of the prefix of values
     * up and including this entry
     * @param data the input array
     * @return an array res that contains the prefix sum
     *         More formally, res has the same length as data and contains
     *         sum[i] = sum(data[0:i])
     *
     */
    public static int[] cumulativeSum(int [] data) {
        // initialise a new array of the same size as data, that will contain the cumulative sums
        int[] res = new int[data.length];
        int sum = 0;

        for (int i = 0; i < data.length; i++) { // loop through the given array
            sum += data[i]; // sum each element of the given array
            res[i] = sum; // add the sum to res, such as res[i] = sum(data[0:i])
        }

        return res; // return res
    }

    /**
     * It must be computed in O(n) where n is the length of data
     * Return the index of the first entry satisfying the predicate test
     * @param data
     * @param p the predicate test
     * @return the index of the first entry satisfying the predicate p,
     *         -1 if no such index
     */
    public static int index(int [] data, Predicate<Integer> p) {
        for (int i = 0; i < data.length; i++) { // loop through data
            // test each integers in data and return the index of the first to satisfy the predicate
            if (p.test(data[i])) { return i; }
        }
        return -1; // if no element in data satisfies the predicate, return -1
    }
}
