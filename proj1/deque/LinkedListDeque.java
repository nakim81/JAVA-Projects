package deque;

public class LinkedListDeque<T> {

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
    public void addFirst(T item) {
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    public void addLast(T item) {
        Node last = sentinel.prev;
        Node newNode = new Node(last, item, sentinel);
        last.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node m = sentinel.next;
        while (m != sentinel) {
            System.out.print(m.item + " ");
            m = m.next;
        }
        System.out.print("\n");
    }

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

    public boolean equals(Object o) {
        boolean check = false;
        if (o instanceof LinkedListDeque<?>) {
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i) == ((LinkedListDeque<?>) o).get(i)) {
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
