package Datenobjekte;

public class User {
    private String sFirstName;
    private String sLastName;
    private String sUsername;
    private String sPass;

    public User(String firstName, String lastName, String username, String pass){
        sFirstName= firstName;
        sLastName= lastName;
        sUsername= username;
        sPass= pass;
    }


    public String getsFirstName() {
        return sFirstName;
    }
    public String getsLastName() {
        return sLastName;
    }
    public String getsPass() {
        return sPass;
    }
    public String getsUsername() {
        return sUsername;
    }


}
