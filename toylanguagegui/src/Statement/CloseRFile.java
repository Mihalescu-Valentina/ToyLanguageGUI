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
import java.io.IOException;

public class CloseRFile implements IStmt{
    IExp exp;

    public CloseRFile(IExp exp) {
        this.exp = exp;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue result = exp.eval(state.getSymTable(),state.getHeap());
        if(!result.getType().equals(new StringType()))
            throw new MyException("The filename that you want to read is not a string");
        if(!state.getFileTable().isVarDef((StringValue)result))
            throw new MyException("The file was not opened!");
        try {
            BufferedReader br = state.getFileTable().lookUp((StringValue) result);
            br.close();
            state.getFileTable().remove((StringValue)result);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
    }
    return null;
}

    @Override
    public IStmt deepCopy() {
        return new CloseRFile(exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        if(exp.typecheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("When closing a file the name of the file has to be a string!");
    }

    @Override
    public String toString(){
        return "CloseRFile("+this.exp+")";
    }
}
