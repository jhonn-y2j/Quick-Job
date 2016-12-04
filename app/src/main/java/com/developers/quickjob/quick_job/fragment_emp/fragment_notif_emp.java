package com.developers.quickjob.quick_job.fragment_emp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.developers.quickjob.quick_job.PostuladosActivity;
import com.developers.quickjob.quick_job.R;
import com.developers.quickjob.quick_job.adapter_emp.AdapterPublicaciones;
import com.developers.quickjob.quick_job.modelo.Empresa;
import com.developers.quickjob.quick_job.modelo.Oferta;
import com.developers.quickjob.quick_job.restapi.VolleySingleton;
import com.developers.quickjob.quick_job.sqlite.Operacionesbd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhonn_aj on 25/11/2016.
 */

public class fragment_notif_emp extends Fragment implements AdapterPublicaciones.OnItemClickOferta {

    LinearLayoutManager layoutManager;
    RecyclerView notificaciones;
    List<Oferta> ofertas;
    AdapterPublicaciones adaptador;
    public static final String ID="id";
    int idemps;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_not_emp, container, false);

        notificaciones = (RecyclerView) view.findViewById(R.id.recycler_notificaciones);

        layoutManager = new LinearLayoutManager(getActivity());

        idemps=getArguments().getInt(ID);

        notificaciones.setLayoutManager(layoutManager);

        Log.d(fragment_notif_emp.class.getName(),"holi "+ idemps);
        ofertas= new ArrayList<>();

        adaptador= new AdapterPublicaciones(getActivity(),ofertas,this);
        notificaciones.setLayoutManager(layoutManager);

        notificaciones.setAdapter(adaptador);

        String url="http://unmsmquickjob.pe.hu/quickjob/notif_empresa.php?id="+idemps;

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

        VolleySingleton.getInstance(getActivity()).addRequestQueue(jsonObjectRequest);


        return view;
    }

    private void procesarRespuesta(JSONObject response) {
        try {
            String mensaje = response.getString("estado");
            switch (mensaje) {
                case "1":
                    // Obtener objeto "meta"
                    JSONArray jsonArray=response.getJSONArray("empresa");
                    for (int i=0; i<jsonArray.length();i++){
                        Oferta oferta= new Oferta();
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        oferta.setPuesto(jsonObject.getString("oferta_puesto"));
                        oferta.setUbicacion_job("# Postulantes: "+jsonObject.getString("nro_post"));
                        oferta.setFecha_publicacion(jsonObject.getString("oferta_fecha_public"));
                        oferta.setId(jsonObject.getString("oferta_id"));
                        Empresa empresa= new Empresa();
                        empresa.setNombre_comercial(jsonObject.getString("empresa_nom_comercial"));
                        oferta.setEmpresa(empresa);
                        Log.d(fragmetn_ofertas_publicadas.class.getName(),oferta.getPuesto());
                        ofertas.add(oferta);
                    }

                    adaptador.notifyDataSetChanged();

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

    @Override
    public void itemClick(Oferta app) {
        Intent intent= new Intent(getActivity(), PostuladosActivity.class);
        intent.putExtra(PostuladosActivity.ID_OFERTA,Integer.parseInt(app.getId()));
        startActivity(intent);
    }

}