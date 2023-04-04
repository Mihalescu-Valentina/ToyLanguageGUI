package Controller;


import ADT.IHeap;
import ADT.PrgState;
import Repository.IRepository;
import Value.IValue;
import Value.RefValue;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repo;

    private ExecutorService executor;

    public Controller(IRepository repo) {
        this.repo = repo;
    }

    public IRepository getRepo() {
        return this.repo;
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }


    void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg -> repo.logPrgStateExec(prg));
        
        //RUN concurrently one step for each of the existing PrgStates
        List<Callable<PrgState>> callList = prgList.stream().map((PrgState p) -> (Callable<PrgState>) (() -> {
            return p.oneStep();
        })).collect(Collectors.toList());
        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                            try {

                                return future.get();
                            } catch (InterruptedException | ExecutionException e) {
                                throw new RuntimeException(e);
                            }

                        }
                ).filter(p -> p != null)
                .collect(Collectors.toList());
        prgList.addAll(newPrgList);
        prgList.forEach(prg -> repo.logPrgStateExec(prg));
        repo.setPrgList(prgList);
    }


    public void allStep() {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        while (prgList.size() > 0) {
            try {

                prgList.forEach(p -> {
                    p.getHeap().setContent((HashMap<Integer, IValue>) safeGarbageCollector1(getAllAddresses1(p.getSymTable().getContent().values(), p.getHeap()), p.getHeap().getContent()));
                });
                this.oneStepForAllPrg(prgList);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        repo.setPrgList(prgList);
    }

    public void allStepGUI() {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());

            try {

                prgList.forEach(p -> {
                    p.getHeap().setContent((HashMap<Integer, IValue>) safeGarbageCollector1(getAllAddresses1(p.getSymTable().getContent().values(), p.getHeap()), p.getHeap().getContent()));
                });
                this.oneStepForAllPrg(prgList);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            prgList = removeCompletedPrg(repo.getPrgList());

        executor.shutdownNow();
        repo.setPrgList(prgList);
    }



    List<Integer> getAllAddresses1
            (Collection<IValue> symTableValues, IHeap<Integer, IValue> heapTable) {
        //takes all the adresses from the symtable
        ConcurrentLinkedDeque<Integer> symTableAdr = symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAdress();
                })
                .collect(Collectors.toCollection(ConcurrentLinkedDeque::new));


        //takes all the adresses from the heap
        symTableAdr.stream()
                .forEach(a -> {
                    IValue v = heapTable.getContent().get(a);
                    if (v instanceof RefValue)
                        if (!symTableAdr.contains(((RefValue) v).getAdress()))
                            symTableAdr.add(((RefValue) v).getAdress());
                });

        return symTableAdr.stream().toList();//conversie de la ConcurrentLinkedDeque la List
    }


    Map<Integer, IValue> safeGarbageCollector1
            (List<Integer> symTableAddr, Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }




}
