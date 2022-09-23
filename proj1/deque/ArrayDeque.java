package deque;

import edu.princeton.cs.algs4.StdOut;

public class ArrayDeque<T> {

    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if (size < items.length) {
            items[nextFirst] = item;
            size ++;
            nextFirst = changeNextFirst(nextFirst);
        } else {
            resize(size*2);
            items[nextFirst] = item;
            size ++;
        }
    }
    public int changeNextFirst(int nextFirstTracker) {
        if (nextFirstTracker == 0) {
            return items.length - 1;
        } else {
            nextFirstTracker --;
            return nextFirstTracker;
        }
    }

    public void addLast(T item) {
        if (size < items.length) {
            items[nextLast] = item;
            size ++;
            nextLast = changeNextLast(nextLast);
        } else {
            resize(size*2);
            items[nextLast] = item;
            size ++;
        }
    }

    public int changeNextLast(int nextLastTracker) {
        if (nextLastTracker == items.length - 1) {
            return 0;
        } else {
            nextLastTracker ++;
            return nextLastTracker;
        }
    }

    public void printDeque() {
        for (int i = 0; i < size; i ++) {
            System.out.print(items[i] + " ");
        }
        System.out.println("\n");
    }

    public T get(int index) {
        if (index < 0 || index > items.length - 1 || items[index] == null) {
            return null;
        }
        return items[index + 1];
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removedItem;
        if ((size - 1)/items.length <= 0.25 && size >= 16) {
            resize(size / 2);
        }
        nextFirst = changeNextLast(nextFirst);
        removedItem = items[nextFirst];
        items[nextFirst] = null;
        size --;
        return removedItem;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T removedItem;
        if ((size - 1)/items.length <= 0.25 && size >= 16) {
            resize(size / 2);
        }
        nextLast = changeNextFirst(nextLast);
        removedItem = items[nextLast];
        items[nextLast] = null;
        size --;
        return removedItem;
    }

    public boolean equals(Object o) {
        boolean check = false;
        if (o instanceof ArrayDeque<?>) {
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i) == ((ArrayDeque<?>) o).get(i)) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
            }
        } else {
            return false;
        }
        return check;
    }

}
