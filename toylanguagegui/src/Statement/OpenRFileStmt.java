package Statement;


import ADT.MyIDictionary;
import ADT.PrgState;
import Exception.MyException;
import Expression.IExp;
import Type.IType;
import Type.StringType;
import Value.IValue;
import Value.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFileStmt implements IStmt {
    private IExp exp;

    public OpenRFileStmt(IExp exp) {
        this.exp = exp;
    }

    public PrgState execute(PrgState prgState) throws MyException {
        IValue result = exp.eval(prgState.getSymTable(), prgState.getHeap());
        if (!result.getType().equals(new StringType()))
            throw new MyException("The name of the file must be a string");
        if (prgState.getFileTable().isVarDef((StringValue) result))
            throw new MyException("The file is already opened!");
        try {
            BufferedReader br = new BufferedReader(new FileReader(((StringValue) result).getVal()));
            prgState.getFileTable().put((StringValue) result, br);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new OpenRFileStmt(exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        if (exp.typecheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("When opening a file the name of the file has to be a string!");
    }

    @Override
    public String toString() {
        return "Open file(" + exp + ")";
    }


}
