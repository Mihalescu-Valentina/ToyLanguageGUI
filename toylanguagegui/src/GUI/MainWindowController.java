package GUI;

import ADT.MyIStack;
import ADT.PrgState;
import Controller.Controller;
import Exception.MyException;
import Statement.IStmt;
import Value.IValue;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


public class MainWindowController implements Initializable {

    @FXML
    private ListView<String> exeStackView;
    @FXML
    private TableView<Map.Entry<String, IValue>> symbolTableView;
    @FXML
    private TableColumn<Map.Entry<String,IValue>,String> symTableNames;
    @FXML
    private TableColumn<Map.Entry<String,IValue>,String> symTableValues;
    @FXML
    private Label progStatesCount;
    @FXML
    private Button execButton;
    @FXML
    private TableView<Map.Entry<Integer,IValue>> heapTableView;
    @FXML
    private TableColumn<Map.Entry<Integer,IValue>,Integer> heapTableAddr;
    @FXML
    private TableColumn <Map.Entry<Integer, IValue>, String> heapTableValues;
    @FXML
    private ListView<String> outputView;
    @FXML
    private ListView<String> fileTableView;
    @FXML
    private ListView<Integer> progIdentifiersView;
    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
        populateProgStatesCount();
        populateIdentifiersView();
        execButton.setDisable(false);
    }

    //this method returns the programState of the selected program from the listview of programIdentifiers
    private PrgState getSelectedProgramState(){
        if(progIdentifiersView.getSelectionModel().getSelectedIndex()==-1)
            return null;
        int id = progIdentifiersView.getSelectionModel().getSelectedItem();
        return controller.getRepo().getProgramWithId(id);
    }


    private void populateProgStatesCount(){
        progStatesCount.setText("No of Program States:" + controller.getRepo().getPrgList().size());
    }

    private void populateHeapTableView(PrgState givenProgramState){
        heapTableView.setItems(FXCollections.observableList(new ArrayList<>(givenProgramState.getHeap().getContent().entrySet())));
        heapTableView.refresh();
    }

    private void populateOutputView(PrgState givenProgramState) throws MyException {
        outputView.setItems(FXCollections.observableArrayList(givenProgramState.getOut().getList()));
    }

    private void populateFileTableView(PrgState givenProgramState){
        fileTableView.setItems(FXCollections.observableArrayList(givenProgramState.getFileTable().getContent().keySet()));
    }

    //this method populates the progIdentifiersView with the id's of all the programs stored into our toy language
    private void populateIdentifiersView(){
        progIdentifiersView.setItems(FXCollections.observableArrayList(controller.getRepo().getPrgList().stream().map(PrgState::getId).collect(Collectors.toList())));
        progIdentifiersView.refresh();
    }

    //this method populates the stack by adding the String equivalents of the Statements in a List that can be used in the FXCollections specific method for completing the list view
    private void populateExeStackView(PrgState givenProgramState){
        MyIStack<IStmt> stack = givenProgramState.getExeStack();
        List<String> stackOutput = new ArrayList<>();
        for (IStmt stm : stack.getValues()){
            stackOutput.add(stm.toString());
        }
        Collections.reverse(stackOutput);
        exeStackView.setItems(FXCollections.observableArrayList(stackOutput));
    }
    private void populateSymTableView(PrgState givenProgramState){
        symbolTableView.setItems(FXCollections.observableList(new ArrayList<>(givenProgramState.getSymTable().getContent().entrySet())));
        symbolTableView.refresh();
    }

    private void changeProgramStateHandler(PrgState currentProgState){
        if(currentProgState==null)
            return;
        try {
            populateProgStatesCount();
            populateIdentifiersView();
            populateHeapTableView(currentProgState);
            populateOutputView(currentProgState);
            populateFileTableView(currentProgState);
            populateExeStackView(currentProgState);
            populateSymTableView(currentProgState);

        } catch (MyException e) {
            Alert error = new Alert(Alert.AlertType.ERROR,e.getMessage());
            error.show();
        }

    }
    public void oneStepHandler() {
        if(controller==null){
            Alert error = new Alert(Alert.AlertType.ERROR,"No program selected!");
            error.show();
            execButton.setDisable(true);
            return;
        }
        PrgState programState = getSelectedProgramState();
        if(programState!=null && !programState.isNotCompleted()){
            Alert error = new Alert(Alert.AlertType.ERROR,"Nothing left to execute!");
            error.show();
            return;
        }
        try {
            controller.allStepGUI();
            changeProgramStateHandler(programState);
            if(controller.getRepo().getPrgList().size()==0)
                execButton.setDisable(true);

        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR,e.getMessage());
            error.show();
            execButton.setDisable(true);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.controller = null;

        heapTableAddr.setCellValueFactory(p-> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        heapTableValues.setCellValueFactory(p-> new SimpleStringProperty(p.getValue().getValue() + " "));

        symTableNames.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getKey() + " "));
        symTableValues.setCellValueFactory(p-> new SimpleStringProperty(p.getValue().getValue() + " "));

        progIdentifiersView.setOnMouseClicked(mouseEvent -> changeProgramStateHandler(getSelectedProgramState()));
        execButton.setDisable(true);

    }


}
