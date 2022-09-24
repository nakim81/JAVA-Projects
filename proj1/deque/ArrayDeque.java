package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;
    private static final int MAGICNUMBER = 8;
    private static final int MAGICNUMBER2 = 16;

    public ArrayDeque() {
        items = (T[]) new Object[MAGICNUMBER];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            int newIndex = (nextLast + i) % items.length;
            a[i] = items[newIndex];
        }
        items = a;
        this.nextFirst = items.length - 1;
        this.nextLast = size;
    }

    @Override
    public void addFirst(T item) {
        if (size < items.length) {
            items[nextFirst] = item;
            size++;
            nextFirst = changeNextFirst(nextFirst);
        } else {
            resize(items.length * 2);
            items[nextFirst] = item;
            nextFirst = changeNextFirst(nextFirst);
            size++;
        }
    }

    private int changeNextFirst(int nextFirstTracker) {
        if (nextFirstTracker == 0) {
            return items.length - 1;
        } else {
            nextFirstTracker--;
            return nextFirstTracker;
        }
    }

    @Override
    public void addLast(T item) {
        if (size < items.length) {
            items[nextLast] = item;
            this.size++;
            nextLast = changeNextLast(nextLast);
        } else {
            resize(items.length * 2);
            items[nextLast] = item;
            nextLast = changeNextLast(nextLast);
            size++;
        }
    }

    private int changeNextLast(int nextLastTracker) {
        if (nextLastTracker == items.length - 1) {
            return 0;
        } else {
            nextLastTracker++;
            return nextLastTracker;
        }
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println("\n");
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > items.length - 1) {
            return null;
        }
        int newIndex = changeNextLast(nextFirst);
        return ((T) items[(newIndex + index) % items.length]);
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removedItem;
        if ((float) this.size() / items.length >= 0.25 || items.length < MAGICNUMBER2) {
            nextFirst = changeNextLast(nextFirst);
            removedItem = items[nextFirst];
            items[nextFirst] = null;
            size--;
            return ((T) removedItem);
        } else {
            resize((items.length / 2));
            nextFirst = changeNextLast(nextFirst);
            removedItem = items[nextFirst];
            items[nextFirst] = null;
            size--;
            return ((T) removedItem);
        }

    }

    @Override
    public T removeLast() {
        if (this.size() == 0) {
            return null;
        }
        T removedItem;
        if ((float) this.size() / items.length >= 0.25 || items.length < MAGICNUMBER2) {
            nextLast = changeNextFirst(nextLast);
            removedItem = items[nextLast];
            items[nextLast] = null;
            size--;
            return ((T) removedItem);
        } else {
            resize((items.length / 2));
            nextLast = changeNextFirst(nextLast);
            removedItem = items[nextLast];
            items[nextLast] = null;
            size--;
            return removedItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o) {
        if (o instanceof Deque n) {
            if (n.size() != this.size()) {
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
                if (n.get(i) != this.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;

        public ArrayDequeIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }
}


