package Value;


import Type.IType;

public interface IValue {
    IType getType();
    IValue deepCopy();

    String toString();

    boolean equals(Object obj);

}
