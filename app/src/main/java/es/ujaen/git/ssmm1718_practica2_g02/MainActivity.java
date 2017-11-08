package es.ujaen.git.ssmm1718_practica2_g02;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements MoreOptionFragment.OnFragmentInteractionListener {

    private ToggleButton Toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void activarOpciones(View view){
        Toggle = (ToggleButton)findViewById(R.id.AdvancedOption);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MoreOptionFragment fragment = new MoreOptionFragment();
        if(Toggle.isChecked()){
            fragmentTransaction.add(R.id.more_option_layout,fragment);
            fragmentTransaction.commit();
        }else{
            Fragment moreoption = (Fragment)fragmentManager.findFragmentById(R.id.more_option_layout);
            fragmentTransaction.remove(moreoption);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
