package es.ujaen.git.ssmm1718_practica2_g02;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * Created by juandy on 29/11/17.
 */

public class TareaAutentica extends AsyncTask<ConnectionData, Void, String> {

    private ConnectionData data;
    Boolean error = false;
    private Context mContext=null;
    public static final String PREFS_SESION = "sesion_details";
    String SesionIDend = "";
    String expiresEnd = "";


    public TareaAutentica(Context context){
        mContext=context;
    }
    
    public String doInBackground(ConnectionData... param) { //Los tres puntos es de java y significa que param puede ser un array
        if (param != null) {
            if (param.length >= 1) {
                data = param[0];

                //Inicio del proceso de autenticacion

                Socket client = null;
                String ans = null;
                String id = null;

                try {
                    String user = data.getUser();
                    String pass = data.getPassword();
                    String ip = data.getIp();
                    int port = data.getPort();


                    //Iniciamos el socket
                    client = new Socket(InetAddress.getByName(ip), port);

                    //Inicio de lectura y escritura
                    BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    DataOutputStream output = new DataOutputStream(client.getOutputStream());
                    //Recibo la respuesta del servidor
                    //ans=input.readLine();
                    //Escribo el mensaje que voy a enviar
                    //Mensaje que hay que enviar GET /~jccuevas/ssmm/autentica.php?user=user&pass=12345\r\n\r\nHTTP/1.1\r\n

                    String sec = "GET /~jccuevas/ssmm/autentica.php?user=" + user + "&pass=" + pass + "\r\n\r\nHTTP/1.1\r\n";
                    output.write(sec.getBytes());
                    //Limpio la salida
                    output.flush();

                    //Recibo respuesta del servidor
                    while ((ans=input.readLine()) != null)
                    //Comprobamos si hemos recibido algo
                    if (ans != null) {
                        Log.d("Respuesta del servidor", ans);
                        //Comprobamos que hemos recibido bien los datos
                        if (ans.startsWith("SESION-ID")) {
                            String params[] = ans.split("&");
                            if (params.length == 2) {
                                String sesionID[] = params[0].split("=");
                                String expires[] = params[1].split("=");
                                if (sesionID != null && expires != null) {
                                    SesionIDend = sesionID[1];
                                    Log.d("SesionID=", SesionIDend);
                                    expiresEnd = expires[1];
                                    Log.d("Expiracion=", expiresEnd);
                                }
                            }
                        }
                    } else {
                        error = true;
                    }


                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (error == true) {
            return "CANCEL";
        }else{
            //Toast.makeText(mContext,"Hola "+data.getUser()+" "+data.getPassword()+" "+data.getIp()+":"+data.getPort(),Toast.LENGTH_LONG).show(); //Muestra una notificacion en la parte baja de la pantalla
            return "OK";
        }
    }
    public void onPostExecute(String respuesta){

        if(respuesta.compareToIgnoreCase("OK")==0){
            Intent service = new Intent(mContext, ServiceActivity.class);
            mContext.startActivity(service);
            SharedPreferences sesion = mContext.getSharedPreferences(PREFS_SESION, 0);
            SharedPreferences.Editor editor = sesion.edit();

            editor.putString("expires", expiresEnd);
            editor.putString("sesionID", SesionIDend);
            editor.commit();
        }
    }
}
