package MyHashMap;

public interface IMyHashMap<K, V> {
    boolean containsKey(K key);

    V put(K key, V val);

    V get(K key);

    V remove(K key);

    int size();

    boolean isEmpty();
}
