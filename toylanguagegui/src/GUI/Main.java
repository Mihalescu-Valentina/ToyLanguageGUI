package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

//Application is an abstract class that extends Object
public class Main extends Application {

    public static void main(String[] args) {
        // by executing this method, a new object of type Application is created
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader mainLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/MainWindow.fxml"));
        Parent mainWindow = mainLoader.load();
        MainWindowController mainWindowController = mainLoader.getController();
        stage.setTitle("Main Window");
        Scene myScene = new Scene(mainWindow);
        stage.setScene(myScene);
        stage.show();

        //fmxLoader is taking the content from the fxml file which contains the Controllers for this window/stage
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/SelectWindow.fxml"));
        Parent root = fxmlLoader.load();
        SelectWindowController selectWindowController = fxmlLoader.getController();
        selectWindowController.setMainWindowController(mainWindowController);
        //scene is the main layer/container of the application
        Scene scene = new Scene(root);

        //the title of the window/stage is set
        Stage secondStage = new Stage();
        secondStage.setTitle("Select Window!");
        //the scene/layer is added to the window
        secondStage.setScene(scene);
        //display the window
        secondStage.show();

        //this ui configuration works as a graph because the scene node can only have one child,that child on the other hand(the root) can contain multiple children/nodes/features/widgets
        //the stage is the parent of the scene
    }
}