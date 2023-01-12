package View;

import ADT.MyDictionary;
import Controller.Controller;
import Exception.MyException;
import Type.IType;

public class RunExample extends Command {
    private Controller ctr;

    public RunExample(String key, String desc, Controller ctr) {
        super(key, desc);
        this.ctr = ctr;
    }

    @Override
    public void execute() {
        try {
            this.ctr.getRepo().getPrgList().get(0).getExeStack().getValues().get(0).typecheck(new MyDictionary<String, IType>());
            ctr.allStep();
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

}
