package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here Jackey-Hua
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (p.key.compareTo(key) > 0) {
            return getHelper(key, p.left);
        } else if (p.key.compareTo(key) < 0) {
            return getHelper(key, p.right);
        }
        return p.value;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            return new Node(key, value);
        }
        if (p.key.compareTo(key) > 0) {
            p.left = putHelper(key, value, p.left);
        } else if (p.key.compareTo(key) < 0) {
            p.right = putHelper(key, value, p.right);
        } else {
            p.value = value;
        }
        return p;
    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
        size += 1;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    private void display(Node p, Set<K> set) {
        if (p == null) {
            return;
        }
        set.add(p.key);
        display(p.right, set);
        display(p.left, set);
    }

    @Override
    public Set<K> keySet() {
        Set<K> ret = new HashSet<>();
        display(root, ret);
        return ret;
    }

    /**
     * Removes the smallest key and associated value from the symbol table.
     */
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    /**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key in the symbol table
     */
    public K min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     *
     * @Source https://algs4.cs.princeton.edu/32bst/BST.java.html
     *
     * pretty handy solution of remove action
     *
     * @Important !!!!!!!!
     */
    @Override
    public V remove(K key) {
        root = removeHelper(root, key);
        return get(key);
    }

    private Node removeHelper(Node p, K key) {
        if (p.key.compareTo(key) > 0) {
            p.left = removeHelper(p.left, key);
        } else if (p.key.compareTo(key) < 0) {
            p.right = removeHelper(p.right, key);
        } else {
            if (p.left == null) {
                p = p.right;
            } else if (p.right == null) {
                p = p.left;
            } else {
                Node t = p;
                p = min(t.right);
                p.right = deleteMin(t.right);
                p.left = t.left;
            }
        }
        return p;
    }

    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value.  Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (value.equals(get(key))) {
            return remove(key);
        }
        return null;
    }

    /**
     * Remember that Set has a inbuilt iterator function.
     */
    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}

