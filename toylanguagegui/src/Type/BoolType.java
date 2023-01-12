package Type;


import Value.BoolValue;

public class BoolType implements IType {

    @Override
    public boolean equals(Object another) {
        if (another instanceof BoolType)
            return true;
        else
            return false;
    }

    @Override
    public BoolValue defaultValue() {
        BoolValue val = new BoolValue(false);
        return val;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public IType deepCopy() {
        return new BoolType();
    }
}
