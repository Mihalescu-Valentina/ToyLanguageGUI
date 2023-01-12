package Repository;


import ADT.PrgState;
import Exception.MyException;

import java.util.List;

public interface IRepository {

    void logPrgStateExec(PrgState prgSt) throws MyException;

    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> prgStates);

    PrgState getProgramWithId(int id);

    void addProgram(PrgState progState) ;
}
