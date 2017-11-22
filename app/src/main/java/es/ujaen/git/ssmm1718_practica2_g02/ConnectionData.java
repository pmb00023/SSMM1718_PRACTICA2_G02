package es.ujaen.git.ssmm1718_practica2_g02;

/**
 * Created by juandy on 22/11/17.
 */

public class ConnectionData extends UserData{
    protected String ip = "127.0.0.1";
    protected short port = 6000;

    //CONSTRUCTOR
    public ConnectionData(String user, String pass, String ip, short port) {
        super(user, pass);
        this.ip = ip;
        this.port = port;
    }

    //GETTERS
    public String getIp() {
        return ip;
    }

    public short getPort() {
        return port;
    }

    //SETTERS
    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(short port) {
        this.port = port;
    }
}
