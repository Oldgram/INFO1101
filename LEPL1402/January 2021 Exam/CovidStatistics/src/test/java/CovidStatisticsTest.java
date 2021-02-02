import org.junit.Assert;
import org.junit.Test;

import java.util.function.Predicate;


public class CovidStatisticsTest {


    @Test
    public void testRollingAverage() {

        int [] values = new int[] {0,1,3,2,4,5};
        double [] res = CovidStatistics.rollingAverage(values,2);
        double [] expected = new double[] {0,0.5,2,2.5,3,4.5};
        Assert.assertArrayEquals(expected,res,0.01);

    }


    @Test
    public void testCumulativeSum() {
        int [] values = new int[] {0,1,3,2,4,5};
        int [] res = CovidStatistics.cumulativeSum(values);
        int [] expected = new int[] {0,1,4,6,10,15};
        Assert.assertArrayEquals(expected,res);

    }


    @Test
    public void testIndex() {

        int [] values = new int[] {0,1,3,2,4,5};
        int idx = CovidStatistics.index(values,v -> v == 3);
        Assert.assertEquals(2,idx);


        idx = CovidStatistics.index(values,v -> v % 2 == 1);
        Assert.assertEquals(1,idx);

        idx = CovidStatistics.index(values,v -> v % 2 == 0 && v > 1);
        Assert.assertEquals(3,idx);


        idx = CovidStatistics.index(values,v -> v < 0);
        Assert.assertEquals(-1,idx);


    }




}
