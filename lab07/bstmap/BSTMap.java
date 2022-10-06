package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private K key;
    private V val;
    private BSTMap left;
    private BSTMap right;
    private int size;

    public BSTMap() {
    }
    @Override
    public void clear() {
        this.key = null;
        this.val = null;
        this.left.clear();
        this.right.clear();

    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (this == null) {
            return null;
        }
        int cmp = key.compareTo(this.key);
        if (cmp < 0) {
            return (V) this.left.get(key);
        }
        else if (cmp > 0) {
            return (V) this.right.get(key);
        } else {
            return (V) this.val;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (value == null) {
            remove(key);
        }
        this.key = key;
        this.val = value;
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

    }
}
