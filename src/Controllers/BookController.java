package Controllers;

import Datenobjekte.Booking;
import Helpers.BookingHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BookController {

    @FXML
    private TextField raumNr;

    @FXML
    private DatePicker datum;

    @FXML
    private TextField veranstaltung;

    @FXML
    private TextField uhrzeit;

    @FXML
    private TextField dauer;

    @FXML
    private TextField notizen;

    public void switchScene(ActionEvent event, String fxml, String css) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Anmeldung");
        window.show();
    }

    void clearAllTextField(){
        raumNr.clear();
        datum.setValue(null);
        veranstaltung.clear();
        uhrzeit.clear();
        dauer.clear();
        notizen.clear();
    }

    @FXML
    void bookClicked(ActionEvent event) throws IOException {
        BookingHelper myBookingHelper= new BookingHelper();
        Booking newBooking= new Booking("000","temp",raumNr.getText(), datum.getValue(),
                        veranstaltung.getText(), uhrzeit.getText(), dauer.getText(), notizen.getText());
        myBookingHelper.insertBooking(newBooking);                                                         //newBooking is not completely
        clearAllTextField();
    }

    @FXML
    void allBookingsClicked(ActionEvent event) {

    }

    @FXML
    void cancelMenuClicked(ActionEvent event) {

    }

    @FXML
    void logoutClicked(ActionEvent event) {

    }

}
