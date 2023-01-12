package Value;


import Type.IType;
import Type.StringType;

public class StringValue implements IValue {
    String val;

    public StringValue(String val) {
        this.val = val;
    }


    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public IValue deepCopy() {
        return new StringValue(val);
    }

    public String getVal() {
        return val;
    }

    public String toString() {
        return val + " ";
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof StringValue;
    }
}
