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

    public void insertBooking(Booking selectedBooking) throws IOException {
        MainController ctrl= MainController.getInstance();

        selectedBooking.setiBookingNr(ctrl.getNrOfLastBooking()+1);
        selectedBooking.setsUser(ctrl.getLoggedUser().getsName());

        ctrl.addNewBooking(selectedBooking);                                         //selectedBooking is completely
    }
}
