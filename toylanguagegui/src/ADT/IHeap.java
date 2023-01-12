package ADT;

import Value.IValue;

import java.util.HashMap;

public interface IHeap<K, V> {
    String toString();

    int put(V value);

    boolean isVarDef(K key);

    V lookUp(K key);

    void update(K key, V value);

    HashMap<Integer, IValue> getContent();

    void setContent(HashMap<Integer, IValue> map);

    int getFreeLocation();
}
