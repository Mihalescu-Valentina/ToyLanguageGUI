package Statement;

import ADT.IHeap;
import ADT.MyIDictionary;
import ADT.MyIStack;
import ADT.PrgState;
import Exception.MyException;
import Expression.IExp;
import Type.BoolType;
import Type.IType;
import Value.BoolValue;
import Value.IValue;

public class whileStmt implements IStmt {

    IExp expression;
    IStmt stmt;

    public whileStmt(IExp exp, IStmt stmt) {
        this.expression = exp;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stack = state.getExeStack();
        MyIDictionary<String, IValue> symTbl = state.getSymTable();
        IHeap<Integer, IValue> hp = state.getHeap();
        IValue eval = expression.eval(symTbl, hp);
        IType val = new BoolType();
        if (!eval.getType().equals(val)) {
            throw new MyException("expr is not a boolean");
        } else if (eval.getType().equals(val)) {
            BoolValue b = (BoolValue) eval;
            if (b.getVal()) {
                stack.push(this);
                stack.push(stmt);
            }


        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new whileStmt(expression.deepCopy(), stmt.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typexp = expression.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            stmt.typecheck(typeEnv.deepCopy());
            return typeEnv;
        } else throw new MyException("The condition of WHILE does not have the type bool! ");
    }

    @Override
    public String toString() {
        return "while(" + expression + ") " + this.stmt;
    }
}
