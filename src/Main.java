import Helpers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainController ctrl= MainController.getInstance();

        Parent root = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));
        primaryStage.setTitle("Reservierungssytem");
        Scene scene= new Scene(root, 300, 275);
        scene.getStylesheets().add("Stylesheets/Login.css");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            try {
                logOut(primaryStage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void logOut(Stage stage) throws IOException {
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("LogOut");
        alert.setHeaderText("Alle Daten werden nach dem Ende des Programmes gespeichert!");
        alert.setContentText("Möchten Sie die Anwendung schließen?");
        if(alert.showAndWait().get()== ButtonType.OK){
            System.out.println("You successfully logged out!");
            MainController ctrl= MainController.getInstance();

            ctrl.getMyFileHelper().writeUsers(ctrl.getHmUsers());
            ctrl.getMyFileHelper().writeBookings(ctrl.getLsBookings());

            stage.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
