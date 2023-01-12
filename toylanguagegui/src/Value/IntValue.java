package Value;


import Type.IType;
import Type.IntType;

public class IntValue implements IValue {
    int val;

    public IntValue(int v) {
        val = v;
    }

    public IType getType() {
        return new IntType();
    }

    @Override
    public String toString() {
        return val + " ";
    }

    @Override
    public IValue deepCopy() {
        return new IntValue(val);
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof IntValue;
    }
}
