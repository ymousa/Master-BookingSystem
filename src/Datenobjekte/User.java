package Datenobjekte;

public class User {
    private String sName;
    private String sUsername;
    private String sPass;

    public User(String name, String username, String pass){
        sName= name;
        sUsername= username;
        sPass= pass;
    }


    public String getsName() {
        return sName;
    }
    public String getsPass() {
        return sPass;
    }
    public String getsUsername() {
        return sUsername;
    }


}
