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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BookController {

    @FXML
    private Label bookMessage;

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

    private void switchScene(ActionEvent event, String fxml, String css) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Anmeldung");
        window.show();
    }

    private void clearAllTextField(){
        raumNr.clear();
        datum.setValue(null);
        veranstaltung.clear();
        uhrzeit.clear();
        dauer.clear();
        notizen.clear();
    }

    @FXML
    private void bookClicked(ActionEvent event) throws IOException {
        BookingHelper myBookingHelper= new BookingHelper();
        Booking newBooking= new Booking("000","temp",raumNr.getText(), datum.getValue(),
                        veranstaltung.getText(), uhrzeit.getText(), dauer.getText(), notizen.getText());
        if(myBookingHelper.checkOver(newBooking)){
            myBookingHelper.insertBooking(newBooking);                                                         //newBooking is not completely
            clearAllTextField();
            bookMessage.setText("Die Buchung wurde hinzugefügt");
        }else{
            bookMessage.setText("Dieses Datum ist bereits verlegt worden. Bitte ändern");
        }
    }

    @FXML
    private void allBookingsClicked(ActionEvent event) throws IOException {
        switchScene(event, "../FXML/AllBookings.fxml","Stylesheets/AllBookings.css"  );
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
