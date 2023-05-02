package MyHashMap;

public class MyHashMap<K, V> implements IMyHashMap<K, V> {

    private int size;
    private int DEFAULT_SIZE;
    private Node<K, V>[] table;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        this.size = 0;
        this.DEFAULT_SIZE = 11;
        this.table = new Node[DEFAULT_SIZE];
    }

    static class Node<K, V> {
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

    @Override
    public V put(K key, V val) {
        int index = hashFunction(key);
        Node<K, V> node = table[index];
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

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int hashFunction(K key) {
        int index = key.hashCode() % DEFAULT_SIZE;
        return index >= 0 ? index : index * -1;
    }

}
