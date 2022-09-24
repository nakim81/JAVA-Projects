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

    private void resize(int capacity, int start) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, start, this.size());
        items = a;
        this.nextLast = (nextLast + this.size()) % items.length;
    }
    @Override
    public void addFirst(T item) {
        if (size <= items.length) {
            items[nextFirst] = item;
            size++;
            nextFirst = changeNextFirst(nextFirst);
        } else {
            resize(size * 2, nextFirst);
            nextFirst = changeNextFirst(nextFirst);
            nextLast = changeNextLast(nextLast);
            items[nextFirst] = item;
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
        if (size <= items.length) {
            items[nextLast] = item;
            this.size++;
            nextLast = changeNextLast(nextLast);
        } else {
            resize(this.size() * 2, nextFirst);
            nextLast = changeNextLast(nextLast);
            items[nextLast] = item;
            nextFirst = changeNextFirst(nextFirst);
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
        if (index < 0 || index > items.length - 1 || items[index] == null) {
            return null;
        }
        return ((T) items[index]);
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removedItem;
        if (this.size() / items.length >= 0.25 || size <= MAGICNUMBER2) {
            nextFirst = changeNextLast(nextFirst);
            removedItem = items[nextFirst];
            items[nextFirst] = null;
            size--;
            return ((T) removedItem);
        } else {
            resize((this.size() / 2), nextFirst);
            removedItem = items[nextFirst];
            items[nextFirst] = null;
            nextLast = changeNextLast(nextLast);
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
        if (this.size() / items.length >= 0.25 || size <= MAGICNUMBER2) {
            nextLast = changeNextFirst(nextLast);
            removedItem = items[nextLast];
            items[nextLast] = null;
            size--;
            return ((T) removedItem);
        } else {
            resize((this.size() / 2), nextFirst);
            removedItem = items[nextLast];
            items[nextLast] = null;
            nextFirst = changeNextFirst(nextFirst);
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


