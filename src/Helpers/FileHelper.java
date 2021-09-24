package Helpers;

import Datenobjekte.Booking;
import Datenobjekte.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FileHelper {

    private BufferedReader br;
    private BufferedWriter bw;

    public HashMap<String, User> readUsers() throws IOException {
        br = new BufferedReader(new FileReader(Consts.txtUsers));
        HashMap<String, User> allUsers= new HashMap<String, User>();
        String sLine = null;
        while ((sLine = br.readLine()) != null) {                                               //reading data from txtUsers and save it in HashMap hmUsers
            String[] sUserData = sLine.split(";");
            allUsers.put(sUserData[2], new User(sUserData[0], sUserData[1], sUserData[2], sUserData[3]));
        }
        br.close();
        br.close();

        return allUsers;
    }

    public ArrayList<Booking> readBookings()throws IOException{
        br = new BufferedReader(new FileReader(Consts.txtBookings));
        ArrayList<Booking> allBookings = new ArrayList<Booking>();
        String sLine = null;
        while ((sLine = br.readLine()) != null) {                                                //reading data from txtBookings and save it in list lsBookings
            String[] sBookingData = sLine.split(";");
            allBookings.add(new Booking(sBookingData[0], sBookingData[1],
                    sBookingData[2], sBookingData[3],
                    sBookingData[4], sBookingData[5],
                    sBookingData[6]));
        }
        br.close();
        br.close();

        return allBookings;
    }

    public void writeUsers(HashMap<String, User> allUsers)throws IOException{
        FileWriter fw = new FileWriter(Consts.txtUsers, false);
        bw = new BufferedWriter(fw);
        String sep = ";";
        for (String key : allUsers.keySet()) {
            String outPutText = allUsers.get(key).getsFirstName()+ sep + allUsers.get(key).getsLastName() + sep + allUsers.get(key).getsUsername() +
                    sep + allUsers.get(key).getsPass();
            bw.write(outPutText);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public void writeBookings(ArrayList<Booking> allBookings)throws IOException{
        FileWriter fw = new FileWriter(Consts.txtBookings, false);
        bw = new BufferedWriter(fw);
        String sep = ";";
        int i = 0;
        for (Booking booking : allBookings) {
            i++;
            String outPutText = i + sep + booking.getsUser() +
                    sep + booking.getsRoomNr() + sep + booking.getlBookingDateAndTime() +
                    sep + booking.getsBookingName() +
                    sep + booking.getiDuration() + sep + booking.getsNots();
            bw.write(outPutText);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }




}
