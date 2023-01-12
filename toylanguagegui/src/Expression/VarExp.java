package Expression;

import ADT.IHeap;
import ADT.MyIDictionary;
import Exception.MyException;
import Type.IType;
import Value.IValue;

public class VarExp implements IExp {
    String id;

    public VarExp(String id) {
        this.id = id;
    }

    public IValue eval(MyIDictionary<String, IValue> tbl, IHeap<Integer, IValue> hp) throws MyException {
        return tbl.lookUp(id);
    }

    @Override
    public IExp deepCopy() {
        return new VarExp(new String(id));
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public IType typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return typeEnv.lookUp(id);
    }
}
