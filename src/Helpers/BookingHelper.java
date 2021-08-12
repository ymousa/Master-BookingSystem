package Helpers;

import Datenobjekte.Booking;
import Datenobjekte.User;

import java.io.IOException;
import java.time.LocalDate;

public class BookingHelper {


    public void showBookings(){
    }

    public void showMyBooking(){

    }

    public void cancelBooking(Booking selectedBooking){
    }

    public void insertBooking(String sRoomNr, LocalDate lBookingDate, String sBookingName,
                              String sBookingTime, String sDuration, String sNots) throws IOException {
        MainController ctrl= MainController.getInstance();
        Booking newBooking = new Booking( String.format("%s", (ctrl.getNrOfLastBooking()+1)), ctrl.getLoggedUser().getsName(), sRoomNr,
                                        lBookingDate, sBookingName, sBookingTime, sDuration, sNots);

        ctrl.addNewBooking(newBooking);
    }
}
