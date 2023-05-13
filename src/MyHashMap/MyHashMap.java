package MyHashMap;

import java.util.Iterator;

public class MyHashMap<K, V> implements IMyHashMap<K, V>, Iterable<MyHashMap.Node<K, V>> {

    private int size;
    private int DEFAULT_SIZE;
    private Node<K, V>[] table;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        this.size = 0;
        this.DEFAULT_SIZE = 11;
        this.table = new Node[DEFAULT_SIZE];
    }

    /**
     * This is a generic class representing a node in a hash table, with a key,
     * value, hash code, and a
     * reference to the next node.
     */
    public static class Node<K, V> {
        final K key;
        final int hash;
        V value;

        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.hash = key.hashCode();
        }

        public int getHash() {
            return hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }
    }

    /**
     * This is a put method for a hash table that adds a new key-value pair to the
     * table or updates the
     * value of an existing key, while ensuring no duplicates are added.
     * 
     * @param key The key of the key-value pair being added to the hash table.
     * @param val "val" is the value associated with the key in the hash table. It
     *            is the value that is
     *            being added or updated in the hash table.
     * @return The method is returning the value (of type V) that was just added or
     *         updated in the hash
     *         table.
     */
    @Override
    public V put(K key, V val) {
        int index = hashFunction(key);
        Node<K, V> node = table[index];
        if (containsKey(key)) { // This should make sure that no Duplicated items can be added making it behave
                                // as a hash set.
            return get(key);
        }
        if (node == null) {
            table[index] = new Node<K, V>(key, val);
        } else {
            while (node.next != null) {
                if (node.key.equals(key)) {
                    node.setValue(val);
                    return val;
                }
                node = node.next;
            }

            if (node.key.equals(key)) { // Checks the last one
                node.setValue(val);
                return val;
            }
            node.next = new Node<>(key, val);

        }
        size++;
        return val;
    }

    /**
     * The function checks if a given key exists in the map and returns a boolean
     * value.
     * 
     * @param key The parameter "key" is of type K, which is a generic type
     *            representing the key used to
     *            access a value in a map. It could be any type of object, depending
     *            on how the map is defined.
     * @return The method `containsKey` returns a boolean value indicating whether
     *         the key is present in
     *         the data structure or not. It returns `true` if the key is present,
     *         and `false` otherwise.
     */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * This is a Java function that retrieves the value associated with a given key
     * in a hash table.
     * 
     * @param key The key parameter is the key of the key-value pair that we want to
     *            retrieve the value
     *            for.
     * @return The method is returning the value associated with the specified key
     *         in the hash table. If
     *         the key is not found in the hash table, the method returns null.
     */
    @Override
    public V get(K key) {
        int index = hashFunction(key);
        Node<K, V> node = table[index];
        if (node == null) {
            return null;
        } else {
            while (node != null) {
                if (node.key.equals(key)) {
                    return node.value;
                }
                node = node.next;
            }
        }
        return null;
    }

    /**
     * This function removes a key-value pair from a hash table and returns the
     * value associated with the
     * key.
     * 
     * @param key The key of the key-value pair that needs to be removed from the
     *            hash table.
     * @return The method is returning the value associated with the key that was
     *         removed from the hash
     *         table. If the key is not found in the hash table, the method returns
     *         null.
     */
    @Override
    public V remove(K key) {
        int hash = hashFunction(key);
        Node<K, V> node = table[hash];
        V value = null;
        if (node == null) {
            value = null;
        } else if (node.key.equals(key)) {
            table[hash] = node.next;
            value = node.value;
            node = null;
        } else {
            Node<K, V> pointer = node;
            node = node.next;
            while (node != null) {
                if (node.key.equals(key)) {
                    pointer.next = node.next;
                    value = node.value;
                    node.next = null;
                }
                pointer = node;
                node = node.next;
            }

        }
        size--;
        return value;
    }

    /**
     * This function returns the size of a data structure.
     * 
     * @return The method is returning the value of the variable "size".
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * This function checks if a data structure is empty by comparing its size to
     * zero.
     * 
     * @return The method is returning a boolean value which indicates whether the
     *         size of the object is
     *         equal to zero or not. If the size is zero, then the method will
     *         return true, indicating that the
     *         object is empty. Otherwise, it will return false, indicating that the
     *         object is not empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * This function returns an iterator that iterates over the nodes in a hash
     * table.
     * It is used so it is possible to traverse the hastable.
     * 
     * @return An iterator over the nodes in the hash table.
     */
    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<Node<K, V>>() {
            int currentIndex = 0;
            Node<K, V> currentNode = null;

            @Override
            public boolean hasNext() {
                while (currentIndex < table.length && table[currentIndex] == null) {
                    currentIndex++;
                }
                if (currentIndex >= table.length) {
                    return false;
                }
                currentNode = table[currentIndex];
                return true;
            }

            @Override
            public Node<K, V> next() {
                Node<K, V> node = currentNode;
                if (node.next != null) {
                    currentNode = node.next;
                } else {
                    currentIndex++;
                    hasNext();
                }
                return node;
            }
        };
    }

    /**
     * This is a hash function in Java that takes a key and returns an index based
     * on the key's hash code.
     * 
     * @param key The key is a parameter of generic type K, which represents the key
     *            used to access a value
     *            in a hash table. It could be any object type that implements the
     *            hashCode() method.
     * @return The method is returning an integer value, which is the index of the
     *         hash table where the
     *         key-value pair will be stored.
     */
    public int hashFunction(K key) {
        int index = key.hashCode() % DEFAULT_SIZE;
        return index >= 0 ? index : index * -1;
    }

}
