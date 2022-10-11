package ngordnet.ngrams;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {
    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        for (int i = startYear; i <= endYear; i++) {
            this.put(i, ts.get(i));
        }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        List<Integer> years = new ArrayList<>();
        Iterator<Integer> myKeyIter = navigableKeySet().iterator();
        while (myKeyIter.hasNext()){
            years.add(myKeyIter.next());
        }
        return years;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        List<Double> data = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            int year = this.years().get(i);
            data.add(this.get(year));
        }
        return data;
    }

    /**
     * Returns the yearwise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     */
    public TimeSeries plus(TimeSeries ts) {
        if (ts == null) {
            return this;
        }
        TimeSeries newTS = new TimeSeries();
        int myPointer = 0;
        int tsPointer = 0;
        while (myPointer < this.size() || tsPointer < ts.size()) {
            if (myPointer >= this.size()) {
                // used all elements in this
                int tsYear = ts.years().get(tsPointer);
                newTS.put(tsYear, ts.get(tsYear));
                tsPointer++;
                continue;
            } else if (tsPointer >= ts.size()) {
                // used all elements in parameter ts
                int myYear = years().get(myPointer);
                newTS.put(myYear, get(myYear));
                myPointer++;
                continue;
            }
            int myYear = years().get(myPointer);
            int tsYear = ts.years().get(tsPointer);
            if (myYear == tsYear) {
                newTS.put(myYear, get(myYear) + ts.get(tsYear));
                myPointer++;
                tsPointer++;
            } else if (myYear < tsYear) {
                newTS.put(myYear, get(myYear));
                myPointer++;
            } else {
                newTS.put(tsYear, ts.get(tsYear));
                tsPointer++;
            }
        }
        return newTS;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. If TS is missing a year that exists in this TimeSeries,
     * throw an IllegalArgumentException. If TS has a year that is not in this TimeSeries, ignore it.
     * Should return a new TimeSeries (does not modify this TimeSeries).
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries newTS = new TimeSeries();
        int myPointer = 0;
        int tsPointer = 0;
        while (myPointer < this.size() || tsPointer < ts.size()) {
            if (myPointer >= this.size()) {
                tsPointer++;
                continue;
            } else if (tsPointer >= ts.size()) {
                throw new IllegalArgumentException();
            }
            int myYear = years().get(myPointer);
            int tsYear = ts.years().get(tsPointer);
            if (myYear == tsYear) {
                newTS.put(myYear, (get(myYear) / ts.get(tsYear)));
                myPointer++;
                tsPointer++;
            } else if (myYear < tsYear) {
                throw new IllegalArgumentException();
            } else {
                tsPointer++;
            }
        }
        return newTS;
    }
}
