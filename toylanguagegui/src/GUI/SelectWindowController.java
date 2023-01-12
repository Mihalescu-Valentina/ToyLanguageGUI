package GUI;

import ADT.MyDictionary;
import ADT.PrgState;
import Controller.Controller;
import Exception.MyException;
import Expression.*;
import Repository.IRepository;
import Repository.Repository;
import Statement.*;
import Type.BoolType;
import Type.IType;
import Type.IntType;
import Type.RefType;
import Value.BoolValue;
import Value.IntValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SelectWindowController implements Initializable {


    @FXML
    private ListView<IStmt> selectItemListView;

    private MainWindowController mainWindowController;

    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    @FXML
    private IStmt selectExample(ActionEvent actionEvent) {
        return selectItemListView.getItems().get(selectItemListView.getSelectionModel().getSelectedIndex());
    }

    private List<IStmt> initExamples() {
        List<IStmt> list = new ArrayList<>();
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt(new ValueExp(new IntValue(2)), "v"),
                        new PrintStmt(new VarExp("v"))));

        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt(new ValueExp(new BoolValue(true)), "a"),
                                new CompStmt(new IfStmt(new VarExp("a"),
                                        new AssignStmt(new ValueExp(new IntValue(2)), "v"),
                                        new AssignStmt(new ValueExp(new IntValue(3)), "v")),
                                        new PrintStmt(new VarExp("v"))))));

        //this example should raise an error because the varf is not a StringType variable(typecheck)
        IStmt ex3 = new CompStmt(new VarDeclStmt("varf", new IntType()),
                new CompStmt(new AssignStmt(new ValueExp(new IntValue(234)), "varf"),
                        new CompStmt(new OpenRFileStmt(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadRFileStmt(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadRFileStmt(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFile(new VarExp("varf"))))))))));


        IStmt ex4 = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(
                                new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(
                                        new NewStmt("a", new VarExp("v")),
                                        new CompStmt(
                                                new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a"))
                                        )
                                )
                        )
                )
        );


        IStmt ex5 = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(
                                new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(
                                        new NewStmt("a", new VarExp("v")),
                                        new CompStmt(
                                                new PrintStmt(new rHExp(new VarExp("v"))),
                                                new PrintStmt(new ArithExp(
                                                        new rHExp(new rHExp(new VarExp("a"))),
                                                        new ValueExp(new IntValue(5)),
                                                        1)
                                                )
                                        )
                                )
                        )
                )
        );

        IStmt ex6 = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(
                                new PrintStmt(new rHExp(new VarExp("v"))),
                                new CompStmt(
                                        new wHStmt("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp(
                                                new rHExp(new VarExp("v")),
                                                new ValueExp(new IntValue(5)),
                                                1)
                                        )
                                )
                        )
                )
        );


        IStmt ex7 = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(
                                new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(
                                        new NewStmt("a", new VarExp("v")),
                                        new CompStmt(
                                                new NewStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new rHExp(new rHExp(new VarExp("a"))))
                                        )
                                )
                        )
                )
        );


        IStmt ex8 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt(new ValueExp(new IntValue(4)), "v"),
                        new CompStmt(
                                new whileStmt(
                                        new RelationalExp(
                                                new VarExp("v"),
                                                new ValueExp(new IntValue(0)),
                                                5
                                        ),
                                        new CompStmt(
                                                new PrintStmt(new VarExp("v")),
                                                new AssignStmt(new ArithExp(
                                                        new VarExp("v"),
                                                        new ValueExp(new IntValue(1)),
                                                        2), "v"
                                                )
                                        )
                                ),
                                new PrintStmt(new VarExp("v"))
                        )
                )
        );


        IStmt ex9 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt(new ValueExp(new IntValue(10)), "v"),
                                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))), new CompStmt(
                                        new forkStmt(new CompStmt(new wHStmt("a", new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt(new ValueExp(new IntValue(32)), "v"),
                                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                                new PrintStmt(new rHExp(new VarExp("a")))
                                                        )))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rHExp(new VarExp("a"))))
                                )

                                ))

                ));

        //this example is wrong because wH is performed on a simple int variable and not on a RefType value
        IStmt ex10 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt(new ValueExp(new IntValue(10)), "v"),
                                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))), new CompStmt(
                                        new forkStmt(new CompStmt(new wHStmt("v", new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt(new ValueExp(new IntValue(32)), "v"),
                                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                                new PrintStmt(new rHExp(new VarExp("a")))
                                                        )))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rHExp(new VarExp("a"))))
                                )

                                ))

                ));
        list.add(ex1);
        list.add(ex2);
        list.add(ex3);
        list.add(ex4);
        list.add(ex5);
        list.add(ex6);
        list.add(ex7);
        list.add(ex8);
        list.add(ex9);
        list.add(ex10);
        return list;


    }

    private void displayExamples() {
        List<IStmt> examples = initExamples();
        selectItemListView.setItems(FXCollections.observableArrayList(examples));
        selectItemListView.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                int index = selectItemListView.getSelectionModel().getSelectedIndex();
            IStmt selectedProgram = selectItemListView.getItems().get(index);
            index++;
            PrgState programState = new PrgState(selectedProgram);
            IRepository repository = new Repository(programState,"log" + index + ".txt");
            Controller controller = new Controller(repository);


            try {
                selectedProgram.typecheck(new MyDictionary<String, IType>());
                mainWindowController.setController(controller);
            } catch (MyException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
                alert.show();
            }

            }

        });


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayExamples();
    }

}