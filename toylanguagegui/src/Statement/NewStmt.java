package Statement;


import ADT.IHeap;
import ADT.MyIDictionary;
import ADT.PrgState;
import Exception.MyException;
import Expression.IExp;
import Type.IType;
import Type.RefType;
import Value.IValue;
import Value.RefValue;

public class NewStmt implements IStmt {

    String var_name;
    IExp expression;

    public NewStmt(String var_n, IExp exp) {
        this.var_name = var_n;
        this.expression = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, IValue> symTbl = state.getSymTable();
        IHeap<Integer, IValue> hp = state.getHeap();
        if (!(symTbl.isVarDef(var_name)))
            throw new MyException("The variable is not in the sym table!");
        if (!(symTbl.<String>lookUp(var_name).getType() instanceof RefType))
            throw new MyException("The variable does not have the type RefType so it can't be allocated on the heap");
        if (!(expression.eval(symTbl, hp).getType().equals(((RefValue) symTbl.lookUp(var_name)).getLocationType())))
            throw new MyException("The types do not match!");
        IHeap<Integer, IValue> heap = state.getHeap();
        int freeAdress = heap.put(expression.eval(symTbl, hp));
        symTbl.update(var_name, new RefValue(freeAdress, expression.eval(symTbl, hp).getType()));
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NewStmt(var_name, expression);
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typevar = typeEnv.lookUp(var_name);
        IType typexp = expression.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types");
    }


    @Override
    public String toString() {
        return "new(" + var_name + "," + expression + ")";
    }
}
