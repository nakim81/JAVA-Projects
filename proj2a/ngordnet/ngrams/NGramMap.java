package ngordnet.ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;

/** An object that provides utility methods for making queries on the
 *  Google NGrams dataset (or a subset thereof).
 *
 *  An NGramMap stores pertinent data from a "words file" and a "counts
 *  file". It is not a map in the strict sense, but it does provide additional
 *  functionality.
 *
 *  @author Josh Hug
 */
public class NGramMap {
    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    private HashMap<String, TimeSeries> hMap;
    private TimeSeries totalCount;
    public NGramMap(String wordsFilename, String countsFilename) {
        this.hMap = new HashMap<>();
        In wordsFile = new In(wordsFilename);
        In countsFile = new In(countsFilename);
        while (wordsFile.hasNextLine()) {
            String firstItemInFile = wordsFile.readString();
            int secondItemInFile = wordsFile.readInt();
            double thirdItemInFile = wordsFile.readDouble();
            double fourthItemInFile = wordsFile.readDouble();
            TimeSeries ts = new TimeSeries();
            ts.put(secondItemInFile, thirdItemInFile);
            hMap.put(firstItemInFile, ts);
        }
        while (countsFile.hasNextLine()) {
            String line = countsFile.readLine();
            String[] tokens = line.split(",");
            int key = Integer.parseInt(tokens[0]);
            double val = Double.parseDouble(tokens[1]);
            totalCount.put(key, val);
        }
    }

    /** Provides the history of WORD. The returned TimeSeries should be a copy,
     *  not a link to this NGramMap's TimeSeries. In other words, changes made
     *  to the object returned by this function should not also affect the
     *  NGramMap. This is also known as a "defensive copy". */
    public TimeSeries countHistory(String word) {
        TimeSeries ts = hMap.get(word);
        return ts;
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     *  returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other words,
     *  changes made to the object returned by this function should not also affect the
     *  NGramMap. This is also known as a "defensive copy". */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries ts = hMap.get(word);
        TimeSeries returnTs = new TimeSeries(ts, startYear, endYear);
        return returnTs;
    }

    /** Returns a defensive copy of the total number of words recorded per year in all volumes. */
    public TimeSeries totalCountHistory() {
        TimeSeries ts = totalCount;
        return ts;
    }

    /** Provides a TimeSeries containing the relative frequency per year of WORD compared to
     *  all words recorded in that year. */
    public TimeSeries weightHistory(String word) {
        TimeSeries history = countHistory(word);
        TimeSeries totalHistory = totalCountHistory();
        TimeSeries weightHistory = history.dividedBy(totalCountHistory());
        return weightHistory;
    }

    /** Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     *  and ENDYEAR, inclusive of both ends. */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries history = new TimeSeries(countHistory(word), startYear, endYear);
        TimeSeries totalHistory = new TimeSeries(totalCountHistory(), startYear, endYear);
        TimeSeries weightHistory = history.dividedBy(totalHistory);
        return weightHistory;
    }

    /** Returns the summed relative frequency per year of all words in WORDS. */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        return null;
    }

    /** Provides the summed relative frequency per year of all words in WORDS
     *  between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     *  this time frame, ignore it rather than throwing an exception. */
    public TimeSeries summedWeightHistory(Collection<String> words, int startYear, int endYear) {
        return null;
    }
}
