package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (correct.size() <= 0 || broken.size() <= 0) {
                if (operationNumber == 0) {
                    // addLast
                    int randVal = StdRandom.uniform(0, 100);
                    correct.addLast(randVal);
                    broken.addLast(randVal);
                } else if (operationNumber == 1) {
                    // size
                    int size = correct.size();
                    int size2 = broken.size();
                }
            } else {
                if (operationNumber == 0) {
                    // addLast
                    int randVal = StdRandom.uniform(0, 100);
                    correct.addLast(randVal);
                    broken.addLast(randVal);
                } else if (operationNumber == 1) {
                    // size
                    int size = correct.size();
                    int size2 = broken.size();
                } else if (operationNumber == 2) {
                    // getLast
                    int correctItem = correct.getLast();
                    int brokenItem = broken.getLast();
                } else if (operationNumber == 3) {
                    // removeLast
                    int correctRemovedItem = correct.removeLast();
                    int brokenRemovedItem = broken.removeLast();
                }
            }
        }
    }
}
