package es.ujaen.git.ssmm1718_practica2_g02;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by juandy on 13/12/17.
 */

public class CheckSesion{

    public static final String PREFS_SESION = "sesion_details";
    private Context mContext = null;
    Date actual;
    Date sesion;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-KK-mm-ss");

    public CheckSesion(Context context){mContext=context;}

    public boolean ComprobarSesion(){
        actual = new Date();

        //Voy a sacar la fecha y hora de las preferencias compartidas
        SharedPreferences sesion_settings = mContext.getSharedPreferences(PREFS_SESION, 0);
        String expires = sesion_settings.getString("expires", "0000-00-00-00-00-00 ");

        //Transformo el string en fecha
        sesion = dateFormat.parse(expires, new ParsePosition(0));

        //Comparo si es anterior la fecha actual que la de expiración
        if (actual.before(sesion)){
            return true;
        }
        return false;
    }
}
