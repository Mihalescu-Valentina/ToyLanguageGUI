package ADT;


import Exception.MyException;
import Value.IValue;

import java.util.HashMap;

public class Heap implements IHeap<Integer, IValue> {
    private HashMap<Integer, IValue> heap;
    private int newFreeLocation;

    public Heap() {
        heap = new HashMap<>();
        newFreeLocation = 1;
    }

    public int getFreeLocation() {
        return this.newFreeLocation;
    }

    @Override
    public boolean isVarDef(Integer key) {
        return heap.containsKey(key);
    }

    public int nextFree() {
        while (isVarDef(newFreeLocation)) {
            if (newFreeLocation == Integer.MAX_VALUE)
                newFreeLocation = 0;
            newFreeLocation = newFreeLocation + 1;
        }

        return newFreeLocation;
    }

    @Override
    public int put(IValue value) {
        this.newFreeLocation = nextFree();
        if (this.newFreeLocation == 0)
            throw new MyException("the heap is full!!");
        heap.put(this.newFreeLocation, value);
        return newFreeLocation;


    }


    @Override
    public void update(Integer key, IValue value) {
        heap.put(key, value);
    }

    @Override
    public HashMap<Integer, IValue> getContent() {
        return this.heap;
    }

    @Override
    public void setContent(HashMap<Integer, IValue> map) {
        this.heap = map;
    }

    @Override
    public IValue lookUp(Integer key) {
        return heap.get(key);
    }

    @Override
    public String toString() {
        return heap.toString();
    }


}
