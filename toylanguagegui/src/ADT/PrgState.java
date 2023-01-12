package ADT;


import Exception.MyException;
import Statement.IStmt;
import Value.IValue;
import Value.StringValue;

import java.io.BufferedReader;

public class PrgState {

    private static int globalId = 0;
    private int id;
    private MyIStack<IStmt> exeStack;

    private MyIDictionary<String, IValue> symTable;

    private MyIList<IValue> out;
    private IHeap<Integer, IValue> heap;
    private MyIFileTable<StringValue, BufferedReader> ftbl;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, IValue> symtbl, MyIList<IValue> ot, MyIFileTable<StringValue, BufferedReader> ftable, IHeap<Integer, IValue> h, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        ftbl = ftable;
        heap = h;
        this.exeStack.push(prg);
        this.id = setGlobalId();
    }

    //second PrgState constructor used for the gui in SelectWindowController
    public PrgState(IStmt originalProgram){
        exeStack = new MyStack<>();
        symTable = new MyDictionary<>();
        out = new MyList<>();
        ftbl = new MyFileTable<>();
        this.heap = new Heap();
        this.id = 1;
        this.exeStack.push(originalProgram);
    }

    public synchronized int setGlobalId() {
        globalId += 1;
        return globalId;
    }

    public Boolean isNotCompleted() {
        return !this.exeStack.isEmpty();
    }

    public int getId() {
        return id;
    }

    public void setId(int id2) {
        id = id2;
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }


    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDictionary<String, IValue> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, IValue> symTable) {
        this.symTable = symTable;
    }

    public MyIList<IValue> getOut() {
        return out;
    }

    public void setOut(MyIList<IValue> out) {
        this.out = out;
    }

    public String toString() {
        return "Program with id: " + id + '\n' +
                "Exe Stack : " + exeStack.toString() + '\n' +
                "Sym Table : " + symTable.toString() + '\n' +
                "Output : " + out.toString() + '\n' +
                "File Table: " + ftbl.toString() + '\n' +
                "Heap: " + heap.toString();
    }

    public IHeap<Integer, IValue> getHeap() {
        return this.heap;
    }

    public void setHeap(IHeap<Integer, IValue> h) {
        this.heap = h;
    }

    public MyIFileTable<StringValue, BufferedReader> getFileTable() {
        return ftbl;
    }

    public void setFileTable(MyIFileTable<StringValue, BufferedReader> ftbl) {
        this.ftbl = ftbl;
    }

    public PrgState oneStep() throws MyException {
        if (this.exeStack.isEmpty()) throw new MyException("prgState stack is empty");
        IStmt crtStmt = this.exeStack.pop();
        return crtStmt.execute(this);
    }

}
