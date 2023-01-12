package ADT;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    private List<T> out;

    public MyList() {
        out = new ArrayList<T>();
    }

    @Override
    public void add(T item) {
        out.add(item);
    }

    @Override
    public String toString() {
        return out.toString();
    }



    public List getList() {
        return out;
    }

    public void setList(List<T> out) {
        this.out = out;
    }
}
