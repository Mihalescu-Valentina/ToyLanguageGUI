package Expression;


import ADT.IHeap;
import ADT.MyIDictionary;
import Exception.MyException;
import Type.IType;
import Type.IntType;
import Value.IValue;
import Value.IntValue;

public class ArithExp implements IExp {
    IExp exp1;
    IExp exp2;

    int op;

    public ArithExp(IExp exp1, IExp exp2,int op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }

    public IValue eval(MyIDictionary<String, IValue> tbl, IHeap<Integer,IValue> hp) throws MyException {

        IValue v1,v2;
        v1= exp1.eval(tbl,hp);
        if (v1.getType().equals(new IntType())) {
            v2 = exp2.eval(tbl,hp);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1= i1.getVal();
                n2 = i2.getVal();
                switch(op){
                    case 1:
                        return new IntValue(n1+n2);
                    case 2:
                        return new IntValue(n1-n2);

                    case 3:
                        return new IntValue(n1*n2);

                    case 4:
                        if(n2==0) throw new MyException("division by zero");
                        else  return new IntValue(n1/n2);



                }

            }else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
        return null;
    }



    public IExp deepCopy(){
        return new ArithExp(exp1.deepCopy(),exp2.deepCopy(),op);
    }

    @Override
    public String toString(){
        if(op==1)
             return exp1.toString()+"+"+exp2.toString();
        else
            if(op==2)
                return exp1.toString()+"-"+exp2.toString();
        else
            if(op==3)
                return exp1.toString()+"*"+exp2.toString();
        else
            if(op==4)
                return exp1.toString()+"/"+exp2.toString();
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
