package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private int size;

    private class Node {
        private K k;
        private V val;
        private Node left, right;
        private int size;

        public Node(K key, V val) {
            this.k = key;
            this.val = val;
        }
    }

    public BSTMap() {
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node n = getNode(root, key);
        if (n == null) {
            return null;
        }
        return n.val;
    }

    private Node getNode(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.k);
        if (cmp < 0) {
            return getNode(x.left, key);
        } else if (cmp > 0) {
            return getNode(x.right, key);
        } else {
            return x;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = putHelper(root, key, value);
    }

    private Node putHelper(Node x, K key, V val) {
        if (x == null) {
            size += 1;
            return new Node(key, val);
        }
        int cmp = key.compareTo(x.k);
        if (cmp < 0) {
            x.left = putHelper(x.left, key, val);
        } else if (cmp > 0) {
            x.right = putHelper(x.right, key, val);
        } else {
            x.val = val;
        }
        return x;
    }

    @Override
    public Set<K> keySet() {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        throw new UnsupportedOperationException();
    }
}
