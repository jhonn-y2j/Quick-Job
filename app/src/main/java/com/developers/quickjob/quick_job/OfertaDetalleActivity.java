package com.developers.quickjob.quick_job;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.developers.quickjob.quick_job.fragment_emp.fragmetn_ofertas_publicadas;
import com.developers.quickjob.quick_job.modelo.Empresa;
import com.developers.quickjob.quick_job.modelo.Oferta;
import com.developers.quickjob.quick_job.modelo.Postulaciones;
import com.developers.quickjob.quick_job.restapi.VolleySingleton;
import com.developers.quickjob.quick_job.sqlite.Operacionesbd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jhonn_aj on 25/11/2016.
 */

public class OfertaDetalleActivity extends AppCompatActivity {

    @BindView(R.id.txt_ofer_area)
    TextView ofert_area;
    @BindView(R.id.txt_ofer_puesto)
    TextView ofert_puesto;
    @BindView(R.id.btn_postular)
    Button btn_postular;

    public static final String ID = "id";
    public static final String ID_OFERT = "id_emp";
    int idusers;
    int idoferts;

    Operacionesbd db;
    @BindView(R.id.text_nomb_emp)
    TextView nomb_emp;
    @BindView(R.id.txt_ofer_tipo)
    TextView OferTipo;
    @BindView(R.id.txt_ofer_disponib)
    TextView OferDisponib;
    @BindView(R.id.txt_ofer_sueldo)
    TextView OferSueldo;
    @BindView(R.id.txt_ofer_data_home)
    TextView OferDataHome;
    @BindView(R.id.txt_ofer_ubicacion)
    TextView OferUbicacion;
    @BindView(R.id.txt_ofer_req)
    TextView OferReq;
    @BindView(R.id.txt_ofer_funciones)
    TextView OferFunciones;
    @BindView(R.id.txt_ofer_perfil)
    TextView OferPerfil;
    @BindView(R.id.txt_ofer_genero)
    TextView OferGenero;
    @BindView(R.id.txt_ofer_ramas)
    TextView OferRamas;
    @BindView(R.id.txt_ofer_fecha_publicacion)
    TextView OferFechaPublicacion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_oferta_detalle);
        this.setTitle("Oferta de Empleo");

        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ButterKnife.bind(this);

        idusers = getIntent().getIntExtra(ID, 0);
        idoferts = getIntent().getIntExtra(ID_OFERT, 0);
        Log.d(OfertaDetalleActivity.class.getName(), " users - > " + idusers + " / oferts -> " + idoferts);

        String url="http://unmsmquickjob.pe.hu/quickjob/oferta_detalle.php?idoferta="+idoferts;
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                procesarRespuesta(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(getApplicationContext()).addRequestQueue(jsonObjectRequest);

        // url para obtener oferta
        //http://unmsmquickjob.pe.hu/quickjob/oferta_detalle.php?idoferta=3
        //db = Operacionesbd.getInstancia(getApplicationContext());

        /*Oferta oferta = db.getOfertasXId(idoferts);
        if (oferta != null) {
            nomb_emp.setText(oferta.getEmpresa().getNombre_comercial());
            ofert_puesto.setText(oferta.getPuesto());
            ofert_area.setText(oferta.getArea());
            OferTipo.setText(oferta.getTipo_trabajo());
            OferDisponib.setText("Tiempo: "+oferta.getDisponibilidad());
            OferSueldo.setText("Sueldo: S/. " +oferta.getSueldo());
            OferDataHome.setText("Inicia: "+oferta.getFecha_inicio());
            OferUbicacion.setText("Lugar: "+ oferta.getUbicacion_job());
            OferReq.setText("Requisitos: \n" + oferta.getRequisitos());
            OferFunciones.setText("Funciones: \n" +oferta.getFunciones());
            OferPerfil.setText("Perfil: \n" +oferta.getPerfil());
            OferGenero.setText("Género: " + oferta.getGenero());
            OferRamas.setText("Ramas: \n" + oferta.getRamas_carrera());
            OferFechaPublicacion.setText("Publicado: " + oferta.getFecha_publicacion());
            Log.d(OfertaDetalleActivity.class.getName(), oferta.getEmpresa().getNombre_comercial());
        }
        */

    }

    private void procesarRespuesta(JSONObject response) {
        try {
            String mensaje = response.getString("estado");
            switch (mensaje) {
                case "1":
                    // Obtener objeto "meta"
                    JSONArray jsonArray=response.getJSONArray("oferta");
                    Oferta oferta= new Oferta();
                    JSONObject jsonObject= jsonArray.getJSONObject(0);
                    nomb_emp.setText(jsonObject.getString("empresa_nom_comercial"));
                    ofert_puesto.setText(jsonObject.getString("oferta_puesto"));
                    ofert_area.setText(jsonObject.getString("oferta_area"));
                    OferTipo.setText(jsonObject.getString("oferta_tipo_trabajo"));
                    OferDisponib.setText("Tiempo: "+jsonObject.getString("oferta_disponib"));
                    OferSueldo.setText("Sueldo: S/. " +jsonObject.getString("oferta_sueldo"));
                    OferDataHome.setText("Inicia: "+jsonObject.getString("oferta_fecha_ini"));
                    OferUbicacion.setText("Lugar: "+ jsonObject.getString("oferta_ubicacion"));
                    OferReq.setText("Requisitos: \n" + jsonObject.getString("oferta_requisitos"));
                    OferFunciones.setText("Funciones: \n" +jsonObject.getString("oferta_funciones"));
                    OferPerfil.setText("Perfil: \n" +jsonObject.getString("oferta_perfil"));
                    OferGenero.setText("Género: " + jsonObject.getString("oferta_genero"));
                    OferRamas.setText("Ramas: \n" + jsonObject.getString("oferta_ramas"));
                    OferFechaPublicacion.setText("Publicado: " + jsonObject.getString("oferta_fecha_public"));

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

    @OnClick(R.id.btn_postular)
    public void onClick() {

        if (idusers > 0 && idoferts > 0) {

            Postulaciones postulaciones = new Postulaciones();
            postulaciones.setIdoferta(idoferts);
            postulaciones.setIdpostulante(idusers);
            postulaciones.setPostular(1);
            postulaciones.setMsjempresa(0);
            postulaciones.setNropostulantes(1);
            postularOferta(postulaciones);
        }

    }


    public  void postularOferta(Postulaciones postulaciones){
        String url="http://unmsmquickjob.pe.hu/quickjob/postular_oferta.php";

        //obtenemos el token
        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        final String token=sharedPreferences.getString("token","");

        final String p=String.valueOf(postulaciones.getIdpostulante());
        final String o=String.valueOf(postulaciones.getIdoferta());
        final String np=String.valueOf(postulaciones.getPostular());
        final String ne=String.valueOf(postulaciones.getMsjempresa());
        final String num=String.valueOf(postulaciones.getNropostulantes());

        HashMap<String,String> map = new HashMap<>();

        map.put("p",p);
        map.put("o",o);
        map.put("np",np);
        map.put("ne",ne);
        map.put("num",num);
        map.put("t",token);

        JSONObject jsonObject= new JSONObject(map);

        Log.d(RegistersUsrsActivity.class.getName(),jsonObject.toString());

        VolleySingleton.getInstance(getApplicationContext()).addRequestQueue(
                new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                procesarPostulacion(response);
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

    private void procesarPostulacion(JSONObject response) {
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

}
