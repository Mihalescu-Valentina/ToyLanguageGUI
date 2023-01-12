package Expression;


import ADT.IHeap;
import ADT.MyIDictionary;
import Exception.MyException;
import Type.IType;
import Value.IValue;

public interface IExp {
    IValue eval(MyIDictionary<String,IValue> tbl, IHeap<Integer,IValue> hp) throws MyException;

    IExp deepCopy();

    String toString();

    IType typecheck(MyIDictionary<String,IType>typeEnv) throws MyException;
}
