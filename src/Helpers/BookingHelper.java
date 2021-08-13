package Helpers;

import Datenobjekte.Booking;
import Datenobjekte.User;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookingHelper {


    public void showBookings(TextArea[] allTextAreas) throws IOException {
        MainController ctrl= MainController.getInstance();

        for (TextArea allTextArea : allTextAreas) {                             //first clear,  unable to edit, wrap text
            allTextArea.clear();
            allTextArea.setWrapText(true);
            allTextArea.setEditable(false);
        }

        for (Booking booking : ctrl.getLsBookings()) {                           //set all Booking in table *****(besser???)
            allTextAreas[0].appendText(booking.getiBookingNr() + "\n");
            allTextAreas[1].appendText(booking.getsUser() + "\n");
            allTextAreas[2].appendText(booking.getlBookingDateAndTime() + "\n");
            allTextAreas[3].appendText(booking.getsBookingName() + "\n");
            allTextAreas[4].appendText(booking.getiDuration() + "\n");
            allTextAreas[5].appendText(booking.getsNots() + "\n");

        }
    }

    public ArrayList<Booking> getLsMyBookings() throws IOException {
        ArrayList<Booking> lsMyBooking= new ArrayList<Booking>();
        MainController ctrl= MainController.getInstance();

        for (Booking booking : ctrl.getLsBookings()) {
            if (booking.getsUser().equals(ctrl.getLoggedUser().getsName())) {               //search for LoggedUser --> Bookings
                lsMyBooking.add(booking);
            }
        }
        return lsMyBooking;
    }

    public void cancelBooking(Booking selectedBooking) throws IOException {
        MainController ctrl= MainController.getInstance();

        ctrl.removeBooking(selectedBooking);
    }

    public void insertBooking(Booking selectedBooking) throws IOException {
        MainController ctrl= MainController.getInstance();

        selectedBooking.setiBookingNr(ctrl.getNrOfLastBooking()+1);
        selectedBooking.setsUser(ctrl.getLoggedUser().getsName());

        ctrl.addNewBooking(selectedBooking);                                         //selectedBooking is completely
    }
}
