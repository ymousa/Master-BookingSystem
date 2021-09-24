package Helpers;

import Datenobjekte.Booking;
import Datenobjekte.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainController {
    private static MainController ctrl;
    private HashMap<String, User> hmUsers;
    private ArrayList<Booking> lsBookings;
    private User loggedUser;
    //private FileHelper myFileHelper;
    private DatabaseHelper myDatabaseHelper;

    public MainController() throws IOException {
        ctrl=this;
        myDatabaseHelper= new DatabaseHelper();
        hmUsers= myDatabaseHelper.readUsers();
        lsBookings= myDatabaseHelper.readBookings();
        //myFileHelper= new FileHelper();
        //hmUsers= myFileHelper.readUsers();
        //lsBookings= myFileHelper.readBookings();

    }


    public static MainController getInstance() throws IOException {
        if(ctrl == null){
            return ctrl= new MainController();
        }else{
            return ctrl;
        }
    }

    /*public FileHelper getMyFileHelper(){
        return myFileHelper;
    }*/

    public DatabaseHelper getMyDatabaseHelper() {
        return myDatabaseHelper;
    }

    public ArrayList<Booking> getLsBookings() {
        return lsBookings;
    }

    private void numberLsBoooking(){
        for(int i=0; i<lsBookings.size(); i++){
            lsBookings.get(i).setiBookingNr(i+1);
        }
    }

    public HashMap<String, User> getHmUsers() {
        return hmUsers;
    }

    public User getLoggedUser(){
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public void switchScene(ActionEvent event, String fxml, String css) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Anmeldung");
        window.show();
    }

    public void addNewUser(User newUser){
        hmUsers.put(newUser.getsUsername(), newUser);
        ctrl.getMyDatabaseHelper().writeUser(newUser);
    }

    public int getNrOfLastBooking(){

        int n= lsBookings.size()-1;
        return lsBookings.get(n).getiBookingNr();
    }

    public void addNewBooking(Booking newBooking){
        ctrl.getMyDatabaseHelper().writeBooking(newBooking);
        lsBookings.add(newBooking);
    }

    public void removeBooking(Booking selectedBooking){
        ctrl.getMyDatabaseHelper().deleteBookingFromSQL(selectedBooking);
        lsBookings.remove(selectedBooking);
        //numberLsBoooking();
    }


}
