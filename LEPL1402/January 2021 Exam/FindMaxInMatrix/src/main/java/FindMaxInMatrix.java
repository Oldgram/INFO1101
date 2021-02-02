// You are allowed to add imports here

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FindMaxInMatrix {
    // You are allowed to add methods or class members here

    public static class Result {
        public int row, column;

        public Result(int row, int column) {
            this.row=row;
            this.column=column;
        }
    }

    /**
     * This method finds the position of greatest element in a square matrix.
     * The matrix has only ONE greatest element.
     * Your solution MUST use threads to accelerate the search for the
     * greatest element.
     * The matrix is represented by a two-dimensional array.
     *
     * For example, if the method is called with the following matrix
     *     (1  2  3)
     *     (3  4  5)
     *     (3  0  3)
     * then the result is row=1, column=2 because 5 is the greatest element.
     *
     *
     * @param matrix a rectangular matrix
     * @param nThreads the number of threads to use
     * @return the row and column of the greatest element
     *
     * You can assume in your solution that:
     *    -  nThreads>0
     *    -  nThreads<=the number of rows and the number of columns of the matrix
     *    -  matrix is a square matrix and has at least one element
     *    -  all elements in the matrix are >=0
     *    - You can ignore (i.e. catch) exceptions
     */
    public static Result findMax(int[][] matrix, int nThreads) {
        Result res = new Result(0, 0); // The first element of the matrix is taken as its maximum
        ExecutorService executor = Executors.newFixedThreadPool(nThreads); // Initialise a threadPool of size nThreads
        List<Future<Result>> futures = new ArrayList<>(); // This will store the Results of the threads as futures

        for (int i = 0; i < matrix.length; i++) {
            int[] row = matrix[i]; // Take a row
            int index = i;
            // Add a job to the threadPool, which is to look for the max value in a row and store it as a Result
            futures.add(executor.submit(() -> findMaxInRow(row, index)));
        }

        try{
            for (Future<Result> rowMax : futures) { // Loop through the Results (each representing the max value of a row)
                Result pos = rowMax.get(); // wait for the thread to finish and get the result
                // if the corresponding value is greater than the actual maximum found, replace it
                if (matrix[pos.row][pos.column] > matrix[res.row][res.column]) { res = pos; }
            }
            executor.shutdown(); // shutdown the threadPool
        } catch(InterruptedException | ExecutionException e) { e.printStackTrace(); }

        return res;
    }

    /*
    * This method finds the position of the maximum value in a given Subarray and return it as a Result
    * @param row : an array of integers (row of the matrix)
    * @param rowIndex : the index of given array (index of the row of the matrix)
    * @return the index of the given row and the index of its maximum value (as a Result)
     */
    private static Result findMaxInRow(int[] row, int rowIndex) {
        int max = row[0], index = 0; // The first element is taken as the maximum value
        for (int i = 1; i < row.length; i++) { // Loop throw the row to find its maximum
            if (row[i] > max) {
                max = row[i];
                index = i;
            }
        }
        return new Result(rowIndex, index); // Return the result as a Result
    }
}
