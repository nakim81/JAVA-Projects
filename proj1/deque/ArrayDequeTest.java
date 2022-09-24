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
    public void getRemoveFirstTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();
        for (int i = 0; i <= 9; i ++) {
            ad.addLast(i);
        }
        for (int i = 0; i <= 16; i ++) {
            ad2.addLast(i);
        }
        assertEquals(7, (int) ad.get(0));
        assertEquals(0, (int) ad.get(1));
        assertEquals(1, (int) ad.get(2));
        assertEquals(3, (int) ad.get(4));
        assertEquals(8, (int) ad.get(8));
        assertEquals(9, (int) ad.get(9));

        assertEquals(16, (int) ad2.get(16));

        assertEquals(7, (int) ad.removeFirst());
        assertEquals(0, (int) ad.removeFirst());
        assertEquals(1, (int) ad.removeFirst());
        assertEquals(2, (int) ad.removeFirst());
    }

    @Test
    public void getTest2() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();
        for (int i = 0; i <= 16; i ++) {
            ad2.addFirst(i);
        }
        assertEquals(0, (int) ad2.get(0));
        assertEquals(7, (int) ad2.get(1));
        assertEquals(6, (int) ad2.get(2));
        assertEquals(8, (int) ad2.get(15));
        assertEquals(15, (int) ad2.get(8));


    }

    @Test
    public void removeLastTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();
        for (int i = 0; i <= 9; i ++) {
            ad.addLast(i);
        }
        assertEquals(7, (int) ad.get(0));
        assertEquals(0, (int) ad.get(1));
        assertEquals(1, (int) ad.get(2));
        assertEquals(3, (int) ad.get(4));
        assertEquals(8, (int) ad.get(8));
        assertEquals(9, (int) ad.get(9));

        assertEquals(9, (int) ad.removeLast());
        assertEquals(8, (int) ad.removeLast());
        assertEquals(6, (int) ad.removeLast());
        assertEquals(5, (int) ad.removeLast());
    }

    @Test
    public void getTest3() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();
        for (int i = 0; i <= 30; i ++) {
            ad.addLast(i);
        }
        for (int i = 0; i <= 25; i++) {
            ad.removeLast();
        }
//        assertEquals(0, (int) ad);
//        assertEquals(null, ad.get(4));
    }

    @Test
    public void resizeTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        ad.addFirst(0);
        ad.addLast(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addLast(4);
        ad.removeLast();
        ad.removeLast() ;
        ad.removeFirst();
        ad.addFirst(8);
        assertEquals(0, (int) ad.get(2));
        assertEquals(null, ad.get(4));
    }
    @Test
    public void removeLastTest2() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        ad.addFirst(0);
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addFirst(4);
        ad.addFirst(5);
        ad.addFirst(6);
        ad.addFirst(7);
        ad.addFirst(8);
        assertEquals(0, (int) ad.removeLast());
    }


    @Test
    public void resizeTest2() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        ad.addFirst(0);
        ad.addFirst(1);
        ad.removeFirst();
        ad.removeLast();
        ad.addLast(4);
        ad.removeFirst();
        ad.addFirst(6);
        ad.addFirst(7);
        ad.addLast(8);
        ad.removeFirst();
        ad.addLast(10);
        ad.addFirst(11);
        ad.addLast(12);
        ad.addLast(13);
        ad.addLast(14);
        ad.get(5);
        ad.addFirst(16);
        ad.addFirst(17);
        ad.get(5);
        ad.get(0);
        ad.addLast(20);
        ad.get(5);
        ad.removeFirst();
//        assertEquals(0, (int) ad.removeLast());
    }


    @Test
    public void removeLastTest3() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        ad.addLast(0);
        ad.addFirst(1);
        ad.addFirst(2);
        ad.get(2);
        ad.addFirst(4);
        ad.addLast(5);
        ad.addLast(6);
        ad.removeFirst();
        ad.removeFirst();
        ad.addLast(9);
        ad.addLast(10);
        ad.removeLast();
        ad.addFirst(12);
        ad.addFirst(13);
        ad.removeLast();
        ad.addFirst(15);
        ad.removeFirst();
        ad.addLast(17);
        ad.addLast(18);
        ad.addLast(19);
        ad.removeFirst();
        ad.removeLast();
    }
}
