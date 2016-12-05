package com.developers.quickjob.quick_job;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.developers.quickjob.quick_job.restapi.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jhonn_aj on 04/12/2016.
 */

public class PostuladoDetalleActivity extends AppCompatActivity {

    public static final String ID = "id_oferta";
    public static final String ID_OFERT = "id_postulante";
    int idpostulante;
    int idoferts;

    @BindView(R.id.text_nomb_ape)
    EditText nombs;
    @BindView(R.id.text_estudio)
    EditText estudio;
    @BindView(R.id.text_carrera)
    EditText carrera;
    @BindView(R.id.text_fecha_nac)
    EditText fechanac;
    @BindView(R.id.text_genero)
    EditText genero;
    @BindView(R.id.text_direccion)
    EditText direccion;
    @BindView(R.id.text_telef)
    EditText telefono;
    @BindView(R.id.text_centroestudio)
    EditText centroestudio;
    @BindView(R.id.text_profesion)
    EditText profesion;
    @BindView(R.id.text_buscar)
    EditText buscar;
    @BindView(R.id.text_sit_acacd)
    EditText situacionacaf;
    @BindView(R.id.text_cond_acad)
    EditText condicion;
    @BindView(R.id.text_exp)
    EditText experiencia;

    String tokenUsers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_postulante);

        this.setTitle("Información");

        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ButterKnife.bind(this);

        idpostulante = getIntent().getIntExtra(ID, 0);
        idoferts = getIntent().getIntExtra(ID_OFERT, 0);
        Log.d(PostuladoDetalleActivity.class.getName(), " postulante - > " + idpostulante + " / oferts -> " + idoferts);

        String url="http://unmsmquickjob.pe.hu/quickjob/notificacionesxusuario.php?idoferta="+idoferts+"&idpostulante="+idpostulante;

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

        VolleySingleton.getInstance(getApplicationContext()).addRequestQueue(jsonArrayRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enviar_respuesta,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.action_notificacion){
            actualizarEstadoPostulante(idpostulante,idoferts);
            enviar_notificacion();
            Toast.makeText(getApplicationContext()," postulante - > " + idpostulante + " / oferts -> " + idoferts + " token " + tokenUsers,Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void actualizarEstadoPostulante(int idpostulante,int idoferts){
        String url="http://unmsmquickjob.pe.hu/quickjob/actualizaraprobacionpostulacion.php";

        final String postulante=""+idpostulante;
        final String oferta=""+idoferts;


        HashMap<String,String> map = new HashMap<>();

        map.put("idpostulante",postulante);
        map.put("idoferta",oferta);

        JSONObject jsonObject= new JSONObject(map);

        VolleySingleton.getInstance(getApplicationContext()).addRequestQueue(
                new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                procesarActualizacion(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(RegistersEmpActivity.class.getName(), "Error Volley: " + error.getMessage());
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
                });


    }

    private void procesarActualizacion(JSONObject response){
        try {
            String estado = response.getString("estado");
            String mensaje = response.getString("mensaje");
            switch (estado) {
                case "1":
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                    break;
                case "2":
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void enviar_notificacion(){
        String app_server_url="http://unmsmquickjob.pe.hu/quickjob/send_una_notificacion.php";

        StringRequest stringRequest= new StringRequest(Request.Method.POST, app_server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(PostuladoDetalleActivity.class.getName(), "Error Volley: " + error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params= new HashMap<String,String>();
                params.put("message","Android Developer :)");
                params.put("token",tokenUsers);
                params.put("title","Postulación Procesada");

                return params;
            }
        };

        VolleySingleton.getInstance(getApplicationContext()).addRequestQueue(stringRequest);
    }

    private void procesarRespuesta(JSONObject response) {

        try {
            String mensaje = response.getString("estado");
            switch (mensaje) {
                case "1":
                    // Obtener objeto "meta"
                    JSONArray jsonArray = response.getJSONArray("oferta");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    nombs.setText(jsonObject.getString("noms_postulante") + jsonObject.getString("apells_postulante"));
                    estudio.setText(jsonObject.getString("centro_estud_postulante"));
                    carrera.setText(jsonObject.getString("carrera_postulante"));
                    fechanac.setText(jsonObject.getString("fecha_nac_postulante"));
                    genero.setText(jsonObject.getString("genero_postulante"));
                    direccion.setText(jsonObject.getString("direccion_postulante"));
                    telefono.setText(jsonObject.getString("telef_postulante"));
                    centroestudio.setText(jsonObject.getString("centro_estud_postulante"));
                    profesion.setText(jsonObject.getString("carrera_postulante"));
                    buscar.setText(jsonObject.getString("tipo_busq_postulante"));
                    situacionacaf.setText(jsonObject.getString("cond_academ_postulante"));
                    condicion.setText(jsonObject.getString("carrera_postulante"));
                    experiencia.setText(jsonObject.getString("empresa_postulante") + " - " + jsonObject.getString("cargo_postulante"));
                    tokenUsers=jsonObject.getString("tokenusers");
                    break;

                case "2":
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(
                            getApplicationContext(),
                            mensaje2,
                            Toast.LENGTH_LONG).show();
                    break;

                case "3":
                    String mensaje3 = response.getString("mensaje");
                    Toast.makeText(
                            getApplicationContext(),
                            mensaje3,
                            Toast.LENGTH_LONG).show();
                    break;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
