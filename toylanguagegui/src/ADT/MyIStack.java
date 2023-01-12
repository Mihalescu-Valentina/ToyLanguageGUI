package ADT;

import Exception.MyException;

import java.util.List;

public interface MyIStack<T> {
    void push(T item);

    T pop() throws MyException;

    boolean isEmpty();

    List<T> getReversed();

    public String toString();

    List<T> getValues();

}
