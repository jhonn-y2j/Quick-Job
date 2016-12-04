package com.developers.quickjob.quick_job;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.developers.quickjob.quick_job.adapter_emp.AdapterPublicaciones;
import com.developers.quickjob.quick_job.fragment_emp.fragmetn_ofertas_publicadas;
import com.developers.quickjob.quick_job.modelo.Empresa;
import com.developers.quickjob.quick_job.modelo.Oferta;
import com.developers.quickjob.quick_job.restapi.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhonn_aj on 02/12/2016.
 */

public class PostuladosActivity extends AppCompatActivity implements AdapterPublicaciones.OnItemClickOferta{

    LinearLayoutManager layoutManager;
    RecyclerView postulados;
    List<Oferta> ofertas;
    AdapterPublicaciones adaptador;
    public static final String ID_OFERTA="id_oferta";
    int idoferta;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postulados);
        this.setTitle("Postulados");
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        postulados=(RecyclerView)findViewById(R.id.recycler_postulados);

        layoutManager= new LinearLayoutManager(getApplicationContext());

        idoferta=getIntent().getIntExtra(ID_OFERTA,0);

        ofertas= new ArrayList<>();
        adaptador= new AdapterPublicaciones(getApplicationContext(),ofertas,this);
        postulados.setLayoutManager(layoutManager);
        postulados.setAdapter(adaptador);

        String url="http://unmsmquickjob.pe.hu/quickjob/postulados_oferta.php?idoferta="+idoferta;
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


    }

    private void procesarRespuesta(JSONObject response) {
        try {
            String mensaje = response.getString("estado");
            switch (mensaje) {
                case "1":
                    // Obtener objeto "meta"
                    JSONArray jsonArray=response.getJSONArray("oferta");
                    for (int i=0; i<jsonArray.length();i++){
                        Oferta oferta= new Oferta();
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        oferta.setPuesto(jsonObject.getString("apells_postulante") + ", " + jsonObject.getString("noms_postulante"));
                        oferta.setUbicacion_job(jsonObject.getString("correo_postulante"));
                        oferta.setFecha_publicacion(jsonObject.getString("telef_postulante"));
                        oferta.setId(jsonObject.getString("id_postulante"));
                        Empresa empresa= new Empresa();
                        empresa.setNombre_comercial(jsonObject.getString("carrera_postulante"));
                        oferta.setEmpresa(empresa);
                        Log.d(fragmetn_ofertas_publicadas.class.getName(),oferta.getPuesto());
                        ofertas.add(oferta);
                    }

                    adaptador.notifyDataSetChanged();

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

    @Override
    public void itemClick(Oferta app) {
        
    }
}
