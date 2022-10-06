package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private K k;
    private V val;
    private BSTMap left;
    private BSTMap right;
    private int size;

    public BSTMap() {
        k = null;
        val = null;
        size = 0;
    }
    @Override
    public void clear() {
        this.k = null;
        this.val = null;
        if (this.left != null) {
            this.left.clear();
        }
        if (this.right != null) {
            this.right.clear();
        }
        this.size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return this.get(key) != null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (this.size == 0) {
            return null;
        }
        int cmp = key.compareTo((K) this.k);
        if (cmp < 0) {
            if (this.left != null) {
                return (V) this.left.get(key);
            }
            return null;
        }
        else if (cmp > 0) {
            if (this.right != null) {
                return (V) this.right.get(key);
            }
            return null;
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
        if (this.k == null) {
            this.k = key;
            this.val = value;
            this.size += 1;
        } else {
            int cmp = key.compareTo(this.k);
            if (cmp < 0) {
                this.left = new BSTMap();
                this.left.put(key, value);
            } else {
                this.right = new BSTMap();
                this.right.put(key, value);
            }
            this.size += 1;
        }
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
