package Value;


import Type.BoolType;
import Type.IType;

public class BoolValue implements IValue {
    boolean val;

    public BoolValue(boolean v) {
        val = v;
    }


    @Override
    //public String toString() {return new BoolType().toString()+""+val;}
    public String toString() {
        return val + " ";
    }

    @Override
    public IType getType() {
        return new BoolType();
    }

    @Override
    public IValue deepCopy() {
        return new BoolValue(val);
    }

    public boolean getVal() {
        return val;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof BoolValue;
    }
}
