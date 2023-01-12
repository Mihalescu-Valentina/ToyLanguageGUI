package Type;

import Value.IValue;

public interface IType {
    IValue defaultValue();
    IType deepCopy();

    String toString();

    public boolean equals(Object obj);
}
