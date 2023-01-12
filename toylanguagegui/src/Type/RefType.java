package Type;


import Value.IValue;
import Value.RefValue;

public class RefType implements IType {
    IType inner;

    public RefType(IType inner) {
        this.inner = inner;
    }


    public IType getInner() {
        return inner;
    }

    public boolean equals(Object another) {
        if (another instanceof RefType)
            return inner.equals(((RefType) another).getInner());
        else return false;
    }

    public String toString() {
        return "Ref " + inner;
    }

    public IValue defaultValue() {
        return new RefValue(0, inner);
    }

    public IType deepCopy() {
        return new RefType(inner);
    }
}
