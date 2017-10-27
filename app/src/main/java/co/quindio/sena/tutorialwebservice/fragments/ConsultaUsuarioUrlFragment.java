package co.quindio.sena.tutorialwebservice.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.quindio.sena.tutorialwebservice.R;
import co.quindio.sena.tutorialwebservice.entidades.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConsultaUsuarioUrlFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConsultaUsuarioUrlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultaUsuarioUrlFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText txtDocumento;
    TextView etiNombre;
    TextView etiProfesion;
    ProgressDialog pDialog;
    Button btnConsultar;
    ImageView campoImagen;


    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public ConsultaUsuarioUrlFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsultaUsuarioUrlFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultaUsuarioUrlFragment newInstance(String param1, String param2) {
        ConsultaUsuarioUrlFragment fragment = new ConsultaUsuarioUrlFragment();
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
        View vista= inflater.inflate(R.layout.fragment_consulta_usuario_url, container, false);

        txtDocumento= (EditText) vista.findViewById(R.id.campoDocumento);
        etiNombre= (TextView) vista.findViewById(R.id.txtNombre);
        etiProfesion= (TextView) vista.findViewById(R.id.txtProfesion);
        btnConsultar= (Button) vista.findViewById(R.id.btnConsultarUsuario);
        campoImagen= (ImageView) vista.findViewById(R.id.imagenId);

        request= Volley.newRequestQueue(getContext());

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();
            }
        });

        return vista;
    }

    private void cargarWebService() {
        pDialog=new ProgressDialog(getContext());
        pDialog.setMessage("Cargando...");
        pDialog.show();

        String url="http://192.168.1.55/ejemploBDRemota/wsJSONConsultarUsuarioUrl.php?documento="+txtDocumento.getText().toString();

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.hide();

                Usuario miUsuario=new Usuario();

                JSONArray json=response.optJSONArray("usuario");
                JSONObject jsonObject=null;

                try {
                    jsonObject=json.getJSONObject(0);
                    miUsuario.setNombre(jsonObject.optString("nombre"));
                    miUsuario.setProfesion(jsonObject.optString("profesion"));
                    miUsuario.setRutaImagen(jsonObject.optString("ruta_imagen"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                etiNombre.setText("Nombre :"+miUsuario.getNombre());
                etiProfesion.setText("Profesion :"+miUsuario.getProfesion());

                String urlImagen="http://192.168.1.55/ejemploBDRemota/"+miUsuario.getRutaImagen();
                Toast.makeText(getContext(), "url "+urlImagen, Toast.LENGTH_LONG).show();
                cargarWebServiceImagen(urlImagen);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
                System.out.println();
                pDialog.hide();
                Log.d("ERROR: ", error.toString());
            }
        });

        request.add(jsonObjectRequest);

    }

    private void cargarWebServiceImagen(String urlImagen) {
        urlImagen=urlImagen.replace(" ","%20");

        ImageRequest imageRequest=new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                campoImagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Error al cargar la imagen",Toast.LENGTH_SHORT).show();
            }
        });
        request.add(imageRequest);
    }

    // TODO: Rename method, update argument and hook method into UI event
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
