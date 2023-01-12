package Statement;


import ADT.MyIDictionary;
import ADT.PrgState;
import Exception.MyException;
import Type.IType;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;

    String toString();

    IStmt deepCopy();

    MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException;

}
