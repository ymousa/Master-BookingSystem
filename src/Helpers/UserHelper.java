package Helpers;

import Datenobjekte.User;

import java.io.IOException;

public class UserHelper{

    public boolean verify(String username, String password) throws IOException {
        MainController ctrl= MainController.getInstance();
        if (ctrl.getHmUsers().containsKey(username)) {
            User tempUser = ctrl.getHmUsers().get(username);
            if (tempUser.getsUsername().equals(username) &&
                    tempUser.getsPass().equals(password)) {
                System.out.println("Success!");
                ctrl.setLoggedUser(tempUser);
                return true;
            }
        }
        System.out.println("Please try again..");
        return false;
    }

    public void register(User newUser) throws IOException {
        MainController ctrl= MainController.getInstance();
        ctrl.addNewUser(newUser);
    }
}
