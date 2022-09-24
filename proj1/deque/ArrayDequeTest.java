package deque;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void isEmptyTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        assertTrue(ad.isEmpty());
    }

    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();

        assertTrue(ad.isEmpty());
        ad.addFirst(1);
        assertEquals(1, ad.size());
        assertFalse(ad.isEmpty());
        ad.addLast(2);
        assertEquals(2, ad.size());
    }

    @Test
    public void addRemoveTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();

        assertTrue(ad.isEmpty());
        ad.addFirst(1);
        assertFalse(ad.isEmpty());
        ad.removeLast();
        assertTrue(ad.isEmpty());
        assertEquals(0, ad.size());
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        for (int i = 0; i <= 9; i ++) {
            ad.addLast(i);
        }
        assertEquals(1, (int) ad.get(1));
        assertEquals(2, (int) ad.get(2));
        assertEquals(4, (int) ad.get(4));
        assertEquals(9, (int) ad.get(9));
    }

//    @Test
//    public void equalsTest() {
//        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
//        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();
//        for (int i = 0; i <= 5; i ++) {
//            ad.addLast(i);
//            ad2.addLast(i);
//        }
//        assertTrue(ad.equals(ad2));
//    }
}
