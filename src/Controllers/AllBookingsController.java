package Controllers;

import Datenobjekte.Booking;
import Helpers.BookingHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class AllBookingsController {

    @FXML
    private TextArea nr;

    @FXML
    private TextArea name;

    @FXML
    private TextArea datum;

    @FXML
    private TextArea veranstaltung;

    @FXML
    private TextArea dauer;

    @FXML
    private TextArea notizen;


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
    void updateResClicked(ActionEvent event) throws IOException {
        TextArea[] allTextAreas= new TextArea[]{nr, name, datum, veranstaltung, dauer, notizen};           //wrap all TextAreas to an Array
        BookingHelper myBookingHelper= new BookingHelper();

        myBookingHelper.showBookings(allTextAreas);
    }

    @FXML
    void bookMenuClicked(ActionEvent event) throws IOException {
        switchScene(event, "../FXML/Book.fxml","Stylesheets/Book.css"  );
    }

    @FXML
    void cancelMenuClicked(ActionEvent event) throws IOException{
        switchScene(event, "../FXML/CancelBooking.fxml","Stylesheets/CancelBooking.css"  );
    }

    @FXML
    void logoutClicked(ActionEvent event) throws IOException{
        switchScene(event, "../FXML/Signin.fxml","Stylesheets/Signin.css"  );
    }



}
