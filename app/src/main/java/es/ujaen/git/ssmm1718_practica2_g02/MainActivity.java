package es.ujaen.git.ssmm1718_practica2_g02;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements MoreOptionFragment.OnFragmentInteractionListener {

    public static final String PREFS_NAME = "connection_details";
    private  TareaAutentica tarea=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //El que hace que se muestre el menu
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Menu de tres puntitos en la barra superior
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){ //Seleccion de opcion elegida
            case R.id.action_settings:
                MoreOptionFragment mof = MoreOptionFragment.newInstance("","");
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();
                mof.show(ft,"propiedades");
                break;
        }
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onResume() {
        super.onResume();

        CheckSesion sesion = new CheckSesion(this);
        Boolean sesionActiva = sesion.ComprobarSesion();
        if (sesionActiva){
           tarea = new TareaAutentica(this);
            //TODO: leer de preferencias compartidas los datos de usuario
            //TODO: meterlo en la calse ConnectionData
            //TODO: Arrancar la tarea
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(tarea!=null){
            tarea.cancel(false);
        }

    }
}
