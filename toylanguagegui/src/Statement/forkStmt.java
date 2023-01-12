package Statement;


import ADT.*;
import Exception.MyException;
import Type.IType;
import Value.IValue;

import java.util.Map;

public class forkStmt implements IStmt {
    private IStmt Stmt;

    public forkStmt(IStmt stmt2) {
        this.Stmt = stmt2;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {

        MyIStack<IStmt> stack2 = new MyStack<IStmt>();
        MyIDictionary<String, IValue> symTbl2 = new MyDictionary<>();
        for (Map.Entry<String, IValue> entry : state.getSymTable().getContent().entrySet()) {
            symTbl2.put(entry.getKey(), entry.getValue().deepCopy());
        }

        return new PrgState(stack2, symTbl2, state.getOut(), state.getFileTable(), state.getHeap(), Stmt);
    }

    @Override
    public IStmt deepCopy() {
        return new forkStmt(Stmt.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {

        Stmt.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }


    @Override
    public String toString() {
        return "fork(" + this.Stmt.toString() + ")";
    }
}
