package Controllers;

import Helpers.MainController;
import Helpers.UserHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label loginMessage;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button logInButton;

    public void switchScene(ActionEvent event, String fxml, String css) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Anmeldung");
        window.show();
    }



    @FXML
    void LogInClick(ActionEvent event) throws IOException {
        UserHelper myUserHelper= new UserHelper();
        if(myUserHelper.verify(username.getText(), password.getText())){
            loginMessage.setText("Success!");
            switchScene(event, "../FXML/AllBookings.fxml","Stylesheets/AllBookings.css" );
        }else {
            loginMessage.setText("Please try again");
        }
    }

    @FXML
    void goToSignIn(ActionEvent event) throws IOException {
        switchScene(event, "../FXML/Signin.fxml","Stylesheets/Signin.css"  );
    }

}
