package ADT;

import java.util.concurrent.ConcurrentHashMap;

public class MyFileTable<K, V> implements MyIFileTable<K, V> {
    private ConcurrentHashMap<K, V> ftbl;

    public MyFileTable() {
        ftbl = new ConcurrentHashMap<>();
    }

    @Override
    public String toString() {
        return ftbl.toString();
    }

    @Override
    public void put(K key, V value) {
        ftbl.put(key, value);
    }

    @Override
    public boolean isVarDef(K key) {
        return ftbl.containsKey(key);
    }

    @Override
    public void update(K key, V value) {
        ftbl.put(key, value);
    }

    @Override
    public void remove(K key) {
        ftbl.remove(key);
    }

    @Override
    public ConcurrentHashMap getContent() {
        return ftbl;
    }


    @Override
    public V lookUp(K key) {
        return ftbl.get(key);
    }


}
