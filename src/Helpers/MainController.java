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

    public MainController() throws IOException {
        ctrl=this;
        FileHelper myFileHelper= new FileHelper();
        hmUsers= myFileHelper.readUsers();
        lsBookings= myFileHelper.readBookings();

    }

    public static MainController getInstance() throws IOException {
        if(ctrl == null){
            return ctrl= new MainController();
        }else{
            return ctrl;
        }
    }

    public ArrayList<Booking> getLsBookings() {
        return lsBookings;
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

    public void addNewUser(String name, String username, String pass){
        hmUsers.put(username, new User(name, username, pass));
    }

    public int getNrOfLastBooking(){

        int n= lsBookings.size()-1;
        return lsBookings.get(n).getiBookingNr();
    }

    public void addNewBooking(Booking newBooking){
        lsBookings.add(newBooking);
    }

    public void removeBooking(){

    }


}
