package ADT;

import java.util.concurrent.ConcurrentHashMap;

public interface MyIFileTable<K, V> {
    String toString();

    void put(K key, V value);

    boolean isVarDef(K key);

    V lookUp(K key);

    void update(K key, V value);

    void remove(K key);

    ConcurrentHashMap getContent();

}
