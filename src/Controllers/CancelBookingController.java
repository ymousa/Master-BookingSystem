package Controllers;

import Datenobjekte.Booking;
import Helpers.BookingHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CancelBookingController implements Initializable {

    @FXML
    private ChoiceBox<Booking> choiceBox;

    @FXML
    private Label selectedBooking;


    private void switchScene(ActionEvent event, String fxml, String css) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Anmeldung");
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {        //set choice box all MY Bookings
        BookingHelper myBookingHelper= new BookingHelper();

        try {
            choiceBox.getItems().addAll( myBookingHelper.getLsMyBookings());
        } catch (IOException e) {
            e.printStackTrace();
        }
        choiceBox.setOnAction(this::getBooking);

    }

    private void getBooking(ActionEvent event) {                      //for choicBox:  myBooking get valueOf chppsedBooking
        selectedBooking.setText(choiceBox.getValue().toString());
    }

    @FXML
    private void cancelButtonClicked(ActionEvent event) throws IOException {
        BookingHelper myBookingHelper= new BookingHelper();
        myBookingHelper.cancelBooking(choiceBox.getValue());                //choiceBox.getValue= selectedBooking

        selectedBooking.setText("Die Reservierung wurde erfolgreich storniert!");
    }


    @FXML
    private void allBookingsClicked(ActionEvent event) throws IOException {
        switchScene(event, "../FXML/AllBookings.fxml","Stylesheets/AllBookings.css"  );
    }

    @FXML
    private void bookMenuClicked(ActionEvent event) throws IOException {
        switchScene(event, "../FXML/Book.fxml","Stylesheets/Book.css"  );
    }

    @FXML
    private void cancelMenuClicked(ActionEvent event) throws IOException{
        switchScene(event, "../FXML/CancelBooking.fxml","Stylesheets/CancelBooking.css"  );
    }

    @FXML
    private void logoutClicked(ActionEvent event) throws IOException{
        switchScene(event, "../FXML/Login.fxml","Stylesheets/Login.css"  );
    }

}
