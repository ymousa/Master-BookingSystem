package Datenobjekte;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Booking {
    private int iBookingNr;
    private String sUser;
    private String sRoomNr;
    private LocalDateTime lBookingDateAndTime;
    private String sBookingName;
    private int iDuration;
    private String sNots;

    public Booking(String sBookingNr, String sUser, String sRoomNr, LocalDate lBookingDate,                        //for adding a Booking from book Tool to list lsBookings
                   String sBookingName, String sBookingTime, String sDuration, String sNots){
        this.iBookingNr= Integer.parseInt(sBookingNr);
        this.sUser = sUser;
        this.sRoomNr= sRoomNr;
        this.lBookingDateAndTime= StringToLocalDateTime(lBookingDate,sBookingTime);
        this.sBookingName= sBookingName;
        this.iDuration= Integer.parseInt(sDuration);
        this.sNots= sNots;
    }

    public Booking(String sBookingNr, String sUser, String sRoomNr, String sDateAndTime,                            //for reading the .txt and saving it in list lsBookings
                   String sBookingName, String sDuration, String sNots){
        this.iBookingNr= Integer.parseInt(sBookingNr);
        this.sUser = sUser;
        this.sRoomNr= sRoomNr;
        this.lBookingDateAndTime= StringToLocalDateTime(sDateAndTime);
        this.sBookingName= sBookingName;
        this.iDuration= Integer.parseInt(sDuration);
        this.sNots= sNots;
    }


    public LocalDateTime StringToLocalDateTime(LocalDate lDate, String sTime) {                                 //convert tow Strings into Date and Time localDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String sDateTime = lDate.toString() + "T" + sTime;
        LocalDateTime parsedDateTime = LocalDateTime.parse(sDateTime, formatter);
        return parsedDateTime;
    }

    public LocalDateTime StringToLocalDateTime(String sDateAndTime) {                                           //convert one String into Date and Time localDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime parsedDateTime = LocalDateTime.parse(sDateAndTime, formatter);
        return parsedDateTime;
    }


    public int getiBookingNr() {
        return iBookingNr;
    }

    public int getiDuration() {
        return iDuration;
    }

    public LocalDateTime getlBookingDateAndTime() {
        return lBookingDateAndTime;
    }

    public String getsBookingName() {
        return sBookingName;
    }

    public String getsNots() {
        return sNots;
    }

    public String getsRoomNr() {
        return sRoomNr;
    }

    public String getsUser() {
        return sUser;
    }

    public void setiBookingNr(int iBookingNr) {
        this.iBookingNr = iBookingNr;
    }

    public void setsUser(String sUser) {
        this.sUser = sUser;
    }
}
