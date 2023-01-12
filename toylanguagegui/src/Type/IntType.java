package Type;


import Value.IntValue;

public class IntType implements IType {
    @Override
    public boolean equals(Object another) {
        if (another instanceof IntType)
            return true;
        else
            return false;
    }

    public String toString() {
        return "int";
    }

    @Override
    public IntValue defaultValue() {
        IntValue val = new IntValue(0);
        return val;

    }

    @Override
    public IType deepCopy() {
        return new IntType();
    }


}
