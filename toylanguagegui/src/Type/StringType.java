package Type;


import Value.IValue;
import Value.StringValue;

public class StringType implements IType {
    @Override
    public IValue defaultValue() {
        return new StringValue("' '");
    }


    @Override
    public String toString() {
        return "String";
    }

    @Override
    public IType deepCopy() {
        return new StringType();
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof StringType;
    }
}
