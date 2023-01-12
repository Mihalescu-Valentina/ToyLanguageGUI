package Statement;


import ADT.MyIDictionary;
import ADT.PrgState;
import Exception.MyException;
import Type.IType;

public class NopStmt implements IStmt {
    public NopStmt() {
    }

    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    ;

    public String toString() {
        return new String();
    }

    ;

    public IStmt deepCopy() {
        return new NopStmt();
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return null;
    }

    ;

}
