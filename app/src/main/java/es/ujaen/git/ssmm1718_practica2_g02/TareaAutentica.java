package es.ujaen.git.ssmm1718_practica2_g02;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static android.app.PendingIntent.getActivity;
import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by juandy on 29/11/17.
 */

public class TareaAutentica extends AsyncTask<ConnectionData, Void, String> {

    private ConnectionData data;
    Boolean error = false;
    private Context mContext=null;

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
                    String sec = "GET /~jccuevas/ssmm/autentica.php?user=" + user + "&pass=" + pass + "\r\n\r\nHTTP/1.1\r\n";
                    output.write(sec.getBytes());
                    //Limpio la salida
                    output.flush();

                    //Recibo respuesta del servidor
                    ans = input.readLine();
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
                                    String SesionIDend = sesionID[1];
                                    Log.d("SesionID=", SesionIDend);
                                    String expiresEnd = expires[1];
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
            return "OK";
        }
    }
    public void onPostExecute(String respuesta){

        if(respuesta.compareToIgnoreCase("OK")==0){
            Intent service = new Intent(mContext, ServiceActivity.class);
            



        }
    }
}
