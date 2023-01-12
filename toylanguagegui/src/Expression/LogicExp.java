package Expression;


import ADT.IHeap;
import ADT.MyIDictionary;
import Exception.MyException;
import Type.BoolType;
import Type.IType;
import Value.BoolValue;
import Value.IValue;

public class LogicExp implements IExp{
    IExp e1;
    IExp e2;
    int op;

    public LogicExp(IExp e1, IExp e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public IValue eval(MyIDictionary<String, IValue> tbl, IHeap<Integer,IValue> hp) throws MyException {
        IValue nr1 = e1.eval(tbl,hp);
        if(nr1.getType().equals(new BoolType())){
            IValue nr2 = e2.eval(tbl,hp);
            if(nr2.getType().equals(new BoolType())){
                BoolValue b1 = (BoolValue) nr1;
                BoolValue b2 = (BoolValue) nr2;
                if(b1.getVal()&&b2.getVal())
                    return new BoolValue(true);
                else
                        return new BoolValue(false);
            }
            else throw new MyException("Operand2 is not an integer");

        }
        else throw new MyException("Operand 1 is not an integer!");

    }

    @Override
    public IExp deepCopy() {
        return new LogicExp(e1.deepCopy(),e2.deepCopy(),op);
    }

    @Override
    public IType typecheck(MyIDictionary<String, IType>typeEnv)throws MyException {
        IType t1,t2;
        t1 = e1.typecheck(typeEnv);
        t2 = e2.typecheck(typeEnv);
        if(t1.equals(new BoolType())) {
            if (t2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new MyException("second operator is not an integer!");

        }
        else
            throw new MyException("The first operand is not an integer");


    }

}
