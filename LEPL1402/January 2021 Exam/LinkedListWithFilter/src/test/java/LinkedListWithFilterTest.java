import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class LinkedListWithFilterTest {


    @Test
    public void test1() {

        LinkedListWithFilter input = new LinkedListWithFilter();
        input.add(3,4,5,6,7,8,9,10);

        LinkedListWithFilter output = input.filter(i -> i%2 == 0);
        List expected = Arrays.asList(4,6,8,10);
        Assert.assertEquals(expected, toList(output));

        output = output.filter(i -> i%3 != 0);
        expected = Arrays.asList(4,8,10);
        Assert.assertEquals(expected, toList(output));

    }

    public static List toList(LinkedListWithFilter l) {
        List answer = new ArrayList();
        LinkedListWithFilter.Node n = l.first;
        while (n != null) {
            answer.add(n.v);
            n = n.next;
        }
        return answer;
    }


}
