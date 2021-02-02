import com.github.guillaumederval.javagrading.Grade;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class FindMaxInMatrixTest {

    private final int[][] smallMatrix = new int[][] {
            { 1, 2,  3,  4, 7 },
            { 5, 0,  4,  8, 2 },
            { 9, 10, 5,  6, 2 },
            { 0, 3,  12, 2, 9 },
            { 1, 1,  7,  8, 9 }
    };

    @Test
    @Grade(value = 5, cpuTimeout=1000)
    public void testSingleThread() {
        FindMaxInMatrix.Result pos=FindMaxInMatrix.findMax(smallMatrix,1);
        Assert.assertEquals(3, pos.row);
        Assert.assertEquals(2, pos.column);
    }

    @Test
    @Grade(value = 5, cpuTimeout=1000)
    public void testTwoThreads() {
        FindMaxInMatrix.Result pos=FindMaxInMatrix.findMax(smallMatrix,2);
        Assert.assertEquals(3, pos.row);
        Assert.assertEquals(2, pos.column);
    }

    @Test
    @Grade(value = 5, cpuTimeout=1000)
    public void testRandomLargeMatrix() {
        final int matrixSize=1001;

        // try different numbers of threads
        for(int numThreads=1;numThreads<=matrixSize;numThreads+=100) {
            // create large matrix with random values between 0 and 99
            Random rng=new Random();
            int[][] inputMatrix=new int[matrixSize][matrixSize];
            for(int i=0; i<matrixSize; i++) {
                for(int j=0; j<matrixSize; j++) {
                    inputMatrix[i][j]=rng.nextInt(100);
                }
            }
            // put the greatest element 500 at a random place in the matrix
            int rowMax=rng.nextInt(matrixSize);
            int colMax=rng.nextInt(matrixSize);
            inputMatrix[rowMax][colMax]=500;

            // test
            FindMaxInMatrix.Result pos=FindMaxInMatrix.findMax(inputMatrix,numThreads);
            Assert.assertEquals(rowMax, pos.row);
            Assert.assertEquals(colMax, pos.column);
        }
    }
}
