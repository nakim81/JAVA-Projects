package deque;

import java.util.Iterator;
import java.util.function.Consumer;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {


    private class Node {
        private T item;
        private Node next;
        private Node prev;

        private Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node last = sentinel.prev;
        Node newNode = new Node(last, item, sentinel);
        last.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node m = sentinel.next;
        while (m != sentinel) {
            System.out.print(m.item + " ");
            m = m.next;
        }
        System.out.print("\n");
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node deletedNode = sentinel.next;
        sentinel.next = deletedNode.next;
        deletedNode.next.prev = sentinel;
        size--;
        return deletedNode.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node deletedNode = sentinel.prev;
        sentinel.prev = deletedNode.prev;
        deletedNode.prev.next = sentinel;
        size--;
        return deletedNode.item;
    }

    @Override
    public T get(int index) {
        if (isEmpty() || index > size - 1 || index < 0) {
            return null;
        }
        Node m = sentinel.next;
        for (int i = 0; i < index; i++) {
            m = m.next;
        }
        return m.item;
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        Node m = sentinel.next;
        return helper(index, m);
    }

    private T helper(int index, Node n) {
        if (index == 0) {
            return n.item;
        } else {
            n = n.next;
            index--;
            return helper(index, n);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque n) {
            if (n.size() != this.size()) {
                return false;
            }
            for (int i = 0; i < this.size; i++) {
                if (this.get(i) != n.get(i)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIter();
    }

    private class LinkedListDequeIter implements Iterator<T> {
        private int wizPos;

        public LinkedListDequeIter() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            Node m = sentinel.next;
            for (int i = 0; i < wizPos; i++) {
                m = m.next;
            }
            wizPos++;
            return m.item;
        }
    }
}
