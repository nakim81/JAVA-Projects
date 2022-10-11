package ngordnet.ngrams;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TestTimeSeries {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994, 1995));

        assertEquals(expectedYears, totalPopulation.years());

        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(0.0, 100.0, 600.0, 500.0));

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertEquals(expectedTotal.get(i), totalPopulation.data().get(i), 1E-10);
        }
    }

    @Test
    public void testConstruct() {
        TimeSeries ts1 = new TimeSeries();
        ts1.put(1991, 0.0);
        ts1.put(1992, 100.0);
        ts1.put(1994, 200.0);

        TimeSeries ts2 = new TimeSeries(ts1, 1991, 1994);
        for (int i = 1991; i < 1995; i++) {
            System.out.println(ts2.get(i));
        }
    }

    @Test
    public void testFromSpec2() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1993, 100.0);
        catPopulation.put(1994, 800.0);
        catPopulation.put(1995, 1500.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1993, 10.0);
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);
        dogPopulation.put(1996, 500.0);

        TimeSeries totalPopulation = catPopulation.dividedBy(dogPopulation);
        // expected: 1994: 2.0
        //           1995: 3.0

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1993, 1994, 1995));

        assertEquals(expectedYears, totalPopulation.years());

        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(10.0, 2.0, 3.0));

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertEquals(expectedTotal.get(i), totalPopulation.data().get(i), 1E-10);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1993, 100.0);
        catPopulation.put(1994, 800.0);
        catPopulation.put(1995, 1500.0);
        catPopulation.put(1996, 500.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1993, 10.0);
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.dividedBy(dogPopulation);
    }
} 