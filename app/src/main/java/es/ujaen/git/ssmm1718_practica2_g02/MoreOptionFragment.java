package es.ujaen.git.ssmm1718_practica2_g02;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static es.ujaen.git.ssmm1718_practica2_g02.MainActivity.PREFS_NAME;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MoreOptionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoreOptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreOptionFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MoreOptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoreOptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoreOptionFragment newInstance(String param1, String param2) {
        MoreOptionFragment fragment = new MoreOptionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentmoreoption = inflater.inflate(R.layout.fragment_more_option, container, false);

        Button ok = (Button) fragmentmoreoption.findViewById(R.id.ok_button_fragment_moreoption);

        final EditText ip = (EditText) fragmentmoreoption.findViewById(R.id.login_ip);
        final EditText port = (EditText) fragmentmoreoption.findViewById(R.id.login_port);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences ip_port = getActivity().getSharedPreferences(PREFS_NAME, 0); //Se crea el recurso que accede a las Preferencias Compartidas
                SharedPreferences.Editor editor = ip_port.edit(); //Se crea el editor de las Preferencias compartidas

                String s_ip = ip.getText().toString(); //Extraemos la direccion IP del campo de texto
                String s_port = port.getText().toString(); //Extraemos el puerto del campo de texto

                short port = 80;

                try{ //Para asegurarnos de que no se pongan letras y de error
                    port = Short.parseShort(s_port);
                }catch (java.lang.NumberFormatException ex){
                    port=80;
                }

                if (s_ip.equals("")==true){ //Comprobamos si se deja el campo en blanco para poner el ajuste por defecto
                    s_ip = "www4.ujaen.es"; //Dirección IP por defecto
                }
                editor.putString("ip",s_ip);
                editor.putInt("port",port);
                editor.commit();
                Toast.makeText(getContext(),"Se han guardado los ajustes correctamente",Toast.LENGTH_LONG).show(); //Muestra una notificacion en la parte baja de la pantalla
                dismiss();
            }
        });
        return fragmentmoreoption;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
