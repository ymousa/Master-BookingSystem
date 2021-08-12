package Helpers;

import Datenobjekte.Booking;
import Datenobjekte.User;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.time.LocalDate;

public class BookingHelper {


    public void showMyBooking(){
    }

    public void showBookings(TextArea[] allTextAreas) throws IOException {
        MainController ctrl= MainController.getInstance();

        for(int i=0; i<allTextAreas.length; i++){                                           //first clear,  unable to edit, wrap text
            allTextAreas[i].clear();
            allTextAreas[i].setWrapText(true);
            allTextAreas[i].setEditable(false);
        }

        for (Booking booking : ctrl.getLsBookings()) {                                      //set all Booking in table *****(besser???)
            allTextAreas[0].appendText(booking.getiBookingNr() + "\n");
            allTextAreas[1].appendText(booking.getsUser() + "\n");
            allTextAreas[2].appendText(booking.getlBookingDateAndTime() + "\n");
            allTextAreas[3].appendText(booking.getsBookingName() + "\n");
            allTextAreas[4].appendText(booking.getiDuration() + "\n");
            allTextAreas[5].appendText(booking.getsNots() + "\n");

        }
    }

    public void cancelBooking(Booking selectedBooking){
    }

    public void insertBooking(Booking selectedBooking) throws IOException {
        MainController ctrl= MainController.getInstance();

        selectedBooking.setiBookingNr(ctrl.getNrOfLastBooking()+1);
        selectedBooking.setsUser(ctrl.getLoggedUser().getsName());

        ctrl.addNewBooking(selectedBooking);                                         //selectedBooking is completely
    }
}
