package com.developers.quickjob.quick_job.fragment;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.developers.quickjob.quick_job.R;
import com.developers.quickjob.quick_job.restapi.VolleySingleton;
import com.developers.quickjob.quick_job.sqlite.Operacionesbd;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jhonn_aj on 24/11/2016.
 */

public class fragment_perfil_usrs extends Fragment {

    @BindView(R.id.text_nomb_ape)
    EditText nomb_apell;
    @BindView(R.id.text_estudio)
    EditText estudio;
    @BindView(R.id.text_carrera)
    EditText carrera;
    @BindView(R.id.text_fecha_nac)
    EditText fecha_nac;
    @BindView(R.id.text_genero)
    EditText genero;
    @BindView(R.id.text_direccion)
    EditText direccion;
    @BindView(R.id.text_telef)
    EditText telefono;
    @BindView(R.id.text_centroestudio)
    EditText centro_estd;
    @BindView(R.id.text_profesion)
    EditText profesion;
    @BindView(R.id.text_buscar)
    EditText buscar;
    @BindView(R.id.text_sit_acacd)
    EditText sit_acad;
    @BindView(R.id.text_cond_acad)
    EditText cond_acd;
    @BindView(R.id.text_exp)
    EditText experiencia;

    public static final String ID = "id";
    int idusers;
    Operacionesbd db;
    @BindView(R.id.notificar)
    ImageButton notificar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil_user, container, false);

        ButterKnife.bind(this, view);

        //db=Operacionesbd.getInstancia(getActivity());

        idusers = getArguments().getInt(ID);

        String url = "http://unmsmquickjob.pe.hu/quickjob/perfil_postulante.php?id=" + idusers;
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                procesarRespuesta(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(getActivity()).addRequestQueue(jsonArrayRequest);

        /*Postulante postulante=db.getPerfilPostulante(idusers);

        if (postulante!=null){
            nomb_apell.setText(postulante.getApellidos()+ ", " +postulante.getNombres());
            estudio.setText(postulante.getCentro_estudios());
            carrera.setText(postulante.getCarrera());
            fecha_nac.setText(postulante.getFecha_nacimiento());
            genero.setText(postulante.getGenero());
            direccion.setText(postulante.getDireccion());
            telefono.setText(""+postulante.getTelefono());
            centro_estd.setText(postulante.getCentro_estudios());
            profesion.setText(postulante.getCarrera());
            buscar.setText(postulante.getTipo_buscqda());
            sit_acad.setText(postulante.getCondicion_acad());
            cond_acd.setText(postulante.getCategorizacion_estud());
            experiencia.setText(postulante.getEmpresa_exp()+ " / " + postulante.getEmpresa_cargo());
        }*/

        Log.d(fragment_perfil_usrs.class.getName(), "holo " + idusers);

        return view;
    }

    private void procesarRespuesta(JSONObject response) {

        try {
            String mensaje = response.getString("estado");
            switch (mensaje) {
                case "1":
                    // Obtener objeto "meta"
                    JSONArray jsonArray = response.getJSONArray("postulante");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    nomb_apell.setText(jsonObject.getString("noms_postulante") + jsonObject.getString("apells_postulante"));
                    estudio.setText(jsonObject.getString("centro_estud_postulante"));
                    carrera.setText(jsonObject.getString("carrera_postulante"));
                    fecha_nac.setText(jsonObject.getString("fecha_nac_postulante"));
                    genero.setText(jsonObject.getString("genero_postulante"));
                    direccion.setText(jsonObject.getString("direccion_postulante"));
                    telefono.setText(jsonObject.getString("telef_postulante"));
                    centro_estd.setText(jsonObject.getString("centro_estud_postulante"));
                    profesion.setText(jsonObject.getString("carrera_postulante"));
                    buscar.setText(jsonObject.getString("tipo_busq_postulante"));
                    sit_acad.setText(jsonObject.getString("cond_academ_postulante"));
                    cond_acd.setText(jsonObject.getString("carrera_postulante"));
                    experiencia.setText(jsonObject.getString("empresa_postulante") + " - " + jsonObject.getString("cargo_postulante"));
                    break;

                case "2":
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(
                            getActivity(),
                            mensaje2,
                            Toast.LENGTH_LONG).show();
                    break;

                case "3":
                    String mensaje3 = response.getString("mensaje");
                    Toast.makeText(
                            getActivity(),
                            mensaje3,
                            Toast.LENGTH_LONG).show();
                    break;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.notificar)
    public void onClick() {
        /*NotificationCompat.Builder builder= new NotificationCompat.Builder(getActivity());
        builder.setSmallIcon(R.drawable.icon_group);
        builder.setAutoCancel(true);
        builder.setVibrate(new long[] {100, 250, 100, 500});
        builder.setContentTitle("Notificacion basica");
        builder.setContentText("jakfjaklfjjgd");
        builder.setSubText("aljfkgj");

        NotificationManager mNotifyMgr =(NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(1,builder.build());*/

        /*final String SENDER_ID="190560486126";
        FirebaseMessaging fm = FirebaseMessaging.getInstance();
        AtomicInteger msgId = new AtomicInteger();
        fm.send(new RemoteMessage.Builder(SENDER_ID + "@gcm.googleapis.com")
                .setMessageId(Integer.toString(msgId.incrementAndGet()))
                .addData("my_message", "Hello World")
                .addData("my_action","SAY_HELLO")
                .build());*/

       /* SharedPreferences sharedPreferences=getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        final String token=sharedPreferences.getString("token","");
        String app_server_url="http://unmsm.pe.hu/quick/insert_fcm.php";

        Log.d(fragment_perfil_usrs.class.getName(),token);

        StringRequest stringRequest= new StringRequest(Request.Method.POST, app_server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(fragment_perfil_usrs.class.getName(), "Error Volley: " + error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params= new HashMap<String,String>();
                params.put("fcm_token",token);

                return params;
            }
        };

        VolleySingleton.getInstance(getActivity()).addRequestQueue(stringRequest);*/

        /*HashMap<String,String> map = new HashMap<>();

        map.put("fcm_token",token);

        JSONObject jsonObject= new JSONObject(map);

        VolleySingleton.getInstance(getActivity()).addRequestQueue(
                new JsonObjectRequest(Request.Method.POST, app_server_url, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //procesarRespuesta(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(fragment_perfil_usrs.class.getName(), "Error Volley: " + error.getMessage());
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String ,String> headers= new HashMap<String, String>();
                        headers.put("Content-Type","application/json; charset=utf-8");
                        headers.put("Accept","application/json");
                        return headers;
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8" + getParamsEncoding();
                    }
                });*/

        // notificar para un usuario especifico

       /* String app_server_url="http://unmsmquickjob.pe.hu/quickjob/send_notification.php";

        StringRequest stringRequest= new StringRequest(Request.Method.POST, app_server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(fragment_perfil_usrs.class.getName(), "Error Volley: " + error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params= new HashMap<String,String>();
                params.put("message","Android Developer :)");
                params.put("title","Oferta Laboral");

                return params;
            }
        };

        VolleySingleton.getInstance(getActivity()).addRequestQueue(stringRequest);*/


    }
}
