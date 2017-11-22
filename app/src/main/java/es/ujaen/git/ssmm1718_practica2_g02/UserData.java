package es.ujaen.git.ssmm1718_practica2_g02;

/**
 * Created by juandy on 22/11/17.
 */

public class UserData {
    protected String user = "";
    protected String password = "";

    //CONSTRUCTOR

    public UserData(String user, String password) {
        this.user = user;
        this.password = password;
    }

    //GETTERS
    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    //SETTERS

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
