package Expression;


import ADT.IHeap;
import ADT.MyIDictionary;
import Exception.MyException;
import Type.IType;
import Type.IntType;
import Value.BoolValue;
import Value.IValue;
import Value.IntValue;

public class RelationalExp implements IExp {
    IExp exp1, exp2;
    int op;

    public RelationalExp(IExp exp1, IExp exp2, int op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl, IHeap<Integer,IValue> hp) throws MyException {
        IValue val1 = exp1.eval(tbl,hp);
        if (val1.getType().equals(new IntType())) {
            IValue val2 = exp2.eval(tbl,hp);
            if (val2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) val1;
                IntValue i2 = (IntValue) val2;
                if (op == 1)
                    return new BoolValue(i1.getVal() < i2.getVal());
                else if (op == 2)
                    return new BoolValue(i1.getVal() <= i2.getVal());
                else if (op == 3)
                    return new BoolValue(i1.getVal() == i2.getVal());
                else if (op == 4)
                    return new BoolValue(i1.getVal() != i2.getVal());
                else if (op == 5)
                    return new BoolValue(i1.getVal() > i2.getVal());
                else if (op == 6)
                    return new BoolValue(i1.getVal() >= i2.getVal());

            } else throw new MyException("Operand2 is not an integer");
        } else throw new MyException("Operand 1 is not an integer!");
        return null;
    }

    @Override
    public IExp deepCopy() {
        return new RelationalExp(exp1.deepCopy(), exp2.deepCopy(), op);
    }

    @Override
    public String toString() {
        if (op == 1)
            return exp1.toString() + "<" + exp2.toString();
        else if (op == 2)
            return exp1.toString() + "<=" + exp2.toString();
        else if (op == 3)
            return exp1.toString() + "==" + exp2.toString();
        else if (op == 4)
            return exp1.toString() + "!=" + exp2.toString();
        else if (op == 5)
            return exp1.toString()+  ">"  + exp2.toString();
        else if(op==6)
            return  exp1.toString()+  ">="  + exp2.toString();
        else {
            return "";
        }
    }

    @Override
    public IType typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typ1,typ2;
        typ1 = exp1.typecheck(typeEnv);
        typ2 = exp2.typecheck(typeEnv);
        if(typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            } else
                throw new MyException("The second operator is not an integer!");

        }
        else
            throw new MyException("The first operator is not an integer!");

    }
}
