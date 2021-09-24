package Helpers;

import Datenobjekte.Booking;
import Datenobjekte.User;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.time.LocalDateTime;
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
            if (booking.getsUser().equals(ctrl.getLoggedUser().getsUsername())) {               //search for LoggedUser --> Bookings
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
        selectedBooking.setsUser(ctrl.getLoggedUser().getsUsername());

        ctrl.addNewBooking(selectedBooking);                                         //selectedBooking is completely

    }

    public boolean checkOver(Booking newBooking) throws IOException {
        MainController ctrl= MainController.getInstance();

        LocalDateTime requiredDate= newBooking.getlBookingDateAndTime();
        for(Booking booking: ctrl.getLsBookings()){
            LocalDateTime checkIn= booking.getlBookingDateAndTime();
            LocalDateTime checkOut= booking.getlBookingDateAndTime().plusHours(booking.getiDuration());
            if(requiredDate.compareTo(checkIn) >= 0 && requiredDate.compareTo(checkOut) <= 0){
                return false; //not available
            }
        }
        return true;    //available
    }
}
