package es.ujaen.git.ssmm1718_practica2_g02;

/**
 * Created by juandy on 22/11/17.
 */

public class ConnectionData extends UserData{
    protected String ip = "127.0.0.1";
    protected int port = 6000;

    //CONSTRUCTOR
    public ConnectionData(String user, String pass) {
        super(user, pass);
    }

    public ConnectionData(String user, String password, String ip, int port) {
        super(user, password);
        this.ip = ip;
        this.port = port;
    }

    //GETTERS
    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    //SETTERS
    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
