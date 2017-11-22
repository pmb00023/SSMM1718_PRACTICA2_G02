package es.ujaen.git.ssmm1718_practica2_g02;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements MoreOptionFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager = getSupportFragmentManager();

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
}
