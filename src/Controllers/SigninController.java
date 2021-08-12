package Controllers;

import Helpers.UserHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SigninController {

    @FXML
    private TextField vorname;

    @FXML
    private TextField nachname;

    @FXML
    private TextField username;

    @FXML
    private PasswordField newpass;

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
    void SignInClick(ActionEvent event) throws IOException {
        UserHelper myUserHelper= new UserHelper();
        myUserHelper.register(vorname.getText()+" "+nachname.getText(), username.getText(), newpass.getText());
        switchScene(event, "../FXML/Login.fxml","Stylesheets/Login.css");
    }

    @FXML
    void goBack(ActionEvent event) {

    }


}
