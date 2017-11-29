package es.ujaen.git.ssmm1718_practica2_g02;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.microedition.khronos.egl.EGLDisplay;

import static es.ujaen.git.ssmm1718_practica2_g02.MainActivity.PREFS_NAME;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_login, container, false);

        Button connect = (Button) fragment.findViewById(R.id.connect_button);

        final EditText user = (EditText) fragment.findViewById(R.id.login_user);
        final EditText pass = (EditText) fragment.findViewById(R.id.login_password);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_user = user.getText().toString();
                String s_pass = pass.getText().toString();

                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                String ip = settings.getString("ip", "127.0.0.1");
                int port = settings.getInt("port", 6000);


                ConnectionData data = new ConnectionData(s_user, s_pass, ip, port);
                Toast.makeText(getContext(),"Hola "+data.getUser()+" "+data.getPassword()+" "+data.getIp()+":"+data.getPort(),Toast.LENGTH_LONG).show(); //Muestra una notificacion en la parte baja de la pantalla
                TareaAutentica autenticacion = new TareaAutentica();
                autenticacion.execute(data);
            }
        });



        return fragment;
    }

   // public class TareaAutentica extends AsyncTsk<ConnectionUserData,Void,String> {

   // }


}
