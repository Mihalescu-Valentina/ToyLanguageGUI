package Statement;

import ADT.MyIDictionary;
import ADT.PrgState;
import Exception.MyException;
import Expression.IExp;
import Type.IType;
import Type.IntType;
import Type.StringType;
import Value.IValue;
import Value.IntValue;
import Value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadRFileStmt implements IStmt {

    public IExp exp;
    private String var_name;

    public ReadRFileStmt(IExp exp, String var_name) {
        this.exp = exp;
        this.var_name = var_name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue result = exp.eval(state.getSymTable(), state.getHeap());
        if (!result.getType().equals(new StringType()))
            throw new MyException("The filename that you want to read is not a string");
        if (!(state.getSymTable().isVarDef(var_name)))
            throw new MyException("The variable not in the symtable");
        if (!state.getSymTable().lookUp(var_name).getType().equals(new IntType()))
            throw new MyException("The variable is not an integer so it can't be read from a file");
        //StringValue varf = (StringValue)state.getSymTable().lookUp(((StringValue)result).getVal());
        StringValue varf = (StringValue) result;
        if (!state.getFileTable().isVarDef(varf))
            throw new MyException("The file table name is not defined");
        else {
            BufferedReader br = state.getFileTable().lookUp(varf);
            int nr = 0;
            try {
                String s = br.readLine();
                nr = Integer.parseInt(s);

            } catch (IOException e) {
                nr = 0;
            }

            state.getSymTable().update(var_name, new IntValue(nr));
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new ReadRFileStmt(exp.deepCopy(), new String(var_name));
    }


    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        if (exp.typecheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("When reading from a file the name of the file has to be a string!");
    }

    @Override
    public String toString() {
        return "ReadFile(" + this.exp + "," + this.var_name + ")";
    }
}
