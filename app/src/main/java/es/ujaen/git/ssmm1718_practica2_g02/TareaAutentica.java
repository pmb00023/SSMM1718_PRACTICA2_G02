package es.ujaen.git.ssmm1718_practica2_g02;

import android.os.AsyncTask;

import java.net.Socket;

/**
 * Created by juandy on 29/11/17.
 */

public class TareaAutentica extends AsyncTask<ConnectionData, Void, String> {
    private ConnectionData data;
    public String doInBackground(ConnectionData... param){ //Los tres puntos es de java y significa que param puede ser un array
        if (param!=null){
            if (param.length>=1){
                data=param[0];

                //Inicio del proceso de autenticacion

                Socket client = null;
                String ans = null;
                String id= null;

                try{
                    String user = data.getUser();
                    String pass = data.getPassword();
                    String ip = data.getIp();
                    String port = data.getPort();


                }
            }
        }
    };
}
